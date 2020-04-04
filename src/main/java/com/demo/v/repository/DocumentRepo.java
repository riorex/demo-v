package com.demo.v.repository;

import com.demo.v.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepo extends JpaRepository<Document, Long> {
    Document findByClientId(Long clientId);
    List<Document> findByUserId(Long userId);
}
