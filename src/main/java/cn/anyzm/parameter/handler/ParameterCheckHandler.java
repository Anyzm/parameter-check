package cn.anyzm.parameter.handler;

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
     * check all the filed for a object
     * @param object
     * @throws Exception
     */
    public static void checkFiled(Object object) throws Exception{
        if(object == null){
            return;
        }
        //get all the object's filed
        Field[] declaredFields = object.getClass().getDeclaredFields();
        if(AnyzmUtils.isEmpty(declaredFields)){
            // there is no declaredField
            return ;
        }
        for(Field field : declaredFields){
            //check the field one by one
            checkOneFiled(field,object);
        }

    }

    /**
     * check one filed of one object
     * @param field
     * @throws Exception
     */
    private static void checkOneFiled(Field field,Object object)throws Exception{
        if(field == null){
            return ;
        }
        field.setAccessible(true);
        // get the field all annotation
        Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
        if(AnyzmUtils.isDeepEmpty(declaredAnnotations)){
            return ;
        }
        for(Annotation annotation : declaredAnnotations){
            if(annotation != null && Router.containsInCheckList(annotation.annotationType())){
                //check the filed for one annotation
                checkForAnnotations(field,object,annotation);
            }
        }
    }

    /**
     * check one field for annotation list
     * @param field
     * @param annotation
     */
    private static void checkForAnnotations(Field field,Object object,Annotation annotation) throws Exception {
        if(field == null || annotation == null){
            return ;
        }
        AnnotationHandler handler = Router.getHandler(annotation.annotationType());
        if(handler != null) {
            handler.checkField(field, object, annotation);
        }
    }


}
