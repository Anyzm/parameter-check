package cn.anyzm.parameter.handler;

import cn.anyzm.parameter.annotation.*;
import cn.anyzm.parameter.handler.impl.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0
 * @Description Router is used for
 * @Date 2019/10/12 - 17:53
 */
public class Router {

    /**
     * the annotations for check list
     */
    private static final List<Class> ANNOTATION_CHECK_LIST = new ArrayList<Class>();
    private static final Map<Class, AnnotationHandler> ANNOTATION_CHECK_MAP = new HashMap<Class, AnnotationHandler>();

    static {
        ANNOTATION_CHECK_LIST.add(AssertBoolean.class);
        ANNOTATION_CHECK_LIST.add(Max.class);
        ANNOTATION_CHECK_LIST.add(Min.class);
        ANNOTATION_CHECK_LIST.add(Range.class);
        ANNOTATION_CHECK_LIST.add(NotBlank.class);
        ANNOTATION_CHECK_LIST.add(NotEmpty.class);
        ANNOTATION_CHECK_LIST.add(NotNull.class);
        ANNOTATION_CHECK_LIST.add(RegularMatch.class);
        ANNOTATION_CHECK_MAP.put(AssertBoolean.class, new AssertBooleanHandler());
        ANNOTATION_CHECK_MAP.put(Max.class, new MaxHandler());
        ANNOTATION_CHECK_MAP.put(Min.class, new MinHandler());
        ANNOTATION_CHECK_MAP.put(Range.class, new RangeHandler());
        ANNOTATION_CHECK_MAP.put(NotBlank.class, new NotBlankHandler());
        ANNOTATION_CHECK_MAP.put(NotEmpty.class, new NotEmptyHandler());
        ANNOTATION_CHECK_MAP.put(NotNull.class, new NotNullHandler());
        ANNOTATION_CHECK_MAP.put(RegularMatch.class, new RegularMatchHandler());
        ANNOTATION_CHECK_MAP.put(null, null);
    }

    public static Class getFromCheckList(int i) {
        return ANNOTATION_CHECK_LIST.get(i);
    }

    public static boolean containsInCheckList(Class clazz) {
        return clazz != null && ANNOTATION_CHECK_LIST.contains(clazz);
    }

    public static AnnotationHandler getHandler(Class clazz) {
        return ANNOTATION_CHECK_MAP.get(clazz);
    }

}
