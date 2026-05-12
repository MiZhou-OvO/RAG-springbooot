package com.example.java.service.impl;

import com.example.java.entity.ChatMessage;
import com.example.java.entity.ChatSession;
import com.example.java.entity.KbDocument;
import com.example.java.service.AiService;
import com.example.java.service.ChatMessageService;
import com.example.java.service.ChatSessionService;
import com.example.java.service.KbDocumentService;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AiServiceImpl implements AiService {

    private static final int MAX_CONTEXT_MESSAGES = 10;
    private static final int MAX_KB_DOCUMENTS = 3;

    private final ObjectProvider<ChatModel> chatModelProvider;
    private final ChatSessionService chatSessionService;
    private final ChatMessageService chatMessageService;
    private final KbDocumentService kbDocumentService;

    public AiServiceImpl(ObjectProvider<ChatModel> chatModelProvider,
                         ChatSessionService chatSessionService,
                         ChatMessageService chatMessageService,
                         KbDocumentService kbDocumentService) {
        this.chatModelProvider = chatModelProvider;
        this.chatSessionService = chatSessionService;
        this.chatMessageService = chatMessageService;
        this.kbDocumentService = kbDocumentService;
    }

    @Override
    public String generate(String message, Long sessionId) {
        if (message == null || message.isBlank()) {
            message = "你好";
        }

        ChatSession session = getOrCreateSession(sessionId);

        chatMessageService.saveMessage(session.getId(), "user", message);

        String enhancedPrompt = buildRagPrompt(message, session.getId());

        ChatModel chatModel = chatModelProvider.getIfAvailable();
        if (chatModel == null) {
            return "DeepSeek is not configured. Please set DEEPSEEK_API_KEY and restart the application.";
        }

        String response;
        try {
            response = chatModel.call(enhancedPrompt);
        }
        catch (Exception ex) {
            response = "DeepSeek request failed. Please check the API key and network. Details: " + ex.getMessage();
        }

        chatMessageService.saveMessage(session.getId(), "assistant", response);
        return response;
    }

    private ChatSession getOrCreateSession(Long sessionId) {
        if (sessionId != null) {
            ChatSession session = chatSessionService.getById(sessionId);
            if (session != null) {
                return session;
            }
        }
        return chatSessionService.createSession("新会话");
    }

    private String buildRagPrompt(String userMessage, Long sessionId) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("你是一个企业级AI助手，拥有知识库参考能力和上下文记忆能力。\n\n");

        List<KbDocument> kbDocs = kbDocumentService.searchRelevant(userMessage, MAX_KB_DOCUMENTS);
        if (!kbDocs.isEmpty()) {
            prompt.append("=== 知识库参考 ===\n");
            for (int i = 0; i < kbDocs.size(); i++) {
                KbDocument doc = kbDocs.get(i);
                prompt.append("[").append(i + 1).append("] ").append(doc.getTitle()).append("\n");
                prompt.append(doc.getContent()).append("\n\n");
            }
        }

        List<ChatMessage> history = chatMessageService.getSessionMessages(sessionId, MAX_CONTEXT_MESSAGES);
        if (!history.isEmpty()) {
            Collections.reverse(history);
            prompt.append("=== 对话上下文 ===\n");
            for (ChatMessage msg : history) {
                String roleLabel = "user".equals(msg.getRole()) ? "用户" : "助手";
                prompt.append(roleLabel).append(": ").append(msg.getContent()).append("\n");
            }
            prompt.append("\n");
        }

        prompt.append("=== 当前问题 ===\n");
        prompt.append(userMessage).append("\n\n");
        prompt.append("请结合知识库参考和对话上下文，回答用户的问题。如果知识库没有相关内容，请根据对话上下文回答。");

        return prompt.toString();
    }
}
