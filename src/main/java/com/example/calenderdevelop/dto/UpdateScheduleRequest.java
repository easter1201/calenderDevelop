package com.example.calenderdevelop.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    private String scheduleTitle;
    private String scheduleContent;
    public UpdateScheduleRequest() {}

    public UpdateScheduleRequest(String scheduleTitle, String scheduleContent){
        this.scheduleTitle = scheduleTitle;
        this.scheduleContent = scheduleContent;
    }
}
