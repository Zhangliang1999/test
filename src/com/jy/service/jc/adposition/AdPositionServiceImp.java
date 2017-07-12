package com.jy.service.jc.adposition;

import com.jy.entity.jc.adposition.AdPosition;
import com.jy.entity.jc.analyst.Analyst;
import com.jy.repository.jc.adposition.AdPositionDao;
import com.jy.repository.jc.analyst.AnalystDao;
import com.jy.service.base.BaseServiceImp;
import com.jy.service.jc.analyst.AnalystService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by susen-pc on 2016/9/4.
 */
@Service("adPositionService")
public class AdPositionServiceImp extends BaseServiceImp<AdPosition> implements AdPositionService{

    @Autowired
    private AdPositionDao adPositionDao;

}
