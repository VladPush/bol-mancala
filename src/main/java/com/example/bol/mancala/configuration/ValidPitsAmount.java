package com.example.bol.mancala.configuration;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidPitsAmountValidator.class)
public @interface ValidPitsAmount {

    String message() default "Pits amount must be %s than or equal to %s.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}