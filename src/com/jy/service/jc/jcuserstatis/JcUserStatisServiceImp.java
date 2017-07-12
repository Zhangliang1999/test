package com.jy.service.jc.jcuserstatis;

import com.jy.entity.jc.jcuserstatis.JcUserStatis;
import com.jy.repository.jc.jcuserstatis.JcUserStatisDao;
import com.jy.service.base.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by susen-pc on 2016/9/4.
 */
@Service("JcUserStatisService")
public class JcUserStatisServiceImp extends BaseServiceImp<JcUserStatis> implements JcUserStatisService {

    @Autowired
    private JcUserStatisDao dao;
}
