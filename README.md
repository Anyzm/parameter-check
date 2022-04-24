# parameter-check
a java project for parameter check

## 1、spring project demo

pom 
```
 <dependency>
     <groupId>com.github.anyzm</groupId>
     <artifactId>parameter-core</artifactId>
     <version>0.0.1-RELEASE</version>
 </dependency>
```

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
ParameterCheckHandler.checkFiled has overload method，use timing by yourself，verify all the timing without timing input。

## 2、spring-boot project demo
pom 
```
 <dependency>
     <groupId>com.github.anyzm</groupId>
     <artifactId>parameter-starter</artifactId>
     <version>0.0.1-RELEASE</version>
 </dependency>
```

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
add @Verify on your method，and add @Verify before your parameter，you can use it with timing。if the parameter is the Basic types or String，you can directly use @NotNull，@NotEmpty and so on。
