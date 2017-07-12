<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String goodsId = StringUtils.defaultIfEmpty(request.getParameter("goodsId"), "");
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
                    <table cellspacing="0" cellpadding="0" border="0" class="customTable">
                        <tbody>
                         <tr style="display:none">
                            <td colspan="2" class="ui-state-error"><input type="hidden" name=goodsId></td>
                        </tr>
                         <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>商品名称：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="20" name="title" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                         <tr class="FormData">
                            <td class="CaptionTD" style="width:100px"><font color="red">*</font>商品封面：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="headPic" onclick="selectPicture()" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                                <a href="#" title="清空" onclick="emptyPath(); return false;" class="lrspace3 aBtnNoTD"
                                   data-toggle="modal"><i class='icon-remove bigger-120 red'></i></a>
                            </td>
                        </tr>
                         <tr class="FormData">
                            <td class="CaptionTD">库存：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="3" name="stock"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">活动开始：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="begintime"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">活动结束：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="endtime"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                          <tr class="FormData" id="sp">
                            <td class="CaptionTD">商品照片：</td>
                            <td class="DataTD" id="pics">&nbsp;
                           <div class="content_all">
			<div class="list_item">
				<div class="start_work start_work_pro">
				<ul class="list_phone" style="list-style: none">
					</ul>
					<ul class="list_phone2" style="list-style: none">
												<!-- 	<li><div class='sdtop0 deltop'>
																<i class='del' id='0'
																	title='/JYSystem/static/images/descImg0.png'></i>
																<div class='sdinner'>
																	<img width='100' height='100'
																		src='/JYSystem/static/images/descImg0.png'></img>
																</div>
															</div></li> 
															-->
														<li>
														   <c:if test="${param.type == 'find'}">
														    <div class="sdtop0 deltop deltopEnd" id="delimg" style="display: none">
                   										   </c:if>
                   										   <c:if test="${param.type != 'find'}">
														    <div class="sdtop0 deltop deltopEnd" id="delimg">
                   										   </c:if>
																<div class="sdinner img_tt">
																	<img width="100"
																		src="${jypath}/static/images/initBg.png"
																		onclick="selectPictures()" />
																</div>
															</div>
														</li>

													</ul>
					<div class="clear"></div>
				</div>
			</div>
		</div>
							</td>
                        </tr>
                         <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>描述：</td>
                            <td class="DataTD">&nbsp;
                              <textarea name="content"  id="content"  maxlength="300" style="resize: none; width: 500px;height: 200px;"></textarea>
                            </td>
                        </tr>
                        <c:if test="${param.type == 'find'}">
                            <tr class="FormData">
                                <td class="CaptionTD">创建时间：</td>
                                <td class="DataTD">&nbsp;
                                    <input type="text" name="createTime"
                                           class="FormElement ui-widget-content ui-corner-all"></td>
                            </tr>
                            <tr class="FormData">
                                <td class="CaptionTD">更新时间：</td>
                                <td class="DataTD">&nbsp;
                                    <input type="text" name="updateTime"
                                           class="FormElement ui-widget-content ui-corner-all"></td>
                            </tr>
                            <tr class="FormData">
                                <td class="CaptionTD">操作人：</td>
                                <td class="DataTD">&nbsp;
                                    <input type="text" maxlength="128" name="operId" jyValidate="required"
                                           class="FormElement ui-widget-content ui-corner-all"/>
                                </td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                    <input type="hidden"  value=""  id="picList" name="picList">
                    <input type="hidden"  value="0"  id="photoNum">
                    <input type="hidden"  value="${param.type}"  id="type">
                </form>
            </div>
            <!--底部按钮-->
            <div class="navbar-fixed-bottom ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
                <div class="ui-dialog-buttonset">
                    <c:if test="${param.type != 'find'}">
                        <button type="button" onclick="doAdd()"
                                class="btn btn-primary btn-xs ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
                                role="button" aria-disabled="false"><span class="ui-button-text"><i
                                class="icon-ok bigger-110"></i>&nbsp;保存</span></button>
                    </c:if>
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
var photoNum = $("#photoNum").val();
var count = 0;
var picList;
var picinfo = new Array();
$(function(){
//删除照片
$(document).on('click','.del',function(e){
	var me=this;
	e.preventDefault();
	$(this).parent().animate({
		"opacity":0
	},500,function  () {
		var type = $("#type").val();
		if(type!='find'){
			alert(me.title);
			alert(me.id);
			var id = me.id;
			var tt = new Array();
	  		tt = id.split(";");
	      JY.Ajax.req(null, jypath + '/jc/goods/deletePicRel', {relId: tt[0],entityId:tt[1]}, function (data) {
	           alert(data);
	           console.log(data);
	        });
		}
		var st = $("#picList").val();
  		var tt = new Array();
  		tt = st.split(";");
		//移除数组中的对应的id
		for(var i=0;i<tt.length;i++){
		var path =tt[i];
			if(path==me.title){
				if(i==0){
					tt.splice(i,1);
				}else{
					tt.splice(i,v.id);
				}
				$(me).parent().remove();
				}
		}
		var ss = "";
		for(var i = 0;i<tt.length;i++){
			if(i == 0){
				ss = tt[i];
			}else{
				ss = ss+";"+tt[i];
			}
		}
	
		$("#picList").val(ss);
	});
});
});
 var type = 0;
