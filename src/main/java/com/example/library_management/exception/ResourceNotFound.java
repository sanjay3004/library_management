package com.example.library_management.exception;

public class ResourceNotFound extends BadRequestException {
    public ResourceNotFound(String message){
        super(message);
    }
}
