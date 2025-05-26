package com.example.calenderdevelop.service;

import com.example.calenderdevelop.config.PasswordEncoder;
import com.example.calenderdevelop.dto.CreateUserRequest;
import com.example.calenderdevelop.dto.DeleteUserRequest;
import com.example.calenderdevelop.dto.UpdateUserRequest;
import com.example.calenderdevelop.dto.UserResponse;
import com.example.calenderdevelop.entity.User;
import com.example.calenderdevelop.exception.EntityNotFoundException;
import com.example.calenderdevelop.exception.LoginFailedException;
import com.example.calenderdevelop.exception.PasswordMisMatchedException;
import com.example.calenderdevelop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    @Transactional
    public UserResponse createUser(CreateUserRequest createRequest){ //유저 회원가입
        String encodedPassword = passwordEncoder.encode(createRequest.getPassword());
        User user = new User(createRequest.getUserName(), createRequest.getEmail(), encodedPassword);
        User saved = userRepository.save(user);
        return new UserResponse(saved);
    }

    @Override
    public UserResponse getUser(Long userId){ //유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId, User.class));
        return new UserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long userId, UpdateUserRequest updateRequest){ //유저ID를 파라미터로 받아 해당 유저명 수정
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId, User.class));
        if(!updateRequest.getEmail().equals(user.getEmail())) throw new LoginFailedException("이메일 불일치");
        if(!passwordEncoder.matches(updateRequest.getPassword(), user.getPassword())) throw new PasswordMisMatchedException("비밀번호 불일치");
        user.update(updateRequest.getUserName(), updateRequest.getEmail());
        return new UserResponse(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId, DeleteUserRequest deleteRequest){ //유저ID를 파라미터로 받아 해당 유저 삭제
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId, User.class));
        if(!passwordEncoder.matches(deleteRequest.getPassword(), user.getPassword())) throw new PasswordMisMatchedException("비밀번호 불일치");
        userRepository.delete(user);
    }

    @Override
    public Long login(String email, String password){ //이메일을 파라미터로 받아 로그인 기능 구현
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new LoginFailedException("이메일 또는 비밀번호 불일치"));
        if(!passwordEncoder.matches(password, user.getPassword())) throw new LoginFailedException("이메일 또는 비밀번호 불일치");

        return user.getUserId();
    }
}
