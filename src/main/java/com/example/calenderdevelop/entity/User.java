package com.example.calenderdevelop.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String email;
    private String password;

    public User(){}

    public User(String userName, String email, String password){
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void update(String userName, String email){
        this.userName = userName;
        this.email = email;
    }
}
