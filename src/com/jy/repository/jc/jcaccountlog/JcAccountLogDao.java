package com.jy.repository.jc.jcaccountlog;

import com.jy.common.mybatis.Page;
import com.jy.entity.jc.jcaccountlog.JcAccountLog;
import com.jy.entity.jc.jcaccountrechargelog.JcAccountRechargeView;
import com.jy.entity.jc.jcuserrank.JcUserRank;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/26.
 */
@JYBatis
public interface JcAccountLogDao extends BaseDao<JcAccountLog> {
}
