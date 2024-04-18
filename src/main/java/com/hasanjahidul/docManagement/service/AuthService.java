package com.hasanjahidul.docManagement.service;

import com.hasanjahidul.docManagement.dto.UserDTO;
import com.hasanjahidul.docManagement.entity.User;
import com.hasanjahidul.docManagement.mapper.UserMapper;
import com.hasanjahidul.docManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Object> signUp(UserDTO.Request dto) {
        User entity = UserMapper.toEntity(dto);
        userRepository.save(entity);
        return ResponseEntity.ok("User created");
    }
}
