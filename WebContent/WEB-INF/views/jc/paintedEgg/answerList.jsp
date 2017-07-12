<%@page import="java.util.List" %>
<%@page import="com.jy.entity.jc.paintedEgg.PaintedEggAnswer" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.jy.entity.jc.paintedEgg.PaintedEgg" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    List<PaintedEggAnswer> list = (List) request.getAttribute("acount");
    String type = (String) request.getAttribute("type");

    String eggid = "";
    String answerid = "";

%>
<!DOCTYPE html >
<html lang="en">
<head>
    <%@include file="../common/includeBaseSet.jsp" %>
    <%@include file="../common/includeSystemSet.jsp" %>
    <script type="text/javascript" charset="utf-8"
            src="${jypath}/static/plugins/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${jypath}/static/plugins/ueditor/ueditor.all.min.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <link href="${jypath}/static/css/jobmanager.css" rel="stylesheet"/>
    <script type="text/javascript" charset="utf-8"
            src="${jypath}/static/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
<div class="container">
    <div class="row-fluid">
        <div class="col-xs-12 ui-dialog">
            <div class="col-xs-6">
                <h3 class="header smaller lighter blue" style="margin: 15px">答案列表</h3>
                <div class="ui-dialog-content ui-widget-content"
                     style="margin-bottom: 10px;">
                    <table cellspacing="0" cellpadding="0" border="0"
                           class="customTable" style="margin-left: 20px;">
                    </table>
                    <table id="myTable"
                           class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center" width="30%">选择正确答案</th>
                            <th class="center" width="70%">答案</th>
                        </thead>
                        <tbody>
                        <tbody>
                        <%
                            for (int i = 0; i < list.size(); i++) {
                                PaintedEggAnswer answer = list.get(i);
                                String right = answer.getRightAnswerId();
                                String checked = "";
                                if (answer.getAnswerid().equals(right)) {
                                    checked = "checked";
                                    eggid = answer.getEggid();
                                    answerid = answer.getAnswerid();

                                }
                        %>
                        <tr>
                            <td class='center'>
                                <input type="radio" name='ids' <%=checked %>
                                       value='<%=answer.getAnswerid() %>' class='ace'
                                       onclick="setRow('<%=answer.getEggid()%>',this)"/> <span
                                    class='lbl'></span>
                            </td>
                            <td class="center">&nbsp; <input type="text" name="name1" style="width:90%"
                                                             value="<%=answer.getAnswer() %>"></td>

                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-xs-6">
                <h3 class="header smaller lighter blue" style="margin: 15px">指定中奖人</h3>
                <table cellspacing="0" cellpadding="0" border="0" class="customTable" style="text-align: left">
                    <tbody>
                    <tr class="FormData">
                        <td class="DataTD">&nbsp;
                            <button type="button" onclick="selectUser()"
                                    class="btn btn-primary btn-xs ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
                                    role="button" aria-disabled="false"><span class="ui-button-text"><i
                                    class="icon-ok bigger-110"></i>&nbsp;从用户中指定</span></button>
                            <button type="button" onclick="selectEggUser()"
                                    class="btn btn-primary btn-xs ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
                                    role="button" aria-disabled="false"><span class="ui-button-text"><i
                                    class="icon-ok bigger-110"></i>&nbsp;从已购该期中指定</span></button>
                        </td>
                    </tr>
                    <tr class="FormData" id="zd" style="display: none">
                        <td class="DataTD"><h3 style="margin-left: 15px;">指定中奖人：<span style="color: red"
                                                                                      id="zdname"></span></h3></td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <form id="auForm" method="POST" onsubmit="return false;">
                <input type="hidden" name="answerid" id="answerid" value="<%=answerid%>"/>
                <input type="hidden" name="eggid" id="eggid" value="<%=eggid %>"/>
            </form>

            <!--底部按钮-->
            <div
                    class="navbar-fixed-bottom ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
                <div class="ui-dialog-buttonset">
                    <%
                        if ("2".equals(type)) {
                    %>
                    <button type="button" onclick="doAdd()"
                            class="btn btn-primary btn-xs ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
                            role="button" aria-disabled="false">
                        <span class="ui-button-text"><i class="icon-ok bigger-110"></i>&nbsp;保存</span>
                    </button>
                    <%
                        }
                    %>

                    <button type="button" onclick="closeWin();"
                            class="btn btn-xs ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
                            role="button" aria-disabled="false">
							<span class="ui-button-text"><i
                                    class="icon-remove bigger-110"></i>&nbsp;取消</span>
                    </button>
                </div>
                <input type="hidden" name="userId" id="userId" value=""/>
                <input type="hidden" name="type" id="type" value="0"/>
                <input type="hidden" name="winType" id="winType" value=""/>
            </div>
        </div>
    </div>
</div>
<script src="${jypath}/static/js/mui/zepto.min.js"
        type="text/javascript" charset="utf-8"></script>
<script src="${jypath}/static/js/mui/zepto.fx.min.js"
        type="text/javascript" charset="utf-8"></script>
<script src="${jypath}/static/js/mui/mui.min.js"></script>
<script>
    function doAdd() {
        var url = jypath + '/jc/paintedEgg/updateRightAnswer';
        var eggid = $("#eggid").val();
        var answerId = $("#answerid").val();
        if (eggid == '') {
            artDialog.alert('请选择一个正确答案!');
            return;
        }
        var type = $('#winType').val();
        var param = {eggid: $("#eggid").val(), answerid: $("#answerid").val(), userId: $("#userId").val(), type: type};
        JY.Ajax.req("auForm", url, param, function (data) {
            if (data.res == 1) {
                artDialog.alert(data.resMsg, function () {
                    artDialog.close();
                    var win = art.dialog.open.origin;//来源页面
                    win.search();
                });
            } else {
                artDialog.alert(data.resMsg);
            }
        });
    }

    function close() {
        var win = art.dialog.open.origin;//来源页面
        win.search();
    }
    function setRow(v, w) {
        $("#eggid").val(v);
        $("#answerid").val(w.value);
    }


    function selectUser() {
        $("#winType").val(0);
        var url = jypath + '/jc/user/index?type=select';
        art.dialog.open(url, {title: '指定中奖者', width: '100%', height: '100%'});
    }

    function selectEggUser() {
        $("#winType").val(1);
        var eggid = $("#eggid").val();
        var url = jypath + '/jc/paintedEgg/buyList?type=select&eggid=' + eggid;
        art.dialog.open(url, {title: '指定中奖者', width: '100%', height: '100%'});
    }
    function setUserId(chks) {
        artDialog.confirm("确认指定中奖人吗？", function () {
                    JY.Ajax.doRequest(null, jypath + '/jc/user/find', {userId: chks}, function (data) {
                        console.log(data);
                        if (data.res == 1) {
                            $("#zd").show();
                            $("#zdname").html(data.obj.nickName);
                            $('#userId').val(chks);
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


