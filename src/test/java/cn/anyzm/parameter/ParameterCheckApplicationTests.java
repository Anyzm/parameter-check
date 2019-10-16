package cn.anyzm.parameter;

import cn.anyzm.parameter.Test.Student;
import cn.anyzm.parameter.handler.ParameterCheckHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ParameterCheckApplicationTests {

    @Test
    public void contextLoads() throws Exception {
        Student student = new Student();
        ParameterCheckHandler.checkFiled(student);
    }

}
