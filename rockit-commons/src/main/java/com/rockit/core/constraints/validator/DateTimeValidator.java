package com.rockit.core.constraints.validator;

import com.rockit.core.constraints.annotation.DateTime;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateTimeValidator implements ConstraintValidator<DateTime, String> {

    private String pattern;

    @Override
    public void initialize(DateTime constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            if (StringUtils.isNotBlank(value)) {
                DateFormat format = new SimpleDateFormat(pattern);
                format.parse(value);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
