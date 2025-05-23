package com.example.calenderdevelop.service;

import com.example.calenderdevelop.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleService {
    ScheduleResponse createSchedule(CreateScheduleRequest createRequest, Long userId);
    ScheduleResponse getSchedule(Long scheduleId);
    Page<PagedScheduleResponse> getAllSchedules(Pageable pageable);
    ScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest updateRequest, Long userId);
    void deleteSchedule(Long scheduleId, DeleteScheduleRequest deleteRequest, Long userId);
}
