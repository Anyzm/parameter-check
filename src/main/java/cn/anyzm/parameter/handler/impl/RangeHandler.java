package cn.anyzm.parameter.handler.impl;

import cn.anyzm.parameter.annotation.Range;
import cn.anyzm.parameter.constant.ExceptionCodeMsg;
import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.exception.ParameterException;
import cn.anyzm.parameter.handler.AnnotationHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0 @Description RangeHandler is used for @Date 2019/10/12 - 17:24
 */
public class RangeHandler extends AnnotationHandler {

  @Override
  protected boolean isTiming(Annotation annotation, String timing) {
    if (!(annotation instanceof Range)) {
      return false;
    }
    Range range = (Range) annotation;
    String[] annotationTiming = range.timing();
    return isTiming(timing, annotationTiming);
  }

  @Override
  public void checkField(Field field, Object object, Annotation annotation)
      throws ParameterException {
    if (field == null || annotation == null) {
      return;
    }
    field.setAccessible(true);
    Range range = (Range) annotation;
    String msg = range.msg();
    try {
      Object o = field.get(object);
      if (o instanceof Number) {
        // Number type check
        Number number = (Number) o;
        boolean canMinEquals = range.canMinEquals();
        boolean canMaxEquals = range.canMaxEquals();
        if (canMinEquals) {
          if (number.doubleValue() < range.minValue()) {
            throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
          }
        } else {
          if (number.doubleValue() <= range.minValue()) {
            throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
          }
        }
        if (canMaxEquals) {
          if (number.doubleValue() > range.maxValue()) {
            throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
          }
        } else {
          if (number.doubleValue() >= range.maxValue()) {
            throw new ParameterException(ValueEnum.DEFAULT_ERROR_CODE, msg, field.getName());
          }
        }
      } else {
        throw new ParameterException(ExceptionCodeMsg.RANGE_CAST_ERROR, field.getName());
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
