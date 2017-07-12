package com.jy.service.jc.analyst;

import com.jy.entity.jc.analyst.Analyst;
import com.jy.entity.system.account.Account;
import com.jy.service.base.BaseService;

/**
 * Created by susen-pc on 2016/9/4.
 */
public interface AnalystService  extends BaseService<Analyst> {


    /**
     * 成为分析师
     * @param analyst
     */
    public void becomeAnalyst(Analyst analyst);
}
