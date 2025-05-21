package com.example.calenderdevelop.service;

import com.example.calenderdevelop.dto.CreateScheduleRequest;
import com.example.calenderdevelop.dto.DeleteScheduleRequest;
import com.example.calenderdevelop.dto.ScheduleResponse;
import com.example.calenderdevelop.dto.UpdateScheduleRequest;

import java.util.List;

public interface ScheduleService {
    ScheduleResponse createSchedule(CreateScheduleRequest createRequest, Long userId);
    ScheduleResponse getSchedule(Long scheduleId);
    List<ScheduleResponse> getAllSchedules();
    ScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest updateRequest, Long userId);
    void deleteSchedule(Long scheduleId, DeleteScheduleRequest deleteRequest, Long userId);
}
