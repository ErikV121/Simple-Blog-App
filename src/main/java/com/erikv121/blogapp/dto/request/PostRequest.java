package com.erikv121.blogapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequest(
        @NotBlank(message = "Author cannot be blank")
        @Size(max = 100, message = "Author name must be at most 100 characters")
        String author,

        @NotBlank(message = "Title cannot be blank")
        @Size(max = 200, message = "Title must be at most 200 characters")
        String title,

        @NotBlank(message = "Body cannot be blank")
        @Size(max = 5000, message = "Body must be at most 5000 characters")
        String body) {
}
