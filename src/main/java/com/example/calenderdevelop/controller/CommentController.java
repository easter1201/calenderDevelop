package com.example.calenderdevelop.controller;

import com.example.calenderdevelop.dto.CommentResponse;
import com.example.calenderdevelop.dto.CreateCommentRequest;
import com.example.calenderdevelop.dto.UpdateCommentRequest;
import com.example.calenderdevelop.exception.LoginFailedException;
import com.example.calenderdevelop.service.CommentService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService){this.commentService = commentService;}

    @PostMapping("/{scheduleId}")
    public CommentResponse createComment(@PathVariable Long scheduleId, @Valid @RequestBody CreateCommentRequest createRequest, HttpServletRequest request){
        Long userId = extractUserIdFromCookie(request);
        return commentService.createComment(scheduleId, userId, createRequest);
    }

    @GetMapping("/{scheduleId}")
    public List<CommentResponse> getComments(@PathVariable Long scheduleId){
        return commentService.findAllBySchedule(scheduleId);
    }

    @PutMapping("/{commentId}")
    public CommentResponse updateComment(@PathVariable Long commentId, @Valid @RequestBody UpdateCommentRequest updateRequest, HttpServletRequest request){
        Long userId = extractUserIdFromCookie(request);
        return commentService.updateComment(commentId, userId, updateRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId, HttpServletRequest request){
        Long userId = extractUserIdFromCookie(request);
        commentService.deleteComment(commentId, userId);
    }

    private Long extractUserIdFromCookie(HttpServletRequest request){
        if(request.getCookies() == null) throw new LoginFailedException("로그인 필요");
        for(Cookie cookie : request.getCookies()){
            if("userId".equals(cookie.getName())) return Long.valueOf(cookie.getValue());
        }
        throw new LoginFailedException("쿠키 없음");
    }
}
