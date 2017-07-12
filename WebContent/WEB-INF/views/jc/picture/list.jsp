<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    //String type = StringUtils.defaultIfEmpty(request.getParameter("type"), "");
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
							&nbsp;&nbsp;<span id="selectisValid"><label></label>：<select  data-placeholder="状态" name="isValid" class="chosen-select isSelect75"></select></span>													
							&nbsp;&nbsp;<button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
                            &nbsp;&nbsp;
                                <c:if test="${param.type == 'select'}">
                                    <button class="btn btn-sm btn-success" onclick="doSelect()">
                                    <i class="icon-ok"></i>
                                        选择图片
                                    </button>
                                </c:if>

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
								<th style="width:10%" class="center">名称</th>
								<%--<th style="width:10%" class="center">描述</th>--%>
								<th style="width:8%"  class="center">路径</th>
                                <th style="width:8%"  class="center">创建时间</th>
                                <th style="width:15%" class="center">操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="row">
						<%--<div class="col-sm-4">
							<div class="dataTables_info customBtn" >
								<c:forEach var="pbtn" items="${permitBtn}">
									<a href="#" title="${pbtn.name}" id="${pbtn.btnId}" class="lrspace3" ><i class='${pbtn.icon} bigger-220'></i></a>
								</c:forEach>
							</div>
						</div>--%>
                        <div class="col-sm-4">
                            <div class="dataTables_info customBtn">

                                <a class="lrspace3" id="addBtn" title="增加" href="#"><i class="icon-plus-sign color-green bigger-220"></i></a>

                                <a class="lrspace3" id="deleteBatchBtn" title="批量删除" href="#"><i class="icon-trash color-red bigger-220"></i></a>

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
			<%@include file="form.jsp" %>
			<!-- #dialog-confirm -->
			<%@include file="../common/dialog.jsp" %>	
			</div>
		</div>
	</div>	
</body>
<script type="text/javascript">
    var basePath = "<%=basePath%>";
    var type = '${param.type}'
    //勾选图片
    function doSelect() {
        var chks = [];
        $('#baseTable input[name="ids"]:checked').each(function(){
            chks.push('/upload/img/' + $(this).val());
        });
        if(chks.length > 1) {
            JY.Model.info("只允许选择一张图片!");
        } else if(chks.length==0) {
            JY.Model.info("您没有选择任何内容!");
        }else{
            var win = art.dialog.open.origin;//来源页面
            win.setPath(chks.toString());
            art.dialog.close()
        }
    }
</script>
<script src="${jypath}/static/js/jc/picture/picture.js"></script>
</html>