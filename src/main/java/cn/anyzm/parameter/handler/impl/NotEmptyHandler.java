package cn.anyzm.parameter.handler.impl;

import cn.anyzm.parameter.annotation.NotEmpty;
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
 * @version 1.0.0 @Description NotEmptyHandler is used for @Date 2019/10/12 - 17:23
 */
public class NotEmptyHandler extends AnnotationHandler {

    @Override
    protected boolean isTiming(Annotation annotation, String timing) {
        if (!(annotation instanceof NotEmpty)) {
            return false;
        }
        NotEmpty notEmpty = (NotEmpty) annotation;
        String[] annotationTiming = notEmpty.timing();
        return isTiming(timing, annotationTiming);
    }

    @Override
    public void checkField(Field field, Object object, Annotation annotation)
            throws ParameterException {
        if (field == null || annotation == null) {
            return;
        }
        field.setAccessible(true);
        NotEmpty notEmpty = (NotEmpty) annotation;
        String msg = notEmpty.msg();
        try {
            Object o = field.get(object);
            if(object == null){
                throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, object);
            }
            if (o instanceof String) {
                String s = (String) o;
                if (AnyzmUtils.isEmpty(s)) {
                    throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
                }
            } else if (o instanceof Collection) {
                Collection collection = (Collection) o;
                if (AnyzmUtils.isEmpty(collection)) {
                    throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
                }
            } else if (o.getClass().isArray()) {
                Object[] objects = (Object[]) o;
                if (AnyzmUtils.isDeepEmpty(objects)) {
                    throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
                }
            } else {
                throw new ParameterException(ExceptionCodeMsg.NOT_EMPTY_CAST_ERROR, field.getName());
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
        NotEmpty notEmpty = (NotEmpty) annotation;
        String msg = notEmpty.msg();
        try {
            Object o = field.get(object);
            if(object == null){
                return msg;
            }
            if (o instanceof String) {
                String s = (String) o;
                if (AnyzmUtils.isEmpty(s)) {
                    return msg;
                }
            } else if (o instanceof Collection) {
                Collection collection = (Collection) o;
                if (AnyzmUtils.isEmpty(collection)) {
                    return msg;
                }
            } else if (o.getClass().isArray()) {
                Object[] objects = (Object[]) o;
                if (AnyzmUtils.isDeepEmpty(objects)) {
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
            throw new ParameterException(ExceptionCodeMsg.NOT_EMPTY_CAST_ERROR);
        }
        NotEmpty notEmpty = (NotEmpty) annotation;
        String msg = notEmpty.msg();
        if(object == null){
            throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, object);
        }
        if (object instanceof String) {
            String s = (String) object;
            if (AnyzmUtils.isEmpty(s)) {
                throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, object);
            }
        } else if (object instanceof Collection) {
            Collection collection = (Collection) object;
            if (AnyzmUtils.isEmpty(collection)) {
                throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, object);
            }
        } else if (object.getClass().isArray()) {
            Object[] objects = (Object[]) object;
            if (AnyzmUtils.isDeepEmpty(objects)) {
                throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, object);
            }
        } else {
            throw new ParameterException(ExceptionCodeMsg.NOT_EMPTY_CAST_ERROR, object);
        }
    }
}
