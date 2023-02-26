package com.example.library_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Response> handleResourceNotFound(ResourceNotFound exception) {
        Response response = new Response();
        response.setMessage(exception.getMessage());
        response.setStatus(404);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyFoundException.class)
    public ResponseEntity<Response> handleAlreadyFound(AlreadyFoundException exception) {
        Response response = new Response();
        response.setMessage(exception.getMessage());
        response.setStatus(400);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<Response> badRequest(BadRequest badRequest){
        Response response = new Response();
        response.setMessage(badRequest.getMessage());
        response.setStatus(400);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
