package com.sajidtech.easytrip.exception;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException (String message) {
        super(message);
    }
}
