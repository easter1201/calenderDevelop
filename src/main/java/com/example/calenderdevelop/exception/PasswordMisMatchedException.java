package com.example.calenderdevelop.exception;

public class PasswordMisMatchedException extends RuntimeException{
    public PasswordMisMatchedException(String message){ super(message); }
}
