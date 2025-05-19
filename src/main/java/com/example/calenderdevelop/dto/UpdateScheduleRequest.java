package com.example.calenderdevelop.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    private String scheduleTitle;
    private String scheduleContent;
    private String userName;
    private String email;
    public UpdateScheduleRequest() {}

    public UpdateScheduleRequest(String scheduleTitle, String scheduleContent, String userName, String email){
        this.scheduleTitle = scheduleTitle;
        this.scheduleContent = scheduleContent;
        this.userName = userName;
        this.email = email;
    }
}
