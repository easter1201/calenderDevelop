package com.example.calenderdevelop.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.util.Strings;


@Getter
@Setter
@Entity
public class Schedule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    @Column(nullable = false)
    private String scheduleTitle;
    private String scheduleContent;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule(User user, String scheduleTitle, String scheduleContent){
        this.user = user;
        this.scheduleTitle = scheduleTitle;
        this.scheduleContent = scheduleContent;
    }

    public Schedule() {}

    public void update(String scheduleTitle, String scheduleContent){
        if(Strings.isNotBlank(scheduleTitle)) this.scheduleTitle = scheduleTitle;
        if(Strings.isNotBlank(scheduleContent)) this.scheduleContent = scheduleContent;
    }

}
