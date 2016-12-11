package com.guwr.accumulate.facade.user.entity;

import com.guwr.accumulate.common.entity.BaseEntity;
import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.wmps.entity.UserInfo
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_user_userinfo")
public class UserInfo extends BaseEntity {


    private static final long serialVersionUID = -1309600883062868382L;

    private String password;
    private String realname;
    private String mobile;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
