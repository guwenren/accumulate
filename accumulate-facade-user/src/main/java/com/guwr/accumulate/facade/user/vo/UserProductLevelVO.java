package com.guwr.accumulate.facade.user.vo;

import com.guwr.accumulate.common.util.CommonUtils;
import com.guwr.accumulate.common.web.vo.BaseVO;

import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.user.vo.UserProductLevelVO
 * Date 2016/9/12
 * Time 15:31
 */
public class UserProductLevelVO extends BaseVO {

    private static final long serialVersionUID = 2392434906231215388L;
    private Integer uid; //用户ID
    private Integer pid; //产品ID
    private BigDecimal invest; //变动金额
    private String uuid; //


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public BigDecimal getInvest() {
        return invest;
    }

    public void setInvest(BigDecimal invest) {
        this.invest = invest;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
