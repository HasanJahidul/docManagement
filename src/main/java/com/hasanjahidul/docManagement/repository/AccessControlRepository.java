package com.hasanjahidul.docManagement.repository;

import com.hasanjahidul.docManagement.entity.AccessControl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessControlRepository extends JpaRepository<AccessControl, Long> {
}