package cn.anyzm.parameter.handler.impl;

import cn.anyzm.parameter.annotation.RegularMatch;
import cn.anyzm.parameter.constant.ExceptionCodeMsg;
import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.exception.ParameterException;
import cn.anyzm.parameter.handler.AnnotationHandler;
import cn.anyzm.parameter.utils.AnyzmUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.regex.Pattern;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0 @Description RegularMatchHandler is used for @Date 2019/10/12 - 17:24
 */
public class RegularMatchHandler extends AnnotationHandler {

    @Override
    protected boolean isTiming(Annotation annotation, String timing) {
        if (!(annotation instanceof RegularMatch)) {
            return false;
        }
        RegularMatch regularMatch = (RegularMatch) annotation;
        String[] annotationTiming = regularMatch.timing();
        return isTiming(timing, annotationTiming);
    }

    @Override
    public void checkField(Field field, Object object, Annotation annotation)
            throws ParameterException {
        if (field == null || annotation == null) {
            return;
        }
        field.setAccessible(true);
        RegularMatch regularMatch = (RegularMatch) annotation;
        String msg = regularMatch.msg();
        try {
            Object o = field.get(object);
            if (o == null) {
                throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
            } else if (o instanceof String) {
                String s = (String) o;
                boolean matches = Pattern.matches(regularMatch.regular(), s);
                if (!matches) {
                    throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
                }
            } else {
                throw new ParameterException(ExceptionCodeMsg.REGULAR_MATCH_CAST_ERROR, field.getName());
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
        RegularMatch regularMatch = (RegularMatch) annotation;
        String msg = regularMatch.msg();
        try {
            Object o = field.get(object);
            if (o == null) {
                return msg;
            } else if (o instanceof String) {
                String s = (String) o;
                boolean matches = Pattern.matches(regularMatch.regular(), s);
                if (!matches) {
                    return msg;
                }
            } else {
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
            throw new ParameterException(ExceptionCodeMsg.REGULAR_MATCH_CAST_ERROR);
        }
        RegularMatch regularMatch = (RegularMatch) annotation;
        String msg = regularMatch.msg();
        if (object == null) {
            throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, object);
        } else if (object instanceof String) {
            String s = (String) object;
            boolean matches = Pattern.matches(regularMatch.regular(), s);
            if (!matches) {
                throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, object);
            }
        } else {
            throw new ParameterException(ExceptionCodeMsg.REGULAR_MATCH_CAST_ERROR, object);
        }
    }
}
