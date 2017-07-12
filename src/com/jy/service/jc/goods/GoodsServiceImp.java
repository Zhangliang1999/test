package com.jy.service.jc.goods;

import com.jy.entity.jc.goods.Goods;
import com.jy.repository.jc.goods.GoodsDao;
import com.jy.service.base.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by susen-pc on 2016/9/17.
 */
@Service("GoodsService")
public class GoodsServiceImp extends BaseServiceImp<Goods> implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
}
