package com.example.java.controller;

import com.example.java.entity.ChatSession;
import com.example.java.pojo.Result;
import com.example.java.service.AiService;
import com.example.java.service.ChatSessionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ai")
public class ChatController {

    private final AiService aiService;
    private final ChatSessionService chatSessionService;

    public ChatController(AiService aiService, ChatSessionService chatSessionService) {
        this.aiService = aiService;
        this.chatSessionService = chatSessionService;
    }

    @GetMapping("/generate")
    public String generate(@RequestParam(value = "message", required = false) String message,
                           @RequestParam(value = "sessionId", required = false) Long sessionId) {
        return aiService.generate(message, sessionId);
    }

    @PostMapping("/session")
    public Result createSession() {
        ChatSession session = chatSessionService.createSession("新会话");
        Map<String, Object> data = new HashMap<>();
        data.put("sessionId", session.getId());
        data.put("title", session.getTitle());
        return Result.success(data);
    }
}
