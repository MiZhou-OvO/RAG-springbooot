package com.example.java.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("kb_document")
public class KbDocument {

    private Long id;
    private String title;
    private String content;
    private String category;
    private String createTime;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "KbDocument{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", content='" + content + '\''
                + ", category='" + category + '\''
                + ", createTime='" + createTime + '\''
                + '}';
    }
}
