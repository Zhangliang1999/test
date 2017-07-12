package com.jy.service.jc.analyst;

import com.jy.common.jc.JcConst;
import com.jy.entity.jc.analyst.Analyst;
import com.jy.entity.jc.jcuserstatis.JcUserStatis;
import com.jy.entity.jc.userlevel.UserLevel;
import com.jy.repository.jc.analyst.AnalystDao;
import com.jy.repository.jc.jcuserstatis.JcUserStatisDao;
import com.jy.repository.jc.userlevel.UserLevelDao;
import com.jy.service.base.BaseServiceImp;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by susen-pc on 2016/9/4.
 */
@Service("AnalystService")
public class AnalystServiceImp extends BaseServiceImp<Analyst> implements AnalystService {

    @Autowired
    private AnalystDao analystDao;
    @Autowired
    private UserLevelDao userLevelDao;
    @Autowired
    private JcUserStatisDao userStatisDao;

    @Override
    @Transactional
    public void becomeAnalyst(Analyst analyst) {
        UserLevel param = new UserLevel();
        param.setUserId(analyst.getUserId());
        param.setMatchType(Short.valueOf(analyst.getMatchType()));
        /**
         * 用户等级
         */
        List<UserLevel> userLevels = userLevelDao.find(param);
        if(userLevels.size() > 0) {
            UserLevel userLevel = userLevels.get(0);
            userLevel.setUpgradeType((short)2); //指定
            userLevel.setLevel((short)4); //知名专家
            userLevel.setUpdateTime(new Date());
            userLevelDao.update(userLevel);
        } else {
            UserLevel userLevel = new UserLevel();
            userLevel.setUpgradeType((short)2); //指定
            userLevel.setLevel((short)4); //知名专家
            userLevel.setUpdateTime(new Date());
            userLevel.setBetCnt(0);
            userLevel.setId(JcConst.buildOrderId(new Date()));
            userLevel.setIsValid(1);
            userLevel.setLoseCnt(0);
            userLevel.setMatchType(Short.valueOf(analyst.getMatchType()));
            userLevel.setProfitRate(new BigDecimal(0));
            userLevel.setWinCnt(0);
            userLevelDao.insert(userLevel);
        }
        /**
         * 用户统计
         */
        JcUserStatis userStaisParam = new JcUserStatis();
        userStaisParam.setUserId(analyst.getUserId());
        List<JcUserStatis> userStatisList = userStatisDao.find(userStaisParam);
        if(userStatisList != null && userStatisList.size() > 0) {
            JcUserStatis userStatis = userStatisList.get(0);
            userStatis.setHighestLevel(4); //知名
            userStatis.setUpdateTime(new Date());
            //计算强项
            userStatis.setStrongPoint(calcStrongPoint(userStatis.getStrongPoint(), analyst.getMatchType()));
            userStatisDao.update(userStatis);
        }
        analystDao.update(analyst);
    }

    /**
     * 计算强项
     * @param strongPoint
     * @param matchType
     * @return
     */
    private String calcStrongPoint(String strongPoint, Short matchType) {
        if(StringUtils.isBlank(strongPoint)) {
            strongPoint = "000000";
        }
        return  StringUtils.left(strongPoint, matchType-1) +"1"+ StringUtils.right(strongPoint, 6-matchType);
    }

    public static void main(String[] args) {
        String strongPoint = "abcdef";
        int matchType = 0;
        strongPoint =  StringUtils.left(strongPoint, matchType-1) +"1"+ StringUtils.right(strongPoint, 6-matchType);
        System.out.println(strongPoint);
    }
}
