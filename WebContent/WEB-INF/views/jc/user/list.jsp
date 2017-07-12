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
                            &nbsp;&nbsp;<span id="selectisValid"><label></label>：<select  data-placeholder="状态" name="isValid" class="chosen-select isSelect75"></select></span>
							<span><label>是否是机器人</label>：
								<select name="isRobot" class="isSelect75">
									<option value="">全部</option>
									<option value="0" selected>否</option>
									<option value="1">是</option>
								</select>
							</span>
                            &nbsp;&nbsp;<button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
                            &nbsp;&nbsp;
                            <c:if test="${param.type == 'select'}">
                                <button class="btn btn-sm btn-success" onclick="doSelect()">
                                    <i class="icon-ok"></i>
                                    选择
                                </button>
                            </c:if>
                            <c:if test="${param.type == 'multipleSelect'}">
                                <button class="btn btn-sm btn-success" onclick="doMultipleSelect()">
                                    <i class="icon-ok"></i>
                                    选择
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
                                <th style="width:10%" class="center">手机号</th>
                                <th style="width:8%"  class="center">昵称</th>
                                <th style="width:10%" class="center">真实姓名</th>
                                <th style="width:10%" class="center">身份证</th>
                                <th style="width:8%"  class="center">个人签名</th>
                                <th style="width:8%"  class="center">邀请码</th>
                                <th style="width:8%"  class="center">总关注数</th>
                                <th style="width:8%"  class="center">创建时间</th>
                                <th style="width:8%"  class="center">更新时间</th>
                                <th style="width:5%"  class="center">是否有效</th>
                                <th style="width:5%"  class="center">是否是机器人</th>
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
    var type = '${param.type}'
    //勾选
    function doSelect() {
        var chks = [];
        $('#baseTable input[name="ids"]:checked').each(function(){
            chks.push($(this).val());
        });
        if(chks.length > 1) {
            JY.Model.info("只允许一条记录!");
        } else if(chks.length==0) {
            JY.Model.info("您没有选择任何内容!");
        }else{
            var win = art.dialog.open.origin;//来源页面
            win.setUserId(chks.toString());
            art.dialog.close()
        }
    }
    /**
     * 多选
     */
    function doMultipleSelect() {
        var chks = [];
        $('#baseTable input[name="ids"]:checked').each(function(){
            var td = $(this).parents("tr").find("td");
            chks.push({userId: $(this).val(), mobile: td.eq(2).text(), nickName: td.eq(3).text(),realName: td.eq(4).text()});
        });
        if(chks.length==0) {
            JY.Model.info("您没有选择任何内容!");
        }else{
            var win = art.dialog.open.origin;//来源页面
            console.log(chks);
            win.setUsers(chks);
            art.dialog.close();
        }
    }
</script>
<script src="${jypath}/static/js/jc/user/user.js"></script>
</html>