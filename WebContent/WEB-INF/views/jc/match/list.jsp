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
							&nbsp;&nbsp;<span><label>获得结果</label>：
								<select  name="matchResult" class="chosen-select isSelect45">
                                <option value="" selected>全部</option>
                                <option value="0">未获得</option>
                                <option value="1">获得</option>
                            </select></span>
							&nbsp;&nbsp;<span><label>有效</label>：
                            <select name="isValid" class="chosen-select isSelect45">
                                <option value="" selected>全部</option>
                                <option value="0">无效</option>
                                <option value="1">有效</option>
                            </select></span>
							&nbsp;&nbsp;<span id="matchTypeId"><label></label>：<select  data-placeholder="状态" name="matchType" class="chosen-select isSelect75"></select></span>
							开始时间:<input type="text" style="width:100px;" name="searchStartTime"/>
							结束时间:<input type="text" style="width:100px;" name="searchEndTime"/>
                            &nbsp;&nbsp;<button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
                      <c:if test="${param.type == 'select'}">
                                    <button class="btn btn-sm btn-success" onclick="doSelect()">
                                    <i class="icon-ok"></i>
                                       选择赛事
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
<%--
                                <th style="width:10%" class="center">比赛编码</th>
--%>
                                <th style="width:10%" class="center">联赛名称</th>
                                <th style="width:5%" class="center">比赛<br/>类型</th>
<%--
                                <th style="width:10%" class="center">场次号</th>
--%>
                                <th style="width:8%"  class="center">队1(主)</th>
                                <th style="width:8%"  class="center">队2(客)</th>
                                <th style="width:8%"  class="center">开始时间</th>
                                <th style="width:8%"  class="center hidden-480">结束时间</th>
<%--
                                <th style="width:8%"  class="center">竞猜截止时间</th>
--%>
                                <%--<th style="width:8%"  class="center hidden-480">创建时间</th>
                                <th style="width:8%"  class="center hidden-480">更新时间</th>--%>
								<th style="width:5%"  class="center hidden-480">是否<br/>有效</th>
								<th style="width:5%"  class="center hidden-480">比赛<br/>结果</th>
								<th style="width:5%"  class="center hidden-480">比分</th>

							<%--
                                <th style="width:8%"  class="center">操作人</th>
--%>

                                <th style="width:8%" class="center">操作</th>
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
    //勾选图片
    function doSelect() {
        var chks = [];
        var title = [];
        $('#baseTable input[name="ids"]:checked').each(function(){
            chks.push($(this).val());
            title.push(this.id);
        });
        if(chks.length > 1) {
            JY.Model.info("只允许选择一场比赛!");
        } else if(chks.length==0) {
            JY.Model.info("您没有选择任何内容!");
        }else{
            var win = art.dialog.open.origin;//来源页面
            win.setMatch(chks.toString(),title.toString());
            art.dialog.close()
        }
    }
    $(function(){
		//时间控件
		$("input[name='searchStartTime']").datetimepicker({
			format: 'yyyy-mm-dd',
			language: 'zh-CN',
			weekStart: 1,
			todayBtn: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 'month'
		});
		$("input[name='searchEndTime']").datetimepicker({
			format: 'yyyy-mm-dd',
			language: 'zh-CN',
			weekStart: 1,
			todayBtn: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 'month'
		});

	})
</script>
<script src="${jypath}/static/js/jc/match/match.js"></script>
</html>