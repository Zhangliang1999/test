package com.jy.controller.jc.betbill;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.mybatis.Page;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.poi.ObjectExcelView;
import com.jy.controller.base.BaseController;
import com.jy.entity.base.DataTablesResponse;
import com.jy.entity.jc.betbill.BetBill;
import com.jy.entity.jc.betbill.BetDetail;
import com.jy.entity.jc.jcuserrank.JcUserRank;
import com.jy.service.jc.betbill.BetBillService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
import static org.apache.shiro.util.ThreadContext.get;

@Controller
@RequestMapping("/jc/betbill/")
public class BetBillController extends BaseController<BetBill> {

    @Autowired
    private BetBillService service;

    //添加页面
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/jc/betbill/add";
    }

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/jc/betbill/list";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    //分页
    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<BetBill> page, BetBill o) {
        AjaxRes ar = getAjaxRes();
        //if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/betbill/index"))) {
        try {
            Page<BetBill> accounts = service.findByPage(o, page);
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
    public AjaxRes find(BetBill o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                List<BetBill> list = service.find(o);
                BetBill acount = list.get(0);
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
                    List<BetBill> list = new ArrayList<BetBill>();
                    for (String s : chk) {
                        BetBill sd = new BetBill();
                        sd.setBetId(s);
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
    public AjaxRes delete(BetBill o, HttpServletRequest request) {
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
     * 更新
     *
     * @param o
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes update(BetBill o) {
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

    //明细分页
    @RequestMapping(value = "findBillDetailByPage", method = RequestMethod.POST)
    @ResponseBody
    public DataTablesResponse findBillDetailByPage(Page<BetDetail> page, BetDetail o) {
        AjaxRes ar = getAjaxRes();
        DataTablesResponse dataTablesResponse = null;
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/betbill/index"))) {
            try {
                page.setPageNum(page.getStart() / page.getLength() + 1);
                page.setPageSize(page.getLength());
                Page<BetDetail> accounts = service.findBillDetailByPage(o, page);
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
    public ModelAndView exportExcel(BetBill o) {
        ModelAndView mv = this.getModelAndView();
        try {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<String> titles = new ArrayList<String>();

            titles.add("投注编码");
            titles.add("账户流水编码");
            titles.add("手机号");
            titles.add("用户昵称");
            titles.add("投注金额");
            titles.add("投注笔数");
            titles.add("预计奖金");
            titles.add("实际奖金");
            titles.add("几串几");
            titles.add("比赛类型");
            titles.add("是否中奖");
            titles.add("创建时间");
            titles.add("更新时间");
            dataMap.put("titles", titles);
            List<BetBill> retData = service.exportExcel(o);
            List<Map<String, String>> varList = new ArrayList<Map<String, String>>();
            for (int i = 0; i < retData.size(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                int index = 1;
                map.put("var" + index++, retData.get(i).getBetId());
                map.put("var" + index++, retData.get(i).getAccountLogId());
                map.put("var" + index++, retData.get(i).getMobile());
                map.put("var" + index++, retData.get(i).getNick_name());
                map.put("var" + index++, String.valueOf(retData.get(i).getAmount()));
                map.put("var" + index++, String.valueOf(retData.get(i).getItems()));
                map.put("var" + index++, String.valueOf(retData.get(i).getBonus()));
                map.put("var" + index++, String.valueOf(retData.get(i).getRealBonus()));
                map.put("var" + index++, String.valueOf(retData.get(i).getBunch()));
                map.put("var" + index++, getMathTypeName(retData.get(i).getMatchType()));
                int iscorrect = retData.get(i).getIscorrect();
                if(iscorrect == 1) {
                    map.put("var" + index++, "中奖");
                } else if(iscorrect == 2) {
                    map.put("var" + index++, "未中奖");
                } else {
                    map.put("var" + index++, "未开奖");
                }
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
