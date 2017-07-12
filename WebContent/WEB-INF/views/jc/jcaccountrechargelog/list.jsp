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
                            &nbsp;&nbsp;
                            <span><label>充值方式</label>：
                            <select  data-placeholder="类型" name="payType" class="chosen-select isSelect95">
                                <option value="" selected>全部</option>
                                <option value="1">AppStore</option>
                                <option value="2">支付宝</option>
                                <option value="3">微信</option>
                            </select></span>                            &nbsp;&nbsp;
                            <button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤"
                                    onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>

                            <span><a class="btn btn-xs btn-light" onclick="exportExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i></a>
                            </span>
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
                                <th style="width:10%" class="center">账号</th>
--%>
                                <th style="width:10%" class="center">用户编码</th>
                                <th style="width:8%"  class="center">手机号</th>
                                <th style="width:8%"  class="center">昵称</th>
<%--
                                <th style="width:8%"  class="center">余额</th>
--%>
                                <th style="width:8%"  class="center">充值金额</th>
                                <th style="width:8%"  class="center">支付方式</th>
<%--
                                <th style="width:8%"  class="center">支付流水号</th>
--%>
                                <th style="width:8%"  class="center">创建时间</th>
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
<script src="${jypath}/static/js/jc/jcaccountrechargelog/jcaccountrechargelog.js"></script>
</html>