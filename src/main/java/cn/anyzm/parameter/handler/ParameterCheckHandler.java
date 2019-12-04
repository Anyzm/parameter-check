package cn.anyzm.parameter.handler;

import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.utils.AnyzmUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0
 * @Description ParameterCheckHandler is used for
 * @Date 2019/10/12 - 11:06
 */
public class ParameterCheckHandler {

    /**
     *  check all the filed for a object with all timing
     * @param object
     * @throws Exception
     */
    public static void checkFiled(Object object)throws Exception{
        checkFiled(object,ValueEnum.EMPTY_STRING);
    }

    /**
     * check all the filed for a object with a timing
     *
     * @param object
     * @throws Exception
     */
    public static void checkFiled(Object object,String timing) throws Exception {
        if (object == null) {
            return;
        }
        //get all the object's filed
        Field[] declaredFields = object.getClass().getDeclaredFields();
        if (AnyzmUtils.isEmpty(declaredFields)) {
            // there is no declaredField
            return;
        }
        //check the field one by one
        checkOneFiled(object,timing, declaredFields);
    }

    /**
     * check filed of one object
     *
     * @param fields
     * @throws Exception
     */
    private static void checkOneFiled(Object object,String timing, Field... fields) throws Exception {
        if (fields == null || fields.length == ValueEnum.ZERO) {
            return;
        }
        for (Field field : fields) {
            field.setAccessible(true);
            // get the field all annotation
            Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
            if (AnyzmUtils.isDeepEmpty(declaredAnnotations)) {
                return;
            }
            for (Annotation annotation : declaredAnnotations) {
                if (annotation != null && Router.containsInCheckList(annotation.annotationType())) {
                    //check the filed for one annotation
                    checkForAnnotations(field, object, annotation,timing);
                }
            }
        }
    }

    /**
     * check one field for annotation list
     *
     * @param field
     * @param annotation
     */
    private static void checkForAnnotations(Field field, Object object, Annotation annotation,String timing) throws Exception {
        if (field == null || annotation == null) {
            return;
        }
        CheckHandler handler = Router.getHandler(annotation.annotationType());
        if (handler != null) {
            handler.checkFieldTemplate(field, object, annotation,timing);
        }
    }


}
