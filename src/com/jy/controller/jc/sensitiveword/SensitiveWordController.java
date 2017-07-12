package com.jy.controller.jc.sensitiveword;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.mybatis.Page;
import com.jy.common.utils.*;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.poi.ExcelUtil;
import com.jy.common.utils.poi.ObjectExcelRead;
import com.jy.common.utils.webpage.PageData;
import com.jy.controller.base.BaseController;
import com.jy.entity.jc.sensitiveword.SensitiveWord;
import com.jy.service.jc.sensitiveword.SensitiveWordService;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/jc/sensitiveword/")
public class SensitiveWordController extends BaseController<SensitiveWord> {

    @Autowired
    private SensitiveWordService service;

    //添加页面
    @RequestMapping("toAdd")
    public String toAdd() {
        return "/jc/sensitiveword/add";
    }

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/jc/sensitiveword/list";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    //分页
    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<SensitiveWord> page, SensitiveWord o) {
        AjaxRes ar = getAjaxRes();
        //if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/jc/sensitiveword/index"))) {
        try {
            Page<SensitiveWord> accounts = service.findByPage(o, page);
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
    public AjaxRes find(SensitiveWord o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                List<SensitiveWord> list = service.find(o);
                SensitiveWord acount = list.get(0);
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
                    List<SensitiveWord> list = new ArrayList<SensitiveWord>();
                    for (String s : chk) {
                        SensitiveWord sd = new SensitiveWord();
                        sd.setSensitiveId(s);
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
    public AjaxRes delete(SensitiveWord o, HttpServletRequest request) {
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
    public AjaxRes update(SensitiveWord o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                o.setUpdateTime(new Date());
                o.setOperId(getCurrUserId());
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
    public AjaxRes add(SensitiveWord o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))) {
            try {
                o.setSensitiveId(get32UUID());
                o.setCreateTime(new Date());
                o.setOperId(getCurrUserId());
                service.insert(o);
                ar.setSucceedMsg(Const.SAVE_SUCCEED);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.SAVE_FAIL);
            }
        }
        return ar;
    }

    /**
     * 打开上传EXCEL页面
     */
    @RequestMapping(value = "toUploadExcel")
    public ModelAndView toUploadExcel() throws Exception {
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("jc/sensitiveword/uploadexcel");
        return mv;
    }

    /**
     * 下载模版
     * @param fileName
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("download")
    public String download(String fileName, HttpServletRequest request,
                           HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="
                + fileName);
        try {
            String realPath = request.getSession().getServletContext().getRealPath("/upload/file/");
            File file = new File(realPath
                    + File.separator + fileName);
            InputStream inputStream = new FileInputStream(file);
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }

            // 这里主要关闭。
            os.close();

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  返回值要注意，要不然就出现下面这句错误！
        //java+getOutputStream() has already been called for this response
        return null;
    }

    /**
     * @param response
     * @param request
     * @param file
     * @date: Nov 12, 2014
     * @author: susen
     */
    @RequestMapping(value = "uploadFile")
    public void uploadFile(HttpServletResponse response, HttpServletRequest request, @RequestParam(value = "file", required = false)
            MultipartFile file) throws Exception {
        String msg = "";
        JSONObject jsonObject = new JSONObject();
        response.setContentType("text/html; charset=utf-8");
        PrintWriter pw = response.getWriter();
        try {
            response.setContentType("text/html; charset=utf-8");
            byte[] bytes = file.getBytes();
            String uploadDir = request.getSession().getServletContext().getRealPath("/uploads");
            File dirPath = new File(uploadDir);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            String prefix = DateUtils.formatDate(new Date(), "yyyyMMdd_HHmmss_");
            String sep = System.getProperty("file.separator");
            String filePathName = uploadDir + sep + prefix + "" + file.getOriginalFilename();
            logger.info("文件上传:" +filePathName);
            File uploadedFile = new File(filePathName);
            FileCopyUtils.copy(bytes, uploadedFile);//拷贝文件
            List<String[]> list = ExcelUtil.readExcel(filePathName);
            String[] head = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                SensitiveWord o = new SensitiveWord();
                o.setSensitiveId(get32UUID());
                o.setSensitiveWord(list.get(i)[0]);
                o.setReplaceWord(list.get(i)[1]);
                o.setCreateTime(new Date());
                o.setOperId(getCurrUserId());
                service.insert(o);
            }
            jsonObject.put("success", true);
            jsonObject.put("msg", msg);
            pw.println(jsonObject);
            return;
        } catch (Exception e) {
            jsonObject.put("success", false);
            jsonObject.put("msg", e.getMessage());
            pw.println(jsonObject);
            e.printStackTrace();
        } finally {
            pw.flush();
            pw.close();
        }
    }
}
