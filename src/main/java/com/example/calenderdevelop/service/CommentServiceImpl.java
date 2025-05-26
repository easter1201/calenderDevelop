package com.example.calenderdevelop.service;

import com.example.calenderdevelop.config.PasswordEncoder;
import com.example.calenderdevelop.dto.CommentResponse;
import com.example.calenderdevelop.dto.CreateCommentRequest;
import com.example.calenderdevelop.dto.DeleteCommentRequest;
import com.example.calenderdevelop.dto.UpdateCommentRequest;
import com.example.calenderdevelop.entity.Comment;
import com.example.calenderdevelop.entity.Schedule;
import com.example.calenderdevelop.entity.User;
import com.example.calenderdevelop.exception.EntityNotFoundException;
import com.example.calenderdevelop.exception.PasswordMisMatchedException;
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
    private final PasswordEncoder passwordEncoder;

    public CommentServiceImpl(CommentRepository commentRepository, ScheduleRepository scheduleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public CommentResponse createComment(Long scheduleId, Long userId, CreateCommentRequest createRequest){ //일정ID, 유저ID를 파라미터로 받아 일정 저장
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException(scheduleId, Schedule.class));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId, User.class));
        Comment comment = new Comment(createRequest.getContent(), schedule, user);
        Comment saved = commentRepository.save(comment);
        return new CommentResponse(saved);
    }

    @Override
    public List<CommentResponse> findAllBySchedule(Long scheduleId){ //일정 ID를 파라미터로 받아 해당 일정에 속한 댓글 전체 조회
        return commentRepository.findAllBySchedule_ScheduleId(scheduleId).stream()
                .map(CommentResponse::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CommentResponse updateComment(Long commentId, Long userId, UpdateCommentRequest updateRequest){ //댓글ID, 유저ID를 파라미터로 받아 해당 댓글 수정
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(commentId, Comment.class));
        if(!comment.getUser().getUserId().equals(userId)) throw new UserIdMisMatchedException("작성자가 아님");
        if(!passwordEncoder.matches(updateRequest.getPassword(), comment.getUser().getPassword())) throw new PasswordMisMatchedException("비밀번호 불일치");

        comment.setContent(updateRequest.getContent());
        return new CommentResponse(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId, DeleteCommentRequest deleteRequest, Long userId){ //댓글ID, 유저ID를 파라미터로 받아 해당 댓글 삭제
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(commentId, Comment.class));
        if(!comment.getUser().getUserId().equals(userId)) throw new UserIdMisMatchedException("찾을 수 없음");
        if(!passwordEncoder.matches(deleteRequest.getPassword(), comment.getUser().getPassword())) throw new PasswordMisMatchedException("비밀번호 불일치");

        commentRepository.delete(comment);
    }
}
