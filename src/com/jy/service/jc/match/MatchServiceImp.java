package com.jy.service.jc.match;

import com.jy.common.utils.base.UuidUtil;
import com.jy.entity.jc.match.JcMatchResult;
import com.jy.entity.jc.match.Match;
import com.jy.entity.jc.matchgame.MatchGame;
import com.jy.repository.jc.match.JcMatchResultMapper;
import com.jy.repository.jc.match.MatchDao;
import com.jy.repository.jc.matchgame.MatchGameDao;
import com.jy.service.base.BaseServiceImp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by susen-pc on 2016/9/17.
 */
@Service("MatchService")
public class MatchServiceImp extends BaseServiceImp<Match> implements MatchService {
    @Autowired
    private MatchDao dao;
    @Autowired
    private MatchGameDao gameDao;
    @Autowired
    private JcMatchResultMapper matchResultDao;

    @Override
    @Transactional
    public int insertMatch(Match o) {
        //进行事务，先添加比赛，再删除其下的玩法，再添加玩法
        int res = 0;
        String matchId = o.getMatchId();
        int count = dao.countMatch(o);
        if (StringUtils.isNotBlank(o.getCompId()) && count > 0) {
            res = 0;
        } else if (StringUtils.isNotBlank(matchId)) {
            dao.insert(o);
            dao.deleteItems(matchId);
            List<MatchGame> items = o.getItems();
            int i = 1;
            if (items != null && items.size() > 0) {
                for (MatchGame item : items) {
                    item.setMatchGameId(UuidUtil.get32UUID());
                    item.setMatchId(matchId);
                }
                dao.insertItems(items);
            }
            res = 1;
        }
        return res;
    }

    /**
     * 1. jc_match更新比赛结果,
     * 2. jc_match_game更新玩法结果,
     * 3. jc_match_result 添加记录。
     * @param o
     * @return
     */
    @Override
    @Transactional
    public int updateMatch(Match o) {
        //更新比赛，删除玩法，重新插入
        int res = 0;
        Short matchResult = o.getMatchResult();
        if(matchResult != null && matchResult != 4) {//有比赛结果，并且比赛结果不为取消
            String score = o.getScore();
            String[] split = score.split(":");
            BigDecimal score1 = new BigDecimal(split[0]);
            BigDecimal score2= new BigDecimal(split[1]);
            List<MatchGame> items = o.getItems();
            for(MatchGame game : items) {
//                Short gameMatchResult = checkGameMatchResult(matchResult, score1, score2, game.getData2(), game.getBetType());
                Short gameMatchResult = checkGameMatchResult(o,game.getData2(),game.getBetType());
                game.setMatchResult(gameMatchResult);
                game.setUpdateTime(o.getUpdateTime());
                gameDao.update(game);
            }
        }
        dao.update(o);//
        /**
         * 比赛结果接口表，后台有定时器去跑后台的程序
         */
        JcMatchResult jcMatchResult = new JcMatchResult();
        jcMatchResult.setMatchType(o.getMatchType());
        jcMatchResult.setCreateTime(new Date());
        jcMatchResult.setMatchId(o.getMatchId());
        jcMatchResult.setState((short)0);
        matchResultDao.insert(jcMatchResult);

        return res;
    }

