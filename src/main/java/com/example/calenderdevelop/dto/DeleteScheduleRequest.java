package com.example.calenderdevelop.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter
public class DeleteScheduleRequest {
    @NotNull(message = "비밀번호 필수")
    @Size(min = 4, max = 25, message = "비밀번호는 최소 4자 이상 25자 이내")
    private String password;
}
