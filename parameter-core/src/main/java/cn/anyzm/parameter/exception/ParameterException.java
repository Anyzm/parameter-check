package cn.anyzm.parameter.exception;

import cn.anyzm.parameter.constant.ExceptionCodeMsg;
import lombok.Data;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0 @Description ParameterException is used for @Date 2019/10/12 - 10:41
 */
@Data
public class ParameterException extends RuntimeException {

  private int code;

  private String msg;

  private Object data;

  public ParameterException() {}

  public ParameterException(Object data) {
    this(ExceptionCodeMsg.DEFAULT_ERROR);
    this.data = data;
  }

  public ParameterException(ExceptionCodeMsg exceptionCodeMsg) {
    super(exceptionCodeMsg.getMsg());
    this.code = exceptionCodeMsg.getCode();
    this.msg = exceptionCodeMsg.getMsg();
  }

  public ParameterException(ExceptionCodeMsg exceptionCodeMsg, Object data) {
    super(exceptionCodeMsg.getMsg());
    this.code = exceptionCodeMsg.getCode();
    this.msg = exceptionCodeMsg.getMsg();
    this.data = data;
  }

  public ParameterException(int code, String msg, Object data) {
    super(msg);
    this.code = code;
    this.msg = msg;
    this.data = data;
  }
}
