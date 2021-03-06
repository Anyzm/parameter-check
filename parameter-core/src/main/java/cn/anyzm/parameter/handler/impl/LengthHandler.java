package cn.anyzm.parameter.handler.impl;

import cn.anyzm.parameter.annotation.Length;
import cn.anyzm.parameter.constant.ExceptionCodeMsg;
import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.exception.ParameterException;
import cn.anyzm.parameter.handler.AnnotationHandler;
import cn.anyzm.parameter.utils.AnyzmUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0 @Description LengthHandler is used for @Date 2019/12/4 - 16:07
 */
public class LengthHandler extends AnnotationHandler {

    @Override
    protected boolean isTiming(Annotation annotation, String timing) {
        if (!(annotation instanceof Length)) {
            return false;
        }
        Length length = (Length) annotation;
        String[] annotationTiming = length.timing();
        return isTiming(timing, annotationTiming);
    }

    @Override
    public void checkField(Field field, Object object, Annotation annotation)
            throws ParameterException {
        if (field == null || annotation == null) {
            return;
        }
        field.setAccessible(true);
        Length length = (Length) annotation;
        String msg = length.msg();
        try {
            Object o = field.get(object);
            if (o == null) {
                if (length.minLength() != ValueEnum.MINUS_ONE) {
                    throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
                }
            } else if (o instanceof String) {
                // String type check length
                String s = (String) o;
                if (s.length() < length.maxLength() || s.length() > length.maxLength()) {
                    throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
                }
            } else if (o instanceof Collection) {
                // Collection type check size
                Collection collection = (Collection) o;
                if (collection.size() < length.minLength() || collection.size() > length.maxLength()) {
                    throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
                }
            } else if (o.getClass().isArray()) {
                Object[] objects = (Object[]) o;
                if (objects.length < length.minLength() || objects.length > length.maxLength()) {
                    throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
                }
            } else {
                throw new ParameterException(ExceptionCodeMsg.LENGTH_CAST_ERROR, field.getName());
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
        Length length = (Length) annotation;
        String msg = length.msg();
        try {
            Object o = field.get(object);
            if (o == null) {
                if (length.minLength() != ValueEnum.MINUS_ONE) {
                    return msg;
                }
            } else if (o instanceof String) {
                // String type check length
                String s = (String) o;
                if (s.length() < length.maxLength() || s.length() > length.maxLength()) {
                    return msg;
                }
            } else if (o instanceof Collection) {
                // Collection type check size
                Collection collection = (Collection) o;
                if (collection.size() < length.minLength() || collection.size() > length.maxLength()) {
                    return msg;
                }
            } else if (o.getClass().isArray()) {
                Object[] objects = (Object[]) o;
                if (objects.length < length.minLength() || objects.length > length.maxLength()) {
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
            throw new ParameterException(ExceptionCodeMsg.LENGTH_CAST_ERROR);
        }
        Length length = (Length) annotation;
        String msg = length.msg();
        if (object == null) {
            if (length.minLength() != ValueEnum.MINUS_ONE) {
                throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, object);
            }
        } else if (object instanceof String) {
            // String type check length
            String s = (String) object;
            if (s.length() < length.maxLength() || s.length() > length.maxLength()) {
                throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, object);
            }
        } else if (object instanceof Collection) {
            // Collection type check size
            Collection collection = (Collection) object;
            if (collection.size() < length.minLength() || collection.size() > length.maxLength()) {
                throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, object);
            }
        } else if (object.getClass().isArray()) {
            Object[] objects = (Object[]) object;
            if (objects.length < length.minLength() || objects.length > length.maxLength()) {
                throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, object);
            }
        } else {
            throw new ParameterException(ExceptionCodeMsg.LENGTH_CAST_ERROR, object);
        }
    }
}
