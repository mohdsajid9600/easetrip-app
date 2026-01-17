package com.sajidtech.easytrip.exception;

public class CabNotFoundException extends RuntimeException {
    public CabNotFoundException(String message) {
        super(message);
    }
}
