package com.jy.service.jc.sensitiveword;

import com.jy.entity.jc.sensitiveword.SensitiveWord;
import com.jy.repository.jc.sensitiveword.SensitiveWordDao;
import com.jy.service.base.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by susen-pc on 2016/9/15.
 */
@Service("sensitiveWordService")
public class SensitiveWordServiceImp  extends BaseServiceImp<SensitiveWord> implements SensitiveWordService {
    @Autowired
    private SensitiveWordDao sensitiveWordDao;
}
