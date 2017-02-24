package com.guwr.accumulate.facade.notify.entity;

import com.guwr.accumulate.common.entity.BaseEntity;
import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.notify.entity.NotifyMessage
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_notify_message")
public class NotifyMessage extends BaseEntity {

    private static final long serialVersionUID = -1309600883062868382L;

    private Integer uid;
    private String title;
    private String content;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
