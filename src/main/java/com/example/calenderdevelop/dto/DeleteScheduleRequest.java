package com.example.calenderdevelop.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter
public class DeleteScheduleRequest {
    @NotNull
    private String password;
}
