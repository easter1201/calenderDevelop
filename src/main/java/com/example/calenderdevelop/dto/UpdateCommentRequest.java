package com.example.calenderdevelop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateCommentRequest {
    @NotBlank(message = "댓글 내용 필수")
    @Size(max = 1000, message = "댓글은 최대 1000자")
    private String content;

    public UpdateCommentRequest(String content){
        this.content = content;
    }
}
