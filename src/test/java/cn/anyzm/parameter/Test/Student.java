package cn.anyzm.parameter.Test;

import cn.anyzm.parameter.annotation.AssertBoolean;
import cn.anyzm.parameter.annotation.Min;
import cn.anyzm.parameter.annotation.NotNull;
import cn.anyzm.parameter.annotation.Range;
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

    @Range(minValue = 1,maxValue = 2)
    private int age = 1;

    @AssertBoolean
    private boolean flag = false;

}
