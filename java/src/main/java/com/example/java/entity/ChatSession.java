package com.example.java.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("chat_session")
public class ChatSession {

    private Long id;
    private String title;
    private String createTime;
    private String updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ChatSession{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", createTime='" + createTime + '\''
                + ", updateTime='" + updateTime + '\''
                + '}';
    }
}
