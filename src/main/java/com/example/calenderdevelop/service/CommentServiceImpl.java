package com.example.calenderdevelop.service;

import com.example.calenderdevelop.dto.CommentResponse;
import com.example.calenderdevelop.dto.CreateCommentRequest;
import com.example.calenderdevelop.dto.UpdateCommentRequest;
import com.example.calenderdevelop.entity.Comment;
import com.example.calenderdevelop.entity.Schedule;
import com.example.calenderdevelop.entity.User;
import com.example.calenderdevelop.exception.EntityNotFoundException;
import com.example.calenderdevelop.exception.UserIdMisMatchedException;
import com.example.calenderdevelop.repository.CommentRepository;
import com.example.calenderdevelop.repository.ScheduleRepository;
import com.example.calenderdevelop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, ScheduleRepository scheduleRepository, UserRepository userRepository){
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public CommentResponse createComment(Long scheduleId, Long userId, CreateCommentRequest createRequest){
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException(scheduleId, Schedule.class));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId, User.class));
        Comment comment = new Comment(createRequest.getContent(), schedule, user);
        Comment saved = commentRepository.save(comment);
        return new CommentResponse(saved);
    }

    @Override
    public List<CommentResponse> findAllBySchedule(Long scheduleId){
        return commentRepository.findAllBySchedule_ScheduleId(scheduleId).stream()
                .map(CommentResponse::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CommentResponse updateComment(Long commentId, Long userId, UpdateCommentRequest updateRequest){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(commentId, Comment.class));
        if(!comment.getUser().getUserId().equals(userId)) throw new UserIdMisMatchedException("찾을 수 없음");

        comment.setContent(updateRequest.getContent());

        return new CommentResponse(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId, Long userId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(commentId, Comment.class));
        if(!comment.getUser().getUserId().equals(userId)) throw new UserIdMisMatchedException("찾을 수 없음");

        commentRepository.delete(comment);
    }
}
