package com.example.peter.repository;

import com.example.peter.entity.UserEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEducationRepository extends JpaRepository<UserEducation,Long> {
    UserEducation findByUserId(long id);
}
