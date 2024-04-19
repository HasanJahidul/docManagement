package com.hasanjahidul.docManagement.mapper;

import com.hasanjahidul.docManagement.dto.AccessControlDTO;
import com.hasanjahidul.docManagement.entity.AccessControl;
import com.hasanjahidul.docManagement.entity.AccessPermission;
import com.hasanjahidul.docManagement.entity.Document;
import com.hasanjahidul.docManagement.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccessMapper {
    public static AccessControlDTO.Response toDTO(Document entity) {
        AccessControlDTO.Response response = new AccessControlDTO.Response();
        response.setDocumentId(entity.getId());
        response.setTitle(entity.getTitle());
        List<AccessControlDTO.PermissionsByUser> permissionsByUsers = entity.getAccessControls().stream()
                .collect(Collectors.groupingBy(AccessControl::getUser))
                .entrySet().stream()
                .map(entry -> {
                    AccessControlDTO.PermissionsByUser permissionsByUser = new AccessControlDTO.PermissionsByUser();
                    permissionsByUser.setUserId(entry.getKey().getId());
                    List<AccessPermission> permissions = entry.getValue().stream()
                            .map(AccessControl::getPermission)
                            .collect(Collectors.toList());
                    permissionsByUser.setPermissions(permissions);
                    return permissionsByUser;
                })
                .collect(Collectors.toList());
        response.setPermissionsByUsers(permissionsByUsers);
        return response;
    }
}
