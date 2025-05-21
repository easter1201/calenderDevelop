package com.example.calenderdevelop.service;

import com.example.calenderdevelop.config.PasswordEncoder;
import com.example.calenderdevelop.dto.CreateUserRequest;
import com.example.calenderdevelop.dto.UpdateUserRequest;
import com.example.calenderdevelop.dto.UserResponse;
import com.example.calenderdevelop.entity.User;
import com.example.calenderdevelop.exception.EntityNotFoundException;
import com.example.calenderdevelop.exception.LoginFailedException;
import com.example.calenderdevelop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserResponse createUser(CreateUserRequest createRequest){
        String encodedPassword = passwordEncoder.encode(createRequest.getPassword());
        User user = new User(createRequest.getUserName(), createRequest.getEmail(), encodedPassword);
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

    @Override
    public Long login(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new LoginFailedException("이메일 또는 비밀번호 불일치"));
        if(!passwordEncoder.matches(password, user.getPassword())) throw new LoginFailedException("이메일 또는 비밀번호 불일치");

        return user.getUserId();
    }
}
