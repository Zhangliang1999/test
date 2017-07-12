package com.jy.service.jc.match;

import com.jy.entity.jc.match.Match;
import com.jy.service.base.BaseService;

/**
 * Created by susen-pc on 2016/9/17.
 */
public interface MatchService extends BaseService<Match> {

    int insertMatch(Match o);
    int updateMatch(Match o);
}
