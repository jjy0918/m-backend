package com.midas.epkorea.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class RoleValidation implements ConstraintValidator<Role,String> {

    String[] roles;

    @Override
    public void initialize(Role constraintAnnotation) {
        roles = constraintAnnotation.roles();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.stream(roles).anyMatch(str->str.equals(value));
    }
}
