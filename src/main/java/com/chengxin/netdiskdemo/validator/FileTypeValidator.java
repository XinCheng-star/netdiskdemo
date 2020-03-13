package com.chengxin.netdiskdemo.validator;

import com.chengxin.netdiskdemo.annotation.NeedType;
import com.chengxin.netdiskdemo.model.FileTypeEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * FileTypeValidator 的注释
 *
 * @author chengxin
 * @date 2020/3/13 18:53
 * @description
 */
public class FileTypeValidator implements ConstraintValidator<NeedType, FileTypeEnum> {
    @Override
    public boolean isValid(FileTypeEnum fileTypeEnum, ConstraintValidatorContext constraintValidatorContext) {
        if(fileTypeEnum == null){
            return false;
        }
        return true;
    }
}
