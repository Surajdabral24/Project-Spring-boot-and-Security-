package com.example.peter.repository;

import com.example.peter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String username);
    Optional<User> findByUsername(String username);

    Optional<User> findByEmailOrPhoneNumber(String email, String phoneNumber);

    User findByPhoneNumber(String phoneNumber);
}
