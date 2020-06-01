package cn.anyzm.parameter.handler;

import cn.anyzm.parameter.annotation.*;
import cn.anyzm.parameter.handler.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0 @Description Router is used for @Date 2019/10/12 - 17:53
 */
public class Router {

  /** the annotations for check list */
  private static final Map<Class, CheckHandler> ANNOTATION_CHECK_MAP =
      new HashMap<Class, CheckHandler>();

  static {
    ANNOTATION_CHECK_MAP.put(AssertBoolean.class, new AssertBooleanHandler());
    ANNOTATION_CHECK_MAP.put(Max.class, new MaxHandler());
    ANNOTATION_CHECK_MAP.put(Min.class, new MinHandler());
    ANNOTATION_CHECK_MAP.put(Range.class, new RangeHandler());
    ANNOTATION_CHECK_MAP.put(NotBlank.class, new NotBlankHandler());
    ANNOTATION_CHECK_MAP.put(NotEmpty.class, new NotEmptyHandler());
    ANNOTATION_CHECK_MAP.put(NotNull.class, new NotNullHandler());
    ANNOTATION_CHECK_MAP.put(RegularMatch.class, new RegularMatchHandler());
    ANNOTATION_CHECK_MAP.put(Length.class, new LengthHandler());
  }

  public static boolean containsInCheckList(Class clazz) {
    return clazz != null && ANNOTATION_CHECK_MAP.keySet().contains(clazz);
  }

  public static CheckHandler getHandler(Class clazz) {
    return ANNOTATION_CHECK_MAP.get(clazz);
  }
}
