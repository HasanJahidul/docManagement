package com.hasanjahidul.docManagement.controller;

import com.hasanjahidul.docManagement.dto.AccessControlDTO;
import com.hasanjahidul.docManagement.service.AccessService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/access")
public class AccessController {
    @Autowired
    private AccessService accessService;
    @GetMapping("/by-doc-id")
    public ResponseEntity<Object> getAccess(@RequestParam Long documentId) {
        return accessService.getAccessListForDocument(documentId);
    }
    @PutMapping("/update-access")
    public ResponseEntity<Object> updateAccess(@RequestBody @Valid AccessControlDTO.Request dto) {
        return accessService.addOrUpdatePermissions(dto);
    }
    @DeleteMapping("/delete-access")
    public ResponseEntity<Object> deleteAccess(@RequestBody @Valid AccessControlDTO.Request dto) {
        return accessService.deletePermissions(dto);
    }

}
