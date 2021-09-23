package com.midas.epkorea.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryValidation implements ConstraintValidator<Category,Integer> {

    private int min;
    private int max;

    @Override
    public void initialize(Category constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return (min <= value && value <= max);
    }
}
