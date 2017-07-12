package com.jy.repository.jc.recommendbill;

import com.jy.common.mybatis.Page;
import com.jy.entity.jc.jcuserrank.JcUserRank;
import com.jy.entity.jc.recommendbill.RecommendBill;
import com.jy.entity.jc.recommenddetail.RecommendDetail;
import com.jy.entity.jc.userrecommendrel.UserRecommendRel;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/4.
 */
@JYBatis
public interface RecommendBillDao extends BaseDao<RecommendBill> {

    /**
     * 获得对象列表
     * @param o 对象
     * @param page 分页对象
     * @return List
     */
    public List<UserRecommendRel> findBuyByPage(@Param("param") UserRecommendRel o, Page<UserRecommendRel> page);
    public List<UserRecommendRel> findBuyByPage(@Param("param") UserRecommendRel o);

}
