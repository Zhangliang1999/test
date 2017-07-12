package com.jy.controller.jc.jcuserstatis;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.mybatis.Page;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.poi.ObjectExcelView;
import com.jy.controller.base.BaseController;
import com.jy.entity.jc.jcuserstatis.JcUserStatis;
import com.jy.service.jc.jcuserstatis.JcUserStatisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jc/jcuserstatis/")
public class JcUserStatisController extends BaseController<JcUserStatis> {

    @Autowired
    private JcUserStatisService service;

    //添加页面
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/jc/jcuserstatis/add";
    }

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/jc/jcuserstatis/list";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    //分页
    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<JcUserStatis> page, JcUserStatis o) {
        AjaxRes ar = getAjaxRes();
        //if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/jcuserstatis/index"))) {
        try {
            Page<JcUserStatis> accounts = service.findByPage(o, page);
            Map<String, Object> p = new HashMap<String, Object>();
            p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
            p.put("list", accounts);
            ar.setSucceed(p);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(Const.DATA_FAIL);
        }
        //}
        return ar;
    }

    /*
       * 导出到excel
       * @return
       */
    @RequestMapping(value = "/exportExcel")
    public ModelAndView exportExcel(JcUserStatis o) {
        ModelAndView mv = this.getModelAndView();
        try {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<String> titles = new ArrayList<String>();
            titles.add("用户编码");
            titles.add("手机号");
            titles.add("昵称");
            titles.add("个人强项");
            titles.add("总盈利率");
            titles.add("总胜");
            titles.add("总负");
            titles.add("我的购买");
            titles.add("我的竞猜");
            titles.add("最高等级");
            titles.add("总消费");
            titles.add("总充值");
            titles.add("总兑换");
            titles.add("创建时间");
            titles.add("更新时间");
            dataMap.put("titles", titles);
            List<JcUserStatis> retData = service.exportExcel(o);
            List<Map<String, String>> varList = new ArrayList<Map<String, String>>();
            for (int i = 0; i < retData.size(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                int index = 1;
                map.put("var" + index++, retData.get(i).getUserId());
                map.put("var" + index++, retData.get(i).getMobile());
                map.put("var" + index++, retData.get(i).getNick_name());
                map.put("var" + index++, retData.get(i).getStrongPoint());
                map.put("var" + index++, retData.get(i).getTotalProfitRate() + "");
                map.put("var" + index++, retData.get(i).getTotalWinCnt() + "");
                map.put("var" + index++, retData.get(i).getTotalLostCnt() + "");
                map.put("var" + index++, retData.get(i).getMyBuyCnt() + "");
                map.put("var" + index++, retData.get(i).getMyBetCnt() + "");
                map.put("var" + index++, retData.get(i).getHighestLevel() + "");
                map.put("var" + index++, retData.get(i).getTotalConsumption() + "");
                map.put("var" + index++, retData.get(i).getTotalRecharge() + "");
                map.put("var" + index++, retData.get(i).getTotalRecharge() + "");
                map.put("var" + index++, DateUtils.formatDate(retData.get(i).getCreateTime(), DateUtils.parsePatterns[3]));
                map.put("var" + index++, DateUtils.formatDate(retData.get(i).getUpdateTime(), DateUtils.parsePatterns[3]));
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
