package com.bacchiega.basketservice.exceptions;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message) {
        super(message);
    }
}
