<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String msgId = StringUtils.defaultIfEmpty(request.getParameter("msgId"), "");
%>
<!DOCTYPE html >
<html lang="en">
<head>
    <%@include file="../common/includeBaseSet.jsp" %>
    <%@include file="../common/includeSystemSet.jsp" %>
    <link rel="stylesheet" href="${jypath}/static/plugins/DataTables-1.10.12/media/css/dataTables.bootstrap.css" />
</head>
<body>
<div class="container">
    <div class="row-fluid">
        <div class="col-xs-12 ui-dialog">
            <div class="ui-dialog-content ui-widget-content" style="margin-bottom:10px;">

                <div class="col-xs-5">
                    <h3 class="header smaller lighter blue">基本信息</h3>
                    <form id="auForm" method="POST" onsubmit="return false;">
                    <table cellspacing="0" cellpadding="0" border="0" class="customTable">
                        <tbody>
                        <tr style="display:none">
                            <td colspan="2" class="ui-state-error"><input type="hidden" name="msgId"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>类别：</td>
                            <td class="DataTD">&nbsp;
<%--
                                //类别（1私信（系统消息）, 2系统通知,3卖单推送,4发单推送,5中奖通知,6每日彩蛋），除了1其他的都要推送
--%>
                                <select class="isSelect145" name="msgType">
                                    <option value="1">消息中心</option>
                                    <option value="2">系统推送</option>
                                   <%-- <option value="3">卖单推送</option>
                                    <option value="4">发单推送</option>
                                    <option value="5">中奖通知</option>
                                    <option value="6">每日彩蛋</option>--%>
                                    通知不选择发件人，则是全部推送
                                </select>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">发送时间：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" name="sendTime"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                                <font color="red">为空表示立即发送</font>
                            </td>
                        </tr>
                        <%--<tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>消息标题：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="title" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>--%>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>消息内容：</td>
                            <td class="DataTD">&nbsp;
                                <textarea name="content"  id="content"  maxlength="300" style="resize: none; width: 145px;height: 150px;"></textarea>
                            </td>
                        </tr>
                        <c:if test="${param.type == 'find'}">
                            <tr class="FormData">
                                <td class="CaptionTD"><font color="red">*</font>是否发送：</td>
                                <td class="DataTD">&nbsp;
                                    <select class="isSelect145" name="state">
                                        <option value="0">未发送</option>
                                        <option value="1">已发送</option>
                                    </select>
                                </td>
                            </tr>
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
                </form>
                </div>
                <div class="col-xs-7">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">接收人 </h3>
                            <div class="table-responsive">
                                <a href="#" title="新增" id="addRow" class="lrspace3"><i class="icon-plus-sign color-green bigger-220"></i></a>
                                <a href="#" title="删除" id="delRow"><i class="icon-remove-sign color-red bigger-220"></i></a>
                                <table id="dataTables" class="table table-striped table-bordered table-hover dataTable"
                                    aria-describedby="dataTables">
                                    <thead>
                                    <tr role="row">
                                        <th><input type="checkbox" class="checkall" /></th>
                                        <th>收件人编码</th>
                                        <th>收件人手机号</th>
                                        <th>真实姓名</th>
                                        <th>昵称</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                </div>

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
<script src="${jypath}/static/plugins/DataTables-1.10.12/media/js/jquery.dataTables.min.js"></script>
<script src="${jypath}/static/plugins/DataTables-1.10.12/media/js/dataTables.bootstrap.js"></script>
<script type="text/javascript">
    var msgId = '<%=msgId%>';
    function doAdd() {
        var url = "";
        if (msgId) {
            url = jypath + '/jc/messagecenter/update';
        } else {
            url = jypath + '/jc/messagecenter/add';
        }
        if (JY.Validate.form("auForm", 2)) {
            var params = $.extend({}, JY.Object.serialize($("#auForm")));
            params.receivers = getTableContent();
            if(params.msgType == 1) {
                if(params.receivers.length == 0){
                    artDialog.alert("私信需要选择收件人！");
                    return;
                }
            }
            $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(params),
                dataType: 'json',
                contentType: "application/json",
                success: function (data, textStatus) {
                    if (data.res == 1) {
                        artDialog.alert(data.resMsg, function () {
                            artDialog.close();
                        });
                    } else {
                        artDialog.alert(data.resMsg);
                    }
                }
            });
        }
    }
    function setForm(data) {
        var l = data.obj;
        $("#auForm input[name='msgId']").val(l.msgId);
        $("#auForm input[name='title']").val(JY.Object.notEmpty(l.title));
        $("#auForm textarea[name='content']").val(JY.Object.notEmpty(l.content));
        $("#auForm select[name='msgType']").val(JY.Object.notEmpty(l.msgType));
        $("#auForm select[name='state']").val(JY.Object.notEmpty(l.state));
        $("#auForm input[name='sendTime']").val(JY.Date.Default(l.sendTime));
        $("#auForm input[name='createTime']").val(JY.Date.Default(l.createTime));
        $("#auForm input[name='updateTime']").val(JY.Date.Default(l.updateTime));
        $("#auForm input[name='operId']").val(JY.Object.notEmpty(l.operId));
    }

    $(function () {
        //时间控件
        $("input[name='sendTime']").datetimepicker({
            format: 'yyyy-mm-dd hh:ii:00',
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 0,
        });
        //加载数据
        if (msgId) {
            JY.Ajax.req(null, jypath + '/jc/messagecenter/find', {msgId: msgId}, function (data) {
                setForm(data);
            });
        }
    })
