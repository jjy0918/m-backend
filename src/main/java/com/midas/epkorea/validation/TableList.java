package com.midas.epkorea.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TableListValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableList {

    String message() default "테이블 리스트 개수 오류";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int max() default 10;

}
