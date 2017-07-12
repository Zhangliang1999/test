package com.jy.controller.jc.jcaccountrechargelog;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.mybatis.Page;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.poi.ObjectExcelView;
import com.jy.controller.base.BaseController;
import com.jy.entity.jc.jcaccountrechargelog.JcAccountRechargeLog;
import com.jy.entity.jc.jcaccountrechargelog.JcAccountRechargeView;
import com.jy.entity.jc.jcaccountrechargelog.JcAccountWithdrawView;
import com.jy.service.jc.jcaccountrechargelog.JcAccountRechargeLogService;
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
@RequestMapping("/jc/jcaccountrechargelog/")
public class JcAccountRechargeLogController extends BaseController<JcAccountRechargeLog> {

    @Autowired
    private JcAccountRechargeLogService service;

    //添加页面
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/jc/jcaccountrechargelog/add";
    }

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/jc/jcaccountrechargelog/list";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    //分页
    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<JcAccountRechargeLog> page, JcAccountRechargeLog o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/jcaccountrechargelog/index"))) {
            try {
                Page<JcAccountRechargeLog> accounts = service.findByPage(o, page);
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
    public AjaxRes find(JcAccountRechargeLog o) {
        AjaxRes ar=getAjaxRes();
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){
            try {
                List<JcAccountRechargeLog> list=service.find(o);
                JcAccountRechargeLog acount =list.get(0);
                ar.setSucceed(acount);
            } catch (Exception e) {
                logger.error(e.toString(),e);
                ar.setFailMsg(Const.DATA_FAIL);
            }
        }
        return ar;
    }

    //分页
    @RequestMapping(value = "findRechargeViewByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findRechargeViewByPage(Page<JcAccountRechargeView> page, JcAccountRechargeView o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/jcaccountrechargelog/index"))) {
            try {
                Page<JcAccountRechargeView> accounts = service.findRechargeViewByPage(o, page);
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
   * 充值导出到excel
   * @return
   */
    @RequestMapping(value = "/exportExcel")
    public ModelAndView exportExcel(JcAccountRechargeView o) {
        ModelAndView mv = this.getModelAndView();
        try {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<String> titles = new ArrayList<String>();
            titles.add("用户编码");        //1
            titles.add("手机号");        //2
            titles.add("昵称");            //3
            titles.add("余额");        //4
            titles.add("充值金额");        //5
            titles.add("充值方式");        //6
            titles.add("创建时间");        //7
            dataMap.put("titles", titles);
            List<JcAccountRechargeView> retData = service.exportExcel(o);
            List<Map<String, String>> varList = new ArrayList<Map<String, String>>();
            for (int i = 0; i < retData.size(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("var1", retData.get(i).getUserId());
                map.put("var2", retData.get(i).getMobile());
                map.put("var3", retData.get(i).getNickName());
                map.put("var4", String.valueOf(retData.get(i).getBalance()));
                map.put("var5", String.valueOf(retData.get(i).getMoney()));
                String payType = String.valueOf(retData.get(i).getPayType());
                String payTypeStr = "";
                if("1".equals(payType)){
                    payTypeStr = "AppStore";
                } else if("2".equals(payType)) {
                    payTypeStr = "支付宝";
                } else if("3".equals(payType)){
                    payTypeStr = "微信";
                }
                map.put("var6", payTypeStr);
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
