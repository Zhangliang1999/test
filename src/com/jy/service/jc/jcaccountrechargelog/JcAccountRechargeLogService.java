package com.jy.service.jc.jcaccountrechargelog;

import com.jy.common.mybatis.Page;
import com.jy.entity.jc.jcaccountrechargelog.JcAccountRechargeLog;
import com.jy.entity.jc.jcaccountrechargelog.JcAccountRechargeView;
import com.jy.entity.jc.jcaccountrechargelog.JcAccountWithdrawView;
import com.jy.entity.jc.jcuserrank.JcUserRank;
import com.jy.service.base.BaseService;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/24.
 */
public interface JcAccountRechargeLogService extends BaseService<JcAccountRechargeLog> {

    public Page<JcAccountRechargeView> findRechargeViewByPage(JcAccountRechargeView o, Page<JcAccountRechargeView> page);
    public Page<JcAccountWithdrawView> findWithdrawViewByPage(JcAccountWithdrawView o, Page<JcAccountWithdrawView> page);

    public List<JcAccountWithdrawView> exportExcel(JcAccountWithdrawView o);

    public List<JcAccountRechargeView> exportExcel(JcAccountRechargeView o);



}
