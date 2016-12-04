package com.guwr.accumulate.facade.authority.exception;

import com.guwr.accumulate.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.authority.exception.AuthorityBizException
 * Date 2016/9/1
 * Time 23:15
 */
public class AuthorityBizException extends BizException {

    private static final long serialVersionUID = 3424589800181389417L;
    private static Logger logger = LoggerFactory.getLogger(AuthorityBizException.class);

    public static final AuthorityBizException USER_NOT_EXIT = new AuthorityBizException(20080001, "用户不存在");
    public static final AuthorityBizException ACCOUNT_OR_PWD_NOT_EXIT = new AuthorityBizException(20080002, "帐号或密码不正确");
    public static final AuthorityBizException ACCOUNT_LOCK = new AuthorityBizException(20080003, "帐号已经禁止登录");

    public AuthorityBizException() {
        super();
    }

    public AuthorityBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public AuthorityBizException(int code, String msg) {
        super(code, msg);
    }

    /**
     * 实例化异常
     *
     * @param msgFormat
     * @param args
     * @return
     */
    public AuthorityBizException newInstance(String msgFormat, Object... args) {
        return new AuthorityBizException(this.code, msgFormat, args);
    }

    public AuthorityBizException print() {
        logger.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
        return new AuthorityBizException(this.code, this.msg);
    }
}
