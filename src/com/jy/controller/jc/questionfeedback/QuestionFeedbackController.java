package com.jy.controller.jc.questionfeedback;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.mybatis.Page;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.jc.questionfeedback.QuestionFeedback;
import com.jy.service.jc.questionfeedback.QuestionFeedbackService;
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
@RequestMapping("/jc/questionfeedback/")
public class QuestionFeedbackController extends BaseController<QuestionFeedback> {

    @Autowired
    private QuestionFeedbackService service;

    //添加页面
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/jc/questionfeedback/add";
    }

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/jc/questionfeedback/list";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    //分页
    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<QuestionFeedback> page, QuestionFeedback o) {
        AjaxRes ar = getAjaxRes();
        //if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/questionfeedback/index"))) {
            try {
                Page<QuestionFeedback> accounts = service.findByPage(o, page);
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
    public AjaxRes find(QuestionFeedback o) {
        AjaxRes ar=getAjaxRes();
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){
            try {
                List<QuestionFeedback> list=service.find(o);
                QuestionFeedback acount =list.get(0);
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
                    List<QuestionFeedback> list = new ArrayList<QuestionFeedback>();
                    for (String s : chk) {
                        QuestionFeedback sd = new QuestionFeedback();
                        sd.setQuestionId(s);
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
    public AjaxRes delete(QuestionFeedback o, HttpServletRequest request) {
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
     * @param o
     * @return
     */
    @RequestMapping(value="update", method=RequestMethod.POST)
    @ResponseBody
    public AjaxRes update(QuestionFeedback o){
        AjaxRes ar=getAjaxRes();
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){
            try {
                o.setUpdateTime(new Date());
                o.setReplyContent(getCurrUserId());
                service.update(o);
                ar.setSucceedMsg(Const.UPDATE_SUCCEED);
            } catch (Exception e) {
                logger.error(e.toString(),e);
                ar.setFailMsg(Const.UPDATE_FAIL);
            }
        }
        return ar;
    }

}
