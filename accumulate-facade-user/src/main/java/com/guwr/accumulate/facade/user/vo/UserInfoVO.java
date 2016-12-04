package com.guwr.accumulate.facade.user.vo;

import com.guwr.accumulate.common.util.CommonUtils;
import com.guwr.accumulate.common.web.vo.BaseVO;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.user.vo.UserInfoVO
 * Date 2016/9/12
 * Time 15:31
 */
public class UserInfoVO extends BaseVO {

    private static final long serialVersionUID = 2392434906231215388L;
    private Integer id;
    private String password;
    private String realname;
    private String mobile;

    private String  orderField;
    private String  orderDirection;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
