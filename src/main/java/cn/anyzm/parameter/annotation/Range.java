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
public @interface Range {

    /**
     * the value for min value
     */
    double minValue();

    /**
     * the value for max value
     */
    double maxValue();

    /**
     * the min value can or can not be equals
     * @return boolean
     */
    boolean canMinEquals() default true;

    /**
     * the max value can or can not be equals
     * @return boolean
     */
    boolean canMaxEquals() default true;

    /**
     * the msg for not pass check
     * @return String
     */
    String msg() default "the number which add Range annotation must be in the min and max range";

    /**
     *  the timing for use the annotation,you can defined multiple timing for different place
     */
    String[] timing() default ValueEnum.EMPTY_STRING;


}
