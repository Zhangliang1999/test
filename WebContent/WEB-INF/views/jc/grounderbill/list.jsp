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
							<select class="isSelect75" name="state">
								<option value="">全部</option>
								<option value="0">有效</option>
								<option value="1">无效</option>
							</select>
                            &nbsp;&nbsp;<button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
							<button class="btn btn-xs btn-light" title="导出到EXCEL"
                                    onclick="exportExcel();"><i class="icon-download-alt bigger-110 icon-only"></i></button>
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
                                <th style="width:10%" class="center">手机号</th>
                                <th style="width:10%" class="center">昵称</th>
                                <th style="width:8%"  class="center">价格</th>
                                <th style="width:8%"  class="center">标题</th>
                                <th style="width:18%"  class="center">内容</th>
<%--
                                <th style="width:8%"  class="center">比赛类型</th>
--%>
								<th style="width:8%"  class="center">状态</th>
								<th style="width:8%"  class="center">创建时间</th>
                                <th style="width:8%"  class="center">更新时间</th>
                                <th style="width:10%" class="center">操作</th>
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
<script src="${jypath}/static/js/jc/grounderbill/grounderbill.js"></script>
</html>