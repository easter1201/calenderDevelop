package com.example.calenderdevelop.service;

import com.example.calenderdevelop.dto.*;
import com.example.calenderdevelop.entity.Schedule;
import com.example.calenderdevelop.entity.User;
import com.example.calenderdevelop.exception.EntityNotFoundException;
import com.example.calenderdevelop.exception.UserIdMisMatchedException;
import com.example.calenderdevelop.repository.ScheduleRepository;
import com.example.calenderdevelop.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleServiceImpl implements  ScheduleService{
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, UserRepository userRepository){
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ScheduleResponse createSchedule(CreateScheduleRequest createRequest, Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId, User.class));

        Schedule schedule = new Schedule(user, createRequest.getScheduleTitle(), createRequest.getScheduleContent());
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
    public Page<PagedScheduleResponse> getAllSchedules(Pageable pageable){
        return scheduleRepository.findAll(pageable)
                .map(PagedScheduleResponse::new);
    }

    @Override
    @Transactional
    public ScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest updateRequest, Long userId){
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException(scheduleId, Schedule.class));
        if(!schedule.getUser().getUserId().equals(userId)) throw new UserIdMisMatchedException("본인 일정 아님");
        if(updateRequest.getScheduleTitle() != null) schedule.setScheduleTitle(updateRequest.getScheduleTitle());
        if(updateRequest.getScheduleContent() != null) schedule.setScheduleContent(updateRequest.getScheduleContent());

        return new ScheduleResponse(schedule);
    }

    @Override
    @Transactional
    public void deleteSchedule(Long scheduleId, DeleteScheduleRequest deleteRequest, Long userId){
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException(scheduleId, Schedule.class));
        if(!schedule.getUser().getUserId().equals(userId)) throw new UserIdMisMatchedException("본인 일정 아님");

        scheduleRepository.delete(schedule);
    }
}
