package com.hasanjahidul.docManagement.repository;

import com.hasanjahidul.docManagement.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}