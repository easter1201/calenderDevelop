package com.example.calenderdevelop.dto;

import com.example.calenderdevelop.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponse {
    private final Long scheduleId;
    private final Long userId;
    private final String scheduleTitle;
    private final String scheduleContent;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private String userName;
    private String email;

    public ScheduleResponse(Schedule schedule){
        this.scheduleId = schedule.getScheduleId();
        this.userId = schedule.getUser().getUserId();
        this.userName = schedule.getUser().getUserName();
        this.email = schedule.getUser().getEmail();
        this.scheduleTitle = schedule.getScheduleTitle();
        this.scheduleContent = schedule.getScheduleContent();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}
