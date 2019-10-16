package cn.anyzm.parameter.handler.impl;

import cn.anyzm.parameter.handler.AnnotationHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0
 * @Description RegularMatchHandler is used for
 * @Date 2019/10/12 - 17:24
 */
public class RegularMatchHandler implements AnnotationHandler {

    @Override
    public void checkField(Field field, Object object, Annotation annotation) throws Exception {
        if(field == null || annotation == null){
            return ;
        }
    }
}
