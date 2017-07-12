package com.jy.service.jc.groundercomplain;

import com.jy.entity.jc.groundebill.GrounderBill;
import com.jy.entity.jc.groundercomplain.GrounderComplain;
import com.jy.repository.jc.grounderbill.GrounderBillDao;
import com.jy.repository.jc.groundercomplain.GrounderComplainDao;
import com.jy.service.base.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by susen-pc on 2016/9/17.
 */
@Service("GrounderComplainService")
public class GrounderComplainServiceImp extends BaseServiceImp<GrounderComplain> implements GrounderComplainService {
    @Autowired
    private GrounderComplainDao grounderComplainDao;
}
