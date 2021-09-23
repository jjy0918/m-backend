package com.midas.epkorea.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategoryValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Category {

    String message() default "카테고리";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min() default 1;

    int max() default 14;
}
