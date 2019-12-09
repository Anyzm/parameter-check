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

    @NotNull(timing = "123")
    private String name;

    @Range(minValue = 1,maxValue = 18,msg = "年龄必须是1-18岁",timing = "456")
    private int age = 0;

    @AssertBoolean(timing = "789",value = false)
    private boolean flag = false;

}
