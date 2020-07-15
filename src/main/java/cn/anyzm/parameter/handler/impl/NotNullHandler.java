package cn.anyzm.parameter.handler.impl;

import cn.anyzm.parameter.annotation.NotNull;
import cn.anyzm.parameter.constant.ExceptionCodeMsg;
import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.exception.ParameterException;
import cn.anyzm.parameter.handler.AnnotationHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0 @Description NotNullHandler is used for @Date 2019/10/12 - 17:21
 */
public class NotNullHandler extends AnnotationHandler {

    @Override
    protected boolean isTiming(Annotation annotation, String timing) {
        if (!(annotation instanceof NotNull)) {
            return false;
        }
        NotNull notNull = (NotNull) annotation;
        String[] annotationTiming = notNull.timing();
        return isTiming(timing, annotationTiming);
    }

    @Override
    public void checkField(Field field, Object object, Annotation annotation)
            throws ParameterException {
        if (field == null || annotation == null) {
            return;
        }
        field.setAccessible(true);
        NotNull notNull = (NotNull) annotation;
        String msg = notNull.msg();
        try {
            Object o = field.get(object);
            if (o == null) {
                throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
            }
        } catch (IllegalAccessException e) {
            throw new ParameterException(e.getMessage());
        }
    }

    @Override
    protected String checkFieldForMsg(Field field, Object object, Annotation annotation)
            throws ParameterException {
        return null;
    }

    @Override
    public void checkOneParam(Object object, Annotation annotation) throws ParameterException {
        if (annotation == null) {
            throw new ParameterException(ExceptionCodeMsg.NOT_NULL_CAST_ERROR);
        }
        NotNull notNull = (NotNull) annotation;
        String msg = notNull.msg();
        if (object == null) {
            throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, object);
        }
    }
}
