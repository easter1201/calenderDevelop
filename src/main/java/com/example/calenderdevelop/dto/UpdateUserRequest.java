package com.example.calenderdevelop.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UpdateUserRequest {
    @NotBlank
    private String userName;
    @NotBlank
    @Email
    private String email;
}
