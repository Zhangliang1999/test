package com.jy.service.jc.betbill;

import com.jy.common.mybatis.Page;
import com.jy.entity.jc.betbill.BetBill;
import com.jy.entity.jc.betbill.BetDetail;
import com.jy.repository.jc.betbill.BetBillDao;
import com.jy.service.base.BaseServiceImp;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/17.
 */
@Service("BetBillService")
public class BetBillServiceImp extends BaseServiceImp<BetBill> implements BetBillService {
    @Autowired
    private BetBillDao dao;

    @Override
    public Page<BetDetail> findBillDetailByPage(@Param("param") BetDetail o, Page<BetDetail> page) {
        page.setResults(dao.findBillDetailByPage(o, page));
        return page;
    }
}
