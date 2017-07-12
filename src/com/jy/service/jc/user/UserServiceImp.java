package com.jy.service.jc.user;

import com.jy.entity.jc.user.User;
import com.jy.entity.jc.user.UserAddress;
import com.jy.repository.jc.user.UserDao;
import com.jy.service.base.BaseServiceImp;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/15.
 */
@Service("userService")
public class UserServiceImp extends BaseServiceImp<User> implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int getSendRecommendCnt(User o) {
        return userDao.getSendRecommendCnt(o);
    }

    @Override
    public int getBuyRecommendCnt(User o) {
        return userDao.getBuyRecommendCnt(o);
    }

    @Override
    public int getFollowCnt(User o) {
        return userDao.getFollowCnt(o);
    }

    @Override
    public int getFansCnt(User o) {
        return userDao.getFansCnt(o);
    }

    @Override
    public List<UserAddress> getUserAddress(UserAddress o) {
        return userDao.getUserAddress(o);
    }

	@Override
	public List<User> queryRobot() {
		List<User> list = userDao.queryRobot();
		return  list;
	}

}
