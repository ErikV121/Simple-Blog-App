package com.erikv121.blogapp.dto.response;

import com.erikv121.blogapp.entity.enums.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.UUID;

public class PostResponse {
    private static final Logger log = LoggerFactory.getLogger(PostResponse.class);
    private UUID id;
    private String username;
    private String title;
    private Category category;
    private String body;
    private LocalDateTime createdAt;
    private boolean anonymous;
    private String url;

    public PostResponse(UUID id, String username, String title, Category category, String body, LocalDateTime createdAt, boolean anonymous, String url) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.category = category;
        this.body = body;
        this.createdAt = createdAt;
        this.anonymous = anonymous;
        setUrl(url);

    }

    public void setUrl(String title) {
        if (title != null) {
            this.url = title.toLowerCase().replace(" ", "-");
        } else {
            this.url = null;
        }
    }

    public String getUrl() {
        return this.url;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
}