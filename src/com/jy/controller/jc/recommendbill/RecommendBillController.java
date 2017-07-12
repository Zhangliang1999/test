package com.jy.controller.jc.recommendbill;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.mybatis.Page;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.poi.ObjectExcelView;
import com.jy.controller.base.BaseController;
import com.jy.entity.base.DataTablesResponse;
import com.jy.entity.jc.betbill.BetBill;
import com.jy.entity.jc.recommendbill.RecommendBill;
import com.jy.entity.jc.recommenddetail.RecommendDetail;
import com.jy.entity.jc.userrecommendrel.UserRecommendRel;
import com.jy.service.jc.recommendbill.RecommendBillService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.jy.common.jc.JcConst.getMathTypeName;

@Controller
@RequestMapping("/jc/recommendbill/")
public class RecommendBillController extends BaseController<RecommendBill> {

    @Autowired
    private RecommendBillService service;

    //添加页面
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/jc/recommendbill/add";
    }

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/jc/recommendbill/list";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    //分页
    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<RecommendBill> page, RecommendBill o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/recommendbill/index"))) {
            try {
                Page<RecommendBill> accounts = service.findByPage(o, page);
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
    public AjaxRes find(RecommendBill o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                List<RecommendBill> list = service.find(o);
                RecommendBill acount = list.get(0);
                ar.setSucceed(acount);
            } catch (Exception e) {
                logger.error(e.toString(), e);
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
                    List<RecommendBill> list = new ArrayList<RecommendBill>();
                    for (String s : chk) {
                        RecommendBill sd = new RecommendBill();
                        sd.setRecommendId(s);
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
    public AjaxRes delete(RecommendBill o, HttpServletRequest request) {
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
    public AjaxRes update(RecommendBill o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                o.setUpdateTime(new Date());
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
    public AjaxRes add(RecommendBill o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))) {
            try {
                o.setRecommendId(get32UUID());
                o.setCreateTime(new Date());
                service.insert(o);
                ar.setSucceedMsg(Const.SAVE_SUCCEED);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.SAVE_FAIL);
            }
        }
        return ar;
    }

    //购买情况分页
    @RequestMapping(value = "findBuyByPage", method = RequestMethod.POST)
    @ResponseBody
    public DataTablesResponse findBuyByPage(Page<UserRecommendRel> page, UserRecommendRel o) {
        AjaxRes ar = getAjaxRes();
        DataTablesResponse dataTablesResponse = null;
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/recommendbill/index"))) {
            try {
                page.setPageNum(page.getStart() / page.getLength() + 1);
                page.setPageSize(page.getLength());
                Page<UserRecommendRel> accounts = service.findBuyByPage(o, page);
                dataTablesResponse = new DataTablesResponse(page.getDraw(), accounts.getTotalRecord(), accounts.getResults());
                ar.setSucceed(dataTablesResponse);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.DATA_FAIL);
            }
        }
        return dataTablesResponse;
    }


    /*
      * 导出到excel
      * @return
      */
    @RequestMapping(value = "/exportExcel")
    public ModelAndView exportExcel(RecommendBill o) {
        ModelAndView mv = this.getModelAndView();
        try {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<String> titles = new ArrayList<String>();
            titles.add("推荐编码");
            titles.add("用户编码");
            titles.add("几串几");
            titles.add("价格");
            titles.add("推理标题");
            titles.add("推理理由");
            titles.add("比赛类型");
            titles.add("盈利率");
            titles.add("创建时间");
            titles.add("更新时间");
            dataMap.put("titles", titles);
            List<RecommendBill> retData = service.exportExcel(o);
            List<Map<String, String>> varList = new ArrayList<Map<String, String>>();
            for (int i = 0; i < retData.size(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                int index = 1;
                map.put("var" + index++, retData.get(i).getRecommendId());
                map.put("var" + index++, retData.get(i).getUserId());
                map.put("var" + index++, String.valueOf(retData.get(i).getBunch()));
                map.put("var" + index++, String.valueOf(retData.get(i).getPrice()));
                map.put("var" + index++, String.valueOf(retData.get(i).getTitle()));
                map.put("var" + index++, String.valueOf(retData.get(i).getReason()));
                map.put("var" + index++, getMathTypeName(retData.get(i).getMatchType()));
                map.put("var" + index++, String.valueOf(retData.get(i).getProfitRate()));
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

    /*
     * 导出到excel
     * @return
     */
    @RequestMapping(value = "/exportBuyExcel")
    public ModelAndView exportBuyExcel(UserRecommendRel o) {
        ModelAndView mv = this.getModelAndView();
        try {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<String> titles = new ArrayList<String>();
            titles.add("账户流水编码");
            titles.add("赛前单编码");
            titles.add("用户编码");
            titles.add("创建时间");
            dataMap.put("titles", titles);
            List<UserRecommendRel> retData = service.exportBuyExcel(o);
            List<Map<String, String>> varList = new ArrayList<Map<String, String>>();
            for (int i = 0; i < retData.size(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                int index = 1;
                map.put("var" + index++, retData.get(i).getAccountLogId());
                map.put("var" + index++, retData.get(i).getRecommendId());
                map.put("var" + index++, String.valueOf(retData.get(i).getUserId()));
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
