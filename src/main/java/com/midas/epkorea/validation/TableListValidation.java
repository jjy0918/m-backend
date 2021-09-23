package com.midas.epkorea.validation;

import com.midas.epkorea.dto.request.TableListRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class TableListValidation implements ConstraintValidator<TableList, List<TableListRequestDto>> {

    private int max;

    @Override
    public void initialize(TableList constraintAnnotation) {
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(List<TableListRequestDto> value, ConstraintValidatorContext context) {
        // 비어있거나, 비어있지 않은데 개수가 max 이하인 경우 => true
        // 비어있지 않은데 개수가 max 초과인 경우 => false

        if(value==null || value.isEmpty()){
            return true;
        }

        if(value.size()>max){
            return false;
        }

        final boolean isValidItem[]={true};
        value.forEach(rq -> isValidItem[0]&=rq.checkTableListItem());

        return isValidItem[0];
    }
}
