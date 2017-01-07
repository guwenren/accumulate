package com.guwr.accumulate.facade.notify.vo;

import com.guwr.accumulate.common.util.CommonUtils;
import com.guwr.accumulate.common.web.vo.BaseVO;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.notify.vo.NotifyMessageVO
 * Date 2016/9/12
 * Time 15:58
 */
public class NotifyMessageVO extends BaseVO{

    private static final long serialVersionUID = -8344439058579757034L;
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
