package com.liyuan.demo.annotation.equallength;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 验证值是否为指定长度。length(-1或0表示不限制)只指定长度。
 * isBlank指是否可以为空。regexp（为空则不校验）表示同时匹配正则表达式
 * To change this template use File | Settings | File Templates.
 */
public class EqualLengthValidator implements ConstraintValidator<EqualLength, String> {

    private int[] length;

    private boolean isBlank;

    private String regexp;

    @Override
    public void initialize(EqualLength equalTo) {
        this.length = equalTo.length();
        this.isBlank = equalTo.isBlank();
        this.regexp = equalTo.regexp();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (isBlank && StringUtils.isBlank(s)) {
            return true;
        }
        if (length != null && length.length > 0) {
            boolean sign = true;
            for (int val : length) {
                if (s.length() == val) {
                    sign = false;
                }
            }
            if (sign) {
                return false;
            }
        }

        if (StringUtils.isNotBlank(regexp) && !s.matches(regexp)) {
            return false;
        }
        return true;
    }
}
