package com.example.calenderdevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Column(nullable = false, length = 1000)
    private String content;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Comment() {}

    public Comment(String content, Schedule schedule, User user){
        this.content = content;
        this.schedule = schedule;
        this.user = user;
    }

    public void update(String content){
        this.content = content;
    }
}
