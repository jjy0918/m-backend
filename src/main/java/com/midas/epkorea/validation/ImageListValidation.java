package com.midas.epkorea.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ImageListValidation implements ConstraintValidator<ImageList, List<String>> {


    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        if(value == null || value.isEmpty()){
            return true;
        }
        final boolean isValid[] = {true};
        value.forEach(str -> isValid[0]&=(str!=null && !str.isEmpty()));

        return isValid[0];
    }
}
