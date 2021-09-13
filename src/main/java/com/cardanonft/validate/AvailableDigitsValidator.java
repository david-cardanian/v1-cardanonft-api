package com.cardanonft.validate;

import com.cardanonft.validate.constraints.AvailableDigits;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;

public class AvailableDigitsValidator implements ConstraintValidator<AvailableDigits, Object>{
    private ArrayList availableDigits = new ArrayList<Long>();

    @Override
    public void initialize(AvailableDigits constraintAnnotation) {
        for (Number n : constraintAnnotation.value()) {
            availableDigits.add(new Long(String.valueOf(n)));
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null || availableDigits == null || availableDigits.size() == 0)
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
        return availableDigits.indexOf(new Long(String.valueOf(value))) != -1;
    }
}
