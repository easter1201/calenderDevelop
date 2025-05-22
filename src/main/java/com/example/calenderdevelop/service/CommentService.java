package com.example.calenderdevelop.service;

import com.example.calenderdevelop.dto.CommentResponse;
import com.example.calenderdevelop.dto.CreateCommentRequest;
import com.example.calenderdevelop.dto.DeleteCommentRequest;
import com.example.calenderdevelop.dto.UpdateCommentRequest;

import java.util.List;

public interface CommentService {
    CommentResponse createComment(Long scheduleId, Long userId, CreateCommentRequest createCommentRequest);
    List<CommentResponse> findAllBySchedule(Long scheduleId);
    CommentResponse updateComment(Long commentId, Long userId, UpdateCommentRequest updateCommentRequest);
    void deleteComment(Long commentId, DeleteCommentRequest deleteRequest, Long userId);
}
