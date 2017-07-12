<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	String eggid =(String) request.getAttribute("eggid");
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
                            &nbsp;&nbsp;<button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
                        <c:if test="${param.type == 'select'}">
                                    <button class="btn btn-sm btn-success" onclick="doSelect()">
                                    <i class="icon-ok"></i>
                                       选择中奖者
                                    </button>
                                </c:if>
                        </div>
                    </div>
					<input type='hidden' class='pageNum' name='pageNum' value='1'/>
					<input type='hidden' class='' name='eggId' id="eggId" value=''/>
					<input type='hidden' class='pageSize'  name='pageSize' value='5'/>
					</form>
				<table id="baseTable"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th style="width: 3%" class="center"><label><input
									type="checkbox" class="ace"><span class="lbl"></span></label>
							</th>
							<th class="center hidden-480">序号</th>
							<th class="center">猜蛋编码</th>
							<th class="center">用户名称</th>
							<th class="center">幸运号开始</th>
							<th class="center">幸运号结束</th>
							<th class="center">总数</th>
							<th class="center">是否正确</th>
							<th class="center">创建时间</th>
							<th class="center">修改时间</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
				<div class="row">
						<div class="col-sm-4">
							<div class="dataTables_info customBtn" >
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
    $(function () {
    	    //加载表格
    	    getbaseList();
    	    //增加回车事件
    });   	   
    	function search() {
    	    $("#searchBtn").trigger("click");
    	}
    	function getbaseList(init) {
           var eggid = '<%=eggid%>';
    	    if (init == 1)$("#baseForm .pageNum").val(1);
    	    JY.Model.loading();
    	    JY.Ajax.doRequest("baseForm", jypath + '/jc/paintedEgg/queryBuyList', {eggid:eggid}, function (data) {
    	        $("#baseTable tbody").empty();
    	        console.log(data);
    	        var obj = data.obj;
    	        var list = obj.list;
    	        var results = list.results;
    	        var permitBtn = obj.permitBtn;
    	        var pageNum = list.pageNum, pageSize = list.pageSize, totalRecord = list.totalRecord;
    	        var html = "";
    	        if (results != null && results.length > 0) {
    	            var leng = (pageNum - 1) * pageSize;//计算序号
    	            var st = '';
    	            for (var i = 0; i < results.length; i++) {
    	                var l=results[i];
    	                if(''!=l.isRight){
    	                if(1 == l.isRight){
   	          		  st= "正确";
   	          		}else if(0 == l.isRight){
    	          			 st= "错误";
   	          		}else{
    	          			st = '未公布';
    	          		}
    	                }else{
    	                	st = '未公布';
    	                }
   	                html+="<tr>";
   	                html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.userId+"' class='ace' /> <span class='lbl'></span></label></td>";
    	            html+="<td class='center'>"+(i+leng+1)+"</td>";
    	            html+="<td class='center'>"+JY.Object.notEmpty(l.eggid)+"</td>";
    	            html+="<td class='center'>"+JY.Object.notEmpty(l.nick_name)+"</td>";
   	                html+="<td class='center'>"+JY.Object.notEmpty(l.luckynumstart)+"</td>";
   	                html+="<td class='center'>"+JY.Object.notEmpty(l.luckynumend)+"</td>";
    	            html+="<td class='center'>"+JY.Object.notEmpty(l.totalcnt)+"</td>";
    	            html+="<td class='center'>"+st+"</td>";
   	                html+="<td class='center'>"+JY.Date.Default(l.createtime).substr(0,10)+"</td>";
    	            html+=JY.Tags.setFunction(l.eggid,permitBtn); //给按钮传主键
   	                html+="</tr>";
    	            }
    	            $("#baseTable tbody").append(html);
    	            JY.Page.setPage("baseForm", "pageing", pageSize, pageNum, totalRecord, "getbaseList");
    	        } else {
    	            html += "<tr><td colspan='10' class='center'>没有相关数据</td></tr>";
    	            $("#baseTable tbody").append(html);
    	            $("#pageing ul").empty();//清空分页
    	        }

    	        JY.Model.loadingClose();
    	    });
    	}
    	
        var basePath = "<%=basePath%>";
        var type = '${param.type}'
        //勾选图片
        function doSelect() {
            var chks = [];
            $('#baseTable input[name="ids"]:checked').each(function(){
                chks.push( $(this).val());
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
</script>
</html>