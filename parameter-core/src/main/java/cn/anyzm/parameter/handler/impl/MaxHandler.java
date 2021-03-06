package cn.anyzm.parameter.handler.impl;

import cn.anyzm.parameter.annotation.Max;
import cn.anyzm.parameter.constant.ExceptionCodeMsg;
import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.exception.ParameterException;
import cn.anyzm.parameter.handler.AnnotationHandler;
import cn.anyzm.parameter.utils.AnyzmUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0 @Description MaxHandler is used for @Date 2019/10/12 - 17:24
 */
public class MaxHandler extends AnnotationHandler {

    @Override
    protected boolean isTiming(Annotation annotation, String timing) {
        if (!(annotation instanceof Max)) {
            return false;
        }
        Max max = (Max) annotation;
        String[] annotationTiming = max.timing();
        return isTiming(timing, annotationTiming);
    }

    @Override
    public void checkField(Field field, Object object, Annotation annotation)
            throws ParameterException {
        if (field == null || annotation == null) {
            return;
        }
        field.setAccessible(true);
        Max max = (Max) annotation;
        String msg = max.msg();
        try {
            Object o = field.get(object);
            if (o instanceof Number) {
                // Number type check
                Number number = (Number) o;
                boolean canEquals = max.canEquals();
                if (canEquals) {
                    if (number.doubleValue() > max.value()) {
                        throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
                    }
                } else {
                    if (number.doubleValue() >= max.value()) {
                        throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
                    }
                }
            } else {
                if (o == null) {
                    return;
                }
                throw new ParameterException(ExceptionCodeMsg.MAX_CAST_ERROR, field.getName());
            }

        } catch (IllegalAccessException e) {
            throw new ParameterException(e.getMessage());
        }
    }

    @Override
    protected String checkFieldForMsg(Field field, Object object, Annotation annotation)
            throws ParameterException {
        if (field == null || annotation == null) {
            return ValueEnum.EMPTY_STRING;
        }
        field.setAccessible(true);
        Max max = (Max) annotation;
        String msg = max.msg();
        try {
            Object o = field.get(object);
            if (o instanceof Number) {
                // Number type check
                Number number = (Number) o;
                boolean canEquals = max.canEquals();
                if (canEquals) {
                    if (number.doubleValue() > max.value()) {
                        return msg;
                    }
                } else {
                    if (number.doubleValue() >= max.value()) {
                        return msg;
                    }
                }
            } else {
                if (o == null) {
                    return ValueEnum.EMPTY_STRING;
                }
                return msg;
            }

        } catch (IllegalAccessException e) {
            throw new ParameterException(e.getMessage());
        }
        return AnyzmUtils.emptyString();
    }

    @Override
    public void checkOneParam(Object object, Annotation annotation) throws ParameterException {
        if (annotation == null) {
            throw new ParameterException(ExceptionCodeMsg.MAX_CAST_ERROR);
        }
        Max max = (Max) annotation;
        String msg = max.msg();
        if (object instanceof Number) {
            // Number type check
            Number number = (Number) object;
            boolean canEquals = max.canEquals();
            if (canEquals) {
                if (number.doubleValue() > max.value()) {
                    throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, object);
                }
            } else {
                if (number.doubleValue() >= max.value()) {
                    throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, object);
                }
            }
        } else {
            if (object == null) {
                return;
            }
            throw new ParameterException(ExceptionCodeMsg.MAX_CAST_ERROR, object);
        }
    }
}
