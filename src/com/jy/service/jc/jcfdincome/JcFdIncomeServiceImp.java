package com.jy.service.jc.jcfdincome;

import com.jy.entity.jc.jcfdincome.JcFdIncome;
import com.jy.repository.jc.jcfdincome.JcFdIncomeDao;
import com.jy.service.base.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by susen-pc on 2016/9/4.
 */
@Service("JcFdIncomeService")
public class JcFdIncomeServiceImp extends BaseServiceImp<JcFdIncome> implements JcFdIncomeService {

    @Autowired
    private JcFdIncomeDao dao;

}
