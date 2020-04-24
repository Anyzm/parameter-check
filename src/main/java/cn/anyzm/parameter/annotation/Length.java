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
public @interface Length {

  /** the value for the min length */
  int minLength() default ValueEnum.MINUS_ONE;

  /** the value for the max length */
  int maxLength() default Integer.MAX_VALUE;

  /**
   * the msg for not pass check
   *
   * @return String
   */
  String msg() default "the number which add Max annotation must be litter than max value";

  /** the timing for use the annotation,you can defined multiple timing for different place */
  String[] timing() default ValueEnum.ALL_THE_TIME;
}
