package com.example.calenderdevelop.controller;

import com.example.calenderdevelop.dto.CreateScheduleRequest;
import com.example.calenderdevelop.dto.DeleteScheduleRequest;
import com.example.calenderdevelop.dto.ScheduleResponse;
import com.example.calenderdevelop.dto.UpdateScheduleRequest;
import com.example.calenderdevelop.exception.LoginFailedException;
import com.example.calenderdevelop.service.ScheduleService;
import com.example.calenderdevelop.service.ScheduleServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleServiceImpl scheduleService){
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedules")
    public ScheduleResponse schedule(@Valid @RequestBody CreateScheduleRequest createRequest, HttpServletRequest request){
        Long userId = extractUserIdFromCookie(request);
        return scheduleService.createSchedule(createRequest, userId);
    }

    @GetMapping("/schedules/{scheduleId}")
    public ScheduleResponse getSchedule(@PathVariable Long scheduleId){
        return scheduleService.getSchedule(scheduleId);
    }

    @GetMapping("/schedules")
    public List<ScheduleResponse> getAllSchedules(){
        return scheduleService.getAllSchedules();
    }

    @PutMapping("/schedules/{scheduleId}")
    public ScheduleResponse updateSchedule(@PathVariable Long scheduleId, @RequestBody @Valid UpdateScheduleRequest updateRequest, HttpServletRequest request){
        Long userId = extractUserIdFromCookie(request);
        return scheduleService.updateSchedule(scheduleId, updateRequest, userId);
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public void deleteSchedule(@PathVariable Long scheduleId, @Valid @RequestBody DeleteScheduleRequest deleteRequest, HttpServletRequest request){
        Long userId = extractUserIdFromCookie(request);
        scheduleService.deleteSchedule(scheduleId, deleteRequest, userId);
    }

    private Long extractUserIdFromCookie(HttpServletRequest request){
        if(request.getCookies() == null) throw new LoginFailedException("로그인 필요");
        for(Cookie cookie : request.getCookies()){
            if("userId".equals(cookie.getName())) return Long.valueOf(cookie.getValue());
        }
        throw new LoginFailedException("쿠키 없음");
    }
}
