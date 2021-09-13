package com.cardanonft.validate;

import com.cardanonft.validate.constraints.NoneAvailableString;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;

public class NoneAvailableStringValidator implements ConstraintValidator<NoneAvailableString, Object>{
    private ArrayList noneAvailableDigits = new ArrayList<Long>();

    @Override
    public void initialize(NoneAvailableString constraintAnnotation) {
        for (String n : constraintAnnotation.value()) {
            noneAvailableDigits.add(n);
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null || noneAvailableDigits == null || noneAvailableDigits.size() == 0)
            return true;

        if(value instanceof String) {
            return isValid((String) value);
        } else if(value instanceof String[]) {
            for (String v : (String[]) value) {
                if (!isValid(v)) return false;
            }
        }

        return true;
    }

    private boolean isValid(String value){
        return noneAvailableDigits.indexOf(value) == -1;
    }
}
