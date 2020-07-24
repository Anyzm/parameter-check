package cn.anyzm.parameter.handler.impl;

import cn.anyzm.parameter.annotation.AssertBoolean;
import cn.anyzm.parameter.constant.ExceptionCodeMsg;
import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.exception.ParameterException;
import cn.anyzm.parameter.handler.AnnotationHandler;
import cn.anyzm.parameter.utils.AnyzmUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0 @Description AssertBooleanHandler is used for @Date 2019/10/12 - 17:25
 */
public class AssertBooleanHandler extends AnnotationHandler {

    @Override
    protected boolean isTiming(Annotation annotation, String timing) {
        if (!(annotation instanceof AssertBoolean)) {
            return false;
        }
        AssertBoolean assertBoolean = (AssertBoolean) annotation;
        String[] annotationTiming = assertBoolean.timing();
        return isTiming(timing, annotationTiming);
    }

    @Override
    public void checkField(Field field, Object object, Annotation annotation)
            throws ParameterException {
        if (field == null || annotation == null) {
            return;
        }
        field.setAccessible(true);
        AssertBoolean assertBoolean = (AssertBoolean) annotation;
        String msg = assertBoolean.msg();
        try {
            Object o = field.get(object);
            if (o instanceof Boolean) {
                Boolean flag = (Boolean) o;
                if (flag != assertBoolean.value()) {
                    throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
                }
            } else {
                throw new ParameterException(ExceptionCodeMsg.ASSERT_BOOLEAN_CAST_ERROR, field.getName());
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
        AssertBoolean assertBoolean = (AssertBoolean) annotation;
        String msg = assertBoolean.msg();
        Object o = null;
        try {
            o = field.get(object);
        } catch (IllegalAccessException e) {
            throw new ParameterException(e.getMessage());
        }
        if (o instanceof Boolean) {
            Boolean flag = (Boolean) o;
            if (flag != assertBoolean.value()) {
                return msg;
            }
        } else {
            return msg;
        }
        return AnyzmUtils.emptyString();
    }

    @Override
    public void checkOneParam(Object object, Annotation annotation) throws ParameterException {
        if (annotation == null) {
            throw new ParameterException(ExceptionCodeMsg.ASSERT_BOOLEAN_CAST_ERROR);
        }
        if (object instanceof Boolean) {
            AssertBoolean assertBoolean = (AssertBoolean) annotation;
            String msg = assertBoolean.msg();
            if (!Objects.equals(assertBoolean.value(), object)) {
                throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, object);
            }
        } else {
            throw new ParameterException(ExceptionCodeMsg.ASSERT_BOOLEAN_CAST_ERROR, object);
        }
    }
}
