package com.hasanjahidul.docManagement.dto;

import lombok.Data;

public class DocumentDTO {
    @Data
    public static class Create {
        private String title;
        private String content;
    }
    @Data
    public static class Update {
        private Long documentId;
        private String title;
        private String content;
    }
    @Data
    public static class DocumentResponse {
        private Long documentId;
        private String title;
        private String content;
    }
}
