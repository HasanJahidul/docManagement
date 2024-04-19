package com.hasanjahidul.docManagement.service;


import com.hasanjahidul.docManagement.dto.UserDTO;
import com.hasanjahidul.docManagement.entity.User;
import com.hasanjahidul.docManagement.mapper.UserMapper;
import com.hasanjahidul.docManagement.model.APIResponseModel;
import com.hasanjahidul.docManagement.model.InvalidRequestModel;
import com.hasanjahidul.docManagement.repository.UserRepository;
import com.hasanjahidul.docManagement.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<Object> signUp(UserDTO.Request dto) {
        User entity = UserMapper.toEntity(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(entity);
        return ResponseEntity.ok(new APIResponseModel<>(200,"User created successfully"));
    }

    public Object login(UserDTO.LoginRequest dto) {
        try {
            User user = userRepository.findByUserName(dto.getUsername());
            if (user == null || !passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new APIResponseModel<>(401,"Invalid username or password"));
            }

            // Authentication successful, generate JWT token and return it
            String jwt = jwtUtil.generateToken(user);
            return ResponseEntity.ok(new APIResponseModel<>(jwt));
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok(new InvalidRequestModel<>("Invalid username or password"));
        }
    }

    private void doAuthenticate(UserDTO.LoginRequest dto) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        try{
            userRepository.findByUserName(dto.getUsername());
        }catch (Exception e){
            throw new RuntimeException("Invalid username or password");
        }
    }
}
