package com.example.calenderdevelop.dto;

import com.example.calenderdevelop.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private Long commentId;
    private String content;
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommentResponse(Comment comment){
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.userName = comment.getUser().getUserName();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
    }
}
