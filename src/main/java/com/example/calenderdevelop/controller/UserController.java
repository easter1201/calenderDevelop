package com.example.calenderdevelop.controller;

import com.example.calenderdevelop.dto.CreateUserRequest;
import com.example.calenderdevelop.dto.LoginRequest;
import com.example.calenderdevelop.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response){
        Long userId = userService.login(loginRequest.getEmail(), loginRequest.getPassword());

        Cookie cookie = new Cookie("userId", String.valueOf(userId));
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok("login");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response){
        Cookie cookie = new Cookie("userId", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok("logout");
    }

    @PostMapping("/sign")
    public ResponseEntity<String> signup(@Valid @RequestBody CreateUserRequest createRequest){
        userService.createUser(createRequest);
        return ResponseEntity.ok("회원가입 완료");
    }
}
