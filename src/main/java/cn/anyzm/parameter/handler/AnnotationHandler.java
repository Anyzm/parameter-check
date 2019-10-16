package cn.anyzm.parameter.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0
 * @Description AnnotationHandler is used for
 * @Date 2019/10/12 - 17:26
 */
public interface AnnotationHandler {
    /**
     * one field check for one annotation
     * @param field
     * @param object
     * @param annotation
     */
    void checkField(Field field,Object object, Annotation annotation) throws Exception;

}
