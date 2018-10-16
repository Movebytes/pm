package com.pm.shared.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Constraint(validatedBy = ActiveProjectValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ActiveProjectList {
    String message() default "{com.pm.shared.validator.ActiveProjectList.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
