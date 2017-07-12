package com.jy.repository.jc.jcaccountrechargelog;

import com.jy.common.mybatis.Page;
import com.jy.entity.jc.jcaccountrechargelog.JcAccountRechargeLog;
import com.jy.entity.jc.jcaccountrechargelog.JcAccountRechargeView;
import com.jy.entity.jc.jcaccountrechargelog.JcAccountWithdrawView;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/4.
 */
@JYBatis
public interface JcAccountRechargeLogDao extends BaseDao<JcAccountRechargeLog> {
    public List<JcAccountRechargeView> findRechargeViewByPage(@Param("param") JcAccountRechargeView o, Page<JcAccountRechargeView> page);
    public List<JcAccountWithdrawView> findWithdrawViewByPage(@Param("param") JcAccountWithdrawView o, Page<JcAccountWithdrawView> page);
    //全部提现
    public List<JcAccountWithdrawView> findWithdrawViewByPage(@Param("param") JcAccountWithdrawView o);
    //全部充值
    public List<JcAccountRechargeView> findRechargeViewByPage(@Param("param") JcAccountRechargeView o);

}
