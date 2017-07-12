<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../common/includeBaseSet.jsp" %>
<%@include file="../common/includeSystemSet.jsp" %>
</head>
<body>
<div class="page-content">
		<div class="row-fluid">	
			<div class="col-xs-12">
					<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
					<div class="row">
						<div class="widget-main">	
							<input type="text" name="keyWord" placeholder="这里输入关键词"   class="input-large">
                            &nbsp;&nbsp;<span><label>状态</label>：
                             <select  data-placeholder="状态" name="state" class="isSelect75">
                                <option value="" selected>全部</option>
                                <option value="0">未发送</option>
                                <option value="1">已发送</option>
                            </select></span> &nbsp;&nbsp;
							<span><label>消息类型</label>：
								<select  data-placeholder="消息类型" name="msgType" class="isSelect115">
									<option value="">全部</option>
									<option value="1">私信</option>
									<option value="2">系统通知</option>
									<option value="3">卖单推送</option>
									<option value="4">发单推送</option>
									<option value="5">中奖通知</option>
									<option value="6">每日彩蛋</option>
								</select>
							</span>
                            &nbsp;&nbsp;
                            <button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button"
                                    onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
                        </div>
                    </div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
					</form>
					<table id="baseTable" class="table table-striped table-bordered table-hover" >
						<thead>
							<tr>
								<th style="width:3%" class="center">
									<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
								</th>
								<th style="width:5%"  class="center hidden-480">序号</th>
<%--
                                <th style="width:10%" class="center">消息编码</th>
--%>
<%--
                                <th style="width:10%" class="center">消息标题</th>
--%>
                                <th style="width:8%"  class="center">消息内容</th>
                                <th style="width:8%"  class="center">发送时间</th>
                                <th style="width:8%"  class="center">类别</th>
                                <th style="width:8%"  class="center">是否发送</th>
                                <th style="width:8%"  class="center">创建时间</th>
                                <th style="width:8%"  class="center">更新时间</th>
<%--
                                <th style="width:8%"  class="center">操作人</th>
--%>
                                <th style="width:15%" class="center">操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<div class="col-sm-4">
							<div class="dataTables_info customBtn" >
								<c:forEach var="pbtn" items="${permitBtn}">
									<a href="#" title="${pbtn.name}" id="${pbtn.btnId}" class="lrspace3" ><i class='${pbtn.icon} bigger-220'></i></a>
								</c:forEach>
							</div>
						</div>
						<div class="col-sm-8">
							<!--设置分页位置-->
							<div id="pageing" class="dataTables_paginate paging_bootstrap">
								<ul class="pagination"></ul>
							</div>
						</div>
					</div>
			<!-- #addorUpdateFrom -->
			<!-- #dialog-confirm -->
			<%@include file="../common/dialog.jsp" %>
			</div>
		</div>
	</div>	
</body>
<script type="text/javascript">
    var basePath = "<%=basePath%>";
</script>
<script src="${jypath}/static/js/jc/messagecenter/messagecenter.js"></script>
</html>