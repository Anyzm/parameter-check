package cn.anyzm.parameter.handler.impl;

import cn.anyzm.parameter.annotation.RegularMatch;
import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.exception.ParameterException;
import cn.anyzm.parameter.handler.AnnotationHandler;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0
 * @Description RegularMatchHandler is used for
 * @Date 2019/10/12 - 17:24
 */
public class RegularMatchHandler extends AnnotationHandler {

    @Override
    protected boolean isTiming(Annotation annotation, String timing) {
        if(annotation == null || !(annotation instanceof RegularMatch)){
            return false;
        }
        RegularMatch regularMatch = (RegularMatch) annotation;
        String[] annotationTiming = regularMatch.timing();
        return isTiming(timing,annotationTiming);
    }

    @Override
    public void checkField(Field field, Object object, Annotation annotation) throws Exception {
        if(field == null || annotation == null){
            return ;
        }
        field.setAccessible(true);
        RegularMatch regularMatch = (RegularMatch) annotation;
        String msg = regularMatch.msg();
        try {
            Object o = field.get(object);
            if( o == null ){
                throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE,msg,object);
            }else if(o instanceof String){

            }else{

            }

        } catch (IllegalAccessException e) {
            throw new ParameterException(msg);
        }
    }

    @Override
    public Map<String,String> checkObject(Object object, Annotation annotation, String timing) throws Exception {
        return null;
    }
}
