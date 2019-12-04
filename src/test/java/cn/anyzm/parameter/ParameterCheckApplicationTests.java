package cn.anyzm.parameter;

import cn.anyzm.parameter.Test.Student;
import cn.anyzm.parameter.handler.ParameterCheckHandler;

public class ParameterCheckApplicationTests {

    public static void main(String[] args) throws Exception {
        Student student = new Student();
        student.setName("123");
        ParameterCheckHandler.checkFiled(student);
    }

}
