package com.midas.epkorea.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RoleValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Role {
    String message() default "권한 오류";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};



    String[] roles() default  {"ADMIN","MANAGER","USER"};
}
