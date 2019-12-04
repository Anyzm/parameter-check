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
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Verify {

    /**
     * the timing or version for check
     * @return
     */
    String timing() default ValueEnum.ALL_THE_TIME;

}
