package com.hasanjahidul.docManagement.mapper;

import com.hasanjahidul.docManagement.config.UserContextHolder;
import com.hasanjahidul.docManagement.dto.DocumentDTO;
import com.hasanjahidul.docManagement.entity.AccessControl;
import com.hasanjahidul.docManagement.entity.AccessPermission;
import com.hasanjahidul.docManagement.entity.Document;
import com.hasanjahidul.docManagement.entity.User;

import java.util.List;

public class DocumentMapper {
    public static Document toEntity(DocumentDTO.Create dto) {
        Document document = new Document();
        document.setTitle(dto.getTitle());
        document.setContent(dto.getContent());

        User user = new User();
        user.setId(UserContextHolder.getUserDetails().getUserId());

        AccessControl accessControl = new AccessControl();
        accessControl.setUser(user);
        accessControl.setPermission(AccessPermission.VIEW);
        accessControl.setDocument(document);

        AccessControl accessControl2 = new AccessControl();
        accessControl2.setUser(user);
        accessControl2.setPermission(AccessPermission.EDIT);
        accessControl2.setDocument(document);

        AccessControl accessControl3 = new AccessControl();
        accessControl3.setUser(user);
        accessControl3.setPermission(AccessPermission.DELETE);
        accessControl3.setDocument(document);

        List<AccessControl> accessControls = List.of(accessControl, accessControl2, accessControl3);
        document.setAccessControls(accessControls);

        return document;
    }
}
