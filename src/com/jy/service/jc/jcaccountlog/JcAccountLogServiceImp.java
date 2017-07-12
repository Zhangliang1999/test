package com.jy.service.jc.jcaccountlog;

import com.jy.entity.jc.jcaccountlog.JcAccountLog;
import com.jy.repository.jc.jcaccountlog.JcAccountLogDao;
import com.jy.service.base.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/4.
 */
@Service("JcAccountLogService")
public class JcAccountLogServiceImp extends BaseServiceImp<JcAccountLog> implements JcAccountLogService {

    @Autowired
    private JcAccountLogDao jcAccountLogDao;
}
