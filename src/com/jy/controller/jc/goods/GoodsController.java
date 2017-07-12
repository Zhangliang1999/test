package com.jy.controller.jc.goods;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.mybatis.Page;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.jc.goods.Goods;
import com.jy.entity.jc.picturerel.PictureRel;
import com.jy.service.jc.goods.GoodsService;
import com.jy.service.jc.picturerel.PictureRelService;

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
@RequestMapping("/jc/goods/")
public class GoodsController extends BaseController<Goods> {

    @Autowired
    private GoodsService service;
    @Autowired
    private PictureRelService prService;

    //添加页面
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/jc/goods/add";
    }

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/jc/goods/list";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    //分页
    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<Goods> page, Goods o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/goods/index"))) {
            try {
                Page<Goods> accounts = service.findByPage(o, page);
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
    public AjaxRes find(Goods o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                List<Goods> list = service.find(o);
                Goods acount = list.get(0);
                if(null!=acount){
                	PictureRel rPictureRel = new PictureRel();
                	rPictureRel.setEntityId(o.getGoodsId());
                	List<PictureRel> rel = prService.find(rPictureRel);
                	acount.setRel(rel);
                }
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
                    List<Goods> list = new ArrayList<Goods>();
                    List<PictureRel> picList = new ArrayList<PictureRel>();
                    for (String s : chk) {
                        Goods sd = new Goods();
                        PictureRel rel =new PictureRel();
                        rel.setEntityId(s);
                        sd.setGoodsId(s);
                        list.add(sd);
                        picList.add(rel);
                    }
                    service.deleteBatch(list);
                    prService.deleteBatch(picList);
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
    public AjaxRes delete(Goods o, HttpServletRequest request) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                service.delete(o);
                PictureRel rel =new PictureRel();
            	rel.setEntityId(o.getGoodsId());
                prService.delete(rel);
                ar.setSucceedMsg(Const.DEL_SUCCEED);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.DEL_FAIL);
            }
        }
        return ar;
    }
    
    
    //删除图片
    @RequestMapping(value = "deletePicRel", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes delete(HttpServletRequest request) {
        AjaxRes ar = getAjaxRes();
        if (!ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
            	String relId = request.getParameter("relId");
            	String entityId =request.getParameter("entityId");
            	PictureRel rel =new PictureRel();
            	rel.setEntityId(entityId);
            	rel.setRelId(relId);
                prService.delete(rel);
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
    public AjaxRes update(Goods o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                o.setUpdateTime(new Date());
                o.setOperId(getCurrUserId());
                service.update(o);
                PictureRel rel =new PictureRel();
                rel.setEntityId(o.getGoodsId());
                prService.delete(rel);
                List<PictureRel> list = new ArrayList<PictureRel>();
                if(null!=o.getPicList()){
                	String pics = o.getPicList();
                	String [] picArray = pics.split(";");
                	for(int i =0;i<picArray.length;i++){
                		PictureRel pr = new PictureRel(); 
                		pr.setRelId(get32UUID());
                		pr.setEntityId(o.getGoodsId());
                		pr.setPicId(picArray[i]);
                		pr.setCreateTime(new Date());
                		short t = 1;
                		pr.setPicRelType(t);
                		pr.setSort(i);
                		list.add(pr);
                }
                }
                	prService.insertBatch(list);
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
    public AjaxRes add(Goods o) {
        AjaxRes ar = getAjaxRes();
        if (!ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))) {
            try {
            	String uuid = get32UUID();
                o.setGoodsId(uuid);
                o.setCreateTime(new Date());
                o.setOperId(getCurrUserId());
                service.insert(o);
                List<PictureRel> list = new ArrayList<PictureRel>();
                if(null!=o.getPicList()){
                	String pics = o.getPicList();
                	String [] picArray = pics.split(";");
                	for(int i =0;i<picArray.length;i++){
                		PictureRel pr = new PictureRel(); 
                		pr.setRelId(get32UUID());
                		pr.setEntityId(uuid);
                		pr.setPicId(picArray[i]);
                		pr.setCreateTime(new Date());
                		short t = 1;
                		pr.setPicRelType(t);
                		pr.setSort(i);
                		list.add(pr);
                }
                	prService.insertBatch(list);
               }
                ar.setSucceedMsg(Const.SAVE_SUCCEED);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.SAVE_FAIL);
            }
        }
        return ar;
    }

}
