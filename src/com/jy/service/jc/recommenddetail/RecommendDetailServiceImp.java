package com.jy.service.jc.recommenddetail;

import com.jy.entity.jc.recommenddetail.RecommendDetail;
import com.jy.repository.jc.recommenddetail.RecommendDetailDao;
import com.jy.service.base.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by susen-pc on 2016/9/4.
 */
@Service("RecommendDetailService")
public class RecommendDetailServiceImp extends BaseServiceImp<RecommendDetail> implements RecommendDetailService {

    @Autowired
    private RecommendDetailDao recommendDetailDao;

}
