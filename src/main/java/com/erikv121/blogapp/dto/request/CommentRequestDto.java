package com.erikv121.blogapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CommentRequestDto {
    @NotBlank(message = "Comment body cannot be empty")
    @Size(max = 5000, message = "Comment cannot exceed 5000 characters")
    private String body;

    private boolean anonymous = false;

    public CommentRequestDto() {
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
