package com.guwr.accumulate.common.web.vo;

import com.guwr.accumulate.common.util.CommonUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.common.web.vo.DataVO
 * Date 2016/9/4
 * Time 10:09
 */
public class DataVO<T> implements Serializable{

    private static final long serialVersionUID = 2662843431842908925L;
    private int draw; // Client request times
    private int recordsTotal; // Total records number without conditions
    private int recordsFiltered; // Total records number with conditions
    private List<T> data; // The data we should display on the page
    // getter and setter method


    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
