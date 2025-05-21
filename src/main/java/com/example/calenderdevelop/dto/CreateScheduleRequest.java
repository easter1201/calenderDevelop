package com.example.calenderdevelop.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class CreateScheduleRequest {
    @NotBlank
    private String scheduleTitle;
    @NotBlank
    private String scheduleContent;

    public CreateScheduleRequest() {}

    public CreateScheduleRequest(String scheduleTitle, String scheduleContent){
        this.scheduleTitle = scheduleTitle;
        this.scheduleContent = scheduleContent;
    }
}
