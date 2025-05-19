package com.example.calenderdevelop.service;

import com.example.calenderdevelop.dto.*;
import com.example.calenderdevelop.entity.User;
import com.example.calenderdevelop.exception.EntityNotFoundException;
import com.example.calenderdevelop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserResponse createUser(CreateUserRequest createRequest){
        User user = new User(createRequest.getUserName(), createRequest.getEmail());
        User saved = userRepository.save(user);
        return new UserResponse(saved);
    }

    @Override
    public UserResponse getUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId, User.class));
        return new UserResponse(user);
    }

    @Override
    public UserResponse updateUser(Long userId, UpdateUserRequest updateRequest){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId, User.class));
        user.update(updateRequest.getUserName(), updateRequest.getEmail());
        return new UserResponse(user);
    }

    @Override
    public void deleteUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId, User.class));
        userRepository.delete(user);
    }
}
