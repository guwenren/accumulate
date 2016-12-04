package com.guwr.accumulate.facade.authority.entity;

import java.io.Serializable;

import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.*;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.notify.entity.Permission
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_authority_permission")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 操作的url */
	private String url;
	/** 操作的名称 */
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return CommonUtils.obj2Json(this);
	}
}