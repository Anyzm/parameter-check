package cn.anyzm.parameter.handler;

import cn.anyzm.parameter.constant.ExceptionCodeMsg;
import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.exception.ParameterException;
import cn.anyzm.parameter.utils.AnyzmUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0 @Description ParameterCheckHandler is used for @Date 2019/10/12 - 11:06
 */
public class ParameterCheckHandler {

    /**
     * check all the filed for a object with all timing
     *
     * @param object
     * @throws Exception
     */
    public static void checkFiled(Object object) throws Exception {
        checkFiled(object, ValueEnum.EMPTY_STRING);
    }

    /**
     * check all the filed for a object with all timing
     *
     * @param object
     * @throws Exception
     */
    public static Map<String, String> checkFiledForError(Object object) throws Exception {
        return checkFiledForError(object, ValueEnum.EMPTY_STRING);
    }

    /**
     * check all the filed for a object with a timing
     *
     * @param object
     * @param timing
     * @throws Exception
     */
    public static void checkFiled(Object object, String timing) throws Exception {
        if (object == null) {
            throw new ParameterException(ExceptionCodeMsg.NULL);
        }
        // get all the object's filed
        Field[] declaredFields = object.getClass().getDeclaredFields();
        // check the field one by one
        checkFileds(object, timing, declaredFields);
    }

    /**
     * check all the filed for a object with a timing
     *
     * @param object
     * @param timing
     * @throws Exception
     */
    public static Map<String, String> checkFiledForError(Object object, String timing) throws Exception {
        if (object == null) {
            throw new ParameterException(ExceptionCodeMsg.NULL);
        }
        // get all the object's filed
        Field[] declaredFields = object.getClass().getDeclaredFields();
        // check the field one by one
        return checkFiledsForError(object, timing, declaredFields);
    }

    /**
     * check all the filed for a object with a timing
     *
     * @param object
     * @param timing
     * @throws Exception
     */
    public static void checkFiled(Object object, Annotation annotation, String timing)
            throws Exception {
        if (object == null) {
            throw new ParameterException(ExceptionCodeMsg.NULL);
        }
        // get all the object's filed
        Field[] declaredFields = object.getClass().getDeclaredFields();
        // check the field one by one
        checkFileds(object, annotation, timing, declaredFields);
    }

    /**
     * check all the filed for a object with a timing
     *
     * @param object
     * @param timing
     * @throws Exception
     */
    public static Map<String, String> checkFiledForError(Object object, Annotation annotation, String timing)
            throws Exception {
        if (object == null) {
            throw new ParameterException(ExceptionCodeMsg.NULL);
        }
        // get all the object's filed
        Field[] declaredFields = object.getClass().getDeclaredFields();
        // check the field one by one
        return checkFiledsForError(object, annotation, timing, declaredFields);
    }

    /**
     * check fileds of one object
     *
     * @param object
     * @param timing
     * @param fields
     * @throws Exception
     */
    public static void checkFileds(
            Object object, Annotation annotation, String timing, Field... fields) throws Exception {
        if (AnyzmUtils.isEmpty(fields)) {
            return;
        }
        if (annotation == null || !Router.containsInCheckList(annotation.annotationType())) {
            throw new ParameterException(ExceptionCodeMsg.ANNOTATION_INVALID);
        }
        for (Field field : fields) {
            field.setAccessible(true);
            // get the field all annotation
            Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
            if (AnyzmUtils.isDeepEmpty(declaredAnnotations)
                    || !AnyzmUtils.copyArrayToList(declaredAnnotations).contains(annotation)) {
                continue;
            }
            // check the filed for one annotation
            checkForAnnotations(object, annotation, timing, field);
        }
    }

