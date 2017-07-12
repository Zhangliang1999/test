package com.jy.controller.jc.jcfdincome;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.mybatis.Page;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.poi.ObjectExcelView;
import com.jy.controller.base.BaseController;
import com.jy.entity.jc.jcfdincome.JcFdIncome;
import com.jy.service.jc.jcfdincome.JcFdIncomeService;
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
@RequestMapping("/jc/jcfdincome/")
public class JcFdIncomeController extends BaseController<JcFdIncome> {

    @Autowired
    private JcFdIncomeService service;

    //添加页面
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/jc/jcfdincome/add";
    }

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/jc/jcfdincome/list";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    //分页
    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<JcFdIncome> page, JcFdIncome o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/jcfdincome/index"))) {
            try {
                Page<JcFdIncome> accounts = service.findByPage(o, page);
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
    public AjaxRes find(JcFdIncome o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                List<JcFdIncome> list = service.find(o);
                JcFdIncome acount = list.get(0);
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
    public ModelAndView exportExcel(JcFdIncome o) {
        ModelAndView mv = this.getModelAndView();
        try {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<String> titles = new ArrayList<String>();
            titles.add("期号");
            titles.add("手机号");
            titles.add("昵称");
            titles.add("发单量");
            titles.add("销售量");
            titles.add("收益");
            titles.add("统计周期");
            titles.add("发单类型");
            dataMap.put("titles", titles);
            List<JcFdIncome> retData = service.exportExcel(o);
            List<Map<String, String>> varList = new ArrayList<Map<String, String>>();
            for (int i = 0; i < retData.size(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                int index = 1;
                map.put("var" + index++, retData.get(i).getIssue());
                map.put("var" + index++, retData.get(i).getMobile());
                map.put("var" + index++, retData.get(i).getNick_name());
                map.put("var" + index++, String.valueOf(retData.get(i).getFdCnt()));
                map.put("var" + index++, String.valueOf(retData.get(i).getSellCnt()));
                map.put("var" + index++, String.valueOf(retData.get(i).getIncome()));
                map.put("var" + index++, retData.get(i).getCycleType() == 1 ? "周" : "月");
                map.put("var" + index++, retData.get(i).getBillType() == 1 ? "赛前单" : "滚球单");
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
