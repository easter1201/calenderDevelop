package com.example.calenderdevelop.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class CreateScheduleRequest {
    @NotBlank
    private String scheduleTitle;
    @NotBlank
    private String scheduleContent;
    @NotBlank
    private String userName;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public CreateScheduleRequest() {}

    public CreateScheduleRequest(String scheduleTitle, String scheduleContent, String userName, String email, String password){
        this.scheduleTitle = scheduleTitle;
        this.scheduleContent = scheduleContent;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
