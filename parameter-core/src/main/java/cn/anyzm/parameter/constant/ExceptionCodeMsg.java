package cn.anyzm.parameter.constant;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0 @Description ParameterException is used for @Date 2019/10/12 - 10:41
 */
public enum ExceptionCodeMsg {
  /** default error */
  DEFAULT_ERROR(6780000, "parameter exception"),
  /** ASSERT_BOOLEAN_CAST_ERROR */
  ASSERT_BOOLEAN_CAST_ERROR(6781001, "AssertBoolean must be added to a Boolean field"),
  /** NOT_EMPTY_CAST_ERROR */
  NOT_EMPTY_CAST_ERROR(6781002, "NotEmpty must be added to a Collection or String field"),
  /** LENGTH_CAST_ERROR */
  LENGTH_CAST_ERROR(6781003, "Length must be added to a Collection or String field"),
  /** NOT_BLANK_CAST_ERROR */
  NOT_BLANK_CAST_ERROR(6781004, "NotBlank must be added to a String field"),
  /** MAX_CAST_ERROR */
  MAX_CAST_ERROR(6781005, "Max must be added to a Number field"),
  /** MIN_CAST_ERROR */
  MIN_CAST_ERROR(6781006, "Min must be added to a Number field"),
  /** RANGE_CAST_ERROR */
  RANGE_CAST_ERROR(6781007, "Range must be added to a Number field"),
  /** REGULAR_MATCH_CAST_ERROR */
  REGULAR_MATCH_CAST_ERROR(6781008, "RegularMatch must be added to a String type"),
  /** NOT_NULL_CAST_ERROR */
  NOT_NULL_CAST_ERROR(678109, "NotNull must be added to a Object field"),
  /** null */
  NULL(6780001, "object must not be null"),
  /** the verify annotation is invalid */
  ANNOTATION_INVALID(6780005, "annotation is invalid");

  private int code;
  private String msg;

  ExceptionCodeMsg(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public int getCode() {
    return this.code;
  }

  public String getMsg() {
    return this.msg;
  }
}
