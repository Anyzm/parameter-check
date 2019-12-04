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
@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface NotNull {

    /**
     * the msg for not pass check
     * @return String
     */
    String msg() default "the object which add NotNull annotation must not be null";

    /**
     *  the timing for use the annotation,you can defined multiple timing for different place
     */
    String[] timing() default ValueEnum.ALL_THE_TIME;


}
