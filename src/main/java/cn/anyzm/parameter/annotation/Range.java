package cn.anyzm.parameter.annotation;

import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.handler.ParameterCheckHandler;

import java.lang.annotation.*;

/**
 * @author huangzhaolai-jk
 * @time 2019-10-12
 * @version 0.0.1
 * @see ParameterCheckHandler
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Range {

  /** the value for min value */
  double minValue();

  /** the value for max value */
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

  /** the timing for use the annotation,you can defined multiple timing for different place */
  String[] timing() default ValueEnum.ALL_THE_TIME;
}
