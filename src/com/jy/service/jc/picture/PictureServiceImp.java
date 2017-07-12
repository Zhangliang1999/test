package com.jy.service.jc.picture;

import com.jy.entity.jc.analyst.Analyst;
import com.jy.entity.jc.picture.Picture;
import com.jy.repository.jc.analyst.AnalystDao;
import com.jy.repository.jc.picture.PictureDao;
import com.jy.service.base.BaseServiceImp;
import com.jy.service.jc.analyst.AnalystService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by susen-pc on 2016/9/4.
 */
@Service("pictureService")
public class PictureServiceImp extends BaseServiceImp<Picture> implements PictureService {

    @Autowired
    private PictureDao pictureDao;

}
