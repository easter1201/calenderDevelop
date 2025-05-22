package com.example.calenderdevelop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {
    @NotBlank(message = "댓글 내용 필수")
    @Size(max = 1000, message = "댓글은 최대 1000자")
    private String content;

    public CreateCommentRequest(String content){
        this.content = content;
    }
}
