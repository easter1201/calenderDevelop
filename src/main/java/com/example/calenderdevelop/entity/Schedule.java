package com.example.calenderdevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Setter
@Entity
public class Schedule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    private String userName;
    private String scheduleTitle;
    private String scheduleContent;

    public Schedule(String userName, String scheduleTitle, String scheduleContent){
        this.userName = userName;
        this.scheduleTitle = scheduleTitle;
        this.scheduleContent = scheduleContent;
    }

    public Schedule() {

    }

    public void update(String scheduleTitle, String scheduleContent){
        if(Strings.isNotBlank(scheduleTitle)) this.scheduleTitle = scheduleTitle;
        if(Strings.isNotBlank(scheduleContent)) this.scheduleContent = scheduleContent;
    }

}
