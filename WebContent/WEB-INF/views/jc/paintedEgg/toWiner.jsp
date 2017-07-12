<%@page import="java.util.List"%>
<%@page import="com.jy.entity.jc.paintedEgg.PaintedEggAnswer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jy.entity.jc.paintedEgg.PaintedEgg"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	PaintedEgg acount = (PaintedEgg)request.getAttribute("acount");
    String eggid = acount.getEggid();
    String userId = acount.getUserId();
    String answerId = acount.getAnswerId();
    String operType = "";
	if(null!=userId){
		operType = "update";
	}else{
		operType = "add";
	}
    String zd = "";
	if(acount.getIszd() == 0){
		zd = "正常中奖号码";
	}else{
		zd = "指定中奖号码";
	}
	
%>
<!DOCTYPE html >
<html lang="en">
<head>
    <%@include file="../common/includeBaseSet.jsp" %>
    <%@include file="../common/includeSystemSet.jsp" %>
    <script type="text/javascript" charset="utf-8" src="${jypath}/static/plugins/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${jypath}/static/plugins/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <link href="${jypath}/static/css/jobmanager.css" rel="stylesheet" />
    <script type="text/javascript" charset="utf-8" src="${jypath}/static/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
<div class="container">
    <div class="row-fluid">
        <div class="col-xs-12 ui-dialog">
        
        
            <div class="ui-dialog-content ui-widget-content" style="margin-bottom:10px;">
                <form id="auForm" method="POST" onsubmit="return false;">
                <%
        			if(null!=userId){
       			 %>	
               <h3 class="header smaller lighter blue">中奖信息</h3>
                    <table cellspacing="0" cellpadding="0" border="0" class="customTable">
                        <tbody>
                         <tr class="FormData">
                             <td class="CaptionTD"><font color="red">*</font>中奖昵称：</td>
                            <td class="DataTD">&nbsp;
                              <input type="text"   value="${acount.userName }" readonly="readonly"  maxlength="25" id="nickname"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                               <td class="CaptionTD" ><font color="red">*</font>中奖号码：</td>
                            <td class="DataTD" style="margin-left: -50px;">
                                 <input type="text"   value="${acount.lucyNum }" readonly="readonly"  maxlength="25" id="number"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                            <td class="CaptionTD" ><font color="red">*</font>是否指定：</td>
                            <td> <input type="text"   value="<%=zd %>" readonly="readonly"  maxlength="25"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        </tbody>
                    </table>
        		<% 
        		}
        		%>
                    <h3 class="header smaller lighter blue">指定中奖人</h3>
                     <table cellspacing="0" cellpadding="0" border="0" class="customTable" style="text-align: left">
                        <tbody>
                         <tr class="FormData">
                            <td class="DataTD">&nbsp;
                            <button type="button" onclick="selectUser()"
                                class="btn btn-primary btn-xs ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
                                role="button" aria-disabled="false"><span class="ui-button-text"><i
                                class="icon-ok bigger-110"></i>&nbsp;从用户中指定</span></button>
                             
                             </td>
                        </tr>
                        <tr class="FormData">
                            <td class="DataTD">&nbsp;
                            
                            <button type="button" onclick="selectEggUser()"
                                class="btn btn-primary btn-xs ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
                                role="button" aria-disabled="false"><span class="ui-button-text"><i
                                class="icon-ok bigger-110"></i>&nbsp;从已购该期中指定</span></button>
                        </tr>
                        </tbody>
                    </table>
					<input type="hidden" name="eggid" id="eggid" value="<%=eggid%>"/>
					<input type="hidden" name="userId" id="userId" value="<%=userId%>"/>
					<input type="hidden" name="operType" id="operType" value="<%=operType%>"/>
					<input type="hidden" name="winType" id="winType" value=""/>
					<input type="hidden" name="answerId" id="answerId" value="<%=answerId%>"/>
					</form>
            </div>
            <!--底部按钮-->
            <div class="navbar-fixed-bottom ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
                <div class="ui-dialog-buttonset">
                    <button type="button" onclick="closeWin();"
                            class="btn btn-xs ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
                            role="button" aria-disabled="false"><span class="ui-button-text"><i
                            class="icon-remove bigger-110"></i>&nbsp;取消</span></button>
                </div>
            </div>
        </div>
    </div>
</div>
	<script src="${jypath}/static/js/mui/zepto.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${jypath}/static/js/mui/zepto.fx.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${jypath}/static/js/mui/mui.min.js"></script>
<script type="text/javascript">
function selectUser() {
	$("#winType").val(0);
    var url = jypath + '/jc/user/index?type=select';
    art.dialog.open(url, {title: '指定中奖者', width: '100%', height: '100%'});
}

function selectEggUser() {
	$("#winType").val(1);
	var eggid = $("#eggid").val();
    var url = jypath + '/jc/paintedEgg/buyList?type=select&eggid='+eggid;
    art.dialog.open(url, {title: '指定中奖者', width: '100%', height: '100%'});
}
function setUserId(chks) {
     $('#userId').val(chks);
     var type  = $('#winType').val();
     var answerId  = $('#answerId').val();
     var operType  = $('#operType').val();
     var id = $("#eggid").val();
     artDialog.confirm("确认指定中奖人吗？", function () {
	        JY.Ajax.doRequest(null, jypath + '/jc/paintedEgg/zd', {eggid: id,userId:chks,type:type,operType:operType,answerId:answerId}, function (data) {
	        	if(data.res == 1) {
                    artDialog.alert(data.resMsg,function(){
                        artDialog.close(); 
                        var win = art.dialog.open.origin;//来源页面
                        //win.search();
                        win.setClose();
                    });
                } else {
                    artDialog.alert(data.resMsg);
                }
	        });
	    }
       );
}
    
</script>
<%@include file="../common/bottom.jsp" %>
</body>
</html>


