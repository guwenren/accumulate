package com.guwr.accumulate.facade.wmps.exception;

import com.guwr.accumulate.common.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.authority.exception.WmpsException
 * Date 2016/9/1
 * Time 23:15
 */
public class WmpsException extends BaseException {

    private static final long serialVersionUID = 3424589800181389417L;
    private static Logger logger = LoggerFactory.getLogger(WmpsException.class);

    public static final WmpsException YU_E_BU_ZU = new WmpsException(40080001, "余额不足");
    public static final WmpsException CHAN_PIN_YI_WAN_CHENG = new WmpsException(40080002, "产品已完成");
    public static final WmpsException TOU_ZI_JIN_E_YOU_WU = new WmpsException(40080003, "投资金额有误");
    public static final WmpsException CHAN_PIN_BU_CUN_ZAI = new WmpsException(40080004, "产品不存在");
    public static final WmpsException CHAN_PIN_WEI_KAI_SHI_REN_GOU = new WmpsException(40080005, "产品未开始认购");
    public static final WmpsException TOU_ZI_JIN_E_DA_YU_CHAN_PIN_KE_TOU_ZONG_E = new WmpsException(40080006, "投资金额大于产品可投总额");

    public WmpsException() {
        super();
    }

    public WmpsException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public WmpsException(int code, String msg) {
        super(code, msg);
    }

    /**
     * 实例化异常
     *
     * @param msgFormat
     * @param args
     * @return
     */
    public WmpsException newInstance(String msgFormat, Object... args) {
        return new WmpsException(this.code, msgFormat, args);
    }

    public WmpsException print() {
        logger.info("==>BaseException, code:" + this.code + ", msg:" + this.msg);
        return new WmpsException(this.code, this.msg);
    }
}
