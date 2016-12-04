package com.guwr.accumulate.facade.authority.vo;

import com.guwr.accumulate.common.util.CommonUtils;
import com.guwr.accumulate.common.web.vo.BaseVO;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.authority.vo.UserVO
 * Date 2016/8/28
 * Time 23:46
 */
public class UserVO extends BaseVO {
    private static final long serialVersionUID = -7062191682722496924L;
    private String username;
    private String password;
    private Boolean rememberMe = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
