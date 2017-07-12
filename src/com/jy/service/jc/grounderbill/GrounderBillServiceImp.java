package com.jy.service.jc.grounderbill;

import com.jy.common.mybatis.Page;
import com.jy.entity.jc.groundebill.GrounderBill;
import com.jy.entity.jc.groundebill.UserGrounderRel;
import com.jy.repository.jc.grounderbill.GrounderBillDao;
import com.jy.service.base.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by susen-pc on 2016/9/17.
 */
@Service("GrouderBillService")
public class GrounderBillServiceImp extends BaseServiceImp<GrounderBill> implements GrounderBillService {
    @Autowired
    private GrounderBillDao dao;

    @Override
    public Page<UserGrounderRel> findBuyByPage(UserGrounderRel o, Page<UserGrounderRel> page) {
        page.setResults(dao.findBuyByPage(o, page));
        return page;
    }
}
