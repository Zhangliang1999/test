package com.jy.service.jc.activitycenter;

import com.jy.entity.jc.activitycenter.ActivityCenter;
import com.jy.entity.jc.userlevel.UserLevel;
import com.jy.repository.jc.activitycenter.ActivityCenterDao;
import com.jy.service.base.BaseServiceImp;
import com.jy.service.jc.userlevel.UserLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by susen-pc on 2016/9/17.
 */
@Service("ActivityCenterService")
public class ActivityCenterServiceImp extends BaseServiceImp<ActivityCenter> implements ActivityCenterService {
    @Autowired
    private ActivityCenterDao activityCenterDao;
}
