package com.example.calenderdevelop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateCommentRequest {
    @NotBlank(message = "댓글 내용 필수")
    @Size(max = 1000, message = "댓글은 최대 1000자")
    private String content;
    @NotBlank(message = "비밀번호 필수")
    @Size(min = 4, max = 25, message = "비밀번호는 최소 4자 이상 25자 이내")
    private String password;

    public UpdateCommentRequest(String content, String password){
        this.content = content;
        this.password = password;
    }
}
