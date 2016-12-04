package com.guwr.accumulate.common.exception;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.common.exceptions.BizException
 * Date 2016/8/21
 * Time 11:13
 */
public class BizException extends RuntimeException {


    protected String msg;
    protected int code;

    public BizException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public BizException() {
        super();
    }

    public BizException newInstance(String msgFormat, Object... args) {
        return new BizException(this.code, msgFormat, args);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message) {
        super(message);
    }
}
