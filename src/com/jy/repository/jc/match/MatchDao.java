package com.jy.repository.jc.match;

import com.jy.entity.jc.match.Match;
import com.jy.entity.jc.matchgame.MatchGame;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/4.
 */
@JYBatis
public interface MatchDao extends BaseDao<Match> {
    /**
     * 批量删除比赛玩法
     * @param matchId
     */
    public void deleteItems(@Param("matchId")String matchId);

    /**
     * 批量插入比赛玩法
     * @param o 集合
     */
    public void insertItems(List<MatchGame> o);

    public int countMatch(Match o);

    List<MatchGame> getItems(String matchId);
}