    /**
     * 判断输赢
     *
     * @param jcMatch
     * @param data2
     * @param betType
     * @return 1胜，3负
     */
    public Short checkGameMatchResult(Match jcMatch, String data2, Short betType) {
        String[] scores = jcMatch.getScore().split(":");
        BigDecimal scoreTeam1 = new BigDecimal(scores[0]);
        BigDecimal scoreTeam2 = new BigDecimal(scores[1]);
        Integer sumscore = jcMatch.getSumscore();
        Short matchResult = jcMatch.getMatchResult();
        short gameMatchResult = 0;
        if (betType == 1) {//亚洲让分盘
            short asianHandicap = getAsianHandicap(scoreTeam1, scoreTeam2, data2, (short) 1);
            if(asianHandicap == 2 || asianHandicap == 6 || asianHandicap == 8) {//负：一半负，负
                gameMatchResult = 3;
            } else {
                gameMatchResult = 1;
            }
        } else if (betType == 2) { //进球大小盘
            gameMatchResult = getTo(scoreTeam1, scoreTeam2, data2);
        } else if (betType == 3) { //全场赛果
            gameMatchResult = matchResult;
        } else if (betType == 4) { //4让分盘
            gameMatchResult = getRf(scoreTeam1, scoreTeam2, data2);
        } else if (betType == 5) {//5比赛总分
            gameMatchResult = getTo(scoreTeam1, scoreTeam2, data2);
        } else if (betType == 6) {//6全场赛果
            gameMatchResult = matchResult;
        } else if (betType == 7) {//7赛局让分盘
            gameMatchResult = getRf(scoreTeam1, scoreTeam2, sumscore+""); //
        } else if (betType == 8) {//总局数
            gameMatchResult = getTo(scoreTeam1, scoreTeam2, sumscore+""); //
        } else if (betType == 10) {//比赛获胜
            gameMatchResult = matchResult;
        } else if (betType == 11) {//让垒盘
            gameMatchResult = getRf(scoreTeam1, scoreTeam2, data2);
        } else if (betType == 12) {//比赛获胜
            gameMatchResult = matchResult;
        } else if (betType == 13) {//比赛总分
            gameMatchResult = getTo(scoreTeam1, scoreTeam2, data2);
        } else if (betType == 14) {//比赛让局盘
            gameMatchResult = getRf(scoreTeam1, scoreTeam2, data2);
        } else if (betType == 15) {//比赛总局数
            gameMatchResult = getTo(scoreTeam1, scoreTeam2, data2);
        } else if (betType == 16) {//比赛获胜
            gameMatchResult = matchResult;
        }
        return gameMatchResult;
    }

    /**
     * 亚洲让分盘
     * 0.25 平均 让分方：平局一半无效，一半输
     * 0.5 平均 让分方：平局也算输
     * 0.75 平均 让分方：赢一球 算一半平，一半赢， 赢两球才算赢
     * 发单或竞猜比赛的结果：
     * 1、赢
     * 2、输
     * 4 、比赛取消
     * 5、 一半无效，一半赢
     * 6 、一半无效，一半输
     * 7、一半平，一半赢
     * 8、一半平，一半输
     * 9、无效
     *
     * @param scoreTeam1
     * @param scoreTeam2
     * @param data2
     * @param betSelect 用户选择
     */
    public short getAsianHandicap(BigDecimal scoreTeam1, BigDecimal scoreTeam2, String data2, short betSelect) {
        boolean isRf = false; //是否是让分方
        String sub = "0"; //后面的小数
        if (data2.length() > 1 && data2.startsWith("-")) {
            isRf = true; //是让分方
        }
        String[] split = data2.split("\\.");
        int rf = Integer.valueOf(split[0]); //让分
        if (split.length > 1) {
            sub = "." + split[1];
        }
        Short matchResult = getRf(scoreTeam1, scoreTeam2, rf + ""); ////比赛结果

        short state = 0; //状态
        if (".25".equals(sub)) {//平半
            if (matchResult == 2) {//1. 比赛平
                if (isRf) {//一半无效，一半输
                    state = 6;
                } else {//一半无效，一半赢
                    state = 5;
                }
            } else if (betSelect == matchResult) {//
                state = 1; //赢
            } else { //3.比赛负
                state = 2; //输
            }
        } else if (".5".equals(sub)) {//半球
            if (matchResult == 2) {//平
                if (isRf) {//输
                    state = 2;
                } else {//赢
                    state = 1;
                }
            } else if (betSelect == matchResult) {
                state = 1; //赢
            } else {
                state = 2; //输
            }
        } else if (".75".equals(sub)) {
            if (isRf) {
                if (betSelect == 1) {
                    BigDecimal subtract = scoreTeam1.subtract(scoreTeam2);
                    if (subtract.intValue() > (1 + rf)) {//赢
                        state = 1;
                    } else if (subtract.intValue() == (1 + rf)) {//一半赢，一半平
                        state = 7;
                    } else {//输
                        state = 2;
                    }
                } else {//选客赢
                    BigDecimal subtract = scoreTeam2.subtract(scoreTeam1); //减的
                    if (subtract.intValue() > (1 + rf)) {//赢
                        state = 1;
                    } else if (subtract.intValue() == (1 + rf)) {//一半赢，一半平
                        state = 7;
                    } else {//输
                        state = 2;
                    }
                }
            } else { //主是受让方
                if (betSelect == 2) {//选择客赢
                    BigDecimal subtract = scoreTeam2.subtract(scoreTeam1); //减的
                    if (subtract.intValue() > (1 + rf)) {//赢
                        state = 1;
                    } else if (subtract.intValue() == (1 + rf)) {//一半赢，一半平
                        state = 7;
                    } else {//输
                        state = 2;
                    }
                } else {//选择主赢
                    BigDecimal subtract = scoreTeam2.subtract(scoreTeam1); //减的
                    if (scoreTeam1.doubleValue() >= scoreTeam2.doubleValue()) {//赢
                        state = 1;
                    } else if (subtract.intValue() == (1 + rf)) {//一半平，一半输
                        state = 8;
                    } else {//输
                        state = 2;
                    }
                }
            }

        } else {
            if (matchResult == 2) {
                state = 9;
            } else if (betSelect == matchResult) {
                state = 1;
            } else {
                state = 2;
            }
        }
        return state;
    }

