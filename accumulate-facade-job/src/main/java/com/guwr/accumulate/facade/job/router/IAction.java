package com.guwr.accumulate.facade.job.router;

import com.guwr.accumulate.facade.job.router.model.RequestModel;
import com.guwr.accumulate.facade.job.router.model.ResponseModel;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.job.router.IAction
 * Date 2016/9/27
 * Time 13:37
 */
public abstract class IAction {
    /**
     *
     * @param requestModel
     * @return
     */
    public abstract ResponseModel execute(RequestModel requestModel);
}
