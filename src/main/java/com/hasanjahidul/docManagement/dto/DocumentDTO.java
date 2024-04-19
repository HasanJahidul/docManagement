package com.hasanjahidul.docManagement.dto;

import lombok.Data;

public class DocumentDTO {
    @Data
    public static class Create {
        private String title;
        private String content;
    }
}
