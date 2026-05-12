package com.example.java.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java.entity.ChatSession;
import com.example.java.mapper.ChatSessionMapper;
import com.example.java.service.ChatSessionService;
import org.springframework.stereotype.Service;

@Service
public class ChatSessionServiceImpl extends ServiceImpl<ChatSessionMapper, ChatSession> implements ChatSessionService {

    @Override
    public ChatSession createSession(String title) {
        ChatSession session = new ChatSession();
        session.setTitle(title == null || title.isBlank() ? "新会话" : title);
        save(session);
        return session;
    }
}
