package com.example.javaassignment.shoppingapi.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class CreateUserException extends RuntimeException {
    public CreateUserException(String message) {
        super(message);
    }
}
