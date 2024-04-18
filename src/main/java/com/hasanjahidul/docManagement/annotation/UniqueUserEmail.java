package com.hasanjahidul.docManagement.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueUserEmail {
    String message() default "User with the same email already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
