package cn.anyzm.parameter.handler;

import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.exception.ParameterException;
import cn.anyzm.parameter.utils.AnyzmUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0
 * @Description AnnotationHandler is used for
 * @Date 2019/10/12 - 17:26
 */
public abstract class AnnotationHandler implements CheckHandler{

    @Override
    public final void checkFieldTemplate(Field field, Object object, Annotation annotation, String timing) throws ParameterException {
        if(isTiming(annotation,timing)){
            checkField(field,object,annotation);
        }
    }

    protected abstract boolean isTiming(Annotation annotation,String timing);

    /**
     * one field check for one annotation
     * @param field
     * @param object
     * @param annotation
     */
    protected abstract void checkField(Field field,Object object, Annotation annotation) throws ParameterException;

    /**
     * to check a object for return true pass and return false not pass
     * @param object
     * @return
     * @throws Exception
     */
    protected abstract Map<String,String> checkObject(Object object, Annotation annotation, String timing)throws ParameterException;

    protected boolean isTiming(String fieldTiming,String... annotationTiming){
        if(AnyzmUtils.isEmpty(annotationTiming)){
            return true;
        }
        if(Arrays.asList(annotationTiming).contains( ValueEnum.ALL_THE_TIME) || Objects.equals(fieldTiming, ValueEnum.ALL_THE_TIME)){
            return true;
        }else {
            return Arrays.asList(annotationTiming).contains(fieldTiming);
        }
    }

}
