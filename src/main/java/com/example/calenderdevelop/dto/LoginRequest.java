package com.example.calenderdevelop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "이메일 필수.")
    @Email(message = "올바른 이메일 형식이 아님")
    @Size(max = 255, message = "이메일은 255자 이내")
    private final String email;
    @NotBlank(message = "비밀번호 필수.")
    @Size(min = 4, max = 25, message = "비밀번호는 최소 4자 이상 25자 이내")
    private final String password;
}
