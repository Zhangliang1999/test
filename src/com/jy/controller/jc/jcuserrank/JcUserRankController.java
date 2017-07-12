package com.jy.controller.jc.jcuserrank;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.mybatis.Page;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.poi.ObjectExcelView;
import com.jy.controller.base.BaseController;
import com.jy.entity.jc.jcaccountrechargelog.JcAccountWithdrawView;
import com.jy.entity.jc.jcuserrank.JcUserRank;
import com.jy.service.jc.jcuserrank.JcUserRankService;
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
@RequestMapping("/jc/jcuserrank/")
public class JcUserRankController extends BaseController<JcUserRank> {

    @Autowired
    private JcUserRankService service;

    //添加页面
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/jc/jcuserrank/add";
    }

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/jc/jcuserrank/list";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    //分页
    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<JcUserRank> page, JcUserRank o) {
        AjaxRes ar = getAjaxRes();
        //if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/jcuserrank/index"))) {
            try {
                Page<JcUserRank> accounts = service.findByPage(o, page);
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

    //查看
    @RequestMapping(value = "find", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes find(JcUserRank o) {
        AjaxRes ar=getAjaxRes();
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){
            try {
                List<JcUserRank> list=service.find(o);
                JcUserRank acount =list.get(0);
                ar.setSucceed(acount);
            } catch (Exception e) {
                logger.error(e.toString(),e);
                ar.setFailMsg(Const.DATA_FAIL);
            }
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
                    List<JcUserRank> list = new ArrayList<JcUserRank>();
                    for (String s : chk) {
                        JcUserRank sd = new JcUserRank();
                        sd.setIssue(s);
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
    /*
       * 导出到excel
       * @return
       */
    @RequestMapping(value = "/exportExcel")
    public ModelAndView exportExcel(JcUserRank o) {
        ModelAndView mv = this.getModelAndView();
        try {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<String> titles = new ArrayList<String>();
            titles.add("期号");
            titles.add("用户编码");
            titles.add("类型");
            titles.add("盈利率");
            titles.add("胜场次");
            titles.add("负场次");
            titles.add("星级");
            titles.add("粉丝数");
            dataMap.put("titles", titles);
            List<JcUserRank> retData = service.exportExcel(o);
            List<Map<String, String>> varList = new ArrayList<Map<String, String>>();
            for (int i = 0; i < retData.size(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("var1", retData.get(i).getIssue());
                map.put("var2", retData.get(i).getUserId());
                map.put("var3", retData.get(i).getType() == null ? "" : (retData.get(i).getType() == 1 ? "周榜" : "月榜"));
                map.put("var4", String.valueOf(retData.get(i).getProfitRate()));
                map.put("var5", String.valueOf(retData.get(i).getWinCount()));
                map.put("var6", String.valueOf(retData.get(i).getLoseCount()));
                map.put("var7", String.valueOf(retData.get(i).getStar()));
                map.put("var8", String.valueOf(retData.get(i).getAttentionCount()));
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
