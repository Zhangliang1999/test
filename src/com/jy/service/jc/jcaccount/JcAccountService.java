package com.jy.service.jc.jcaccount;

import com.jy.entity.jc.jcaccount.JcAccount;
import com.jy.entity.jc.jcuserrank.JcUserRank;
import com.jy.service.base.BaseService;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/24.
 */
public interface JcAccountService extends BaseService<JcAccount> {
    //系统充值
    int sysRecharge(String operId, String accountId, int money);
}
