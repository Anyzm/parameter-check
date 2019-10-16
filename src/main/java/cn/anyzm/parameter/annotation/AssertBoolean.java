package cn.anyzm.parameter.annotation;

import cn.anyzm.parameter.constant.ValueEnum;
import cn.anyzm.parameter.handler.ParameterCheckHandler;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author huangzhaolai-jk
 * @time 2019-10-12
 * @version 0.0.1
 * @see ParameterCheckHandler
 */
@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AssertBoolean {

    /**
     * the msg for not pass check
     * @return String
     */
    String msg() default "the object which add AssertBoolean annotation must be match the value";

    /**
     * the value for match the boolean
     */
    boolean value() default ValueEnum.ASSERT_BOOLEAN_DEFAULT_VALUE;

    /**
     *  the timing for use the annotation,you can defined multiple timing for different place
     */
    String[] timing() default ValueEnum.EMPTY_STRING;


}
