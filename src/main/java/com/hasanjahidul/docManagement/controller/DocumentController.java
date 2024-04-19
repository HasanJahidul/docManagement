package com.hasanjahidul.docManagement.controller;

import com.hasanjahidul.docManagement.dto.DocumentDTO;
import com.hasanjahidul.docManagement.service.DocumentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/document")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @PostMapping("/create")
    public ResponseEntity<Object> createDocument(@Valid @RequestBody DocumentDTO.Create dto){
        return ResponseEntity.ok(documentService.createDocument(dto));
    }
    @PutMapping("/update")
    public ResponseEntity<Object> updateDocument(@Valid @RequestBody DocumentDTO.Update dto){
        return ResponseEntity.ok(documentService.updateDocument(dto));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteDocument(@RequestParam Long documentId){
        return ResponseEntity.ok(documentService.deleteDocument(documentId));
    }
    @GetMapping("/all")
    public ResponseEntity<Object> getAllDocuments(){
        return ResponseEntity.ok(documentService.getAllDocuments());
    }
    @GetMapping("get-by-id")
    public ResponseEntity<Object> getDocumentById(@RequestParam Long documentId){
        return ResponseEntity.ok(documentService.getDocumentById(documentId));
    }
}
