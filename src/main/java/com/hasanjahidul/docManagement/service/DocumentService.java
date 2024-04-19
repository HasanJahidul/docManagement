package com.hasanjahidul.docManagement.service;

import com.hasanjahidul.docManagement.config.UserContextHolder;
import com.hasanjahidul.docManagement.dto.DocumentDTO;
import com.hasanjahidul.docManagement.entity.AccessPermission;
import com.hasanjahidul.docManagement.entity.Document;
import com.hasanjahidul.docManagement.mapper.DocumentMapper;
import com.hasanjahidul.docManagement.model.APIResponseModel;
import com.hasanjahidul.docManagement.model.CreateSuccessModel;
import com.hasanjahidul.docManagement.model.NotFoundModel;
import com.hasanjahidul.docManagement.model.UpdateSuccessModel;
import com.hasanjahidul.docManagement.repository.DocumentRepository;
import com.hasanjahidul.docManagement.utils.SoftDeleteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private SoftDeleteUtil softDeleteUtil;

    public ResponseEntity<Object> getAllDocuments() {
        List<AccessPermission> permissions = Arrays.asList(AccessPermission.VIEW, AccessPermission.EDIT, AccessPermission.DELETE);
        List<Document> entity = documentRepository.findByAccessControls_PermissionInOrCreatedBy(
                List.of(AccessPermission.VIEW),
                UserContextHolder.getUserDetails().getUserId());
        List<DocumentDTO.DocumentResponse> dto = DocumentMapper.toDTOList(entity);
        return ResponseEntity.ok(new APIResponseModel<>(200,dto, (long) dto.size()));

    }

    public ResponseEntity<Object> getDocumentById(Long documentId) {
        Document entity = documentRepository.findById(documentId).orElse(null);
        System.out.println(entity);
        if (entity == null) {
            return ResponseEntity.ok(new NotFoundModel("Document not found",404));
        }
        Long userId = UserContextHolder.getUserDetails().getUserId();
//        check if user has permission to delete the document
        if (entity.getAccessControls().stream().noneMatch(accessControl -> accessControl.getUser().getId().equals(userId) && accessControl.getPermission().equals(AccessPermission.DELETE))) {
            return ResponseEntity.ok(new APIResponseModel<>(403,"You do not have permission to delete this document"));
        }
        return ResponseEntity.ok(new APIResponseModel<>(DocumentMapper.toDTO(entity)));
    }

    public ResponseEntity<Object> createDocument(DocumentDTO.Create dto) {

        Document document = DocumentMapper.toEntity(dto);
        documentRepository.save(document);
        return ResponseEntity.ok(new CreateSuccessModel("Document created successfully"));
    }

    public ResponseEntity<Object> updateDocument(DocumentDTO.Update dto) {
        Optional<Document> documentOptional = documentRepository.findById(dto.getDocumentId());
        if (documentOptional.isEmpty()) {
            return ResponseEntity.ok(new NotFoundModel("Document not found",404));
        }
        Document document = documentOptional.get();
        Document updatedDocument = DocumentMapper.toEntity(dto, document);
        documentRepository.save(updatedDocument);
        return ResponseEntity.ok(new UpdateSuccessModel("Document updated successfully"));
    }

    public ResponseEntity<Object> deleteDocument(Long documentId) {
        Document entity = documentRepository.findById(documentId).orElse(null);
        System.out.println(entity);
        if (entity == null) {
            return ResponseEntity.ok(new NotFoundModel("Document not found",404));
        }
        Long userId = UserContextHolder.getUserDetails().getUserId();
//        check if user has permission to delete the document
        if (entity.getAccessControls().stream().noneMatch(accessControl -> accessControl.getUser().getId().equals(userId) && accessControl.getPermission().equals(AccessPermission.DELETE))) {
            return ResponseEntity.ok(new APIResponseModel<>(403,"You do not have permission to delete this document"));
        }
        softDeleteUtil.softDelete(entity,documentRepository,"accessControls");
        return ResponseEntity.ok(new UpdateSuccessModel("Document deleted successfully"));
    }
}
