package com.midas.epkorea.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Constraint(validatedBy = ImageListValidation.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageList {
    String message() default "리스트 내용 오류";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
