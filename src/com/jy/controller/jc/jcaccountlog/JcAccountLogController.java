package com.jy.controller.jc.jcaccountlog;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.mybatis.Page;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.poi.ObjectExcelView;
import com.jy.controller.base.BaseController;
import com.jy.entity.jc.jcaccountlog.JcAccountLog;
import com.jy.entity.jc.jcaccountrechargelog.JcAccountRechargeLog;
import com.jy.entity.jc.jcuserrank.JcUserRank;
import com.jy.service.jc.jcaccountlog.JcAccountLogService;
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

import static com.jy.common.jc.JcConst.getSourceName;

@Controller
@RequestMapping("/jc/jcaccountlog/")
public class JcAccountLogController extends BaseController<JcAccountLog> {

    @Autowired
    private JcAccountLogService service;

    //添加页面
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/jc/jcaccountlog/add";
    }

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/jc/jcaccountlog/list";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    //分页
    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<JcAccountLog> page, JcAccountLog o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/jcaccountlog/index"))) {
            try {
                Page<JcAccountLog> accounts = service.findByPage(o, page);
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
    public AjaxRes find(JcAccountLog o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                List<JcAccountLog> list = service.find(o);
                JcAccountLog acount = list.get(0);
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
    public ModelAndView exportExcel(JcAccountLog o) {
        ModelAndView mv = this.getModelAndView();
        try {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<String> titles = new ArrayList<String>();
            titles.add("账户流水编码");
            titles.add("账号");
            titles.add("手机号");
            titles.add("昵称");
            titles.add("上一次余额");
            titles.add("当前余额");
            titles.add("收入");
            titles.add("支出");
            titles.add("来源");
            titles.add("创建时间");
            dataMap.put("titles", titles);
            List<JcAccountLog> retData = service.exportExcel(o);
            List<Map<String, String>> varList = new ArrayList<Map<String, String>>();
            for (int i = 0; i < retData.size(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                int index = 1;
                map.put("var" + index++, retData.get(i).getAccountLogId());
                map.put("var" + index++, retData.get(i).getAccountId());
                map.put("var" + index++, retData.get(i).getMobile());
                map.put("var" + index++, retData.get(i).getNick_name());
                map.put("var" + index++, String.valueOf(retData.get(i).getLastBalance()));
                map.put("var" + index++, String.valueOf(retData.get(i).getCurrBalance()));
                map.put("var" + index++, String.valueOf(retData.get(i).getIncome()));
                map.put("var" + index++, String.valueOf(retData.get(i).getExpenditure()));
                map.put("var" + index++, getSourceName(retData.get(i).getSource()));
                map.put("var" + index++, DateUtils.formatDate(retData.get(i).getCreateTime(), DateUtils.parsePatterns[3]));
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
