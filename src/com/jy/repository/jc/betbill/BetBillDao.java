package com.jy.repository.jc.betbill;

import com.jy.common.mybatis.Page;
import com.jy.entity.jc.betbill.BetBill;
import com.jy.entity.jc.betbill.BetDetail;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/4.
 */
@JYBatis
public interface BetBillDao extends BaseDao<BetBill> {
    /**
     * 竞猜明细
     * @param o
     * @param page
     * @return
     */
    public List<BetDetail> findBillDetailByPage(@Param("param")BetDetail o, Page<BetDetail> page);
}
