package cn.anyzm.parameter.handler;

import cn.anyzm.parameter.exception.ParameterException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0 @Description CheckHandler is used for @Date 2019/12/4 - 17:57
 */
public interface CheckHandler {

    /**
     * check field template function
     * @param field
     * @param object
     * @param annotation
     * @param timing
     * @throws ParameterException
     */
  void checkFieldTemplate(Field field, Object object, Annotation annotation, String timing)
      throws ParameterException;

  /**
   * check field for catch error message
   *
   * @param field
   * @param object
   * @param annotation
   * @param timing
   * @return
   * @throws ParameterException
   */
  String checkFieldForMsg(Field field, Object object, Annotation annotation, String timing)
      throws ParameterException;
}
