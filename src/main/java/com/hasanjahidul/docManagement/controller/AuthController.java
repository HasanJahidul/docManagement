package com.hasanjahidul.docManagement.controller;

import com.hasanjahidul.docManagement.dto.UserDTO;
import com.hasanjahidul.docManagement.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@Valid @RequestBody UserDTO.Request dto){
        System.out.println(dto);
        return this.authService.signUp(dto);
    }
    @PostMapping("/login")
    public Object login(@Valid @RequestBody UserDTO.LoginRequest dto){
        return this.authService.login(dto);

    }
    @GetMapping("/logout")
    public void logout(){

    }
}
