package com.guwr.accumulate.service.user.core.service.impl;


import com.alibaba.fastjson.JSON;
import com.guwr.accumulate.common.enums.NotifyDestination;
import com.guwr.accumulate.common.util.AmountUtils;
import com.guwr.accumulate.facade.notify.entity.NotifyTransactionMessage;
import com.guwr.accumulate.facade.notify.facade.INotifyTransactionMessageFacade;
import com.guwr.accumulate.facade.notify.vo.NotifyTransactionMessageVO;
import com.guwr.accumulate.facade.user.entity.UserProductLevel;
import com.guwr.accumulate.facade.user.vo.UserProductInvestVO;
import com.guwr.accumulate.facade.user.vo.UserProductLevelVO;
import com.guwr.accumulate.facade.wmps.entity.Product;
import com.guwr.accumulate.facade.wmps.vo.ProductRecordVO;
import com.guwr.accumulate.service.user.core.dao.UserProductLevelRepository;
import com.guwr.accumulate.service.user.core.service.IUserProductInvestService;
import com.guwr.accumulate.service.user.core.service.IUserProductLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.user.service.impl.UserProductLevelService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class UserProductLevelService implements IUserProductLevelService {

    private static Logger logger = LoggerFactory.getLogger(UserProductLevelService.class);

    @Autowired
    private UserProductLevelRepository repository;

    @Autowired
    private IUserProductInvestService userProductInvestService;

    @Autowired
    private INotifyTransactionMessageFacade notifyTransactionMessageFacade;

    @Override
    public UserProductLevel save(UserProductLevel entity) {
        return repository.save(entity);
    }

    @Override
    public UserProductLevel findOneByInvest(BigDecimal invest) {
        return repository.findOneByInvest(invest);
    }

    @Override
    public UserProductLevel findUserProductLevelByIn(UserProductLevelVO info) {
        logger.info("findUserProductLevelByIn.info = [" + info + "]");
        Integer uid = info.getUid();
        String uuid = info.getUuid();
        BigDecimal invest = info.getInvest();
        Integer phases = info.getPhases();
        BigDecimal interestrate = info.getInterestrate();
        logger.info("{},查找用户当前投资级别", uid);
        UserProductLevel userProductLevel;

        NotifyTransactionMessageVO messageVO = new NotifyTransactionMessageVO();
        messageVO.setMessageBody("");
        messageVO.setUuid(uuid);
        messageVO.setConsumerQueue(NotifyDestination.UPDATE_PROEARN_INTERESTRATE_MESSAGE.name());


        NotifyTransactionMessage notifyMessage = notifyTransactionMessageFacade.saveNotifyTransactionMessage(messageVO);
        logger.info(uid + "_保存修改投资预期收益与利率消息");



        UserProductInvestVO userProductInvestVO = new UserProductInvestVO();
        userProductInvestVO.setInvest(invest);
        userProductInvestVO.setUid(uid);
        userProductInvestVO.setUuid(info.getUuid());
        BigDecimal afterTotalInvest = userProductInvestService.changeInInvest(userProductInvestVO);
        userProductLevel = this.findOneByInvest(afterTotalInvest);


        BigDecimal vipInterestrate = userProductLevel.getInterestrate(); // vip利率
        BigDecimal proearn = getProearn(vipInterestrate, interestrate, phases, invest);

        ProductRecordVO productRecordVO = new ProductRecordVO();
        productRecordVO.setProearn(proearn);
        productRecordVO.setInterestrate(vipInterestrate);
        productRecordVO.setUuid(uuid);
        String messageBody = JSON.toJSONString(productRecordVO);
        notifyMessage.setMessageBody(messageBody);
        notifyTransactionMessageFacade.update(notifyMessage);

        notifyTransactionMessageFacade.sendNotifyTransactionMessage(notifyMessage);//将消息发送至mq
        logger.info(uid + "_发送修改投资预期收益与利率消息到MQ");

        return userProductLevel;
    }

    @Override
    public UserProductLevel findUserProductLevelByOut(UserProductLevelVO info) {
        UserProductLevel userProductLevel = null;
        return userProductLevel;
    }

    /**
     * 计算预期收益
     *
     * @param interestrate
     * @param vipInterestrate
     * @param phases
     * @param invest
     * @return
     */
    private BigDecimal getProearn(BigDecimal interestrate,BigDecimal  vipInterestrate, Integer phases,BigDecimal invest) {
        BigDecimal proearn;
        BigDecimal realInterestrate = vipInterestrate.add(interestrate); // 真实利率 = 产品利率+ vip利率
        BigDecimal multiply = invest.multiply(realInterestrate);// 真实利率 * 投资有效金额
        BigDecimal divAmount = AmountUtils.div(multiply, new BigDecimal(365));// 每天收益
        proearn = divAmount.multiply(new BigDecimal(phases)); // 预期收益
        return proearn;
    }

    private NotifyTransactionMessageVO buildMessageByProductRecordVO(Integer uid,String uuid) {
        ProductRecordVO productRecordVO = new ProductRecordVO();

        productRecordVO.setUid(uid);
//        productRecordVO.setProearn(proearn);
//        productRecordVO.setInterestrate(interestrate);
        productRecordVO.setUuid(uuid);
        String messageBody = JSON.toJSONString(productRecordVO);

        NotifyTransactionMessageVO messageVO = new NotifyTransactionMessageVO();
        messageVO.setMessageBody(messageBody);
        messageVO.setUuid(uuid);
        messageVO.setConsumerQueue(NotifyDestination.UPDATE_PROEARN_INTERESTRATE_MESSAGE.name());

        return messageVO;
    }
}
