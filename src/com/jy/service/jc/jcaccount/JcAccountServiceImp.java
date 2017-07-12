package com.jy.service.jc.jcaccount;

import com.jy.common.utils.base.UuidUtil;
import com.jy.entity.jc.analyst.Analyst;
import com.jy.entity.jc.jcaccount.JcAccount;
import com.jy.entity.jc.jcaccountlog.JcAccountLog;
import com.jy.entity.jc.user.User;
import com.jy.repository.jc.analyst.AnalystDao;
import com.jy.repository.jc.jcaccount.JcAccountDao;
import com.jy.repository.jc.jcaccountlog.JcAccountLogDao;
import com.jy.repository.jc.user.UserDao;
import com.jy.service.base.BaseServiceImp;
import com.jy.service.jc.analyst.AnalystService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by susen-pc on 2016/9/4.
 */
@Service("JcAccountService")
public class JcAccountServiceImp extends BaseServiceImp<JcAccount> implements JcAccountService {

    @Autowired
    private JcAccountDao jcAccountDao;
    @Autowired
    private JcAccountLogDao jcAccountLogDao;

    @Override
    @Transactional
    public int sysRecharge(String operId, String accountId, int money) {
        /**
         * 账户修改
         */
        JcAccount account = new JcAccount();
        account.setAccountId(accountId);
        JcAccount jcAccount = jcAccountDao.find(account).get(0);
        Integer lastBalance = jcAccount.getBalance();
        jcAccount.setBalance(lastBalance + money);//
        jcAccount.setUpdateTime(new Date());
        jcAccount.setOperId(operId);
        jcAccountDao.update(jcAccount);
        /**
         * 记录流水
         */
        JcAccountLog log = new JcAccountLog();
        log.setAccountLogId(UuidUtil.get32UUID());
        log.setAccountId(accountId); //
        log.setLastBalance(lastBalance);
        log.setCurrBalance(jcAccount.getBalance());
        log.setIncome(money);
        log.setSource(14);
        log.setCreateTime(new Date());
        jcAccountLogDao.insert(log);

        return 0;
    }
}