   /* *//**
     * 判断输赢
     *
     * @param matchResult
     * @param scoreTeam1
     * @param scoreTeam2
     * @param data2
     * @param betType
     * @return
     *//*
    public Short checkGameMatchResult(Short matchResult, BigDecimal scoreTeam1, BigDecimal scoreTeam2, String data2, Short betType) {
        short gameMatchResult = 0;
        if (betType == 1) {//亚洲让分盘
            gameMatchResult = getRf(scoreTeam1, scoreTeam2, data2);
        } else if (betType == 2) { //进球大小盘
            gameMatchResult = getTo(scoreTeam1, scoreTeam2, data2);
        } else if (betType == 3) { //全场赛果
            gameMatchResult = matchResult;
        } else if (betType == 4) { //4让分盘
            gameMatchResult = getRf(scoreTeam1, scoreTeam2, data2);
        } else if (betType == 5) {//5比赛总分
            gameMatchResult = getTo(scoreTeam1, scoreTeam2, data2);
        } else if (betType == 6) {//6全场赛果
            gameMatchResult = matchResult;
        } else if (betType == 7) {//7赛局让分盘
            gameMatchResult = getRf(scoreTeam1, scoreTeam2, data2);
        } else if (betType == 8) {//总局数
            gameMatchResult = getTo(scoreTeam1, scoreTeam2, data2);
        } else if (betType == 10) {//比赛获胜
            gameMatchResult = matchResult;
        } else if (betType == 11) {//让垒盘
            gameMatchResult = getRf(scoreTeam1, scoreTeam2, data2);
        } else if (betType == 12) {//比赛获胜
            gameMatchResult = matchResult;
        } else if (betType == 13) {//比赛总分
            gameMatchResult = getTo(scoreTeam1, scoreTeam2, data2);
        } else if (betType == 14) {//比赛让局盘
            gameMatchResult = getRf(scoreTeam1, scoreTeam2, data2);
        } else if (betType == 15) {//比赛总局数
            gameMatchResult = getTo(scoreTeam1, scoreTeam2, data2);
        } else if (betType == 16) {//比赛获胜
            gameMatchResult = matchResult;
        }
        return gameMatchResult;
    }
*/
    /**
     * 大小球
     *
     * @param scoreTeam1
     * @param scoreTeam2
     * @param data2      总比分
     * @return
     */
    private Short getTo(BigDecimal scoreTeam1, BigDecimal scoreTeam2, String data2) {
        Short matchResult;
        BigDecimal score = BigDecimal.valueOf(Double.valueOf(data2));
        BigDecimal add = scoreTeam1.add(scoreTeam2); //实际总比分
        if (add.doubleValue() > score.doubleValue()) {
            matchResult = 1;//胜
        } else {
            matchResult = 3;//负
        }
        return matchResult;
    }

    /**
     * 让分
     *
     * @param scoreTeam1
     * @param scoreTeam2
     * @param data2      让分
     * @return
     */
    private Short getRf(BigDecimal scoreTeam1, BigDecimal scoreTeam2, String data2) {
        Short matchResult;
        BigDecimal rf = BigDecimal.valueOf(Double.valueOf(data2));
        BigDecimal subtract = scoreTeam1.add(rf);
        if (subtract.doubleValue() > scoreTeam2.doubleValue()) {//
            matchResult = 1;//胜
        } else if (subtract.doubleValue() == scoreTeam2.doubleValue()) {
            matchResult = 2;//平
        } else {
            matchResult = 3;//负
        }
        return matchResult;
    }

    /**
     * 转换比赛结果
     *
     * @param winner
     * @return
     */
    private Short parseWinner(String winner) {
        short matchResult = 0; //无结果
        if ("1".equals(winner)) {//胜
            matchResult = 1;
        } else if ("2".equals(winner)) {//负
            matchResult = 3;
        } else if ("3".equals(winner)) {//平
            matchResult = 2;
        }
        return matchResult;
    }
}
