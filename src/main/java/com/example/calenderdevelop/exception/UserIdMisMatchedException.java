package com.example.calenderdevelop.exception;

public class UserIdMisMatchedException extends RuntimeException{
    public UserIdMisMatchedException(String message){
        super(message);
    }
}
