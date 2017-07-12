package com.jy.controller.jc.jcaccountwithdraw;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.mybatis.Page;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.poi.ObjectExcelView;
import com.jy.controller.base.BaseController;
import com.jy.entity.jc.jcaccountrechargelog.JcAccountRechargeView;
import com.jy.entity.jc.jcaccountrechargelog.JcAccountWithdrawView;
import com.jy.service.jc.jcaccountrechargelog.JcAccountRechargeLogService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
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
@RequestMapping("/jc/jcaccountwithdraw/")
public class JcAccountWithdrawController extends BaseController<JcAccountWithdrawView> {

    @Autowired
    private JcAccountRechargeLogService service;

    //添加页面
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/jc/jcaccountwithdraw/add";
    }

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/jc/jcaccountwithdraw/list";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    //分页
    @RequestMapping(value = "findWithdrawViewByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findWithdrawViewByPage(Page<JcAccountWithdrawView> page, JcAccountWithdrawView o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/jcaccountwithdraw/index"))) {
            try {
                Page<JcAccountWithdrawView> accounts = service.findWithdrawViewByPage(o, page);
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

    /*
     * 提现导出到excel
     * @return
     */
    @RequestMapping(value = "/exportExcel")
    public ModelAndView exportExcel(JcAccountWithdrawView o) {
        ModelAndView mv = this.getModelAndView();
        try {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<String> titles = new ArrayList<String>();
            titles.add("用户编码");        //1
            titles.add("手机号");        //2
            titles.add("昵称");            //3
            titles.add("余额");        //4
            titles.add("提现金额");        //5
            titles.add("提现方式");        //6
            titles.add("创建时间");        //7
            dataMap.put("titles", titles);
            List<JcAccountWithdrawView> retData = service.exportExcel(o);
            List<Map<String, String>> varList = new ArrayList<Map<String, String>>();
            for (int i = 0; i < retData.size(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("var1", retData.get(i).getUserId());
                map.put("var2", retData.get(i).getMobile());
                map.put("var3", retData.get(i).getNickName());
                map.put("var4", String.valueOf(retData.get(i).getBalance()));
                map.put("var5", String.valueOf(retData.get(i).getMoney()));
                String withdrawType = String.valueOf(retData.get(i).getWithdrawType());
                String withdrawTypeStr = "";
                if ("1".equals(withdrawType)) {
                    withdrawTypeStr = "AppStore";
                } else if ("2".equals(withdrawType)) {
                    withdrawTypeStr = "支付宝";
                } else if ("3".equals(withdrawType)) {
                    withdrawTypeStr = "微信";
                }
                map.put("var6", withdrawTypeStr);
                map.put("var7", String.valueOf(DateUtils.formatDate(retData.get(i).getCreateTime(), DateUtils.parsePatterns[3])));
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


}
