package com.midas.epkorea.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidation.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "암호 오류";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min() default 8;

    int max() default 12;

}
