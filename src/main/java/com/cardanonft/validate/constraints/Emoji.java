package com.cardanonft.validate.constraints;

import com.cardanonft.validate.EmojiValidator;

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
@Constraint(validatedBy = { EmojiValidator.class })
public @interface Emoji {
    String message() default "The content cannot include any Emojis";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
