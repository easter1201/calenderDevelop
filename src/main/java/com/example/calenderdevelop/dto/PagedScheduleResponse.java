package com.example.calenderdevelop.dto;

import com.example.calenderdevelop.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PagedScheduleResponse {
    private final String scheduleTitle;
    private final String scheduleContent;
    private final int numberOfComment;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String userName;

    public PagedScheduleResponse(Schedule schedule){
        this.scheduleTitle = schedule.getScheduleTitle();
        this.scheduleContent = schedule.getScheduleContent();
        this.numberOfComment = schedule.getComments().size();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
        this.userName = schedule.getUser().getUserName();
    }
}
