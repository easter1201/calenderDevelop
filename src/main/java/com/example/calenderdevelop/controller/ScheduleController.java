package com.example.calenderdevelop.controller;

import com.example.calenderdevelop.dto.*;
import com.example.calenderdevelop.exception.LoginFailedException;
import com.example.calenderdevelop.service.ScheduleService;
import com.example.calenderdevelop.service.ScheduleServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleServiceImpl scheduleService){
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedules") //일정 등록
    public ScheduleResponse createSchedule(@Valid @RequestBody CreateScheduleRequest createRequest, HttpServletRequest request){
        Long userId = extractUserIdFromCookie(request);
        return scheduleService.createSchedule(createRequest, userId);
    }

    @GetMapping("/schedules/{scheduleId}") //일정 조회
    public ScheduleResponse getSchedule(@PathVariable Long scheduleId){
        return scheduleService.getSchedule(scheduleId);
    }

    @GetMapping("/schedules") //일정 전체 조회
    public List<PagedScheduleResponse> getAllSchedules(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt"));
        return scheduleService.getAllSchedules(pageable).getContent();
    }

    @PutMapping("/schedules/{scheduleId}") //일정 수정
    public ScheduleResponse updateSchedule(@PathVariable Long scheduleId, @RequestBody @Valid UpdateScheduleRequest updateRequest, HttpServletRequest request){
        Long userId = extractUserIdFromCookie(request);
        return scheduleService.updateSchedule(scheduleId, updateRequest, userId);
    }

    @DeleteMapping("/schedules/{scheduleId}") //일정 제거
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
