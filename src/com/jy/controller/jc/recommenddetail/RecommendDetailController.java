package com.jy.controller.jc.recommenddetail;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.mybatis.Page;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.base.DataTablesResponse;
import com.jy.entity.jc.recommenddetail.RecommendDetail;
import com.jy.service.jc.recommenddetail.RecommendDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/jc/recommenddetail/")
public class RecommendDetailController extends BaseController<RecommendDetail> {

    @Autowired
    private RecommendDetailService service;

    //分页
    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public DataTablesResponse findByPage(Page<RecommendDetail> page, RecommendDetail o, HttpServletRequest request) {
        AjaxRes ar = getAjaxRes();
        DataTablesResponse dataTablesResponse = null;
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/recommendbill/index"))) {
            try {
                page.setPageNum(page.getStart()/page.getLength() + 1);
                page.setPageSize(page.getLength());
                Page<RecommendDetail> accounts = service.findByPage(o, page);
                dataTablesResponse = new DataTablesResponse(page.getDraw(), accounts.getTotalRecord(), accounts.getResults());
                ar.setSucceed(dataTablesResponse);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.DATA_FAIL);
            }
        }
        return dataTablesResponse;
    }

}
