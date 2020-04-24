package cn.anyzm.parameter.handler.impl;

import cn.anyzm.parameter.annotation.NotBlank;
import cn.anyzm.parameter.constant.ExceptionCodeMsg;
import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.exception.ParameterException;
import cn.anyzm.parameter.handler.AnnotationHandler;
import cn.anyzm.parameter.utils.AnyzmUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0 @Description NotNullHandler is used for @Date 2019/10/12 - 17:21
 */
public class NotBlankHandler extends AnnotationHandler {

  @Override
  protected boolean isTiming(Annotation annotation, String timing) {
    if (!(annotation instanceof NotBlank)) {
      return false;
    }
    NotBlank notBlank = (NotBlank) annotation;
    String[] annotationTiming = notBlank.timing();
    return isTiming(timing, annotationTiming);
  }

  @Override
  public void checkField(Field field, Object object, Annotation annotation)
      throws ParameterException {
    if (field == null || annotation == null) {
      return;
    }
    field.setAccessible(true);
    NotBlank notBlank = (NotBlank) annotation;
    String msg = notBlank.msg();
    try {
      Object o = field.get(object);
      if (o instanceof String) {
        String s = (String) o;
        if (AnyzmUtils.isBlank(s)) {
          throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
        }
      } else {
        throw new ParameterException(ExceptionCodeMsg.NOT_BLANK_CAST_ERROR, field.getName());
      }
    } catch (IllegalAccessException e) {
      throw new ParameterException(msg);
    }
  }

  @Override
  protected String checkFieldForMsg(Field field, Object object, Annotation annotation)
      throws ParameterException {
    return null;
  }
}
