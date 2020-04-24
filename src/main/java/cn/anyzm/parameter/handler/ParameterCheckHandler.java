package cn.anyzm.parameter.handler;

import cn.anyzm.parameter.constant.ExceptionCodeMsg;
import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.exception.ParameterException;
import cn.anyzm.parameter.utils.AnyzmUtils;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Any;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;

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
  public static void checkFileds(Object object, String timing, Field... fields) throws Exception {
    if (AnyzmUtils.isEmpty(fields)) {
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
          // check the filed for one annotation
          checkForAnnotations(object, annotation, timing, field);
        }
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
}
