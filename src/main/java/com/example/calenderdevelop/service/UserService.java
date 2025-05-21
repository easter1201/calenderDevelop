package com.example.calenderdevelop.service;

import com.example.calenderdevelop.dto.*;

public interface UserService {
    UserResponse createUser(CreateUserRequest createRequest);
    UserResponse getUser(Long userId);
    UserResponse updateUser(Long userId, UpdateUserRequest updateRequest);
    void deleteUser(Long userId);
    Long login(String email, String password);
}
