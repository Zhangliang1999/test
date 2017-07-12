package com.jy.service.jc.userlevel;

import com.jy.entity.jc.userlevel.UserLevel;
import com.jy.repository.jc.userlevel.UserLevelDao;
import com.jy.service.base.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by susen-pc on 2016/9/15.
 */
@Service("userLevelService")
public class UserLevelServiceImp  extends BaseServiceImp<UserLevel> implements UserLevelService {

    @Autowired
    private UserLevelDao userLevelDao;
}
