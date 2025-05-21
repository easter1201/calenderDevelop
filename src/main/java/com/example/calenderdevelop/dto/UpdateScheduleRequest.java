package com.example.calenderdevelop.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    @Size(max = 255, message = "제목은 255자 이내")
    private String scheduleTitle;
    @Size(max = 1000, message = "내용은 1000자 이내")
    private String scheduleContent;
    public UpdateScheduleRequest() {}

    public UpdateScheduleRequest(String scheduleTitle, String scheduleContent){
        this.scheduleTitle = scheduleTitle;
        this.scheduleContent = scheduleContent;
    }
}
