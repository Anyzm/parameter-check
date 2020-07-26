package cn.anyzm.parameter.annotation;

import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.handler.ParameterCheckHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author huangzhaolai-jk
 * @version 0.0.1
 * @see ParameterCheckHandler
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Verify {

  /**
   * the timing or version for check
   *
   * @return the check timing
   */
  String timing() default ValueEnum.ALL_THE_TIME;
}
