package com.jy.repository.jc.user;

import com.jy.entity.jc.sensitiveword.SensitiveWord;
import com.jy.entity.jc.user.User;
import com.jy.entity.jc.user.UserAddress;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/4.
 */
@JYBatis
public interface UserDao extends BaseDao<User> {

    /**
     * 发单数
     * @param o
     * @return
     */
    public int getSendRecommendCnt(User o);

    /**
     * 购买发单数
     * @param o
     * @return
     */
    public int getBuyRecommendCnt(User o);

    /**
     * 关注的人数
     * @param o
     * @return
     */
    public int getFollowCnt(User o);

    /**
     * 粉丝人数
     * @param o
     * @return
     */
    public int getFansCnt(User o);

    /**
     * 用户地址
     * @param o
     * @return
     */
    List<UserAddress> getUserAddress(UserAddress o);
    
    /**
     * 所有的机器人
     * @return
     */
    List<User> queryRobot();
    
}
