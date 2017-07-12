package com.jy.controller.jc.user;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.jc.JcConst;
import com.jy.common.mybatis.Page;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.jc.user.User;
import com.jy.entity.jc.user.UserAddress;
import com.jy.service.jc.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/jc/user/")
public class UserController extends BaseController<User> {

    @Autowired
    private UserService service;

    //添加页面
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/jc/user/add";
    }

    //用户信息页面
    @RequestMapping("/toUserinfo")
    public String toUserinfo() {
        return "/jc/user/userinfo";
    }

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/jc/user/list";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    //分页
    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<User> page, User o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/user/index"))) {
            try {
                Page<User> accounts = service.findByPage(o, page);
                Map<String, Object> p = new HashMap<String, Object>();
                p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
                p.put("list", accounts);
                ar.setSucceed(p);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.DATA_FAIL);
            }
        }
        return ar;
    }


    //查看
    @RequestMapping(value = "find", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes find(User o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                List<User> list = service.find(o);
                User acount = list.get(0);
                ar.setSucceed(acount);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.DATA_FAIL);
            }
        }
        return ar;
    }

    //用户所有相关信息
    @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes getUserInfo(User o) {
        AjaxRes ar = getAjaxRes();
        try {
            List<User> list = service.find(o);
            User user = list.get(0);
            //所有
            int buyCnt = service.getBuyRecommendCnt(o);
            int sendCnt = service.getSendRecommendCnt(o);
            //本周
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
            String format = df.format(cal.getTime());
            System.out.println(format);
            user.setSearchStartTime(DateUtils.formatDate(cal.getTime()));
//            user.setSearchStartTime(cal.getTime());
            int buyCntByWeek = service.getBuyRecommendCnt(o);
            int sendCntByWeek = service.getSendRecommendCnt(o);
            //本月第一天
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            user.setSearchStartTime(DateUtils.formatDate(cal.getTime()));
//            user.setSearchStartTime(cal.getTime());
            int buyCntByMonth = service.getBuyRecommendCnt(o);
            int sendCntByMonth = service.getSendRecommendCnt(o);
            //关注的人数
            int followCnt = service.getFollowCnt(o);
            //粉丝人数
            int fansCnt = service.getFansCnt(o);
            //用户地址
            UserAddress userAddress = new UserAddress();
            userAddress.setUserId(o.getUserId());
            List<UserAddress> userAddressList = service.getUserAddress(userAddress);

            Map<String, Object> retMap = new HashMap<>();
            retMap.put("obj", user);
            retMap.put("buyCnt", buyCnt);
            retMap.put("sendCnt", sendCnt);
            retMap.put("buyCntByWeek", buyCntByWeek);
            retMap.put("sendCntByWeek", sendCntByWeek);
            retMap.put("buyCntByMonth", buyCntByMonth);
            retMap.put("sendCntByMonth", sendCntByMonth);
            retMap.put("followCnt", followCnt);
            retMap.put("fansCnt", fansCnt);
            retMap.put("userAddressList", userAddressList);

            ar.setSucceed(retMap);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(Const.DATA_FAIL);
        }
        return ar;
    }

    /**
     * 批量删除
     *
     * @param chks
     * @return
     */
    @RequestMapping(value = "deleteBatch", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes deleteBatch(String chks, HttpServletRequest request) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))) {
            try {
                if (StringUtils.isNotBlank(chks)) {
                    String[] chk = chks.split(",");
                    List<User> list = new ArrayList<User>();
                    for (String s : chk) {
                        User sd = new User();
                        sd.setUserId(s);
                        list.add(sd);
                    }
                    service.deleteBatch(list);
                }
                ar.setSucceedMsg(Const.DEL_SUCCEED);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.DEL_FAIL);
            }
        }
        return ar;
    }

    //删除
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes delete(User o, HttpServletRequest request) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                service.delete(o);
                ar.setSucceedMsg(Const.DEL_SUCCEED);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.DEL_FAIL);
            }
        }
        return ar;
    }

    /**
     * 更新（回复）
     *
     * @param o
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes update(User o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                o.setUpdateTime(new Date());
                o.setOperId(getCurrUserId());
                service.update(o);
                ar.setSucceedMsg(Const.UPDATE_SUCCEED);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.UPDATE_FAIL);
            }
        }
        return ar;
    }

    /**
     * 添加
     *
     * @param o
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes add(User o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))) {
            try {
                o.setUserId(get32UUID());
                o.setCreateTime(new Date());
                o.setIsRobot(0);
                o.setPassword(JcConst.INIT_PASSWORD);
                service.insert(o);
                ar.setSucceedMsg(Const.SAVE_SUCCEED);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.SAVE_FAIL);
            }
        }
        return ar;
    }
}
