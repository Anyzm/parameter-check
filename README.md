# parameter-check
a java project for parameter check

##1、spring project demo
```
package com.example.demo;
import cn.anyzm.parameter.annotation.AssertBoolean;
import cn.anyzm.parameter.annotation.NotNull;
import cn.anyzm.parameter.annotation.Range;
import lombok.Data;
/**
 * @author huangzhaolai-jk
 * @version 1.0.0
 * @Description Student is used for
 * @Date 2020/07/12 - 18:43
 */
@Data
public class Student {

    @NotNull(timing = "123")
    private String name;

    @Range(minValue = 1,maxValue = 18,msg = "年龄必须是1-18岁",timing = "456")
    private int age = 0;

    @AssertBoolean(timing = "789",value = false)
    private boolean flag = false;
}
    public static void main(String[] args) throws Exception {
        Student student = new Student();
        student.setAge(0);
        student.setFlag(true);
        ParameterCheckHandler.checkFiled(student);
        ParameterCheckHandler.checkFiled(student,"789");
    }
```
其中ParameterCheckHandler.checkFiled有重载方法，可以传入校验的timing，不传默认全部校验。

##2、spring-boot project demo
```
package com.example.demo;

import cn.anyzm.parameter.annotation.Verify;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ZhaoLai Huang
 * created by ZhaoLai Huang on 2020/7/25
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    @Verify
    public String test(@Verify @RequestBody Student student) {
        System.out.println(student);
        return "ok";
    }
}

```
在需要拦截的方法上加上@Verify的注解，另外在需要校验的引用类型参数前加上@Verify，同时可以传入timing校验时机。如果参数是基本类型或是字符串类型，则可直接加上@NotNull，@NotEmpty等注解。
