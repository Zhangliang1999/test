package com.jy.service.jc.betbill;

import com.jy.common.mybatis.Page;
import com.jy.entity.jc.betbill.BetBill;
import com.jy.entity.jc.betbill.BetDetail;
import com.jy.entity.jc.jcuserrank.JcUserRank;
import com.jy.service.base.BaseService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/17.
 */
public interface BetBillService extends BaseService<BetBill> {

    /**
     * 竞猜明细
     * @param o
     * @param page
     * @return
     */
    public Page<BetDetail> findBillDetailByPage(@Param("param")BetDetail o, Page<BetDetail> page);
}
