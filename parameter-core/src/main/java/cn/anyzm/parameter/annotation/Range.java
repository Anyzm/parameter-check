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
public @interface Range {

  /** the value for min value *
   * @return the min value
   */
  double minValue();

  /** the value for max value *
   * @return the max value
   */
  double maxValue();

  /**
   * the min value can or can not be equals
   *
   * @return boolean
   */
  boolean canMinEquals() default ValueEnum.DEFAULT_CAN_EQUALS;

  /**
   * the max value can or can not be equals
   *
   * @return boolean
   */
  boolean canMaxEquals() default ValueEnum.DEFAULT_CAN_EQUALS;

  /**
   * the msg for not pass check
   *
   * @return String
   */
  String msg() default "the number which add Range annotation must be in the min and max range";

  /** the timing for use the annotation,you can defined multiple timing for different place *
   * @return the timing to use
   */
  String[] timing() default ValueEnum.ALL_THE_TIME;
}
