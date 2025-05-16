package com.example.calenderdevelop.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter
public class CreateScheduleRequest {
    @NotBlank
    private String userName;
    @NotBlank
    private String scheduleTitle;
    @NotBlank
    private String scheduleContent;
}
