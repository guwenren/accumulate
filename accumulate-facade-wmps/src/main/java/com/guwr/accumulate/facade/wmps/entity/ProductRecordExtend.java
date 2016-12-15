package com.guwr.accumulate.facade.wmps.entity;

import com.guwr.accumulate.common.util.CommonUtils;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.wmps.entity.ProductRecordExtend
 * Date 2016/8/21
 * Time 14:32
 */
public class ProductRecordExtend extends ProductRecord {


    private static final long serialVersionUID = -1309600883062868382L;


    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
