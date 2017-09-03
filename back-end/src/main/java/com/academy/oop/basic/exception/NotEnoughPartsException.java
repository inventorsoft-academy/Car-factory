package com.academy.oop.basic.exception;

public class NotEnoughPartsException extends RuntimeException {

    private String message;

    public NotEnoughPartsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
