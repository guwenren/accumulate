package com.guwr.accumulate.service.user.core.service.impl;


import com.alibaba.fastjson.JSON;
import com.guwr.accumulate.common.enums.NotifyDestination;
import com.guwr.accumulate.common.util.AmountUtils;
import com.guwr.accumulate.facade.notify.entity.NotifyTransactionMessage;
import com.guwr.accumulate.facade.notify.facade.INotifyTransactionMessageFacade;
import com.guwr.accumulate.facade.notify.vo.NotifyTransactionMessageVO;
import com.guwr.accumulate.facade.user.entity.UserProductInvest;
import com.guwr.accumulate.facade.user.entity.UserProductLevel;
import com.guwr.accumulate.facade.user.enums.UserProductInvestUserType;
import com.guwr.accumulate.facade.user.vo.UserProductLevelVO;
import com.guwr.accumulate.facade.wmps.vo.ProductRecordVO;
import com.guwr.accumulate.service.user.core.dao.UserProductLevelRepository;
import com.guwr.accumulate.service.user.core.service.IUserProductEarningsService;
import com.guwr.accumulate.service.user.core.service.IUserProductInvestService;
import com.guwr.accumulate.service.user.core.service.IUserProductLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

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

    @Autowired
    private IUserProductEarningsService userProductEarningsService;

    @Override
    public UserProductLevel save(UserProductLevel entity) {
        return repository.save(entity);
    }

    @Override
    public UserProductLevel findOneByInvest(BigDecimal invest) {
        return repository.findOneByInvest(invest);
    }

    @Override
    public void updateUserProductLevelByIn(UserProductLevelVO info) {
        logger.info("updateUserProductLevelByIn.info = [" + info + "]");
        Date date = new Date();
        Integer uid = info.getUid();
        String uuid = info.getUuid();
        BigDecimal invest = info.getInvest();
        Integer phases = info.getPhases();
        Integer pid = info.getPid();
        BigDecimal interestrate = info.getInterestrate();
        logger.info("{},查找用户当前投资级别", uid);

        BigDecimal afterTotalInvest = BigDecimal.ZERO; //总投资金额
        BigDecimal totalInvest = BigDecimal.ZERO; //总投资金额

        UserProductInvest userProductInvest = userProductInvestService.findOneByUid(uid);
        logger.info("{}_用户产品投资总额_{}", uid, JSON.toJSON(userProductInvest));
        if (userProductInvest == null) {
            userProductInvest = new UserProductInvest();
            userProductInvest.setUuid(uuid);
            userProductInvest.setUid(uid);
            userProductInvest.setCreateTime(date);
            userProductInvest.setUpdateTime(date);
            userProductInvest.setUserType(UserProductInvestUserType.USERTYPE_NORMAL.getValue());
            totalInvest = invest;
        } else {
            afterTotalInvest = userProductInvest.getTotalInvest();
            userProductInvest.setUpdateTime(date);
            totalInvest = totalInvest.add(afterTotalInvest);
        }
        userProductInvest.setTotalInvest(totalInvest);

        UserProductLevel productLevel = findOneByInvest(totalInvest);
        logger.info("{}_vip级别_{}", uid, productLevel);
        BigDecimal vipInterestrate = productLevel.getInterestrate(); // vip利率
        BigDecimal proearn = getProearn(vipInterestrate, interestrate, phases, invest);

        // 用户投资收益
        userProductEarningsService.saveOrUpdateUserProductEarnings(date, uid, uuid, invest, pid, vipInterestrate, proearn);

        NotifyTransactionMessageVO messageVO = buildMessageByProductRecordVO(uid, proearn, interestrate, uuid);

        NotifyTransactionMessage notifyMessage = notifyTransactionMessageFacade.saveNotifyTransactionMessage(messageVO);
        logger.info(uid + "_保存修改投资预期收益与利率消息");

        //修改投资总额，添加修改记录
        userProductInvestService.changeInInvest(userProductInvest, invest, afterTotalInvest, uuid);

        notifyTransactionMessageFacade.sendNotifyTransactionMessage(notifyMessage);//将消息发送至mq
        logger.info(uid + "_发送修改投资预期收益与利率消息到MQ");
    }


    @Override
    public UserProductLevel findUserProductLevelByOut(UserProductLevelVO info) {
        UserProductLevel userProductLevel = null;
        return userProductLevel;
    }

    /**
     * 计算预期收益
     *
     * @param interestrate  vip利率
     * @param pInterestrate 产品利率
     * @param phases        期限
     * @param invest        投资总额
     * @return
     */
    private BigDecimal getProearn(BigDecimal interestrate, BigDecimal pInterestrate, Integer phases, BigDecimal invest) {
        BigDecimal proearn;
        BigDecimal realInterestrate = interestrate.add(pInterestrate); // 真实利率 =  vip利率 + 产品利率
        BigDecimal multiply = invest.multiply(realInterestrate);// 真实利率 * 投资有效金额
        BigDecimal divAmount = AmountUtils.div(multiply, new BigDecimal(365));// 每天收益
        proearn = divAmount.multiply(new BigDecimal(phases)); // 预期收益
        return proearn;
    }


    private NotifyTransactionMessageVO buildMessageByProductRecordVO(Integer uid, BigDecimal proearn, BigDecimal interestrate, String uuid) {
        ProductRecordVO productRecordVO = new ProductRecordVO();
        productRecordVO.setProearn(proearn);
        productRecordVO.setInterestrate(interestrate);
        productRecordVO.setUuid(uuid);
        productRecordVO.setUid(uid);
        productRecordVO.setConsumerQueue(NotifyDestination.UPDATE_PROEARN_INTERESTRATE_MESSAGE.name());
        String messageBody = JSON.toJSONString(productRecordVO);

        NotifyTransactionMessageVO info = new NotifyTransactionMessageVO();
        info.setMessageBody(messageBody);
        info.setUuid(uuid);
        info.setConsumerQueue(productRecordVO.getConsumerQueue());
        return info;
    }
}
