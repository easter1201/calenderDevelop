package com.example.calenderdevelop.controller;

import com.example.calenderdevelop.dto.CreateUserRequest;
import com.example.calenderdevelop.dto.DeleteUserRequest;
import com.example.calenderdevelop.dto.LoginRequest;
import com.example.calenderdevelop.dto.UpdateUserRequest;
import com.example.calenderdevelop.exception.LoginFailedException;
import com.example.calenderdevelop.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login") //로그인
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response){
        Long userId = userService.login(loginRequest.getEmail(), loginRequest.getPassword());

        Cookie cookie = new Cookie("userId", String.valueOf(userId));
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok("login");
    }

    @PostMapping("/logout") //로그아웃
    public ResponseEntity<String> logout(HttpServletResponse response){
        Cookie cookie = new Cookie("userId", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok("logout");
    }

    @PostMapping("/sign") //회원가입(유저 등록)
    public ResponseEntity<String> signup(@Valid @RequestBody CreateUserRequest createRequest){
        userService.createUser(createRequest);
        return ResponseEntity.ok("회원가입 완료");
    }

    @PutMapping("/rename") //유저명 수정
    public ResponseEntity<String> reName(@Valid @RequestBody UpdateUserRequest updateRequest, HttpServletRequest request){
        Long userId = extractUserIdFromCookie(request);
        userService.updateUser(userId, updateRequest);
        return ResponseEntity.ok("수정 완료");
    }

    @DeleteMapping("/quit") //유저 삭제(회원 탈퇴)
    public ResponseEntity<String> quit(@Valid @RequestBody DeleteUserRequest deleteRequest, HttpServletRequest request){
        Long userId = extractUserIdFromCookie(request);
        userService.deleteUser(userId, deleteRequest);
        return ResponseEntity.ok("회원 탈퇴 완료");
    }

    private Long extractUserIdFromCookie(HttpServletRequest request){
        if(request.getCookies() == null) throw new LoginFailedException("로그인 필요");
        for(Cookie cookie : request.getCookies()){
            if("userId".equals(cookie.getName())) return Long.valueOf(cookie.getValue());
        }
        throw new LoginFailedException("쿠키 없음");
    }
}
