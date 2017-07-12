package com.jy.service.jc.matchgame;

import com.jy.entity.jc.matchgame.MatchGame;
import com.jy.repository.jc.matchgame.MatchGameDao;
import com.jy.service.base.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by susen-pc on 2016/9/4.
 */
@Service("MatchGameService")
public class MatchGameServiceImp extends BaseServiceImp<MatchGame> implements MatchGameService {

    @Autowired
    private MatchGameDao matchGameDao;

}
