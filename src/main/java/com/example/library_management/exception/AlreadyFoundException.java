package com.example.library_management.exception;

public class AlreadyFoundException extends BadRequestException{
    public AlreadyFoundException(String message) {
        super(message);
    }
}
