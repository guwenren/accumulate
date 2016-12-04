package com.guwr.accumulate.common.page;

import com.guwr.accumulate.common.util.CommonUtils;

import java.io.Serializable;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.common.page.PageParam
 * Date         2016/11/6
 * Time         16:22
 * Description
 */
public class PageParam implements Serializable {
    private static final long serialVersionUID = 6297178964005032338L;
    private int pageNum;
    private int numPerPage;

    public PageParam(int pageNum, int numPerPage) {
        this.pageNum = pageNum;
        this.numPerPage = numPerPage;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getNumPerPage() {
        return this.numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getFirstResult() {
        return (this.pageNum - 1) * numPerPage;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
