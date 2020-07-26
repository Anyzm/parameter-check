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
public @interface NotBlank {

  /**
   * the msg for not pass check
   *
   * @return String
   */
  String msg() default "the object which add NotNull annotation must not be blank";

  /** the timing for use the annotation,you can defined multiple timing for different place *
   * @return the use timing
   */
  String[] timing() default ValueEnum.ALL_THE_TIME;
}
