package com.midas.epkorea.validation;

import com.midas.epkorea.dto.request.BannerRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class BannerValidation implements ConstraintValidator<Banner, List<BannerRequestDto>> {

    private int max;

    @Override
    public void initialize(Banner constraintAnnotation) {
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(List<BannerRequestDto> value, ConstraintValidatorContext context) {
        if(value==null || value.isEmpty()){
            return true;
        }

        if(value.size()>max){
            return false;
        }

        final boolean isValidItem[]={true};
        value.forEach(rq -> isValidItem[0]&=rq.checkBannerItem());

        return isValidItem[0];
    }
}
