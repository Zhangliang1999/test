package com.jy.service.jc.grounderbill;

import com.jy.common.mybatis.Page;
import com.jy.entity.jc.goods.Goods;
import com.jy.entity.jc.groundebill.GrounderBill;
import com.jy.entity.jc.groundebill.UserGrounderRel;
import com.jy.entity.jc.userrecommendrel.UserRecommendRel;
import com.jy.service.base.BaseService;

/**
 * Created by susen-pc on 2016/9/17.
 */
public interface GrounderBillService extends BaseService<GrounderBill> {

    Page<UserGrounderRel> findBuyByPage(UserGrounderRel o,Page<UserGrounderRel> page);
}
