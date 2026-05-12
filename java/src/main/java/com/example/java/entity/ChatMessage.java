package com.example.java.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("chat_message")
public class ChatMessage {

    private Long id;
    private Long sessionId;
    private String role;
    private String content;
    private String createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ChatMessage{"
                + "id=" + id
                + ", sessionId=" + sessionId
                + ", role='" + role + '\''
                + ", content='" + content + '\''
                + ", createTime='" + createTime + '\''
                + '}';
    }
}