function selectPicture() {
	type = 0;
    var url = jypath + '/jc/picture/index?type=select';
    art.dialog.open(url, {title: '选择图片', width: 850, height: 500});
}

function selectPictures() {
	type = 1;
    var url = jypath + '/jc/picture/index?type=select';
    art.dialog.open(url, {title: '选择图片', width: 850, height: 500});
}
function setPath(chks) {
	if(type == 0){
    $("#auForm input[name$='headPic']").val(chks);
	}else{
		photoNum++;
 		if(photoNum >=5){
 		  $(".img_tt").hide();
 		}
 		picList = $("#picList").val();
 		picinfo = [];
 		picinfo.push(chks);
 		if(picList==''){
 			$("#picList").val(chks);
 		}else{
 			var a  = $("#picList").val();
 			$("#picList").val(a+";"+chks);
 		}
       var img = jypath+chks;
       //var img = jypath+"/static/images/descImg0.png";
       // var node = "<li><div class='sdtop0 deltop'><i  class='del' id ='"+ count + "' title = '"+ img + "'></i><div class='sdinner'><img width='100' height='100' src='"+ img + "'></img></div></div></li>";
	   var tt ="<li><div class='sdtop0 deltop'><i class='del' id ='"+ count + "' title = '"+ img + "' ></i><div class='sdinner'><img width='100' src='${jypath}"+img+"' /></div></div></li>";
		$(".list_phone").prepend(tt);
		count++;
	}
}
//清空图片路径
function emptyPath() {
    $("#auForm input[name$='headPic']").val("");
}
    var goodsId = '<%=goodsId%>';
    function doAdd() {
        var url = "";
        if (goodsId) {
            url = jypath + '/jc/goods/update';
        } else {
            url = jypath + '/jc/goods/add';
        }
        if (JY.Validate.form("auForm",2)) {
            var body=$("#content").val();
            var param = {content: body};
            JY.Ajax.req("auForm", url, param, function (data){
                if(data.res == 1) {
                    artDialog.alert(data.resMsg,function(){
                        artDialog.close();
                    });
                } else {
                    artDialog.alert(data.resMsg);
                }
            });
        }
    }
    function setForm(data) {
        var l = data.obj;
        $("#auForm input[name='goodsId']").val(l.goodsId);
        $("#auForm input[name='headPic']").val(JY.Object.notEmpty(l.headPic));
        $("#auForm input[name='stock']").val(JY.Object.notEmpty(l.stock));
        $("#auForm input[name='title']").val(JY.Object.notEmpty(l.title));
        $("#auForm textarea[name='content']").val(JY.Object.notEmpty(l.content));
        $("#auForm input[name='begintime']").val(JY.Date.Default(l.begintime));
        $("#auForm input[name='endtime']").val(JY.Date.Default(l.endtime));
        $("#auForm input[name='createTime']").val(JY.Date.Default(l.createTime));
        $("#auForm input[name='updateTime']").val(JY.Date.Default(l.updateTime));
        $("#auForm input[name='operId']").val(JY.Object.notEmpty(l.operId));
        var type = $("#type").val();
        if(JY.Object.notEmpty(l.rel)){
        	$("#delImg").hide();
        	for(var i =0;i<l.rel.length;i++){
        		var picList = $("#picList").val();
        		var img = l.rel[i].picId;
        		var relId = l.rel[i].relId;
        		var entityId = l.rel[i].entityId;
        		var gg = relId+";"+entityId;
        		if(picList==''){
         			$("#picList").val(img);
         		}else{
         			var a  = $("#picList").val();
         			$("#picList").val(a+";"+img);
         		}
        		var tt;
        		if(type == 'find'){
        			tt ="<li><div class='sdtop0 deltop'><div class='sdinner'><img width='100' src='${jypath}"+img+"' /></div></div></li>";
        		}else{
        			 tt ="<li><div class='sdtop0 deltop'><i class='del' title='"+img+"' id='"+gg+"' value='"+entityId+"' ></i><div class='sdinner'><img width='100' src='${jypath}"+img+"' /></div></div></li>";
        		}
        			
        			$(".list_phone").prepend(tt);
        	}
        }
    }
    $(function () {
        //时间控件
        $("input[name='begintime']").datetimepicker({
            format:'yyyy-mm-dd hh:ii:00',language:'zh-CN',weekStart:1,todayBtn:1,autoclose: 1,todayHighlight: 1,startView: 2,minView:0,
        }).on('changeDate', function(ev){
            var beginTime=$("input[name='begintime']").val();
            $("input[name='endtime']").datetimepicker('setStartDate',beginTime);
        });
        $("input[name='endtime']").datetimepicker({
            format:'yyyy-mm-dd hh:ii:00',language:'zh-CN',weekStart:1,todayBtn:1,autoclose: 1,todayHighlight: 1,startView: 2,minView:0,
        }).on('changeDate', function(ev){
            var endTime=$("input[name='endtime']").val();
            $("input[name='begintime']").datetimepicker('setEndDate',endTime);
        });

        //加载数据
        if (goodsId) {
            JY.Ajax.req(null, jypath + '/jc/goods/find', {goodsId: goodsId}, function (data) {
                setForm(data);
            });
        }
    })
</script>
<%@include file="../common/bottom.jsp" %>
</body>
</html>


