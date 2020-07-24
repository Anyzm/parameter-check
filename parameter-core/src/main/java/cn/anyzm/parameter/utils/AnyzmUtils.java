package cn.anyzm.parameter.utils;

import cn.anyzm.parameter.constant.ValueEnum;

import java.util.*;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0 @Description AnyzmUtils is used for @Date 2019/10/12 - 16:30
 */
public class AnyzmUtils {

    /**
     * return a empty String
     * @return
     */
    public static String emptyString(){
        return ValueEnum.EMPTY_STRING;
    }

    /**
     * check the array and it's elements all are empty
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> boolean isDeepEmpty(T[] array) {
        if (array == null || array.length == ValueEnum.ZERO) {
            return true;
        } else {
            for (T t : array) {
                if (t != null) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * check the array is empty
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == ValueEnum.ZERO;
    }

    /**
     * check the Map is empty
     *
     * @param map
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(Map map) {
        return map == null || map.size() == 0;
    }

    /**
     * check String is empty
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        return s == null || ValueEnum.EMPTY_STRING.equals(s);
    }

    /**
     * check Collection is empty
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * check String is blank
     *
     * @param s
     * @return
     */
    public static boolean isBlank(String s) {
        return s == null || ValueEnum.EMPTY_STRING.equals(s.trim());
    }

    /**
     * copy Array to a List
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> List<T> copyArrayToList(T[] array) {
        if (isEmpty(array)) {
            return new ArrayList<>();
        }
        return Arrays.asList(array);
    }
}
