package com.guwr.accumulate.service.wmps.core.service.impl;


import com.alibaba.fastjson.JSON;
import com.guwr.accumulate.common.enums.NotifyDestination;
import com.guwr.accumulate.common.util.AmountUtils;
import com.guwr.accumulate.common.util.StringUtils;
import com.guwr.accumulate.facade.account.entity.AccountBalance;
import com.guwr.accumulate.facade.account.entity.AccountBalanceRecord;
import com.guwr.accumulate.facade.account.facade.IAccountBalanceFacade;
import com.guwr.accumulate.facade.account.facade.IAccountBalanceRecordFacade;
import com.guwr.accumulate.facade.account.vo.AccountBalanceRecordVO;
import com.guwr.accumulate.facade.notify.entity.NotifyTransactionMessage;
import com.guwr.accumulate.facade.notify.facade.INotifyTransactionMessageFacade;
import com.guwr.accumulate.facade.notify.vo.NotifyMessageVO;
import com.guwr.accumulate.facade.notify.vo.NotifyTransactionMessageVO;
import com.guwr.accumulate.facade.user.entity.UserProductInvest;
import com.guwr.accumulate.facade.user.entity.UserProductLevel;
import com.guwr.accumulate.facade.user.facade.*;
import com.guwr.accumulate.facade.user.vo.UserProductLevelVO;
import com.guwr.accumulate.facade.wmps.entity.Product;
import com.guwr.accumulate.facade.wmps.entity.ProductRecord;
import com.guwr.accumulate.facade.wmps.entity.ProductRecordExtend;
import com.guwr.accumulate.facade.wmps.enums.ProductRecordStatus;
import com.guwr.accumulate.facade.wmps.enums.ProductStatus;
import com.guwr.accumulate.facade.wmps.exception.WmpsBizException;
import com.guwr.accumulate.facade.wmps.vo.ProductRecordVO;
import com.guwr.accumulate.service.wmps.core.dao.ProductRecordRepository;
import com.guwr.accumulate.service.wmps.core.service.IProductRecordService;
import com.guwr.accumulate.service.wmps.core.service.IProductService;
import com.guwr.accumulate.service.wmps.task.InterestTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by gwr
 * Description 产品购买记录
 * Path com.guwr.accumulate.service.wmps.service.impl.ProductRecordService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class ProductRecordService implements IProductRecordService {

    private final static BigDecimal MULTIPLE_AMOUNT = new BigDecimal(100d);//投资倍数
    private static Logger logger = LoggerFactory.getLogger(ProductRecordService.class);
    @Autowired
    private ProductRecordRepository repository;
    @Autowired
    private IProductService productService;
    @Autowired
    private IUserInfoFacade userInfoFacade;
    @Autowired
    private IAccountBalanceRecordFacade accountBalanceRecordFacade;
    @Autowired
    private IAccountBalanceFacade accountBalanceFacade;
    @Autowired
    private IUserProductEarningsFacade userProductEarningsFacade;
    @Autowired
    private INotifyTransactionMessageFacade notifyTransactionMessageFacade;
    @Autowired
    private IUserProductDayInterFacade userProductDayInterFacade;
    @Autowired
    private IUserProductFundsInfoFacade userProductFundsInfoFacade;
    @Autowired
    private IUserProductLevelFacade userProductLevelFacade;
    @Autowired
    private IUserProductInvestFacade userProductInvestFacade;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductRecord save(ProductRecord entity) {
        return repository.save(entity);
    }

    @Override
    public ProductRecord findOne(Integer id) {
        return repository.findOne(id);
    }


    @Override
    public void interestTask() {
        /**
         * 1、获取当天需要发息的总人数
         */
        int interestDate = 1481817600;
        int size = 3;//默认一个线程跑5个
        int interestCount = this.findInterestCount(interestDate);
        int threadTaskSize; // 线程数
        if (interestCount % size == 0) {
            threadTaskSize = interestCount / size;
        } else {
            threadTaskSize = interestCount / size + 1;
        }
        ExecutorService executorService = Executors.newFixedThreadPool(threadTaskSize);
        for (int i = 0; i < threadTaskSize; i++) {
            InterestTask task = new InterestTask(threadTaskSize, i, interestDate);
            task.setProductRecordService(this);
            task.setUserProductDayInterFacade(userProductDayInterFacade);
            task.setUserProductFundsInfoFacade(userProductFundsInfoFacade);
            task.setUserProductEarningsFacade(userProductEarningsFacade);
            task.setAccountBalanceRecordFacade(accountBalanceRecordFacade);
            FutureTask<Integer> funcTrue = new FutureTask<>(task);
            executorService.execute(funcTrue);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addProductRecord(ProductRecordVO info) {
        logger.info("addProductRecord.info = [" + info + "]");
        Integer uid = info.getUid();
        Integer pid = info.getPid();
        String uuid = StringUtils.getUUID();
        Date date = new Date();
        BigDecimal investAmount = info.getInvestAmount();

        check(info); //检查

        Product product = productService.findOneCheck(pid);//加载购买产品信息

        if (!Objects.equals(ProductStatus.PUBLISHED.getValue(), product.getStatus())) {//状态是否为已发布
            throw WmpsBizException.CHAN_PIN_WEI_KAI_SHI_REN_GOU.print();
        }

        BigDecimal productInvestAmount = product.getInvestAmount();//投资总额
        BigDecimal productEffectAmount = product.getEffectAmount();//有效总额
        BigDecimal amount = product.getAmount();

        BigDecimal subtractAmount = amount.subtract(productEffectAmount); //剩余金额

        BigDecimal effectAmount;//有效金额
//        if (investAmount.compareTo(subtractAmount) > 0) {
//            effectAmount = subtractAmount;
//        } else {
//            effectAmount = investAmount;
//        }
        if (investAmount.compareTo(subtractAmount) == 1) { // 投资金额>可投总额
            throw WmpsBizException.TOU_ZI_JIN_E_DA_YU_CHAN_PIN_KE_TOU_ZONG_E.print();
        }

        effectAmount = investAmount;

        BigDecimal invest_amount = productInvestAmount.add(investAmount); //总投资金额
        BigDecimal effect_amount = productEffectAmount.add(effectAmount); //总有效金额
        product.setInvestAmount(invest_amount);
        product.setEffectAmount(effect_amount);
        product.setUpdateTime(date);

        BigDecimal invest = effectAmount;
        UserProductInvest userProductInvest = userProductInvestFacade.findUserProductInvestByUid(uid);
        if (userProductInvest != null) {
            invest = invest.add(userProductInvest.getTotalInvest());
        }

        UserProductLevel userProductLevel = userProductLevelFacade.findUserProductLevelByInvest(invest);
        BigDecimal interestrate = userProductLevel.getInterestrate();//vip级别利息

        //预期收益
        BigDecimal proearn = AmountUtils.calculateProearn(interestrate, product.getInterestrate(), product.getPhases(), effectAmount);

        /**
         *  添加资金流水，同时修改用户账户总额
         */
        AccountBalanceRecordVO balanceRecordVO = new AccountBalanceRecordVO();
        balanceRecordVO.setAmount(effectAmount);
        balanceRecordVO.setUid(uid);
        balanceRecordVO.setUuid(uuid);
        AccountBalanceRecord outgo = accountBalanceRecordFacade.outgo(balanceRecordVO);
        logger.info("addProductRecord.outgo = " + outgo);
        /**
         * 添加用户投资记录
         */
        ProductRecord productRecord = new ProductRecord();
        productRecord.setProearn(proearn);
        productRecord.setInterestrate(interestrate);
        productRecord.setEffectAmount(effectAmount);
        productRecord.setInvestAmount(investAmount);
        productRecord.setCreateTime(date);
        productRecord.setUpdateTime(date);
        productRecord.setPid(pid);
        productRecord.setUid(uid);
        productRecord.setUuid(uuid);
        productRecord.setStatus(ProductRecordStatus.WMPS_BUY_RECORD_STATUS_SUCCESS.getValue());

        NotifyTransactionMessageVO transactionMessageVO = buildMessageByNotifyMessageVO(uid, uuid, pid);

        NotifyTransactionMessageVO transactionMessageVO1 = buildMessageByNotifyTransactionMessageVO(uid, effectAmount, uuid, product.getPhases(), product.getInterestrate(), pid);

        NotifyTransactionMessage notifyMessage = notifyTransactionMessageFacade.saveNotifyTransactionMessage(transactionMessageVO);
        logger.info(uid + "_保存邮件待确认消息");
        NotifyTransactionMessage notifyTransactionMessage = notifyTransactionMessageFacade.saveNotifyTransactionMessage(transactionMessageVO1);
        logger.info(uid + "_保存用户投资等级待确认消息");

        this.save(productRecord);  //保存购买记录
        this.productService.update(product);//更新产品购买金额

        notifyTransactionMessageFacade.sendNotifyTransactionMessage(notifyMessage);
        logger.info(uid + "_发送邮件已确认消息到MQ");
        notifyTransactionMessageFacade.sendNotifyTransactionMessage(notifyTransactionMessage);//将消息发送至mq
        logger.info(uid + "_发送用户投资等级已确认消息到MQ");
    }


    @Override
    public ProductRecord findOneProductRecordByUUID(String uuid) {
        return repository.findOneProductRecordByUUID(uuid);
    }

    @Override
    public ProductRecord updateProductRecord(ProductRecord productRecord) {
        return repository.save(productRecord);
    }

    @Override
    public int findInterestCount(int interestDate) {
        logger.info("interestDate = [" + interestDate + "]");
        return repository.findInterestCount(interestDate);
    }

    @Override
    public List<Integer> findListByMOD(Integer mod, Integer number, Integer interestDate) {
        logger.info("findListByMOD.mod = [" + mod + "], number = [" + number + "], interestDate = [" + interestDate + "]");
        return repository.findListByMOD(mod, number, interestDate);
    }

    @Override
    public List<ProductRecordExtend> findProductRecordExtendListByMOD(Integer mod, Integer number, Integer interestDate) {
        logger.info("findProductRecordExtendListByMOD.mod = [" + mod + "], number = [" + number + "], interestDate = [" + interestDate + "]");
        return repository.findProductRecordExtendListByMOD(mod, number, interestDate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProearnAndInterestrateByUUID(ProductRecordVO info) {
        String uuid = info.getUuid();
        BigDecimal proearn = info.getProearn();
        BigDecimal interestrate = info.getInterestrate();
        logger.info("{}_根据UUID修改投资记录利率与预期收益", uuid);
        ProductRecord productRecord = findOneProductRecordByUUID(uuid);
        if (productRecord == null) {
            System.out.println("---------------------------------------------------------------------------_" + uuid);
            throw WmpsBizException.CHAN_PIN_BU_CUN_ZAI.print();
        }
        productRecord.setProearn(proearn);
        productRecord.setInterestrate(interestrate);
        updateProductRecord(productRecord);
    }

    /**
     * 检查参数是否有效
     *
     * @param info
     */
    private void check(ProductRecordVO info) {
        Integer uid = info.getUid();
        BigDecimal investAmount = info.getInvestAmount();
        BigDecimal[] divideAndRemainder = investAmount.divideAndRemainder(MULTIPLE_AMOUNT);
        if (investAmount.compareTo(BigDecimal.ZERO) < 1) {
            throw WmpsBizException.TOU_ZI_JIN_E_YOU_WU.print();
        }
        if (divideAndRemainder[1].compareTo(BigDecimal.ZERO) != 0) { //不是100的整倍数
            throw WmpsBizException.TOU_ZI_JIN_E_YOU_WU.print();
        }
        userInfoFacade.findOneCheck(uid);

        AccountBalance accountBalance = accountBalanceFacade.findOneByUidCheck(uid);

        BigDecimal balance = accountBalance.getBalance();//账户余额
        if (investAmount.compareTo(balance) > 0) { //账户余额是否足够
            throw WmpsBizException.YU_E_BU_ZU.print();
        }
    }

    /**
     * 组装预发送消息
     *
     * @param uid
     * @param uuid
     * @param pid
     * @return
     */
    public NotifyTransactionMessageVO buildMessageByNotifyMessageVO(Integer uid, String uuid, Integer pid) {

        NotifyMessageVO notifyMessageVO = new NotifyMessageVO();//消息体
        notifyMessageVO.setUid(uid);
        notifyMessageVO.setTitle(uid + "_购买产品");
        notifyMessageVO.setContent(uid + "_购买，" + pid + "_产品");
        notifyMessageVO.setUuid(uuid);
        notifyMessageVO.setConsumerQueue(NotifyDestination.MESSAGE_NOTIFY.name());
        String messageBody = JSON.toJSONString(notifyMessageVO);

        NotifyTransactionMessageVO info = new NotifyTransactionMessageVO();
        info.setMessageBody(messageBody);
        info.setUuid(uuid);
        info.setConsumerQueue(notifyMessageVO.getConsumerQueue());
        return info;
    }

    private NotifyTransactionMessageVO buildMessageByNotifyTransactionMessageVO(Integer uid, BigDecimal effectAmount, String uuid, Integer phases, BigDecimal interestrate, Integer pid) {
        UserProductLevelVO userProductLevelVO = new UserProductLevelVO();
        userProductLevelVO.setPid(pid);
        userProductLevelVO.setUid(uid);
        userProductLevelVO.setInvest(effectAmount);
        userProductLevelVO.setUuid(uuid);
        userProductLevelVO.setPhases(phases);
        userProductLevelVO.setInterestrate(interestrate);
        userProductLevelVO.setConsumerQueue(NotifyDestination.USER_PRODUCT_LEVEL_MESSAGE.name());
        String messageBody = JSON.toJSONString(userProductLevelVO);

        NotifyTransactionMessageVO info = new NotifyTransactionMessageVO();
        info.setMessageBody(messageBody);
        info.setUuid(uuid);
        info.setConsumerQueue(userProductLevelVO.getConsumerQueue());

        return info;
    }
}


