package com.guwr.accumulate.facade.job.handler;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.job.handler.IJobHandler
 * Date 2016/9/27
 * Time 11:13
 */
public abstract class IJobHandler {
    /**
     * job handler <br><br>
     * the return Object will be and stored
     * @param params
     * @throws Exception  default sussecc, fail if catch exception
     */
    public abstract void execute(String... params) throws Exception;
}
