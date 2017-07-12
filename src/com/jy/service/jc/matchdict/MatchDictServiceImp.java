package com.jy.service.jc.matchdict;

import com.jy.entity.jc.activitycenter.ActivityCenter;
import com.jy.entity.jc.matchdict.MatchDict;
import com.jy.repository.jc.activitycenter.ActivityCenterDao;
import com.jy.repository.jc.matchdict.MatchDictDao;
import com.jy.service.base.BaseServiceImp;
import com.jy.service.jc.activitycenter.ActivityCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by susen-pc on 2016/9/17.
 */
@Service("MatchDictService")
public class MatchDictServiceImp extends BaseServiceImp<MatchDict> implements MatchDictService {
    @Autowired
    private MatchDictDao matchDictDao;
}
