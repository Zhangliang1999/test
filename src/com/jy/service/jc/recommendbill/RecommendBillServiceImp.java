package com.jy.service.jc.recommendbill;

import com.jy.common.mybatis.Page;
import com.jy.entity.jc.recommendbill.RecommendBill;
import com.jy.entity.jc.recommenddetail.RecommendDetail;
import com.jy.entity.jc.userrecommendrel.UserRecommendRel;
import com.jy.repository.jc.recommendbill.RecommendBillDao;
import com.jy.service.base.BaseServiceImp;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/4.
 */
@Service("RecommendBillService")
public class RecommendBillServiceImp extends BaseServiceImp<RecommendBill> implements RecommendBillService {

    @Autowired
    private RecommendBillDao dao;

    @Override
    public Page<UserRecommendRel> findBuyByPage(UserRecommendRel o, Page<UserRecommendRel> page) {
        page.setResults(dao.findBuyByPage(o, page));
        return page;
    }

    @Override
    public List<UserRecommendRel> exportBuyExcel(UserRecommendRel o) {
        return dao.findBuyByPage(o);
    }
}
