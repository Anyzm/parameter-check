package cn.anyzm.parameter.utils;

import cn.anyzm.parameter.constant.ValueEnum;

import java.lang.reflect.Array;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0
 * @Description AnyzmUtils is used for
 * @Date 2019/10/12 - 16:30
 */
public class AnyzmUtils {
    /**
     * check the array and it's elements all are empty
     * @param array
     * @param <T>
     * @return
     */
    public static <T> boolean isDeepEmpty(T[] array) {
        if (array == null || array.length == ValueEnum.ZERO) {
            return true;
        } else {
            for (T t : array) {
                if (t instanceof Array) {
                    Object[] ax = (Object[]) t;
                    if (!isDeepEmpty(ax)) {
                        return false;
                    }
                } else {
                    if (t != null) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    /**
     * check the array is empty
     * @param array
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == ValueEnum.ZERO;
    }

}
