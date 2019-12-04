package cn.anyzm.parameter.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0
 * @Description CheckHandler is used for
 * @Date 2019/12/4 - 17:57
 */
public interface CheckHandler {

    /**
     * check field template function
     * @param field
     * @param object
     * @param annotation
     * @param timing
     */
    void checkFieldTemplate(Field field, Object object, Annotation annotation, String timing) throws Exception;

}
