package com.guwr.accumulate.facade.authority.exception;

import com.guwr.accumulate.common.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.authority.exception.AuthorityException
 * Date 2016/9/1
 * Time 23:15
 */
public class AuthorityException extends BaseException {

    private static final long serialVersionUID = 3424589800181389417L;
    private static Logger logger = LoggerFactory.getLogger(AuthorityException.class);

    public static final AuthorityException USER_NOT_EXIT = new AuthorityException(20080001, "用户不存在");
    public static final AuthorityException ACCOUNT_OR_PWD_NOT_EXIT = new AuthorityException(20080002, "帐号或密码不正确");
    public static final AuthorityException ACCOUNT_LOCK = new AuthorityException(20080003, "帐号已经禁止登录");

    public AuthorityException() {
        super();
    }

    public AuthorityException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public AuthorityException(int code, String msg) {
        super(code, msg);
    }

    /**
     * 实例化异常
     *
     * @param msgFormat
     * @param args
     * @return
     */
    public AuthorityException newInstance(String msgFormat, Object... args) {
        return new AuthorityException(this.code, msgFormat, args);
    }

    public AuthorityException print() {
        logger.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
        return new AuthorityException(this.code, this.msg);
    }
}
