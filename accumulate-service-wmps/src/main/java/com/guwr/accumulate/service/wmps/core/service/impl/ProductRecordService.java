package com.guwr.accumulate.service.wmps.core.service.impl;


import com.alibaba.fastjson.JSON;
import com.guwr.accumulate.common.enums.NotifyDestination;
import com.guwr.accumulate.common.util.AmountUtils;
import com.guwr.accumulate.common.util.StringUtils;
import com.guwr.accumulate.facade.account.entity.AccountBalance;
import com.guwr.accumulate.facade.account.entity.AccountBalanceRecord;
import com.guwr.accumulate.facade.account.exception.AccountBizException;
import com.guwr.accumulate.facade.account.facade.IAccountBalanceFacade;
import com.guwr.accumulate.facade.account.facade.IAccountBalanceRecordFacade;
import com.guwr.accumulate.facade.account.vo.AccountBalanceRecordVO;
import com.guwr.accumulate.facade.notify.entity.NotifyTransactionMessage;
import com.guwr.accumulate.facade.notify.facade.INotifyTransactionMessageFacade;
import com.guwr.accumulate.facade.notify.vo.NotifyMessageVO;
import com.guwr.accumulate.facade.notify.vo.NotifyTransactionMessageVO;
import com.guwr.accumulate.facade.user.entity.UserInfo;
import com.guwr.accumulate.facade.user.entity.UserProductEarnings;
import com.guwr.accumulate.facade.user.entity.UserProductLevel;
import com.guwr.accumulate.facade.user.exception.UserBizException;
import com.guwr.accumulate.facade.user.facade.IUserInfoFacade;
import com.guwr.accumulate.facade.user.facade.IUserProductEarningsFacade;
import com.guwr.accumulate.facade.user.facade.IUserProductLevelFacade;
import com.guwr.accumulate.facade.user.vo.UserProductLevelVO;
import com.guwr.accumulate.facade.wmps.entity.Product;
import com.guwr.accumulate.facade.wmps.entity.ProductRecord;
import com.guwr.accumulate.facade.wmps.enums.ProductStatus;
import com.guwr.accumulate.facade.wmps.exception.WmpsBizException;
import com.guwr.accumulate.facade.wmps.vo.ProductRecordVO;
import com.guwr.accumulate.service.wmps.core.dao.ProductRecordRepository;
import com.guwr.accumulate.service.wmps.core.service.IProductRecordService;
import com.guwr.accumulate.service.wmps.core.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * Created by gwr
 * Description 产品购买记录
 * Path com.guwr.accumulate.service.wmps.service.impl.ProductRecordService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class ProductRecordService implements IProductRecordService {

    private static Logger logger = LoggerFactory.getLogger(ProductRecordService.class);

    private final static BigDecimal MULTIPLE_AMOUNT = new BigDecimal(100d);//投资倍数

    @Autowired
    private ProductRecordRepository repository;

    @Autowired
    private IProductService productService;

    @Autowired
    private IUserInfoFacade userInfoFacade;

    @Autowired
    private IUserProductLevelFacade userProductLevelFacade;

    @Autowired
    private IAccountBalanceRecordFacade accountBalanceRecordFacade;

    @Autowired
    private IAccountBalanceFacade accountBalanceFacade;

    @Autowired
    private IUserProductEarningsFacade userProductEarningsFacade;

    @Autowired
    private INotifyTransactionMessageFacade notifyTransactionMessageFacade;

    @Override
    public ProductRecord save(ProductRecord entity) {
        return repository.save(entity);
    }

    @Override
    public ProductRecord findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public void addProductRecord(ProductRecordVO info) {
        logger.info("ProductRecordService.addProductRecord.info = [" + info + "]");
        Integer uid = info.getUid();
        Integer pid = info.getPid();
        String uuid = StringUtils.getUUID();
        Date date = new Date();
        BigDecimal investAmount = info.getInvestAmount();

        BigDecimal[] divideAndRemainder = investAmount.divideAndRemainder(MULTIPLE_AMOUNT);
        if (divideAndRemainder[1].compareTo(BigDecimal.ZERO) != 0) { //不是100的整倍数
            throw WmpsBizException.TOU_ZI_JIN_E_YOU_WU.print();
        }

        UserInfo userInfo = userInfoFacade.findOne(uid);
        if (userInfo == null) { //用户
            throw UserBizException.YONG_HU_BU_CUN_ZAI.print();
        }

        AccountBalance accountBalance = accountBalanceFacade.findOneByUid(uid);
        if (accountBalance == null) { //账户
            throw AccountBizException.YONG_HU_ZHANG_HU_BU_CUN_ZAI.print();
        }

        BigDecimal balance = accountBalance.getBalance();//账户余额
        if (investAmount.compareTo(balance) > 0) { //账户余额是否足够
            throw WmpsBizException.YU_E_BU_ZU.print();
        }

        Product product = productService.findOne(pid);//加载购买产品信息
        if (product == null) { //产品是否存在
            throw WmpsBizException.CHAN_PIN_BU_CUN_ZAI.print();
        }

        if (!Objects.equals(ProductStatus.PUBLISHED.getValue(), product.getStatus())) {//状态是否为已发布
            throw WmpsBizException.CHAN_PIN_WEI_KAI_SHI_REN_GOU.print();
        }


        BigDecimal productInvestAmount = product.getInvestAmount();//投资总额
        BigDecimal productEffectAmount = product.getEffectAmount();//有效总额
        BigDecimal amount = product.getAmount();

        BigDecimal subtractAmount = amount.subtract(productEffectAmount); //剩余金额

        if (subtractAmount.compareTo(BigDecimal.ZERO) != 1) {
            throw WmpsBizException.CHAN_PIN_YI_WAN_CHENG.print();
        }

        BigDecimal effectAmount;//有效金额
        if (investAmount.compareTo(subtractAmount) > 0) {
            effectAmount = subtractAmount;
        } else {
            effectAmount = investAmount;
        }

        BigDecimal invest_amount = productInvestAmount.add(investAmount); //总投资金额
        BigDecimal effect_amount = productEffectAmount.add(effectAmount); //总有效金额
        product.setInvestAmount(invest_amount);
        product.setEffectAmount(effect_amount);
        product.setUpdateTime(date);

        /**
         * 计算当笔投资VIP利率
         */
        UserProductLevelVO userProductLevelVO = new UserProductLevelVO();
        userProductLevelVO.setUid(uid);
        userProductLevelVO.setInvest(effectAmount);

        UserProductLevel userProductLevel = userProductLevelFacade.findUserProductLevelByIn(userProductLevelVO);
        logger.info("ProductRecordService.addProductRecord.userProductLevel = " + userProductLevel + "");
        Integer lid = userProductLevel.getId();
        BigDecimal vipInterestrate = userProductLevel.getInterestrate(); // vip利率
        BigDecimal proearn = proearn(userProductLevel, product, effectAmount);
        /**
         *  添加资金流水，同时修改用户账户总额
         */
        AccountBalanceRecordVO accountBalanceRecordVO = new AccountBalanceRecordVO();
        accountBalanceRecordVO.setAmount(effectAmount);
        accountBalanceRecordVO.setUid(uid);
        AccountBalanceRecord outgo = accountBalanceRecordFacade.outgo(accountBalanceRecordVO);
        logger.info("ProductRecordService.addProductRecord.outgo = " + outgo);

        // 同一利率同一产品是否投资过
        UserProductEarnings userProductEarnings = userProductEarningsFacade.findOneByUidPidLid(uid, pid, lid);
        logger.info("ProductRecordService.addProductRecord.userProductEarnings = " + userProductEarnings);
        if (userProductEarnings == null) {
            userProductEarnings = new UserProductEarnings();
            userProductEarnings.setInvestAmount(effectAmount);
            userProductEarnings.setCreateTime(date);
            userProductEarnings.setUpdateTime(date);
            userProductEarnings.setUid(uid);
            userProductEarnings.setPid(pid);
            userProductEarnings.setVipInterestrate(vipInterestrate);
            userProductEarnings.setProearn(proearn);
            userProductEarnings.setUuid(uuid);
            userProductEarnings.setRealearn(BigDecimal.ZERO);
            userProductEarningsFacade.save(userProductEarnings);
        } else {
            BigDecimal sumAmount = userProductEarnings.getInvestAmount().add(effectAmount);
            proearn = proearn(userProductLevel, product, sumAmount);
            userProductEarnings.setInvestAmount(sumAmount);
            userProductEarnings.setProearn(proearn);
            userProductEarnings.setUpdateTime(date);
            userProductEarningsFacade.update(userProductEarnings);
        }

        /**
         * 添加用户投资记录
         */
        ProductRecord productRecord = new ProductRecord();
        proearn = AmountUtils.round(proearn, 2);
        productRecord.setProearn(proearn);
        productRecord.setVipInterestrate(vipInterestrate);
        productRecord.setEffectAmount(effectAmount);
        productRecord.setInvestAmount(investAmount);
        productRecord.setCreateTime(date);
        productRecord.setUpdateTime(date);
        productRecord.setPid(pid);
        productRecord.setUid(uid);
        productRecord.setUuid(uuid);


        NotifyMessageVO notifyMessageVO = new NotifyMessageVO();//消息体
        notifyMessageVO.setUid(uid);
        notifyMessageVO.setTitle(uid + "购买产品");
        notifyMessageVO.setContent(uid + "购买，" + pid + "产品");
        notifyMessageVO.setUuid(uuid);
        String messageBody = JSON.toJSONString(notifyMessageVO);
        NotifyTransactionMessageVO notifyTransactionMessageVO = new NotifyTransactionMessageVO();
        notifyTransactionMessageVO.setMessageBody(messageBody);
        notifyTransactionMessageVO.setUuid(uuid);
        notifyTransactionMessageVO.setConsumerQueue(NotifyDestination.MESSAGE_NOTIFY.name());
        logger.info("发送待确认消息");
        NotifyTransactionMessage notifyTransactionMessage = notifyTransactionMessageFacade.saveNotifyTransactionMessage(notifyTransactionMessageVO);
        logger.info("ProductRecordService.addProductRecord.notifyTransactionMessage = " + userProductEarnings);
        this.save(productRecord);  //保存购买记录
        this.productService.update(product);//更新产品购买金额
        logger.info("发送已确认消息到MQ");
        notifyTransactionMessageFacade.sendNotifyTransactionMessage(notifyTransactionMessage);//将消息发送至mq
    }

    @Override
    public ProductRecord findOneProductRecordByUUID(String uuid) {
        return repository.findOneProductRecordByUUID(uuid);
    }


    /**
     * 计算预期收益
     *
     * @param userProductLevel
     * @param product
     * @param effectAmount
     * @return
     */
    private BigDecimal proearn(UserProductLevel userProductLevel, Product product, BigDecimal effectAmount) {
        BigDecimal proearn;
        BigDecimal interestrate = userProductLevel.getInterestrate(); //vip利率
        BigDecimal realInterestrate = product.getInterestrate().add(interestrate); // 真实利率 = 产品利率+ vip利率
        BigDecimal multiply = effectAmount.multiply(realInterestrate);// 真实利率 * 投资有效金额
        BigDecimal divAmount = AmountUtils.div(multiply, new BigDecimal(365));// 每天收益
        proearn = divAmount.multiply(new BigDecimal(product.getPhases())); // 预期收益
        return proearn;
    }
}
