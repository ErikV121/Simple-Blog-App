package com.erikv121.blogapp.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponseDto {
    private HttpStatus status;
    private String errorMessage;
    private String apiPath;
    private LocalDateTime timestamp;

    public ErrorResponseDto() {}

    public ErrorResponseDto(HttpStatus status, String errorMessage, String apiPath, LocalDateTime timestamp) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.apiPath = apiPath;
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
