package com.example.calenderdevelop.controller;

import com.example.calenderdevelop.dto.CreateScheduleRequest;
import com.example.calenderdevelop.dto.DeleteScheduleRequest;
import com.example.calenderdevelop.dto.ScheduleResponse;
import com.example.calenderdevelop.dto.UpdateScheduleRequest;
import com.example.calenderdevelop.service.ScheduleService;
import com.example.calenderdevelop.service.ScheduleServiceImpl;
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
    public ScheduleResponse schedule(@Valid @RequestBody CreateScheduleRequest createRequest){
        return scheduleService.createSchedule(createRequest);
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
    public ScheduleResponse updateSchedule(@PathVariable Long scheduleId, @RequestBody @Valid UpdateScheduleRequest updateRequest){
        return scheduleService.updateSchedule(scheduleId, updateRequest);
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public void deleteSchedule(@PathVariable Long scheduleId, @Valid @RequestBody DeleteScheduleRequest deleteRequest){
        scheduleService.deleteSchedule(scheduleId, deleteRequest);
    }
}
