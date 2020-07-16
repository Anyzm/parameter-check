package cn.anyzm.parameter.handler.impl;

import cn.anyzm.parameter.annotation.NotBlank;
import cn.anyzm.parameter.constant.ExceptionCodeMsg;
import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.exception.ParameterException;
import cn.anyzm.parameter.handler.AnnotationHandler;
import cn.anyzm.parameter.utils.AnyzmUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0 @Description NotNullHandler is used for @Date 2019/10/12 - 17:21
 */
public class NotBlankHandler extends AnnotationHandler {

    @Override
    protected boolean isTiming(Annotation annotation, String timing) {
        if (!(annotation instanceof NotBlank)) {
            return false;
        }
        NotBlank notBlank = (NotBlank) annotation;
        String[] annotationTiming = notBlank.timing();
        return isTiming(timing, annotationTiming);
    }

    @Override
    public void checkField(Field field, Object object, Annotation annotation)
            throws ParameterException {
        if (field == null || annotation == null) {
            return;
        }
        field.setAccessible(true);
        NotBlank notBlank = (NotBlank) annotation;
        String msg = notBlank.msg();
        try {
            Object o = field.get(object);
            if (o == null) {
                throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
            }
            if (o instanceof String) {
                String s = (String) o;
                if (AnyzmUtils.isBlank(s)) {
                    throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
                }
            } else {
                throw new ParameterException(ExceptionCodeMsg.NOT_BLANK_CAST_ERROR, field.getName());
            }
        } catch (IllegalAccessException e) {
            throw new ParameterException(e.getMessage());
        }
    }

    @Override
    protected String checkFieldForMsg(Field field, Object object, Annotation annotation)
            throws ParameterException {
        if (field == null || annotation == null) {
            return AnyzmUtils.emptyString();
        }
        field.setAccessible(true);
        NotBlank notBlank = (NotBlank) annotation;
        String msg = notBlank.msg();
        try {
            Object o = field.get(object);
            if (o == null) {
                return msg;
            }
            if (o instanceof String) {
                String s = (String) o;
                if (AnyzmUtils.isBlank(s)) {
                    return msg;
                }
            } else {
               return msg;
            }
        } catch (IllegalAccessException e) {
            throw new ParameterException(e.getMessage());
        }
        return ValueEnum.EMPTY_STRING;
    }

    @Override
    public void checkOneParam(Object object, Annotation annotation) throws ParameterException {
        if (annotation == null) {
            throw new ParameterException(ExceptionCodeMsg.NOT_BLANK_CAST_ERROR);
        }
        NotBlank notBlank = (NotBlank) annotation;
        String msg = notBlank.msg();
        if (object == null) {
            throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, object);
        }
        if (object instanceof String) {
            if (AnyzmUtils.isBlank((String) object)) {
                throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, object);
            }
        }
    }

}
