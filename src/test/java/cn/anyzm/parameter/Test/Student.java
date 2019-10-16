package cn.anyzm.parameter.Test;

import cn.anyzm.parameter.annotation.NotNull;
import lombok.Data;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0
 * @Description Student is used for
 * @Date 2019/10/12 - 18:43
 */
@Data
public class Student {

    @NotNull
    private String name;

    private int age = 1;

}
