package com.cardanonft.validate;

import com.cardanonft.validate.constraints.NoneAvailableDigits;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;

public class NoneAvailableDigitsValidator implements ConstraintValidator<NoneAvailableDigits, Object>{
    private ArrayList noneAvailableDigits = new ArrayList<Long>();

    @Override
    public void initialize(NoneAvailableDigits constraintAnnotation) {
        for (Number n : constraintAnnotation.value()) {
            noneAvailableDigits.add(new Long(String.valueOf(n)));
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null || noneAvailableDigits == null || noneAvailableDigits.size() == 0)
            return true;

        if(value instanceof Number) {
            return isValid((Number) value);
        } else if(value instanceof long[]) {
            for (Number v : (long[]) value) {
                if (!isValid(v)) return false;
            }
        } else if(value instanceof int[]) {
            for (Number v : (int[]) value) {
                if (!isValid(v)) return false;
            }
        } else {
            for (Number v : (Number[]) value) {
                return isValid(v);
            }
        }

        return true;
    }

    private boolean isValid(Number value){
        return noneAvailableDigits.indexOf(new Long(String.valueOf(value))) == -1;
    }
}
