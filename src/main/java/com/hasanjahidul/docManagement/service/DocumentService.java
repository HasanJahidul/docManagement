package com.hasanjahidul.docManagement.service;

import com.hasanjahidul.docManagement.config.UserContextHolder;
import com.hasanjahidul.docManagement.dto.DocumentDTO;
import com.hasanjahidul.docManagement.entity.AccessPermission;
import com.hasanjahidul.docManagement.entity.Document;
import com.hasanjahidul.docManagement.mapper.DocumentMapper;
import com.hasanjahidul.docManagement.model.CreateSuccessModel;
import com.hasanjahidul.docManagement.model.NotFoundModel;
import com.hasanjahidul.docManagement.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public ResponseEntity<Object> getDocumentById(Long id) {
        Document entity = documentRepository.findByIdAndAccessControls_PermissionAndAccessControls_User_Id(
                id,
                AccessPermission.VIEW,
                UserContextHolder.getUserDetails().getUserId()
        );
        if(entity == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
        return ResponseEntity.ok(entity);


    }

    public ResponseEntity<Object> createDocument(DocumentDTO.Create dto) {

        Document document = DocumentMapper.toEntity(dto);
        documentRepository.save(document);
        return ResponseEntity.ok(new CreateSuccessModel("Document created successfully"));
    }

    public Document updateDocument(Long id, Document updatedDocument) {
        Optional<Document> documentOptional = documentRepository.findById(id);
        if (documentOptional.isPresent()) {
            Document document = documentOptional.get();
            document.setTitle(updatedDocument.getTitle());
            document.setContent(updatedDocument.getContent());
            return documentRepository.save(document);
        }
        return null;
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
}
