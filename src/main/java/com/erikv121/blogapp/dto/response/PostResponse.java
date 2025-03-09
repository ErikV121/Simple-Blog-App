package com.erikv121.blogapp.dto.response;

import java.time.LocalDateTime;

public class PostResponse {
    private String author;
    private String title;
    private String body;
    private LocalDateTime createdAt;

    public PostResponse() {}

    public PostResponse(String author, String title, String body, LocalDateTime createdAt) {
        this.author = author;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
