package com.jy.controller.jc.analyst;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.mybatis.Page;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.security.AccountShiroUtil;
import com.jy.controller.base.BaseController;
import com.jy.entity.jc.analyst.Analyst;
import com.jy.entity.system.account.Account;
import com.jy.service.jc.analyst.AnalystService;
import com.jy.service.system.account.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/jc/analyst/")
public class AnalystController extends BaseController<Analyst> {

    @Autowired
    private AnalystService service;

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/jc/analyst/list";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<Analyst> page, Analyst o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/analyst/index"))) {
            try {
                Page<Analyst> accounts = service.findByPage(o, page);
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

    @RequestMapping(value = "check", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes check(Analyst o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                List<Analyst> analysts = service.find(o);
                Analyst old = analysts.get(0);
                old.setUpdateTime(new Date());
                old.setState(o.getState());
                Account currentUser = AccountShiroUtil.getCurrentUser();
                old.setVerId(currentUser.getAccountId()); //操作人
                if(o.getState() == 2) {
                    service.becomeAnalyst(old); //成为分析师
                } else {
                    service.update(old);
                }
                String updateSucceed = Const.UPDATE_SUCCEED;
                ar.setSucceedMsg(updateSucceed);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.UPDATE_FAIL);
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
    public AjaxRes deleteBatch(String chks) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))) {
            try {
                //事务删除
                if (StringUtils.isNotBlank(chks)) {
                    String[] chk = chks.split(",");
                    List<Analyst> list = new ArrayList<Analyst>();
                    for (String s : chk) {
                        Analyst sd = new Analyst();
                        sd.setUserId(s);
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
    @RequestMapping(value="add", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(Account o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))){			
			try {
				o.setAccountId(get32UUID());	
				int res=service.insertAccount(o);
				if(res==1)ar.setSucceedMsg(Const.SAVE_SUCCEED);
				else ar.setFailMsg("登录名已存在");	
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.SAVE_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="delBatch", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes delBatch(String chks){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))){		
			try {
				service.deleteBatchAccount(chks);
				ar.setSucceedMsg(Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="find", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(Account o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){	
			try {
				List<Account> list=service.find(o);
				Account acount =list.get(0);
				ar.setSucceed(acount);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(Account o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){
			try {
				o.setUpdateTime(new Date());
				service.update(o);
				ar.setSucceedMsg(Const.UPDATE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="del", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(Account o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){
			try {
				service.deleteAccount(o);
				ar.setSucceedMsg(Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="resetPwd", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes resetPwd(Account o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){
			try {		
				o.setPassword(getPageData().getString("pwd"));
				int res=service.sysResetPwd(o);
				if(res==1) ar.setSucceedMsg(Const.UPDATE_SUCCEED);
				else ar.setFailMsg(Const.UPDATE_FAIL);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="setSetting", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes setSetting(String skin){
		AjaxRes ar=getAjaxRes();
		try {
			service.setSetting(skin);
			ar.setSucceedMsg(Const.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(Const.UPDATE_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="getPerData", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes getPerData(){
		AjaxRes ar=getAjaxRes();
		try {
			Account account=service.getPerData();
			ar.setSucceed(account);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(Const.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="setHeadpic", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes setHeadpic(Account o){
		AjaxRes ar=getAjaxRes();
		try {
			service.setHeadpic(o);
			ar.setSucceedMsg(Const.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(Const.UPDATE_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="setPerData", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes setPerData(Account o){
		AjaxRes ar=getAjaxRes();
		try {
			service.setPerData(o);
			ar.setSucceedMsg(Const.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(Const.UPDATE_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="preResetPWD", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes resetPWD(String opwd,String npwd,String qpwd){
		AjaxRes ar=getAjaxRes();
		try {
			int res=service.preResetPwd(opwd,npwd,qpwd);
			if     (res==1)ar.setSucceedMsg(Const.UPDATE_SUCCEED);
			else if(res==2)ar.setFailMsg("密码不正确");			
			else if(res==3)ar.setFailMsg("两次密码不一致");			
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(Const.UPDATE_FAIL);
		}
		return ar;
	}*/

}
