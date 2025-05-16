package com.example.calenderdevelop.service;

import com.example.calenderdevelop.dto.*;

import java.util.List;

public interface ScheduleService {
    ScheduleResponse createSchedule(CreateScheduleRequest createRequest);
    ScheduleResponse getSchedule(Long scheduleId);
    List<ScheduleResponse> getAllSchedules();
    ScheduleResponse updateSchedule(Long id, UpdateScheduleRequest updateRequest);
    void deleteSchedule(Long id, DeleteScheduleRequest deleteRequest);
}
