package com.guwr.accumulate.facade.user.entity;

import com.guwr.accumulate.common.entity.BaseEntity;
import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by gwr
 * Description  每日发息表
 * Path com.guwr.accumulate.facade.wmps.entity.UserProductDayInter
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_user_wmps_day_inter")
public class UserProductDayInter extends BaseEntity {


    private static final long serialVersionUID = 7057799382653381564L;

    private Integer uid; //用户ID
    private Integer inter; //利息
    private Integer status; // 状态：0未发 1已发

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getInter() {
        return inter;
    }

    public void setInter(Integer inter) {
        this.inter = inter;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
