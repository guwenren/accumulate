package com.guwr.accumulate.facade.account.exception;

import com.guwr.accumulate.common.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.account.exception.AccountException
 * Date 2016/9/1
 * Time 23:15
 */
public class AccountException extends BaseException {

    private static final long serialVersionUID = 3424589800181389417L;
    private static Logger logger = LoggerFactory.getLogger(AccountException.class);

    public static final AccountException YONG_HU_ZHANG_HU_BU_CUN_ZAI = new AccountException(30000001, "用户账户不存在");
    public static final AccountException YU_E_BU_ZU = new AccountException(30000002, "余额不足");
    public static final AccountException YU_E_BU_PI_PEI = new AccountException(30000003, "余额不匹配");

    public AccountException() {
        super();
    }

    public AccountException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public AccountException(int code, String msg) {
        super(code, msg);
    }

    /**
     * 实例化异常
     *
     * @param msgFormat
     * @param args
     * @return
     */
    public AccountException newInstance(String msgFormat, Object... args) {
        return new AccountException(this.code, msgFormat, args);
    }

    public AccountException print() {
        logger.info("==>BaseException, code:" + this.code + ", msg:" + this.msg);
        return new AccountException(this.code, this.msg);
    }
}
