package com.chengxin.netdiskdemo.annotation;

import com.chengxin.netdiskdemo.validator.FileTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileTypeValidator.class)
public @interface NeedType {
    String message() default "file must need type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
