package com.example.calenderdevelop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequest {
    @NotBlank
    private final String email;
    @NotBlank
    private final String password;
}
