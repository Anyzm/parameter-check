package cn.anyzm.parameter.constant;

/**
 * @author huangzhaolai-jk
 * @version 1.0.0
 * @Description ParameterException is used for
 * @Date 2019/10/12 - 10:41
 */
public enum ExceptionCodeMsg {
    /**
     * default error
     */
    DEFAULT_ERROR(6780000,"parameter exception"),
    /**
     * null
     */
    NULL(6780001,"object must not be null"),
    /**
     * empty
     */
    EMPTY(6780002,"object must not be empty"),
    /**
     * blank
     */
    BLANK(6780003,"object must not be blank"),
    /**
     * ASSERT_BOOLEAN
     */
    ASSERT_BOOLEAN(6780004,"object must be true");

    private int code;
    private String msg;

    ExceptionCodeMsg(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }
}
