package com.jy.controller.jc.paintedEgg;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.jc.JcConst;
import com.jy.common.mybatis.Page;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.poi.ObjectExcelView;
import com.jy.controller.base.BaseController;
import com.jy.entity.base.DataTablesResponse;
import com.jy.entity.jc.messagecenter.MessageCenter;
import com.jy.entity.jc.messagecenter.MessageReceiver;
import com.jy.entity.jc.paintedEgg.PaintedEgg;
import com.jy.entity.jc.paintedEgg.PaintedEggAnswer;
import com.jy.entity.jc.paintedEgg.PaintedEggUser;
import com.jy.entity.jc.paintedEgg.PaintedEggWiner;
import com.jy.entity.jc.user.User;
import com.jy.service.jc.messagecenter.MessageCenterService;
import com.jy.service.jc.paintedEgg.PaintedEggService;
import com.jy.service.jc.user.UserService;
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

/**
 * 猜蛋
 *
 * @author zr
 */
@Controller
@RequestMapping("/jc/paintedEgg/")
public class PaintedEggController extends BaseController<PaintedEgg> {

    @Autowired
    private PaintedEggService paintedEggService;

    @Autowired
    private UserService service;

    @Autowired
    private MessageCenterService messageCenterService;

    //添加页面
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/jc/paintedEgg/add";
    }

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/jc/paintedEgg/list";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    //分页
    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<PaintedEgg> page, PaintedEgg o) {
        AjaxRes ar = getAjaxRes();
        if (!ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/PaintedEgg/index"))) {
            try {
                Page<PaintedEgg> accounts = paintedEggService.findByPage(o, page);
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
    @RequestMapping(value = "find", method = RequestMethod.GET)
    public String find(PaintedEgg o, HttpServletRequest request) {
        try {
            List<PaintedEgg> list = paintedEggService.find(o);
            if (list.size() > 0) {
                PaintedEgg acount = list.get(0);
                PaintedEggAnswer answer = new PaintedEggAnswer();
                answer.setEggid(acount.getEggid());
                List<PaintedEggAnswer> answers = paintedEggService.queryPaintedEggAnswer(answer);
                acount.setAnswers(answers);
                PaintedEggWiner winer = new PaintedEggWiner();
                winer.setEggid(acount.getEggid());
                //中奖号码及中奖人
                List<PaintedEggWiner> winers = paintedEggService.queryPaintedEggWinner(winer);
                if (winers.size() > 0) {
                    PaintedEggWiner pp = winers.get(0);
                    acount.setUserName(pp.getNick_name());
                    acount.setLucyNum(pp.getLuckynum() + "");
                    if (pp.getStatus() == 1) {
                        acount.setIszd(1);
                    } else {
                        acount.setIszd(0);
                    }
                }
                request.setAttribute("acount", acount);
            }

        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        if ("find".equals(request.getParameter("type"))) {
            return "/jc/paintedEgg/query";
        } else {
            return "/jc/paintedEgg/update";
        }

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
        if (!ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))) {
            try {
                if (StringUtils.isNotBlank(chks)) {
                    String[] chk = chks.split(",");
                    List<PaintedEgg> list = new ArrayList<PaintedEgg>();
                    for (String s : chk) {
                        PaintedEgg sd = new PaintedEgg();
                        sd.setEggid(s);
                        list.add(sd);
                    }
                    paintedEggService.deleteBatch(list);
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
    public AjaxRes delete(PaintedEgg o, HttpServletRequest request) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                paintedEggService.delete(o);
                ar.setSucceedMsg(Const.DEL_SUCCEED);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.DEL_FAIL);
            }
        }
        return ar;
    }


    //指定中奖人
    @RequestMapping(value = "zd", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes zd(PaintedEgg o, HttpServletRequest request) {
        AjaxRes ar = getAjaxRes();
//		JcConst.PAINTED_LuckyNum = 10000000; // 全局的恢复为初始
        // 查询猜蛋主表
        List<PaintedEgg> eggs = paintedEggService.find(o);
        try {
            List<PaintedEgg> paintedEggs = paintedEggService.find(o);
            PaintedEgg egg = paintedEggs.get(0);
            // 幸运号开始//幸运号结束//幸运号总数
            PaintedEggUser userMax = paintedEggService.queryEggMaxUserInfo();
            int start = 0;
            int end = 0;
//			if (null != userMax) {
//				if (null != userMax.getLuckynumstart()) {
//					start = userMax.getLuckynumstart();
//				}
//				if (null != userMax.getLuckynumend()) {
//					end = userMax.getLuckynumend();
//				}
//				if (start > end) {
//					JcConst.PAINTED_LuckyNum = start;
//				} else if (start < end) {
//					JcConst.PAINTED_LuckyNum = end;
//				}
//			}
            PaintedEggWiner winer = new PaintedEggWiner();
            if (o.getType().equals("0")) {
                String date = DateUtils.getDate(DateUtils.parsePatterns[0]);
                int lucyNum = JcConst.getLuckynum(JcConst.LUCKYNUM_KEY + date, 1)[0];
                // 先入库一条假的购买信息
                List<PaintedEggUser> list = new ArrayList<>();
                PaintedEggUser paintedEggUser = new PaintedEggUser();
                paintedEggUser.setCreatetime(new Date());
                paintedEggUser.setEggid(o.getEggid());
                paintedEggUser.setEgguserlogid(get32UUID());
                paintedEggUser.setLuckynumstart(lucyNum);
                paintedEggUser.setLuckynumend(lucyNum);
                paintedEggUser.setTotalcnt(1);
                paintedEggUser.setAnswerId(o.getAnswerId());
                paintedEggUser.setUserId(o.getUserId());
                paintedEggUser.setIsright(1);
                list.add(paintedEggUser);
                paintedEggService.insertUserBatch(list);
                winer.setLuckynum(lucyNum);
            } else {
                // 如果是1的话就查询这个人购买的信息中来获取中奖号码
                o.setIsright(2);
                List<PaintedEggUser> users = paintedEggService.queryUserBuyList(o);
                if (users.size() > 0) {
                    PaintedEggUser user = users.get(0);
                    winer.setLuckynum(user.getLuckynumstart());
                }
            }
            winer.setEggid(o.getEggid());
            winer.setUserId(o.getUserId());
            winer.setStatus(1);
            if (o.getOperType().equals("add")) {
                winer.setCreatetime(new Date());
                paintedEggService.zdWin(winer);
                //添加推送
                MessageCenter msg = new MessageCenter();
                msg.setMsgId(get32UUID());
                msg.setContent("恭喜您彩蛋中奖了！");
                msg.setTitle("中奖推送");
                msg.setMsgType((short)5);
                msg.setState(0);
                msg.setCreateTime(new Date());
                messageCenterService.insert(msg);
                List<MessageReceiver> receivers = new ArrayList<>();
                MessageReceiver receiver = new MessageReceiver();
                receiver.setMsgId(msg.getMsgId());
                receiver.setReceiver(winer.getUserId());
                receivers.add(receiver);
                messageCenterService.insertReceivers(receivers);
            } else {
                paintedEggService.updateWin(winer);
            }
            o.setState(2); // 更新猜蛋信息为完成状态 以及猜蛋人数
            int purchased = 0;
            if (null != egg.getPurchased()) {
                purchased = egg.getPurchased();
            }
            o.setPurchased(purchased + 1); // 原来的数量+补齐机器人的数量+指定的数量
            paintedEggService.update(o);

            o.setUpdatetime(new Date());
            o.setEggid(o.getEggid());
            o.setRightanswerid(o.getAnswerId());
            paintedEggService.update(o);
            PaintedEggUser paintedEggUser = new PaintedEggUser();
            paintedEggUser.setAnswerId(o.getAnswerId());
            paintedEggUser.setEggid(o.getEggid());
            paintedEggUser.setUpdatetime(new Date());
            paintedEggService.ModifyAnswerUserBatch(paintedEggUser);
            ar.setSucceedMsg(Const.SAVE_SUCCEED);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(Const.SAVE_FAIL);
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
    public AjaxRes update(PaintedEgg o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                o.setUpdatetime(new Date());
                o.setEggid(o.getEggid());
                List<PaintedEggAnswer> list = new ArrayList<PaintedEggAnswer>();
                if (null != o.getInputAnswers()) {
                    String answres = o.getInputAnswers();
                    String[] answresArray = answres.split(",");
                    for (int i = 0; i < answresArray.length; i++) {
                        PaintedEggAnswer pr = new PaintedEggAnswer();
                        pr.setEggid(o.getEggid());
                        pr.setAnswerid(get32UUID());
                        pr.setCreatetime(new Date());
                        pr.setSort(i);
                        pr.setAnswer(answresArray[i].toString());
                        list.add(pr);
                    }
                }
                paintedEggService.update(o);
                PaintedEggAnswer dEggAnswer = new PaintedEggAnswer();
                dEggAnswer.setEggid(o.getEggid());
                paintedEggService.deleteAnswerBatch(dEggAnswer);
                paintedEggService.insertAnswerBatch(list);

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
    @RequestMapping(value = "add")
    @ResponseBody
    public AjaxRes add(PaintedEgg o) {
        AjaxRes ar = getAjaxRes();
        try {
            String eggid = o.getEggid();
            o.setEggid(eggid);
            o.setCreatetime(new Date());
            o.setOperid(getCurrUserId());
            List<PaintedEggAnswer> list = new ArrayList<PaintedEggAnswer>();
            if (null != o.getInputAnswers()) {
                String answres = o.getInputAnswers();
                String[] answresArray = answres.split(",");
                for (int i = 0; i < answresArray.length; i++) {
                    PaintedEggAnswer pr = new PaintedEggAnswer();
                    pr.setEggid(eggid);
                    pr.setAnswerid(get32UUID());
                    pr.setCreatetime(new Date());
                    pr.setSort(i);
                    pr.setAnswer(answresArray[i].toString());
                    list.add(pr);
                }
            }

            paintedEggService.insert(o);
            //增加答案
            paintedEggService.insertAnswerBatch(list);
            //添加推送
            MessageCenter msg = new MessageCenter();
            msg.setMsgId(get32UUID());
            msg.setContent("彩蛋推送");
            msg.setTitle("彩蛋推送");
            msg.setMsgType((short) 6);
            msg.setState(0);
            msg.setCreateTime(new Date());
            messageCenterService.insert(msg);
            ar.setSucceedMsg(Const.SAVE_SUCCEED);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(Const.SAVE_FAIL);
        }
        return ar;
    }


    //分页查询所有的用户购买记录
    @RequestMapping(value = "paintedEggUser", method = RequestMethod.POST)
    @ResponseBody
    public DataTablesResponse findByPage(Page<PaintedEggUser> page, PaintedEggUser o) {
        AjaxRes ar = getAjaxRes();
        DataTablesResponse dataTablesResponse = null;
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/paintedEgg/index"))) {
            try {
                page.setPageNum(page.getStart() / page.getLength() + 1);
                page.setPageSize(page.getLength());
                Page<PaintedEggUser> accounts = paintedEggService.queryPaintedEggUser(o, page);
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
    public ModelAndView exportExcel(PaintedEggUser o) {
        ModelAndView mv = this.getModelAndView();
        try {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<String> titles = new ArrayList<String>();
            titles.add("彩蛋编码");
            titles.add("购买者");
            titles.add("账户流水编码");
            titles.add("幸运号开始");
            titles.add("幸运号结束");
            titles.add("总注数");
            titles.add("是否正确");
            titles.add("创建时间");
            dataMap.put("titles", titles);
            List<PaintedEggUser> retData = paintedEggService.exportExcel(o);
            List<Map<String, String>> varList = new ArrayList<Map<String, String>>();
            for (int i = 0; i < retData.size(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                int index = 1;
                map.put("var" + index++, retData.get(i).getEggid());
                map.put("var" + index++, retData.get(i).getUserId());
                map.put("var" + index++, retData.get(i).getAccountLogId());
                map.put("var" + index++, String.valueOf(retData.get(i).getLuckynumstart()));
                map.put("var" + index++, String.valueOf(retData.get(i).getLuckynumend()));
                map.put("var" + index++, String.valueOf(retData.get(i).getTotalcnt()));
                map.put("var" + index++, retData.get(i).getIsright() == null ? "" : (retData.get(i).getIsright() == 1 ? "正确" : "不正确"));
                map.put("var" + index++, DateUtils.formatDate(retData.get(i).getCreatetime(), DateUtils.parsePatterns[3]));
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


    //所有的答案
    @RequestMapping(value = "setAnswer", method = RequestMethod.GET)
    public String answerList(PaintedEgg o, HttpServletRequest request) {
        try {
            PaintedEggAnswer answer = new PaintedEggAnswer();
            answer.setEggid(o.getEggid());
            List<PaintedEggAnswer> answers = paintedEggService.queryPaintedEggAnswer(answer);
            String type = "2";
            if (null != answers) {
                PaintedEggAnswer paintedEggAnswer = answers.get(0);
                if (null != paintedEggAnswer.getRightAnswerId()) {
                    type = "1";
                }
            }
            request.setAttribute("type", type);
            request.setAttribute("acount", answers);

        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return "/jc/paintedEgg/answerList";

    }


    //指定中奖人页面
    @RequestMapping(value = "toWiner", method = RequestMethod.GET)
    public String toWiner(PaintedEgg o, HttpServletRequest request) {
        try {
            PaintedEgg acount = new PaintedEgg();
            PaintedEggWiner winer = new PaintedEggWiner();
            winer.setEggid(o.getEggid());
            acount.setEggid(o.getEggid());
            acount.setAnswerId(o.getAnswerId());
            List<PaintedEggWiner> winers = paintedEggService.queryPaintedEggWinner(winer);
            if (winers.size() > 0) {
                PaintedEggWiner pp = winers.get(0);
                acount.setUserName(pp.getNick_name());
                acount.setLucyNum(pp.getLuckynum() + "");
                acount.setUserId(pp.getUserId());
                if (pp.getStatus() == 1) {
                    acount.setIszd(1);
                } else {
                    acount.setIszd(0);
                }
            }
            request.setAttribute("acount", acount);

        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return "/jc/paintedEgg/toWiner";

    }


    /**
     * 更新 设置答案
     *
     * @param o
     * @return
     */
    @RequestMapping(value = "updateRightAnswer", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes setAnswer(PaintedEgg o, HttpServletRequest request) {
        AjaxRes ar = getAjaxRes();
        String eggid = request.getParameter("eggid");
        String answerid = request.getParameter("answerid");
        if (!StringUtils.isEmpty(o.getUserId())) { // 如果用户不为空则说明是指定中奖
            try {
                List<PaintedEgg> eggs = paintedEggService.find(o);
                PaintedEgg egg = eggs.get(0);
                PaintedEggWiner winer = new PaintedEggWiner();
                if (o.getType().equals("0")) {
                    String date = DateUtils.getDate(DateUtils.parsePatterns[0]);
                    int lucyNum = JcConst.getLuckynum(JcConst.LUCKYNUM_KEY + date, 1)[0];
                    // 先入库一条假的购买信息
                    List<PaintedEggUser> list = new ArrayList<>();
                    PaintedEggUser paintedEggUser = new PaintedEggUser();
                    paintedEggUser.setCreatetime(new Date());
                    paintedEggUser.setEggid(o.getEggid());
                    paintedEggUser.setEgguserlogid(get32UUID());
                    paintedEggUser.setLuckynumstart(lucyNum);
                    paintedEggUser.setLuckynumend(lucyNum);
                    paintedEggUser.setTotalcnt(1);
                    paintedEggUser.setAnswerId(answerid);
                    paintedEggUser.setUserId(o.getUserId());
                    paintedEggUser.setIsright(1);
                    list.add(paintedEggUser);
                    paintedEggService.insertUserBatch(list);
                    winer.setLuckynum(lucyNum);
                } else {
                    // 如果是1的话就查询这个人购买的信息中来获取中奖号码
                    o.setIsright(1);
                    List<PaintedEggUser> users = paintedEggService.queryUserBuyList(o);
                    if (users.size() > 0) {
                        PaintedEggUser user = users.get(0);
                        winer.setLuckynum(user.getLuckynumstart());
                    }
                }
                winer.setEggid(o.getEggid());
                winer.setUserId(o.getUserId());
                winer.setStatus(1);
                winer.setCreatetime(new Date());
                paintedEggService.zdWin(winer);
                o.setState(2); // 更新猜蛋信息为完成状态 以及猜蛋人数
                int purchased = 0;
                if (null != egg.getPurchased()) {
                    purchased = egg.getPurchased();
                }
                o.setPurchased(purchased + 1); // 原来的数量+补齐机器人的数量+指定的数量
                paintedEggService.update(o);

                o.setUpdatetime(new Date());
                o.setEggid(o.getEggid());
                o.setRightanswerid(answerid);
                paintedEggService.update(o);
                PaintedEggUser paintedEggUser = new PaintedEggUser();
                paintedEggUser.setAnswerId(o.getAnswerId());
                paintedEggUser.setEggid(o.getEggid());
                paintedEggUser.setUpdatetime(new Date());
                paintedEggService.ModifyAnswerUserBatch(paintedEggUser);



                //添加推送
                MessageCenter msg = new MessageCenter();
                msg.setMsgId(get32UUID());
                msg.setContent("恭喜您彩蛋中奖了！");
                msg.setTitle("中奖推送");
                msg.setMsgType((short)5);
                msg.setState(0);
                msg.setCreateTime(new Date());
                messageCenterService.insert(msg);
                List<MessageReceiver> receivers = new ArrayList<>();
                MessageReceiver receiver = new MessageReceiver();
                receiver.setMsgId(msg.getMsgId());
                receiver.setReceiver(winer.getUserId());
                receivers.add(receiver);
                messageCenterService.insertReceivers(receivers);


                ar.setSucceedMsg(Const.SAVE_SUCCEED);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.SAVE_FAIL);
            }
        } else {
            // 批量修改选择改答案人的状态
            try {
                PaintedEggUser vPaintedEggUser = new PaintedEggUser();
                vPaintedEggUser.setAnswerId(answerid);
                vPaintedEggUser.setEggid(eggid);
                vPaintedEggUser.setUpdatetime(new Date());
                paintedEggService.ModifyAnswerUserBatch(vPaintedEggUser);

                // 查询购买猜蛋答案正确的人
                o.setIsright(1);
                List<PaintedEggUser> paintedEggUsers = paintedEggService.queryUserBuyList(o);
                Random rand = new Random(paintedEggUsers.size());

                // 查询购买猜蛋最后的50名时间的汇总之和
                PaintedEggUser pu = new PaintedEggUser();
                pu.setEggid(o.getEggid());
                PaintedEggUser timeCount = paintedEggService.getByUer50(pu);

                // 查询猜蛋的总需下线
                List<PaintedEgg> paintedEggs = paintedEggService.find(o);
                PaintedEgg egg = paintedEggs.get(0);
                int eggLimit = egg.getLowerlimit();

                // 中奖号码序列
                int luckyNum = (int) (timeCount.getTimeCount() % eggLimit);
                Collections.shuffle(paintedEggUsers, rand);
                // 中奖用户及其中奖号码
                PaintedEggUser lucyUser = paintedEggUsers.get(luckyNum);

                o.setUpdatetime(new Date());
                o.setEggid(eggid);
                o.setRightanswerid(answerid);
                o.setState(2); // 更新猜蛋信息为完成状态
                o.setRightanswerid(o.getAnswerId());
                paintedEggService.update(o);
                PaintedEggWiner winer = new PaintedEggWiner();
                winer.setLuckynum(lucyUser.getLuckynumstart());
                winer.setEggid(o.getEggid());
                winer.setUserId(lucyUser.getUserId()); // 这里要根据幸运号从库里查出来
                winer.setStatus(1);
                winer.setCreatetime(new Date());
                paintedEggService.zdWin(winer);
                ar.setSucceedMsg(Const.UPDATE_SUCCEED);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.SAVE_FAIL);
            }
        }
        return ar;
    }


    //分页所有购买猜蛋的的用户
    @RequestMapping(value = "queryBuyList", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes queryBuyList(Page<PaintedEggUser> page, PaintedEggUser o) {
        AjaxRes ar = getAjaxRes();
        if (!ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/PaintedEgg/index"))) {
            try {
                Page<PaintedEggUser> accounts = paintedEggService.queryPaintedEggUser(o, page);
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


    @RequestMapping("buyList")
    public String buyList(Model model, HttpServletRequest request) {
        String eggid = request.getParameter("eggid");
        request.setAttribute("eggid", eggid);
        model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
        return "/jc/paintedEgg/buyList";
    }


    //补位机器人
    @RequestMapping(value = "robot", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes robot(PaintedEgg o, HttpServletRequest request) {
        AjaxRes ar = getAjaxRes();
        try {
            //先查询出改期猜蛋的下线人数
            List<PaintedEgg> eggs = paintedEggService.find(o);
            if (eggs.size() > 0) {
                PaintedEgg ePaintedEgg = eggs.get(0);
                int limit = ePaintedEgg.getLowerlimit();
                if (limit > 100) {
                    limit = 99;
                }
                List<PaintedEggUser> list = new ArrayList<>();
                List<User> users = service.queryRobot();
                if (null != users) {
                    for (int i = 0; i < limit; i++) {
//             					++JcConst.PAINTED_LuckyNum;
                        String date = DateUtils.getDate(DateUtils.parsePatterns[0]);
                        int lucyNum = JcConst.getLuckynum(JcConst.LUCKYNUM_KEY + date, 1)[0];
                        User user = users.get(i);
                        PaintedEggUser paintedEggUser = new PaintedEggUser();
                        paintedEggUser.setCreatetime(new Date());
                        paintedEggUser.setEggid(o.getEggid());
                        paintedEggUser.setEgguserlogid(get32UUID());
                        paintedEggUser.setLuckynumstart(lucyNum);
                        paintedEggUser.setLuckynumend(lucyNum);
                        paintedEggUser.setTotalcnt(1);
                        paintedEggUser.setAnswerId("");
                        paintedEggUser.setUserId(user.getUserId());
                        paintedEggUser.setIsright(1);
                        list.add(paintedEggUser);
                    }
                    paintedEggService.insertUserBatch(list);
                }

                int purchased = 0;
                if (null != ePaintedEgg.getPurchased()) {
                    purchased = ePaintedEgg.getPurchased();
                }
                o.setPurchased(purchased + limit);   //原来的数量+补齐机器人的数量
                o.setUpdatetime(new Date());
                paintedEggService.update(o);
            }
            ar.setSucceedMsg(Const.SAVE_SUCCEED);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(Const.SAVE_FAIL);
        }
        return ar;
    }

}
