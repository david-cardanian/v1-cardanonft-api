package com.cardanonft.validate;

import com.cardanonft.validate.constraints.MacAddr;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MacAddrValidator implements ConstraintValidator<MacAddr, Object> {

    @Override
    public void initialize(MacAddr constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null)
            return true;

        Pattern pattern = Pattern.compile("^([0-9A-F][0-9A-F]:){5}([0-9A-F][0-9A-F])");
        Matcher matcher = pattern.matcher(value.toString());
        return matcher.find();
    }
}
