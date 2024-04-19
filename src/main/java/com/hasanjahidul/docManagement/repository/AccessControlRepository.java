package com.hasanjahidul.docManagement.repository;

import com.hasanjahidul.docManagement.entity.AccessControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessControlRepository extends JpaRepository<AccessControl, Long> {
//    List<AccessControl> findByDocuments_IdOrderByDocuments_IdAsc(Long id);
List<AccessControl> findByDocument_IdOrderByDocument_IdAscPermissionAsc(Long id);
}