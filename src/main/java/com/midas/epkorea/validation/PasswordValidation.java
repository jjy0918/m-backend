package com.midas.epkorea.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidation implements ConstraintValidator<Password,String> {

    private int min;
    private int max;

    @Override
    public void initialize(Password constraintAnnotation) {
        min=constraintAnnotation.min();
        max=constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        int passwordLength = value.length();
        return (!value.isEmpty() && min <= passwordLength && passwordLength <= max);
    }

}
