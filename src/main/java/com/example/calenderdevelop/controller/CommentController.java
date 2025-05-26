package com.example.calenderdevelop.controller;

import com.example.calenderdevelop.dto.CommentResponse;
import com.example.calenderdevelop.dto.CreateCommentRequest;
import com.example.calenderdevelop.dto.DeleteCommentRequest;
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

    @PostMapping("/{scheduleId}") //댓글 등록
    public CommentResponse createComment(@PathVariable Long scheduleId, @Valid @RequestBody CreateCommentRequest createRequest, HttpServletRequest request){
        Long userId = extractUserIdFromCookie(request);
        return commentService.createComment(scheduleId, userId, createRequest);
    }

    @GetMapping("/{scheduleId}") //댓글 조회
    public List<CommentResponse> getComments(@PathVariable Long scheduleId){
        return commentService.findAllBySchedule(scheduleId);
    }

    @PutMapping("/{commentId}") //댓글 수정
    public CommentResponse updateComment(@PathVariable Long commentId, @Valid @RequestBody UpdateCommentRequest updateRequest, HttpServletRequest request){
        Long userId = extractUserIdFromCookie(request);
        return commentService.updateComment(commentId, userId, updateRequest);
    }

    @DeleteMapping("/{commentId}") //댓글 삭제
    public void deleteComment(@PathVariable Long commentId, @Valid @RequestBody DeleteCommentRequest deleteRequest , HttpServletRequest request){
        Long userId = extractUserIdFromCookie(request);
        commentService.deleteComment(commentId, deleteRequest, userId);
    }

    private Long extractUserIdFromCookie(HttpServletRequest request){ //쿠키값에서 userId 추출
        if(request.getCookies() == null) throw new LoginFailedException("로그인 필요");
        for(Cookie cookie : request.getCookies()){
            if("userId".equals(cookie.getName())) return Long.valueOf(cookie.getValue());
        }
        throw new LoginFailedException("쿠키 없음");
    }
}
