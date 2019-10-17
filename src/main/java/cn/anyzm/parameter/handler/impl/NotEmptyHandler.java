package cn.anyzm.parameter.handler.impl;

import cn.anyzm.parameter.handler.AnnotationHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0
 * @Description NotEmptyHandler is used for
 * @Date 2019/10/12 - 17:23
 */
public class NotEmptyHandler implements AnnotationHandler {

    @Override
    public void checkField(Field field, Object object, Annotation annotation,String timing) throws Exception{
        if(field == null || annotation == null){
            return ;
        }
    }

    @Override
    public boolean checkObject(Object object, Annotation annotation, String timing) throws Exception {
        return false;
    }
}
