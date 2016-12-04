package com.guwr.accumulate.facade.user.exception;

import com.guwr.accumulate.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.facade.user.exception.UserBizException
 * Date         2016/11/10
 * Time         14:44
 * Description
 */
public class UserBizException extends BizException {

    private static final long serialVersionUID = 3424589800181389417L;
    private static Logger logger = LoggerFactory.getLogger(UserBizException.class);

    public static final UserBizException SHOU_JI_HAO_MA_BU_NENG_WEI_KONG = new UserBizException(20080001, "手机号码不能为空");
    public static final UserBizException YONG_HU_BU_CUN_ZAI = new UserBizException(20080002, "用户不存在");


    public UserBizException() {
        super();
    }

    public UserBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public UserBizException(int code, String msg) {
        super(code, msg);
    }

    /**
     * 实例化异常
     *
     * @param msgFormat
     * @param args
     * @return
     */
    public UserBizException newInstance(String msgFormat, Object... args) {
        return new UserBizException(this.code, msgFormat, args);
    }

    public UserBizException print() {
        logger.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
        return new UserBizException(this.code, this.msg);
    }
}
