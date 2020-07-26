package cn.anyzm.parameter.annotation;

import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.handler.ParameterCheckHandler;

import java.lang.annotation.*;

/**
 * @author huangzhaolai-jk
 * @version 0.0.1
 * @see ParameterCheckHandler
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Max {

  /** the value for the max *
   * @return the max value
   */
  double value();

  /**
   * the msg for not pass check
   *
   * @return String
   */
  String msg() default "the number which add Max annotation must be litter than max value";

  /**
   * the number can or can not be equals
   *
   * @return boolean
   */
  boolean canEquals() default ValueEnum.DEFAULT_CAN_EQUALS;

  /** the timing for use the annotation,you can defined multiple timing for different place *
   * @return the use timing
   */
  String[] timing() default ValueEnum.ALL_THE_TIME;
}
