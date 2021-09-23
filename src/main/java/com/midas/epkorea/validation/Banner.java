package com.midas.epkorea.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = BannerValidation.class)
public @interface Banner {
    String message() default "배너 개수가 30개 초과이거나 비정상적인 값이 들어있습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int max() default 30;
}
