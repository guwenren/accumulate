package com.guwr.accumulate.facade.user.exception;

import com.guwr.accumulate.common.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.facade.user.exception.UserException
 * Date         2016/11/10
 * Time         14:44
 * Description
 */
public class UserException extends BaseException {

    private static final long serialVersionUID = 3424589800181389417L;
    private static Logger logger = LoggerFactory.getLogger(UserException.class);

    public static final UserException SHOU_JI_HAO_MA_BU_NENG_WEI_KONG = new UserException(20080001, "手机号码不能为空");
    public static final UserException YONG_HU_BU_CUN_ZAI = new UserException(20080002, "用户不存在");


    public UserException() {
        super();
    }

    public UserException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public UserException(int code, String msg) {
        super(code, msg);
    }

    /**
     * 实例化异常
     *
     * @param msgFormat
     * @param args
     * @return
     */
    public UserException newInstance(String msgFormat, Object... args) {
        return new UserException(this.code, msgFormat, args);
    }

    public UserException print() {
        logger.info("==>BaseException, code:" + this.code + ", msg:" + this.msg);
        return new UserException(this.code, this.msg);
    }
}
