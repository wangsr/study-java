package org.wsr.stu.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author wangsr
 * @date 2018/9/14
 * @description 自定义验证
 */
public class CustomUrlValidator implements ConstraintValidator<CustomUrl, String> {

    @Override
    public void initialize(CustomUrl constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.hasText(value) && value.contains("a")) {
            return true;
        }
        return false;
    }
}
