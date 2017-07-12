package com.jy.controller.jc.jcaccount;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.mybatis.Page;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.poi.ObjectExcelView;
import com.jy.controller.base.BaseController;
import com.jy.entity.jc.jcaccount.JcAccount;
import com.jy.entity.jc.jcuserrank.JcUserRank;
import com.jy.entity.jc.user.User;
import com.jy.service.jc.jcaccount.JcAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jc/jcaccount/")
public class JcAccountController extends BaseController<JcAccount> {

    @Autowired
    private JcAccountService service;

    //添加页面
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/jc/jcaccount/add";
    }

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/jc/jcaccount/list";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    //分页
    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<JcAccount> page, JcAccount o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/jcaccount/index"))) {
            try {
                Page<JcAccount> accounts = service.findByPage(o, page);
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
    public AjaxRes find(JcAccount o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                List<JcAccount> list = service.find(o);
                JcAccount acount = list.get(0);
                ar.setSucceed(acount);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.DATA_FAIL);
            }
        }
        return ar;
    }
    /*
      * 导出到excel
      * @return
      */
    @RequestMapping(value = "/exportExcel")
    public ModelAndView exportExcel(JcAccount o) {
        ModelAndView mv = this.getModelAndView();
        try {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<String> titles = new ArrayList<String>();
            titles.add("用户编码");
            titles.add("账户编码");
            titles.add("手机号");
            titles.add("昵称");
            titles.add("当前余额");
            titles.add("创建时间");
            titles.add("更新时间");
            titles.add("操作人");
            dataMap.put("titles", titles);
            List<JcAccount> retData = service.exportExcel(o);
            List<Map<String, String>> varList = new ArrayList<Map<String, String>>();
            for (int i = 0; i < retData.size(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                int index = 1;
                map.put("var" + index++, retData.get(i).getUserId());
                map.put("var" + index++, retData.get(i).getAccountId());
                map.put("var" + index++, retData.get(i).getMobile());
                map.put("var" + index++, retData.get(i).getNick_name());
                map.put("var" + index++, String.valueOf(retData.get(i).getBalance()));
                map.put("var" + index++, DateUtils.formatDate(retData.get(i).getCreateTime(), DateUtils.parsePatterns[3]));
                map.put("var" + index++, DateUtils.formatDate(retData.get(i).getUpdateTime(), DateUtils.parsePatterns[3]));
                map.put("var" + index++, retData.get(i).getOperId());
                varList.add(map);
            }
            dataMap.put("varList", varList);
            ObjectExcelView erv = new ObjectExcelView();
            mv = new ModelAndView(erv, dataMap);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }

    //添加页面
    @RequestMapping("/toSysRecharge")
    public String toSysRecharge() {
        return "/jc/jcaccount/sysRecharge";
    }

    /**
     * 系统充值
     *
     * @param o
     * @return
     */
    @RequestMapping(value = "sysRecharge", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes sysRecharge(JcAccount o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                service.sysRecharge(getCurrUserId(), o.getAccountId(), o.getBalance());
                ar.setSucceedMsg(Const.SAVE_SUCCEED);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.SAVE_FAIL);
            }
        }
        return ar;
    }

}
