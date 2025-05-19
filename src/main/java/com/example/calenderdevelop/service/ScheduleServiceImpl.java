package com.example.calenderdevelop.service;

import com.example.calenderdevelop.dto.CreateScheduleRequest;
import com.example.calenderdevelop.dto.DeleteScheduleRequest;
import com.example.calenderdevelop.dto.ScheduleResponse;
import com.example.calenderdevelop.dto.UpdateScheduleRequest;
import com.example.calenderdevelop.entity.Schedule;
import com.example.calenderdevelop.entity.User;
import com.example.calenderdevelop.exception.EntityNotFoundException;
import com.example.calenderdevelop.repository.ScheduleRepository;
import com.example.calenderdevelop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements  ScheduleService{
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, UserRepository userRepository){
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ScheduleResponse createSchedule(CreateScheduleRequest createRequest){
        User user = userRepository.findByUserNameAndEmail(createRequest.getUserName(), createRequest.getEmail()).orElse(null);
        if(user == null){
            user = new User(createRequest.getUserName(), createRequest.getEmail());
            user = userRepository.save(user);
        }
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
    public List<ScheduleResponse> getAllSchedules(){
        return scheduleRepository.findAll().stream()
                .map(ScheduleResponse::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest updateRequest){
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException(scheduleId, Schedule.class));
        if(updateRequest.getScheduleTitle() != null) schedule.setScheduleTitle(updateRequest.getScheduleTitle());
        if(updateRequest.getScheduleTitle() != null) schedule.setScheduleContent(updateRequest.getScheduleContent());

        User user = schedule.getUser();
        if(updateRequest.getUserName() != null) user.setUserName(updateRequest.getUserName());
        if(updateRequest.getEmail() != null) user.setEmail(updateRequest.getEmail());

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
