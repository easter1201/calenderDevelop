package com.example.calenderdevelop.dto;

import lombok.Getter;

@Getter
public class LoginResponse {
    private final Long id;

    public LoginResponse(Long id){
        this.id = id;
    }
}
