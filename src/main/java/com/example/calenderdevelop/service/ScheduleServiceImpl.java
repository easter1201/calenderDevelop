package com.example.calenderdevelop.service;

import com.example.calenderdevelop.dto.*;
import com.example.calenderdevelop.entity.Schedule;
import com.example.calenderdevelop.exception.EntityNotFoundException;
import com.example.calenderdevelop.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements  ScheduleService{
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository){
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponse createSchedule(CreateScheduleRequest createRequest){
        Schedule schedule = new Schedule(createRequest.getUserName(), createRequest.getScheduleTitle(), createRequest.getScheduleContent());
        Schedule saved = scheduleRepository.save(schedule);
        return new ScheduleResponse(saved);
    }

    @Override
    public ScheduleResponse getSchedule(Long scheduleId){
        final Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException(scheduleId, Schedule.class));
        return new ScheduleResponse(schedule);
    }

    @Override
    public List<ScheduleResponse> getAllSchedules(){
        return scheduleRepository.findAll().stream()
                .map(ScheduleResponse::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest updateRequest){
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException(scheduleId, Schedule.class));
        schedule.update(updateRequest.getScheduleTitle(), updateRequest.getScheduleContent());
        return new ScheduleResponse(schedule);
    }

    @Override
    @Transactional
    public void deleteSchedule(Long scheduleId, DeleteScheduleRequest deleteRequest){
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException(scheduleId, Schedule.class));
        scheduleRepository.delete(schedule);
    }
}
