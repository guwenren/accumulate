package com.guwr.accumulate.facade.authority.entity;

import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.notify.entity.RolePermission
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_authority_role_permission")
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer rid;
    private Integer pid;

    public RolePermission() {
    }

    public RolePermission(Integer rid, Integer pid) {
        this.rid = rid;
        this.pid = pid;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}