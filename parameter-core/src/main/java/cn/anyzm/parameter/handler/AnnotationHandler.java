package cn.anyzm.parameter.handler;

import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.exception.ParameterException;
import cn.anyzm.parameter.utils.AnyzmUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0 @Description AnnotationHandler is used for @Date 2019/10/12 - 17:26
 */
public abstract class AnnotationHandler implements CheckHandler {

  @Override
  public final void checkFieldTemplate(
      Field field, Object object, Annotation annotation, String timing) throws ParameterException {
    if (isTiming(annotation, timing)) {
      checkField(field, object, annotation);
    }
  }

  @Override
  public String checkFieldForMsg(Field field, Object object, Annotation annotation, String timing) throws ParameterException {
      if (isTiming(annotation, timing)) {
          return checkFieldForMsg(field, object, annotation);
      }
      return null;
  }

  protected abstract boolean isTiming(Annotation annotation, String timing);

  /**
   * one field check for one annotation
   *
   * @param field the check field
   * @param object the check object
   * @param annotation the check annotation
   */
  protected abstract void checkField(Field field, Object object, Annotation annotation)
      throws ParameterException;

    /**
     * one field check for one annotation and return error message
     * @param field the check field
     * @param object the check object
     * @param annotation the check annotation
     * @return error massage
     * @throws ParameterException exception
     */
  protected abstract String checkFieldForMsg(Field field, Object object, Annotation annotation)
            throws ParameterException;

  protected boolean isTiming(String fieldTiming, String... annotationTiming) {
    if (AnyzmUtils.isEmpty(annotationTiming)) {
      return true;
    }
    if (Arrays.asList(annotationTiming).contains(ValueEnum.ALL_THE_TIME)
        || Objects.equals(fieldTiming, ValueEnum.ALL_THE_TIME)) {
      return true;
    } else {
      return Arrays.asList(annotationTiming).contains(fieldTiming);
    }
  }
}
