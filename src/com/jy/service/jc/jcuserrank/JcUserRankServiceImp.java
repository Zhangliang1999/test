package com.jy.service.jc.jcuserrank;

import com.jy.entity.jc.jcuserrank.JcUserRank;
import com.jy.repository.jc.jcuserrank.JcUserRankDao;
import com.jy.service.base.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/4.
 */
@Service("JcUserRankService")
public class JcUserRankServiceImp extends BaseServiceImp<JcUserRank> implements JcUserRankService {

    @Autowired
    private JcUserRankDao dao;
}
