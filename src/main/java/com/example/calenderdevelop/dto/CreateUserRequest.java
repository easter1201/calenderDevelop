package com.example.calenderdevelop.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class CreateUserRequest {
    @NotBlank(message = "유저명 필수")
    @Size(max = 255, message = "유저명은 255글자 이내")
    private String userName;
    @NotBlank(message = "이메일 필수")
    @Email(message = "올바른 이메일 형식이 아님")
    @Size(max = 255, message = "이메일은 255자 이내")
    private String email;
    @NotBlank(message = "비밀번호 필수")
    @Size(min = 4, max = 25, message = "비밀번호는 최소 4자 이상 25자 이내")
    private String password;
}
