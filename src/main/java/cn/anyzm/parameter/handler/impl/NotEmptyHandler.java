package cn.anyzm.parameter.handler.impl;

import cn.anyzm.parameter.annotation.NotEmpty;
import cn.anyzm.parameter.constant.ExceptionCodeMsg;
import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.exception.ParameterException;
import cn.anyzm.parameter.handler.AnnotationHandler;
import cn.anyzm.parameter.utils.AnyzmUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0 @Description NotEmptyHandler is used for @Date 2019/10/12 - 17:23
 */
public class NotEmptyHandler extends AnnotationHandler {

  @Override
  protected boolean isTiming(Annotation annotation, String timing) {
    if (!(annotation instanceof NotEmpty)) {
      return false;
    }
    NotEmpty notEmpty = (NotEmpty) annotation;
    String[] annotationTiming = notEmpty.timing();
    return isTiming(timing, annotationTiming);
  }

  @Override
  public void checkField(Field field, Object object, Annotation annotation)
      throws ParameterException {
    if (field == null || annotation == null) {
      return;
    }
    field.setAccessible(true);
    NotEmpty notEmpty = (NotEmpty) annotation;
    String msg = notEmpty.msg();
    try {
      Object o = field.get(object);
      if (o instanceof String) {
        String s = (String) o;
        if (AnyzmUtils.isEmpty(s)) {
          throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
        }
      } else if (o instanceof Collection) {
        Collection collection = (Collection) o;
        if (AnyzmUtils.isEmpty(collection)) {
          throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
        }
      } else if (o.getClass().isArray()) {
        Object[] objects = (Object[]) o;
        if (AnyzmUtils.isDeepEmpty(objects)) {
          throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
        }
      } else {
        throw new ParameterException(ExceptionCodeMsg.NOT_EMPTY_CAST_ERROR, field.getName());
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
