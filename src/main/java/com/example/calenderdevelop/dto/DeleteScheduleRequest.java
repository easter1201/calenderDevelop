package com.example.calenderdevelop.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter
public class DeleteScheduleRequest {
    @NotBlank
    private String userName;
}
