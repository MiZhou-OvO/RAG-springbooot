package com.example.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.java.entity.ChatSession;

public interface ChatSessionService extends IService<ChatSession> {

    ChatSession createSession(String title);
}
