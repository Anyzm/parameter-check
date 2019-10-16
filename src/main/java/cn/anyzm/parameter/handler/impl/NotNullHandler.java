package cn.anyzm.parameter.handler.impl;

import cn.anyzm.parameter.annotation.NotNull;
import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.exception.ParameterException;
import cn.anyzm.parameter.handler.AnnotationHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0
 * @Description NotNullHandler is used for
 * @Date 2019/10/12 - 17:21
 */
public class NotNullHandler implements AnnotationHandler {

    @Override
    public void checkField(Field field, Object object, Annotation annotation) throws Exception {
        if(field == null || annotation == null){
            return ;
        }
        field.setAccessible(true);
        NotNull notNull = (NotNull) annotation;
        String msg = notNull.msg();
        try {
            Object o = field.get(object);
            if( o == null ){
                throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE,msg,object);
            }
        } catch (IllegalAccessException e) {
            throw new ParameterException(msg);
        }
    }

}
