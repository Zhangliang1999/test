package com.jy.service.jc.recommendbill;

import com.jy.common.mybatis.Page;
import com.jy.entity.jc.jcuserrank.JcUserRank;
import com.jy.entity.jc.recommendbill.RecommendBill;
import com.jy.entity.jc.userrecommendrel.UserRecommendRel;
import com.jy.service.base.BaseService;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/14.
 */
public interface RecommendBillService extends BaseService<RecommendBill> {
    /**
     * 获得对象列表
     * @param o 对象
     * @param page 分页对象
     * @return List
     */
    public Page<UserRecommendRel> findBuyByPage(UserRecommendRel o,Page<UserRecommendRel> page);

    /**
     * 购买单
     * @param o
     * @return
     */
    List<UserRecommendRel> exportBuyExcel(UserRecommendRel o);
}
