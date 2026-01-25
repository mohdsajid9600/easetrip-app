package com.sajidtech.easytrip.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ApiErrorResponse {

    private boolean success;
    private String message;
    private String error;
    private int statusCode;
    private LocalDateTime timestamp;

    public ApiErrorResponse(boolean success, String message, String error, int statusCode) {
        this.success = success;
        this.message = message;
        this.error = error;
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
    }
}
