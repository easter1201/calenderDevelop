package com.example.calenderdevelop.service;

import com.example.calenderdevelop.dto.*;

import java.util.List;

public interface ScheduleService {
    ScheduleResponse createSchedule(CreateScheduleRequest createRequest);
    ScheduleResponse getSchedule(Long scheduleId);
    List<ScheduleResponse> getAllSchedules();
    ScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest updateRequest);
    void deleteSchedule(Long scheduleId, DeleteScheduleRequest deleteRequest);
}
