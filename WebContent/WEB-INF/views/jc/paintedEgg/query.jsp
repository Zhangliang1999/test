<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.jy.common.utils.DateUtils"%>
<%@page import="com.sun.accessibility.internal.resources.accessibility"%>
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
	String state = "";
	if(acount.getState() ==0){
		state="未开始";
	}else if(acount.getState() ==1){
		state="进行中";
	}else if(acount.getState() ==2){
		state="已揭晓";
	}
	List<PaintedEggAnswer> list = new ArrayList<PaintedEggAnswer>();
	if(null!=acount.getAnswers()){
		 list = acount.getAnswers();
	}
	String s1="";
	String s2="";
	String s3="";
	SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	if(null!=acount.getUpdatetime()){
	 s1 = sdf.format(acount.getUpdatetime()); 
	}
	
	if(null!=acount.getCreatetime()){
		 s2 = sdf.format(acount.getCreatetime()); 
	}
	
	
	if(null!=acount.getEndtime()){
		s3 = sdf.format(acount.getEndtime()); 
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
    <link rel="stylesheet" href="${jypath}/static/plugins/DataTables-1.10.12/media/css/dataTables.bootstrap.css" />
</head>
<body style="height:870px;">
<div class="container">
    <div class="row-fluid">
        <div class="col-xs-12 ui-dialog">
            <div class="ui-dialog-content ui-widget-content" style="margin-bottom:10px;">
                <div class="col-xs-4">
                    <h3 class="header smaller lighter blue">基本信息</h3>
                <form id="auForm" method="POST" onsubmit="return false;">
                    <table cellspacing="0" cellpadding="0" border="0" class="customTable">
                        <tbody>
                        <tr style="display:none">
                            <td colspan="2" class="ui-state-error">
                                <input type="hidden" name="recommendId">
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">商品名称：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text"  value="${acount.title}" maxlength="32" readonly="readonly" 
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">赛事名称：</td>
                            <td class="DataTD">&nbsp;  
                                <input type="text"  value=" ${acount.team_id1} vs  ${acount.team_id2}"  maxlength="32" readonly="readonly" 
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">状态：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text"  value=" <%=state%>"    maxlength="10" readonly="readonly" 
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">购买价格：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="12" name="title"   value="${acount.price }" readonly="readonly"  
                                       class="FormElement ui-widget-content ui-corner-all"/>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">总需下线：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="12" jyValidate="required" value="${acount.lowerlimit }" readonly="readonly"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"> 题目内容：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text"   value="${acount.content }" readonly="readonly"  maxlength="25"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"> 中奖昵称：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text"   value="${acount.userName }" readonly="readonly"  maxlength="25"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"> 中奖号码：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text"   value="${acount.lucyNum }" readonly="readonly"  maxlength="25"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                          <tr class="FormData">
                            <td class="CaptionTD"> 是否为指定：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text"   value="<%=zd %>" readonly="readonly"  maxlength="25"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                            <tr class="FormData">
                                <td class="CaptionTD">创建时间：</td>
                                <td class="DataTD">&nbsp;
                                    <input type="text" name="createTime" disabled   value="<%=s2 %>" readonly="readonly" 
                                           class="FormElement ui-widget-content ui-corner-all"></td>
                            </tr>
                             <tr class="FormData">
                            <td class="CaptionTD">结束时间：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="endtime" jyValidate="required" value="<%=s3 %>" readonly="readonly"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                            </tr>
                            <tr class="FormData">
                                <td class="CaptionTD">更新时间：</td> 
                                <td class="DataTD">&nbsp;
                                    <input type="text" name="updateTime" disabled value="<%=s1 %>" readonly="readonly" 
                                           class="FormElement ui-widget-content ui-corner-all"></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                </div>
                <div class="col-xs-8">
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">购买明细</h3>
                            <div class="table-responsive">
                                    <table id="dataTables" class="table table-striped table-bordered table-hover dataTable"
                                         <%--  aria-describedby="dataTables"--%>>
                                        <thead>
                                        <tr role="row">
                                            <th>手机号</th>
                                            <th>昵称</th>
                                            <th>幸运号开始</th>
                                            <th>幸运号结束</th>
                                            <th>总注数</th>
                                            <th>是否正确</th>
                                        </tr>
                                        </thead>
                                      </table>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">问题答案</h3>
                             <%--<div class="table-header" id="buytip">
                                 &nbsp;
                             </div>--%>
                            <div class="table-responsive">
                                <table id="buyTable" class="table table-striped table-bordered table-hover dataTable"
                                       class="table table-striped table-bordered table-hover"
                                       aria-describedby="buyTable">
                                    <thead>
                                    <tr role="row">
                                        <th class="center" width="40">序号</th>
                                        <th class="center">答案</th>
<%--
                                        <th class="center" width="60">是否正确</th>
--%>
                                    </tr>
                                    </thead>
                                    <tbody>
								<%
									for(int i =0;i<list.size();i++){
										PaintedEggAnswer answer = list.get(i);
								%>
									<tr>
									<td class="center"><%=i+1 %></td>
									<td class="DataTD">&nbsp; <input type="text" name="name1" maxlength="15" value="<%=answer.getAnswer() %>"
                                           class="FormElement ui-widget-content ui-corner-all" style="width: 400px;"></td>
<%--
                                        <td class="center"></td>
--%>
								</tr>
								<%		
									}
								%>
							</tbody>
                                </table>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
            <!--底部按钮-->
            <div class="navbar-fixed-bottom ui-dialog-buttonpane ui-widget-content ui-helper-clearfix" style="z-index: 999">
                <div class="ui-dialog-buttonset">
                    <button type="button" onclick="closeWin();"
                            class="btn btn-xs ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
                            role="button" aria-disabled="false"><span class="ui-button-text"><i
                            class="icon-remove bigger-110"></i>&nbsp;取消</span></button>
                </div>
            </div>
        </div>
    </div>
    <script src="${jypath}/static/plugins/DataTables-1.10.12/media/js/jquery.dataTables.min.js"></script>
    <script src="${jypath}/static/plugins/DataTables-1.10.12/media/js/dataTables.bootstrap.js"></script>
    <%@include file="../common/bottom.jsp" %>
     <script type="text/javascript">
        var table;
        var eggid = '<%=eggid%>';
        function showTable() {
            table = $('#dataTables').DataTable({
                ajax: {
                    type:"POST",
                    url: jypath + '/jc/paintedEgg/paintedEggUser',
                   // "dataSrc": "obj",
                    data: function ( d ) {
                        //添加额外的参数传给服务器
                        d.eggid = eggid;
                    }
                },
                scrollY: 200,
                serverSide: true, //服务器端分页
                sort : false,
                filter : false,
                bLengthChange:false, //每页显示行数
                columns: [
                    {"data": "mobile"},
                    {"data": "nick_name"},
                    {"data": "luckynumstart"},
                    {"data": "luckynumend"},
                    {"data": "totalcnt"},
                    {"data": "isright"}
                ],
                columnDefs: [
                    {
                        targets: 5,
                        render: function (a, b) {
                            if (a == 1) {
                                return "<font color='green'>正确</font>";
                            } else if (a == 0) {
                                return "<font color='red'>错误</font>";
                            }
                            return "<font color='gray'>错误</font>";;
                        }
                    }

                ],
                language : {
                    url :  jypath + "/static/plugins/DataTables-1.10.12/language_chinese.json"
                }
            });

        }
        //查询
        function doSearch() {
            table.ajax.reload();
        }
        $(function(){
            showTable();
        })
    </script>
</body>
</html>


