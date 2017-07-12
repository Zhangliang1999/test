package com.jy.service.jc.jcaccountrechargelog;

import com.jy.common.mybatis.Page;
import com.jy.entity.jc.jcaccountrechargelog.JcAccountRechargeLog;
import com.jy.entity.jc.jcaccountrechargelog.JcAccountRechargeView;
import com.jy.entity.jc.jcaccountrechargelog.JcAccountWithdrawView;
import com.jy.repository.jc.jcaccountrechargelog.JcAccountRechargeLogDao;
import com.jy.service.base.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/4.
 */
@Service("JcAccountRechargeLogService")
public class JcAccountRechargeLogServiceImp extends BaseServiceImp<JcAccountRechargeLog> implements JcAccountRechargeLogService {

    @Autowired
    private JcAccountRechargeLogDao dao;

    @Override
    public Page<JcAccountRechargeView> findRechargeViewByPage(JcAccountRechargeView o, Page<JcAccountRechargeView> page) {
        page.setResults(dao.findRechargeViewByPage(o, page));
        return page;
    }

    @Override
    public Page<JcAccountWithdrawView> findWithdrawViewByPage(JcAccountWithdrawView o, Page<JcAccountWithdrawView> page) {
        page.setResults(dao.findWithdrawViewByPage(o, page));
        return page;
    }

    @Override
    public List<JcAccountWithdrawView> exportExcel(JcAccountWithdrawView o) {
        return dao.findWithdrawViewByPage(o);
    }

    @Override
    public List<JcAccountRechargeView> exportExcel(JcAccountRechargeView o) {
        return dao.findRechargeViewByPage(o);
    }
}
