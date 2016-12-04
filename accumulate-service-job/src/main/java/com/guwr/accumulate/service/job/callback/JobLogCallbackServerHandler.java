package com.guwr.accumulate.service.job.callback;

import com.alibaba.fastjson.JSON;
import com.guwr.accumulate.common.util.CommonUtils;
import com.guwr.accumulate.facade.job.entity.QrtzTriggerLog;
import com.guwr.accumulate.facade.job.router.model.RequestModel;
import com.guwr.accumulate.facade.job.router.model.ResponseModel;
import com.guwr.accumulate.service.job.util.DynamicSchedulerUtils;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by gwr
 * Description 客户端调用服务端异步通知服务处理类
 * Path com.guwr.accumulate.service.job.callback.JobLogCallbackServerHandler
 * Date 2016/9/18
 * Time 18:12
 */
public class JobLogCallbackServerHandler extends AbstractHandler {

    private static Logger logger = LoggerFactory.getLogger(JobLogCallbackServerHandler.class);

    @Override
    public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        logger.info("JobLogCallbackServerHandler.handle");

        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        // parse hex-json to request model
        String requestHex = httpServletRequest.getParameter("hex");
        RequestModel requestModel = JSON.parseObject(requestHex, RequestModel.class);

        int logId = requestModel.getLogId();
        QrtzTriggerLog qrtzTriggerLog = DynamicSchedulerUtils.qrtzTriggerLogService.findOne(logId);
        logger.info("qrtzTriggerLog = " + qrtzTriggerLog);
        // process
        ResponseModel responseModel;
        if (qrtzTriggerLog != null) {
            // save log
            qrtzTriggerLog.setHandleTime(new Date());
            qrtzTriggerLog.setHandleStatus(requestModel.getStatus());
            qrtzTriggerLog.setHandleMsg(requestModel.getMsg());
            DynamicSchedulerUtils.qrtzTriggerLogService.updateHandleInfo(qrtzTriggerLog);
            responseModel = new ResponseModel(ResponseModel.SUCCESS, null);
        } else {
            responseModel = new ResponseModel(ResponseModel.FAIL, "log item not found.");
        }

        // format response model to hex-json
        String responseHex = CommonUtils.obj2Json(responseModel);

        // response
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        request.setHandled(true);//
        httpServletResponse.getWriter().println(responseHex);
    }
}
