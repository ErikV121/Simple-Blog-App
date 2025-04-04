package com.erikv121.blogapp.entity;

import com.erikv121.blogapp.entity.enums.Category;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;

    @Column
    private String author;

    @Column(nullable = false)
    private String title;

//    @Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String body;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Category category;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private boolean anonymous = false;

    @Column(nullable = true)
    private String url;

    public Post() {
    }

    public Post(String author, String title, String body,Category category, boolean anonymous) {
        this.author = author;
        this.title = title;
        this.body = body;
        this.category = category;
        this.anonymous = anonymous;
        setUrl(title);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthor() {
        return anonymous ? "Anonymous" : author;
    }

    public String getOriginalAuthor() {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String title) {
        if (title != null) {
            this.url = title.toLowerCase().replace(" ", "-");
        } else {
            this.url = null;
        }
    }

}
