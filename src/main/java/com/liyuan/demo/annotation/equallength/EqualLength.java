package com.liyuan.demo.annotation.equallength;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 验证值是否为指定长度。length（-1不限制）只指定长度。
 * isnull指是否可以为空。regexp（为空则不校验）表示同时匹配正则表达式
 */
@Documented
@Constraint(validatedBy = {EqualLengthValidator.class})
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EqualLength {
    String message() default "内容不一致";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String field() default "";

    int[] length() default {};

    boolean isBlank() default false;

    String regexp() default "";


}
