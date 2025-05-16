package com.example.calenderdevelop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Schedule extends BaseEntity{
    @Id
    private Long scheduleId;
    private String userName;
    private String scheduleTitle;
    private String scheduleContent;
}
