package com.example.library_management.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response {
    public Response(String message) {
        this.message = message;
    }

    private String message;
    private int status;

}


