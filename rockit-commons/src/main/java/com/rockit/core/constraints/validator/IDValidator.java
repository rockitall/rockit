package com.rockit.core.constraints.validator;

import com.rockit.core.constraints.annotation.ID;
import com.rockit.core.constraints.annotation.Mobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IDValidator implements ConstraintValidator<ID, String> {

    private String regexp;

    @Override
    public void initialize(ID constraintAnnotation) {
        this.regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.matches(regexp);
    }

    public static void main(String[] args) {

    }

}
