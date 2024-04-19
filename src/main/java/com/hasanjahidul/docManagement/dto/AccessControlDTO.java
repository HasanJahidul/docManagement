package com.hasanjahidul.docManagement.dto;

import com.hasanjahidul.docManagement.entity.AccessPermission;
import lombok.Data;

import java.util.List;

public class AccessControlDTO {
    @Data
    public static class Request{
        private Long documentId;
        private List<PermissionsByUser> permissionsByUsers;
    }
    @Data
    public static class Response{
        private Long documentId;
        private String title;
        private List<PermissionsByUser> permissionsByUsers;
    }
    @Data
    public static class PermissionsByUser{
        private Long userId;
        private List<AccessPermission> permissions;
    }

}
