package com.hasanjahidul.docManagement.service;

import com.hasanjahidul.docManagement.entity.AccessControl;
import com.hasanjahidul.docManagement.entity.Document;
import com.hasanjahidul.docManagement.repository.AccessControlRepository;
import com.hasanjahidul.docManagement.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccessService {
    @Autowired
    private AccessControlRepository accessRepository;
    @Autowired
    private DocumentRepository documentRepository;

    public List<AccessControl> getAccessListForDocument(Long documentId) {
//        return accessRepository.findByDocuments_IdOrderByDocuments_IdAsc(documentId);
        return accessRepository.findAll();
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

    public AccessControl updateAccessForDocument(Long documentId, AccessControl updatedAccess) {
        Optional<Document> documentOptional = documentRepository.findById(documentId);
        if (documentOptional.isPresent()) {
            Document document = documentOptional.get();
            Optional<AccessControl> accessOptional = accessRepository.findById(updatedAccess.getId());
            if (accessOptional.isPresent()) {
                AccessControl access = accessOptional.get();
                access.setUser(updatedAccess.getUser());
                access.setPermission(updatedAccess.getPermission());
                access.setDocument(document);
                return accessRepository.save(access);
            }
        }
        return null;
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
