package cn.anyzm.parameter.starter.common;

import cn.anyzm.parameter.annotation.Verify;
import cn.anyzm.parameter.handler.CheckHandler;
import cn.anyzm.parameter.handler.ParameterCheckHandler;
import cn.anyzm.parameter.handler.Router;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author ZhaoLai Huang
 * created by ZhaoLai Huang on 2020/2/3
 */
@Aspect
@Configuration
@Component
public class ParameterCheckAspect {

    @Bean
    public ParameterCheckAspect parameterCheckAspect() {
        return this;
    }

    /**
     * 这里定义了一个总的匹配规则，以后拦截的时候直接拦截valid()方法即可，无须去重复写execution表达式
     */
    @Pointcut("@annotation(cn.anyzm.parameter.annotation.Verify)")
    public void valid() {
    }

    @Before("valid()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        Object[] params = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //参数注解，1维是参数，2维是注解
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            Object param = params[i];
            Annotation[] paramAnn = annotations[i];
            for (Annotation annotation : paramAnn) {
                CheckHandler handler = Router.getHandler(annotation.getClass());
                if (handler != null) {
                    handler.checkOneParam(params[i], annotation);
                } else if (annotation.annotationType().equals(Verify.class)) {
                    Verify verify = (Verify) annotation;
                    ParameterCheckHandler.checkFiled(params[i], verify.timing());
                }
            }
        }
    }


}
