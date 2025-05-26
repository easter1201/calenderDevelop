package com.example.calenderdevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;


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

    @OneToMany(mappedBy = "schedule") //해당 일정에 종속된 댓글
    private List<Comment> comments = new ArrayList<>();

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

    public List<Comment> getComments(){
        return comments;
    }
}
