package com.cardanonft.validate;



import com.cardanonft.validate.constraints.Emoji;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiValidator implements ConstraintValidator<Emoji, Object>{

    @Override
    public void initialize(Emoji constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null)
            return true;

        Pattern pattern = Pattern.compile("[\\uD83C-\\uDBFF\\uDC00-\\uDFFF]+");
        Matcher matcher = pattern.matcher(value.toString().replace("-", ""));

        return !matcher.find();
    }
}
