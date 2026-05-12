package com.example.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.java.entity.ChatMessage;

import java.util.List;

public interface ChatMessageService extends IService<ChatMessage> {

    List<ChatMessage> getSessionMessages(Long sessionId, int limit);

    ChatMessage saveMessage(Long sessionId, String role, String content);
}
