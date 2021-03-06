package com.guwr.accumulate.web.controller.notify;

import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.common.web.controller.BaseController;
import com.guwr.accumulate.facade.notify.entity.NotifyMessage;
import com.guwr.accumulate.facade.notify.facade.INotifyMessageFacade;
import com.guwr.accumulate.facade.notify.vo.NotifyMessageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.controller.notify.NotifyController
 * Date         2016/11/10
 * Time         11:32
 * Description
 */
@Controller
@RequestMapping("notify")
public class NotifyController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(NotifyController.class);

    @Autowired
    private INotifyMessageFacade notifyMessageFacade;

    /**
     * 列出用户信息.
     *
     * @return
     */
    @RequestMapping("messageList")
    public String messageList(Model model, NotifyMessageVO info) {
        logger.info("model = [" + model + "], info = [" + info + "]");
        PageBean<NotifyMessage> listPage = notifyMessageFacade.findListPageByCondition(info);
        model.addAttribute(MODEL_KEY, listPage);
        model.addAttribute(QUERY_KEY, info);
        return "notify/notify_message_list";
    }
}
