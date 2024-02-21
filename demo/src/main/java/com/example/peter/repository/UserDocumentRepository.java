package com.example.peter.repository;

import com.example.peter.entity.UserDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDocumentRepository extends JpaRepository<UserDocuments,Long> {
    UserDocuments findByUserId(long id);

}
