package cn.anyzm.parameter.handler.impl;

import cn.anyzm.parameter.annotation.AssertBoolean;
import cn.anyzm.parameter.annotation.Max;
import cn.anyzm.parameter.constant.ExceptionCodeMsg;
import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.exception.ParameterException;
import cn.anyzm.parameter.handler.AnnotationHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0
 * @Description AssertBooleanHandler is used for
 * @Date 2019/10/12 - 17:25
 */
public class AssertBooleanHandler extends AnnotationHandler {

    @Override
    protected boolean isTiming(Annotation annotation, String timing) {
        if(annotation == null || !(annotation instanceof AssertBoolean)){
            return false;
        }
        AssertBoolean assertBoolean = (AssertBoolean) annotation;
        String[] annotationTiming = assertBoolean.timing();
        return isTiming(timing,annotationTiming);
    }

    @Override
    public void checkField(Field field, Object object, Annotation annotation) throws Exception {
        if (field == null || annotation == null) {
            return;
        }
        field.setAccessible(true);
        AssertBoolean assertBoolean = (AssertBoolean) annotation;
        String msg = assertBoolean.msg();
        try {
            Object o = field.get(object);
            if (o == null) {
                throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, object);
            } else if (o instanceof Boolean) {
                Boolean flag = (Boolean) o;
                if (flag != assertBoolean.value()) {
                    throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, object);
                }
            } else {
                throw new ParameterException(ExceptionCodeMsg.ASSERT_BOOLEAN_CAST_ERROR, field.getName());
            }

        } catch (IllegalAccessException e) {
            throw new ParameterException(msg);
        }

    }

    @Override
    public Map<String,String> checkObject(Object object, Annotation annotation, String timing) throws Exception {
        if (object != null && object instanceof Boolean && annotation != null) {
            Max max = (Max) annotation;
//            max.canEquals()
//            return (Boolean) object;
        }
        return null;
    }
}
