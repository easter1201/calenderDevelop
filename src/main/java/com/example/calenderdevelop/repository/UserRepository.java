package com.example.calenderdevelop.repository;

import com.example.calenderdevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserNameAndEmail(String userName, String email);

    Optional<User> findByEmail(String email);

    Long findIdByEmailAndPassword(String email, String password);

}
