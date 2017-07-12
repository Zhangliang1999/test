package com.jy.repository.jc.grounderbill;

import com.jy.common.mybatis.Page;
import com.jy.entity.jc.goods.Goods;
import com.jy.entity.jc.groundebill.GrounderBill;
import com.jy.entity.jc.groundebill.UserGrounderRel;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/4.
 */
@JYBatis
public interface GrounderBillDao extends BaseDao<GrounderBill> {

    List<UserGrounderRel> findBuyByPage(@Param("param") UserGrounderRel o, Page<UserGrounderRel> page);

}
