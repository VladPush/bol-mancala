package com.example.bol.mancala.configuration;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

public class ValidPitsAmountValidator implements ConstraintValidator<ValidPitsAmount, Integer> {

    @Value("${mancala.pits.min}")
    private Integer minPits;

    @Value("${mancala.pits.max}")
    private Integer maxPits;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value > maxPits) {
            formatMessage(context, StringLiterals.LESS.getName(), maxPits);
            return false;
        } else if (value < minPits) {
            formatMessage(context, StringLiterals.MORE.getName(), minPits);
            return false;
        }
        return true;
    }

    private void formatMessage(ConstraintValidatorContext context, String sign, Integer value) {
        String msg = context.getDefaultConstraintMessageTemplate();
        String formattedMsg = String.format(msg, sign, value);
        context.disableDefaultConstraintViolation();

        context.buildConstraintViolationWithTemplate(formattedMsg)
                .addConstraintViolation();
    }
}
