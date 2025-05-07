package com.erikv121.blogapp.dto.request;

import com.erikv121.blogapp.dto.response.PostResponse;
import com.erikv121.blogapp.entity.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public class PostRequest {
    private UUID id;

    private UUID userId;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 200, message = "Title must be at most 200 characters")
    private String title;

    @NotBlank(message = "text cannot be blank")
    @Size(max = 65_535, message = "Cannot be more than 65,535 characters")
    private String body;

    private Category category;

    private boolean anonymous = false;

    private String url;

    public PostRequest() {}

    public PostRequest(UUID id, String title, Category category,
                       String body, boolean anonymous, String url) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.body = body;
        this.anonymous = anonymous;
        setUrl(url);
    }

    public static PostRequest fromResponse(PostResponse postResponse) {
        return new PostRequest(
                postResponse.getId(),
                postResponse.getTitle(),
                postResponse.getCategory(),
                postResponse.getBody(),
                postResponse.isAnonymous(),
                postResponse.getUrl()
        );
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String title) {
            this.url = title.toLowerCase().replace(" ", "-");
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
}
