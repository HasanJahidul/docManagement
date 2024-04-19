package com.hasanjahidul.docManagement.repository;

import com.hasanjahidul.docManagement.entity.AccessPermission;
import com.hasanjahidul.docManagement.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


public interface DocumentRepository extends JpaRepository<Document, Long> {
    Document findByIdAndAccessControls_PermissionAndAccessControls_User_Id(Long id, AccessPermission permission, Long userId);

    List<Document> findByAccessControls_PermissionInOrCreatedBy(Collection<AccessPermission> permissions, Long createdBy);
}