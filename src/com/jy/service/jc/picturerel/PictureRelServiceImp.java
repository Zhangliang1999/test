package com.jy.service.jc.picturerel;

import com.jy.entity.jc.picturerel.PictureRel;
import com.jy.repository.jc.picturerel.PictureRelDao;
import com.jy.service.base.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by susen-pc on 2016/9/4.
 */
@Service("PictureRelService")
public class PictureRelServiceImp extends BaseServiceImp<PictureRel> implements PictureRelService {

    @Autowired
    private PictureRelDao pictureRelDao;

}
