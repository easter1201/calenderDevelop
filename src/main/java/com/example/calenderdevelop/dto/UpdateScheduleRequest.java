package com.example.calenderdevelop.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    @NotBlank
    private String scheduleTitle;
    @NotBlank
    private String scheduleContent;
}
