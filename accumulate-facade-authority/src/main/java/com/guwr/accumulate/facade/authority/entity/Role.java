package com.guwr.accumulate.facade.authority.entity;

import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.notify.entity.Role
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_authority_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色类型
     */
    private String type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}