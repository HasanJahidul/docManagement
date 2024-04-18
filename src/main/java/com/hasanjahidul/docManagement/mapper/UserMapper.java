package com.hasanjahidul.docManagement.mapper;

import com.hasanjahidul.docManagement.dto.UserDTO;
import com.hasanjahidul.docManagement.entity.User;

public class UserMapper {
    public static UserDTO.Response toResponse(User user) {
        UserDTO.Response dto = new UserDTO.Response();
        dto.setUserId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUserName());
        dto.setEmail(user.getEmail());
        return dto;

    }

    public static User toEntity(UserDTO.Request dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setUserName(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }
}