    /**
     * check fileds of one object
     *
     * @param object
     * @param timing
     * @param fields
     * @throws Exception
     */
    public static Map<String, String> checkFiledsForError(
            Object object, Annotation annotation, String timing, Field... fields) throws Exception {
        if (AnyzmUtils.isEmpty(fields)) {
            return Collections.EMPTY_MAP;
        }
        if (annotation == null || !Router.containsInCheckList(annotation.annotationType())) {
            throw new ParameterException(ExceptionCodeMsg.ANNOTATION_INVALID);
        }
        Map<String, String> result = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            // get the field all annotation
            Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
            if (AnyzmUtils.isDeepEmpty(declaredAnnotations)
                    || !AnyzmUtils.copyArrayToList(declaredAnnotations).contains(annotation)) {
                continue;
            }
            // check the filed for one annotation
            Map<String, String> msgMap = checkForAnnotationForError(object, annotation, timing, field);
            if (!AnyzmUtils.isEmpty(msgMap)) {
                result.putAll(msgMap);
            }
        }
        return result;
    }

    /**
     * check fileds of one object
     *
     * @param object
     * @param timing
     * @param fields
     * @throws Exception
     */
    public static void checkFileds(Object object, String timing, Field... fields) throws Exception {
        if (AnyzmUtils.isEmpty(fields)) {
            return;
        }
        for (Field field : fields) {
            field.setAccessible(true);
            // get the field all annotation
            Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
            if (AnyzmUtils.isDeepEmpty(declaredAnnotations)) {
                continue;
            }
            for (Annotation annotation : declaredAnnotations) {
                if (annotation != null && Router.containsInCheckList(annotation.annotationType())) {
                    // check the filed for one annotation
                    checkForAnnotations(object, annotation, timing, field);
                }
            }
        }
    }

    /**
     * check fileds of one object
     *
     * @param object
     * @param timing
     * @param fields
     * @throws Exception
     */
    public static Map<String, String> checkFiledsForError(Object object, String timing, Field... fields) throws Exception {
        if (AnyzmUtils.isEmpty(fields)) {
            return Collections.EMPTY_MAP;
        }
        Map<String, String> result = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            // get the field all annotation
            Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
            if (AnyzmUtils.isDeepEmpty(declaredAnnotations)) {
                continue;
            }
            for (Annotation annotation : declaredAnnotations) {
                if (annotation != null && Router.containsInCheckList(annotation.annotationType())) {
                    // check the filed for one annotation
                    Map<String, String> msgMap = checkForAnnotationForError(object, annotation, timing, field);
                    if (!AnyzmUtils.isEmpty(msgMap)) {
                        result.putAll(msgMap);
                    }
                }
            }
        }
        return result;
    }

    /**
     * check one field for annotation list
     *
     * @param object
     * @param annotation
     * @param timing
     * @param fields
     * @throws Exception
     */
    private static void checkForAnnotations(
            Object object, Annotation annotation, String timing, Field... fields) throws Exception {
        if (object == null) {
            throw new ParameterException(ExceptionCodeMsg.NULL);
        }
        if (AnyzmUtils.isEmpty(fields) || annotation == null) {
            return;
        }
        CheckHandler handler = Router.getHandler(annotation.annotationType());
        if (handler != null) {
            for (Field field : fields) {
                handler.checkFieldTemplate(field, object, annotation, timing);
            }
        }
    }

    /**
     * check one field for annotation list
     *
     * @param object
     * @param annotation
     * @param timing
     * @param fields
     * @throws Exception
     */
    private static Map<String, String> checkForAnnotationForError(
            Object object, Annotation annotation, String timing, Field... fields) throws Exception {
        if (object == null) {
            throw new ParameterException(ExceptionCodeMsg.NULL);
        }
        if (AnyzmUtils.isEmpty(fields) || annotation == null) {
            return Collections.EMPTY_MAP;
        }
        CheckHandler handler = Router.getHandler(annotation.annotationType());
        Map<String, String> result = new HashMap<>((int) ((float) fields.length / 0.75F + 1.0F));
        if (handler != null) {
            for (Field field : fields) {
                String msg = handler.checkFieldForMsg(field, object, annotation, timing);
                if (!AnyzmUtils.isBlank(msg)) {
                    result.put(field.getName(), msg);
                }
            }
            return result;
        }
        return result;
    }
}
