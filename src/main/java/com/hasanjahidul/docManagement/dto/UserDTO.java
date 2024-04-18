package com.hasanjahidul.docManagement.dto;

import com.hasanjahidul.docManagement.annotation.UniqueUserEmail;
import com.hasanjahidul.docManagement.annotation.UniqueUserName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class UserDTO {
    @Data
    public static class Request {
        @NotBlank(message = "Username is required")
//        @UniqueUserName
        String username;
        @NotBlank(message = "Password is required")
        String password;
//        @UniqueUserEmail
        @Email
        String email;
        @NotBlank(message = "Name is required")
        String name;

    }
    @Data
    public static class Response {
        private Long userId;
        private String name;
        private String username;
        private String email;
    }
}