</script>
<script type="text/javascript">
    var table; // use a global for the submit and return data rendering in the examples
    function showTable() {
        table = $('#dataTables').DataTable({
            ajax: {
                type:"POST",
                url: jypath + '/jc/messagecenter/findReceivers',
                data: function ( d ) {
                    //添加额外的参数传给服务器
                    d.msgId = msgId;
                }
            },
            scrollY: 200,
            serverSide: false, //服务器端分页
            sort : false,
            filter : false,
            bLengthChange:false, //每页显示行数
//                bDisplayLength : 5, //默认10
            columns: [
                {
                    "sClass": "text-center", //居中
                    "data": "ID",
                    "render": function (data, type, full, meta) {
                        return '<input type="checkbox"  class="checkchild"  value="' + full.receiver + '" />';
                    },
                    "bSortable": false
                },
                {"data": "receiver"},
                {"data": "mobile"},
                {"data": "realName"},
                {"data": "nickName"}
            ],
            // "order": [[ 4, "desc" ]],
            columnDefs: [
                /* {
                 targets: 1,
                 render: function (a, b, c, d) {
                 if(c.tsizem > 200 && c.tabDayChangeRate > 100) {//异动
                 return "<font color='red'>"+c.table_name+"</font>";
                 }
                 return a;
                 }*/
            ],
            language : {
                url :  jypath + "/static/plugins/DataTables-1.10.12/language_chinese.json"
            },
            //工具栏
            initComplete: function () {//完成
//                $("#mytool").append('<button id="datainit" type="button" class="btn btn-primary btn-sm">添加</button>&nbsp');
//                $("#mytool").append('<button id="datainit" type="button" class="btn btn-primary btn-sm">删除</button>&nbsp');
            }

        });

    }

    //选中用户回调
    function setUsers(rows) {
        var tableData = getTableContent();
        for(var i=0;i<rows.length;i++) {
            var isExisted = false;
            for(var j=0;j<tableData.length;j++) {
                if(tableData[j].receiver == rows[i].userId) {
                    isExisted = true;
                    break;
                }
            }
            if(isExisted == false) {//有就不添加
                table.row.add({receiver: rows[i].userId, mobile: rows[i].mobile, nickName: rows[i].nickName, realName: rows[i].realName}
                ).draw(false);
            }
        }
    }
    //获取所有行
    function getTableContent(){
        var table = $('#dataTables').dataTable();
        var nTrs = table.fnGetNodes();//fnGetNodes获取表格所有行，nTrs[i]表示第i行tr对象
        var retData = [];
        for(var i = 0; i < nTrs.length; i++){
            retData.push(table.fnGetData(nTrs[i]));//fnGetData获取一行的数据
        }
        console.log("retData",retData);
        return retData;
    }

    $(document).ready(function() {
        showTable();
        //添加
        $("#addRow").on("click", function(){
            var url = jypath + '/jc/user/index?type=multipleSelect'; //多选
            art.dialog.open(url, {
                title: '选择', width: 1000, height: 500});
        });
        //删除选中
       $("#delRow").on("click", function(){
            var selectedIds = [];
            $(".checkchild:checked").each(function(i) {
                table.row($(this).parents('tr')).remove().draw();
            });
        });
        /**
         * 全选
         */
        $(".checkall").click(function () {
            var check = $(this).prop("checked");
            $(".checkchild").prop("checked", check);
        });
    });


</script>
<%@include file="../common/bottom.jsp" %>
</body>
</html>


