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
     * @param field the check field
     * @param object the check object
     * @param annotation the check annotation
     * @param timing the timing to check
     * @throws ParameterException exception
     */
  void checkFieldTemplate(Field field, Object object, Annotation annotation, String timing)
      throws ParameterException;

    /**
     * 判断一个参数是否有效
     * @param object the check object
     * @param annotation the check annotation
     * @throws ParameterException exception
     */
  void checkOneParam(Object object, Annotation annotation)throws ParameterException;

  /**
   * check field for catch error message
   *
   * @param field the check field
   * @param object the check object
   * @param annotation the check annotation
   * @param timing the timing to check
   * @return error massage
   * @throws ParameterException exception
   */
  String checkFieldForMsg(Field field, Object object, Annotation annotation, String timing)
      throws ParameterException;

}
