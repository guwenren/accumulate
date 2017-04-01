package com.guwr.accumulate.common.exception;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.common.exceptions.BaseException
 * Date 2016/8/21
 * Time 11:13
 */
public class BaseException extends RuntimeException {


    protected String msg;
    protected int code;

    public BaseException(int code, String msgFormat, Object... args) {
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

    public BaseException() {
        super();
    }

    public BaseException newInstance(String msgFormat, Object... args) {
        return new BaseException(this.code, msgFormat, args);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message) {
        super(message);
    }
}
