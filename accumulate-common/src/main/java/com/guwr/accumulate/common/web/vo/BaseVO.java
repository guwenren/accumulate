package com.guwr.accumulate.common.web.vo;

import com.guwr.accumulate.common.util.CommonUtils;

import java.io.Serializable;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.common.web.vo.BaseVO
 * Date 2016/8/28
 * Time 23:47
 */
public class BaseVO implements Serializable {
    public final static String SORT_TYPE_ASC = "asc";
    public final static String SORT_TYPE_DESC = "desc";
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer pageNum; //页码
    private Integer numPerPage;  //数量
    private String sortType = SORT_TYPE_DESC;//排序方式
    private String uuid;
    private String consumerQueue;

    public Integer getPageNum() {
        if (pageNum == null || pageNum <= 1)
            pageNum = 1;
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getNumPerPage() {
        if (numPerPage == null || numPerPage <= 0)
            numPerPage = 15;
        return numPerPage;
    }

    public void setNumPerPage(Integer numPerPage) {
        this.numPerPage = numPerPage;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getConsumerQueue() {
        return consumerQueue;
    }

    public void setConsumerQueue(String consumerQueue) {
        this.consumerQueue = consumerQueue;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
