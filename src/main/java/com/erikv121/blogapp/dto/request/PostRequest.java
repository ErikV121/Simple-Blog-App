package com.erikv121.blogapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostRequest {
        @NotBlank(message = "Author cannot be blank")
        @Size(max = 100, message = "Author name must be at most 100 characters")
        private String author;

        @NotBlank(message = "Title cannot be blank")
        @Size(max = 200, message = "Title must be at most 200 characters")
        private String title;

        @NotBlank(message = "Body cannot be blank")
        @Size(max = 5000, message = "Body must be at most 5000 characters")
        private String body;

        public PostRequest() {}

        public PostRequest(String author, String title, String body) {
                this.author = author;
                this.title = title;
                this.body = body;
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
}
