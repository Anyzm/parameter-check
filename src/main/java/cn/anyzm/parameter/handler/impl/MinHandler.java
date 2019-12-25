package cn.anyzm.parameter.handler.impl;

import cn.anyzm.parameter.annotation.Min;
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
 * @Description MinHandler is used for
 * @Date 2019/10/12 - 17:24
 */
public class MinHandler extends AnnotationHandler {

    @Override
    protected boolean isTiming(Annotation annotation, String timing) {
        if(annotation == null || !(annotation instanceof Min)){
            return false;
        }
        Min min = (Min) annotation;
        String[] annotationTiming = min.timing();
        return isTiming(timing,annotationTiming);
    }

    @Override
    public void checkField(Field field, Object object, Annotation annotation) throws ParameterException{
        if (field == null || annotation == null) {
            return;
        }
        field.setAccessible(true);
        Min min = (Min) annotation;
        String msg = min.msg();
        try {
            Object o = field.get(object);
            if (o == null) {
                return ;
            } else if (o instanceof Number) {
                //Number type check
                Number number = (Number) o;
                boolean canEquals = min.canEquals();
                if(canEquals){
                    if(number.doubleValue() < min.value()){
                        throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
                    }
                }else{
                    if(number.doubleValue() <= min.value()){
                        throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
                    }
                }
            } else {
                throw new ParameterException(ExceptionCodeMsg.MIN_CAST_ERROR, field.getName());
            }

        } catch (IllegalAccessException e) {
            throw new ParameterException(msg);
        }
    }

}
