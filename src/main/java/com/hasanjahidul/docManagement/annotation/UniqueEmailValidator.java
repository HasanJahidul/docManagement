package com.hasanjahidul.docManagement.annotation;

import com.hasanjahidul.docManagement.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {
    @Autowired
    private UserRepository userRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !userRepository.existsByEmailIgnoreCase(value);
    }
}