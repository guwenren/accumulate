package com.guwr.accumulate.facade.account.exception;

import com.guwr.accumulate.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.account.exception.AccountBizException
 * Date 2016/9/1
 * Time 23:15
 */
public class AccountBizException extends BizException {

    private static final long serialVersionUID = 3424589800181389417L;
    private static Logger logger = LoggerFactory.getLogger(AccountBizException.class);

    public static final AccountBizException ACCOUNTBALANCE_NOT_EXIT = new AccountBizException(30000001, "用户账户不存在");
    public static final AccountBizException YU_E_BU_ZU = new AccountBizException(30000002, "余额不足");
    public static final AccountBizException YU_E_BU_PI_PEI = new AccountBizException(30000003, "余额不匹配");

    public AccountBizException() {
        super();
    }

    public AccountBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public AccountBizException(int code, String msg) {
        super(code, msg);
    }

    /**
     * 实例化异常
     *
     * @param msgFormat
     * @param args
     * @return
     */
    public AccountBizException newInstance(String msgFormat, Object... args) {
        return new AccountBizException(this.code, msgFormat, args);
    }

    public AccountBizException print() {
        logger.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
        return new AccountBizException(this.code, this.msg);
    }
}
