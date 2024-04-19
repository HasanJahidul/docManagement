package com.hasanjahidul.docManagement.controller;

import com.hasanjahidul.docManagement.dto.DocumentDTO;
import com.hasanjahidul.docManagement.service.DocumentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/document")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @PostMapping("/create")
    public ResponseEntity<Object> createDocument(@Valid @RequestBody DocumentDTO.Create dto){
        return ResponseEntity.ok(documentService.createDocument(dto));
    }
}
