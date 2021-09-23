package com.midas.epkorea.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = BannerValidation.class)
public @interface Banner {
    String message() default "배너 리스트 개수 오류";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int max() default 30;
}
