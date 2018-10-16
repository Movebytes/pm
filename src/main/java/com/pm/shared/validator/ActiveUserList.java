package com.pm.shared.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Constraint(validatedBy = ActiveUserValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ActiveUserList {
    String message() default "{com.pm.shared.validator.ActiveUserList.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}