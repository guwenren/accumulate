package com.guwr.accumulate.facade.job.executor;

import com.alibaba.fastjson.JSON;
import com.guwr.accumulate.facade.job.router.IAction;
import com.guwr.accumulate.facade.job.router.action.RunAction;
import com.guwr.accumulate.facade.job.router.model.RequestModel;
import com.guwr.accumulate.facade.job.router.model.ResponseModel;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gwr
 * Description  请求处理类
 * Path com.guwr.accumulate.facade.job.executor.JobExecutorHandler
 * Date 2016/9/27
 * Time 10:31
 */
public class JobExecutorHandler extends AbstractHandler {

    private static Logger logger = LoggerFactory.getLogger(JobExecutorHandler.class);

    @Override
    public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        logger.info("JobExecutorHandler.handle");
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        // parse hex-json to request model
        String requestHex = httpServletRequest.getParameter("hex");
        ResponseModel responseModel = null;
        if (StringUtils.isNotBlank(requestHex)) {
            try {
                RequestModel requestModel = JSON.parseObject(requestHex, RequestModel.class);
                // timestamp check
                if (System.currentTimeMillis() - requestModel.getTimestamp() > 60000) {
                    responseModel = new ResponseModel(ResponseModel.SUCCESS, "Timestamp Timeout.");
                } else {
                    IAction action = new RunAction();
                    responseModel = action.execute(requestModel);
                }
            } catch (Exception e) {
                logger.error("", e);
                responseModel = new ResponseModel(ResponseModel.SUCCESS, e.getMessage());
            }
        }
        if (responseModel == null) {
            responseModel = new ResponseModel(ResponseModel.SUCCESS, "系统异常");
        }

        String responseHex = JSON.toJSONString(responseModel);
        // return
        logger.info("返回Response");
        httpServletResponse.setContentType("text/plain;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        request.setHandled(true);
        httpServletResponse.getWriter().println(responseHex);
    }
}