package com.hasanjahidul.docManagement.service;

import com.hasanjahidul.docManagement.dto.AccessControlDTO;
import com.hasanjahidul.docManagement.entity.AccessControl;
import com.hasanjahidul.docManagement.entity.AccessPermission;
import com.hasanjahidul.docManagement.entity.Document;
import com.hasanjahidul.docManagement.entity.User;
import com.hasanjahidul.docManagement.mapper.AccessMapper;
import com.hasanjahidul.docManagement.mapper.DocumentMapper;
import com.hasanjahidul.docManagement.model.APIResponseModel;
import com.hasanjahidul.docManagement.model.NotFoundModel;
import com.hasanjahidul.docManagement.repository.AccessControlRepository;
import com.hasanjahidul.docManagement.repository.DocumentRepository;
import com.hasanjahidul.docManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccessService {
    @Autowired
    private AccessControlRepository accessRepository;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private UserRepository userRepository;



    public ResponseEntity<Object> getAccessListForDocument(Long documentId) {
        Document entity = documentRepository.findByIdOrderByIdAscAccessControls_PermissionAsc(documentId);
        AccessControlDTO.Response response = AccessMapper.toDTO(entity);
        return ResponseEntity.ok(new APIResponseModel<>(200, response));


    }

    public AccessControl addAccessForDocument(Long documentId, AccessControl access) {
        Optional<Document> documentOptional = documentRepository.findById(documentId);
        if (documentOptional.isPresent()) {
            Document document = documentOptional.get();
            access.setDocument(document);
            return accessRepository.save(access);
        }
        return null;
    }

    @Transactional
    public ResponseEntity<Object> addOrUpdatePermissions(AccessControlDTO.Request request) {
        // Get the document
        Document document = documentRepository.findById(request.getDocumentId())
                .orElseThrow(() -> new IllegalArgumentException("Document not found with id: " + request.getDocumentId()));

        // Group permissions by user
        Map<Long, List<AccessPermission>> permissionsByUserId = request.getPermissions().stream()
                .collect(Collectors.toMap(
                        AccessControlDTO.PermissionsByUser::getUserId,
                        AccessControlDTO.PermissionsByUser::getPermissions
                ));

        // Update or add permissions for each user
        for (Map.Entry<Long, List<AccessPermission>> entry : permissionsByUserId.entrySet()) {
            Long userId = entry.getKey();
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
            List<AccessPermission> permissions = entry.getValue();

            for (AccessPermission permission : permissions) {
                // Check if the permission already exists for the user
                boolean permissionExists = document.getAccessControls().stream()
                        .anyMatch(accessControl -> accessControl.getUser().equals(user) && accessControl.getPermission() == permission);

                if (!permissionExists) {
                    // Create a new access control entry if permission doesn't exist
                    AccessControl accessControl = new AccessControl();
                    accessControl.setDocument(document);
                    accessControl.setUser(user);
                    accessControl.setPermission(permission);
                    document.getAccessControls().add(accessControl);
                }
            }
        }

        // Save the updated document
        documentRepository.save(document);
        return ResponseEntity.ok(new APIResponseModel<>(200, "Permissions updated successfully"));
    }

    public void deleteAccessForDocument(Long documentId, Long accessId) {
        Optional<Document> documentOptional = documentRepository.findById(documentId);
        if (documentOptional.isPresent()) {
            Document document = documentOptional.get();
            Optional<AccessControl> accessOptional = accessRepository.findById(accessId);
            if (accessOptional.isPresent()) {
                AccessControl access = accessOptional.get();
                if (access.getDocument().equals(document)) {
                    accessRepository.delete(access);
                }
            }
        }
    }
}
