package com.example.calenderdevelop.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class CreateScheduleRequest {
    @NotBlank(message = "제목 필수")
    @Size(max = 255, message = "제목은 255자 이내")
    private String scheduleTitle;
    @NotBlank(message = "내용 필수")
    @Size(max = 1000, message = "내용은 1000자 이내")
    private String scheduleContent;

    public CreateScheduleRequest() {}

    public CreateScheduleRequest(String scheduleTitle, String scheduleContent){
        this.scheduleTitle = scheduleTitle;
        this.scheduleContent = scheduleContent;
    }
}
