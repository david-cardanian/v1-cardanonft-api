package com.cardanonft.validate.constraints;

import com.cardanonft.validate.MacAddrValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { MacAddrValidator.class })
public @interface MacAddr {
    String message() default "The name cannot include any mac_address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
