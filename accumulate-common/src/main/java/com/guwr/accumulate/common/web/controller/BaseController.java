package com.guwr.accumulate.common.web.controller;

import com.guwr.accumulate.common.web.themes.dwz.DwzParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.common.web.controller.BaseController
 * Date         2016/11/12
 * Time         14:12
 * Description
 */
public class BaseController implements Serializable {

    @Autowired
    private HttpServletRequest req;
    protected static final String DWZPARAM_KEY = "m";
    protected static final String MODEL_KEY = "model";
    protected static final String QUERY_KEY = "query";

    public String operateSuccess(Model model) {
        return operateSuccess("操作成功", model);
    }

    public String operateSuccess(String message, Model model) {
        ajaxDone("200", message, model);
        return "common/operateSuccess";
    }

    public String operateError(String message, Model model) {
        ajaxDone("300", message, model);
        return "common/operateError";
    }

    private void ajaxDone(String statusCode, String message, Model model) {
        DwzParam param = getDwzParam(statusCode, message);
        model.addAttribute(DWZPARAM_KEY, param);
    }

    /**
     * 根据request对象，获取页面提交过来的DWZ框架的AjaxDone响应参数值.
     *
     * @param statusCode 状态码.
     * @param message    操作结果提示消息.
     * @return DwzParam :封装好的DwzParam对象 .
     */
    public DwzParam getDwzParam(String statusCode, String message) {
        // 获取DWZ Ajax响应参数值,并构造成参数对象
        String navTabId = req.getParameter("navTabId");
        String dialogId = req.getParameter("dialogId");
        String callbackType = req.getParameter("callbackType");
        String forwardUrl = req.getParameter("forwardUrl");
        String rel = req.getParameter("rel");
        return new DwzParam(statusCode, message, navTabId, dialogId, callbackType, forwardUrl, rel, null);
    }
}
