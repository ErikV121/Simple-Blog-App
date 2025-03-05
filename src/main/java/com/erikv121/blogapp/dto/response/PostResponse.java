package com.erikv121.blogapp.dto.response;

import java.time.LocalDateTime;

public record PostResponse(
        String author,
        String title,
        String body,
        LocalDateTime createdAt) {
}
