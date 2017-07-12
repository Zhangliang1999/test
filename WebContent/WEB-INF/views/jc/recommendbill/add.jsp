<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String recommendId = StringUtils.defaultIfEmpty(request.getParameter("recommendId"), "");
%>
<!DOCTYPE html >
<html lang="en">
<head>
    <%@include file="../common/includeBaseSet.jsp" %>
    <%@include file="../common/includeSystemSet.jsp" %>
    <link rel="stylesheet" href="${jypath}/static/plugins/DataTables-1.10.12/media/css/dataTables.bootstrap.css" />
</head>
<body style="height:885px;">
<div class="container">
    <div class="row-fluid">
        <div class="col-xs-12 ui-dialog">
            <div class="ui-dialog-content ui-widget-content" style="margin-bottom:10px;">
                <div class="col-xs-3">
                    <h3 class="header smaller lighter blue">基本信息</h3>
                <form id="auForm" method="POST" onsubmit="return false;">
                    <table cellspacing="0" cellpadding="0" border="0" class="customTable">
                        <tbody>
                        <tr style="display:none">
                            <td colspan="2" class="ui-state-error">
                                <input type="hidden" name="recommendId">
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">状态：</td>
                            <td class="DataTD">&nbsp;
                                <select name="state" class="isSelect145">
                                    <option value="0">有效</option>
                                    <option value="1">无效</option>
                                </select>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">用户编码：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" name="userId"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">几串几：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" name="bunch"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">价格：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" name="price"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>推理标题：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="100" name="title" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>推理理由：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="100" name="reason" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">比赛类型：</td>
                            <td class="DataTD">&nbsp;
                                <input type="hidden" name="matchType"/>
                                <input type="text" name="matchTypeName" disabled="disabled"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">盈利率：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" name="profitRate"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        <c:if test="${param.type == 'find'}">
                            <tr class="FormData">
                                <td class="CaptionTD">创建时间：</td>
                                <td class="DataTD">&nbsp;
                                    <input type="text" name="createTime" disabled
                                           class="FormElement ui-widget-content ui-corner-all"></td>
                            </tr>
                            <tr class="FormData">
                                <td class="CaptionTD">更新时间：</td>
                                <td class="DataTD">&nbsp;
                                    <input type="text" name="updateTime" disabled
                                           class="FormElement ui-widget-content ui-corner-all"></td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </form>
                </div>
                <div class="col-xs-9">
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">发单明细</h3>
                            <div class="table-responsive">
                                    <table id="dataTables" class="table table-striped table-bordered table-hover dataTable"
                                         <%--  aria-describedby="dataTables"--%>>
                                        <thead>
                                        <tr role="row">
                                            <th>比赛编码</th>
                                            <th>玩法编码</th>
                                            <th>竞猜结果</th>
                                            <th>赛事前瞻</th>
                                            <th>球队实力比例</th>
                                            <th>球队实力描述</th>
                                            <th>体彩数据比例</th>
                                            <th>体彩数据描述</th>
                                            <th>专家点评</th>
                                            <th>技术统计</th>
                                            <th>盈利率</th>
                                        </tr>
                                        </thead>
                                      </table>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">购买赛前单</h3>
                             <%--<div class="table-header" id="buytip">
                                 &nbsp;
                             </div>--%>
                            <div class="table-responsive">
                                <button class="btn btn-xs btn-light" title="导出到EXCEL"
                                        onclick="exportExcel();"><i class="icon-download-alt bigger-110 icon-only">导出到EXCEL</i></button>
                                <table id="buyTable" class="table table-striped table-bordered table-hover dataTable"
                                       class="table table-striped table-bordered table-hover"
                                       aria-describedby="buyTable">
                                    <thead>
                                    <tr role="row">
                                        <th>账户流水编码</th>
                                        <th>赛前单编码</th>
                                        <th>用户编码</th>
                                        <th>创建时间</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
            <!--底部按钮-->
            <div class="navbar-fixed-bottom ui-dialog-buttonpane ui-widget-content ui-helper-clearfix" style="z-index: 999">
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
    <script src="${jypath}/static/plugins/DataTables-1.10.12/media/js/jquery.dataTables.min.js"></script>
    <script src="${jypath}/static/plugins/DataTables-1.10.12/media/js/dataTables.bootstrap.js"></script>
    <script type="text/javascript">
        var recommendId = '<%=recommendId%>';
        function doAdd() {
            var url = "";
            if (recommendId) {
                url = jypath + '/jc/recommendbill/update';
            } else {
                url = jypath + '/jc/recommendbill/add';
            }
            if (JY.Validate.form("auForm",2)) {
                JY.Ajax.req("auForm", url, null, function (data){
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
        //赋值
        function setForm(data) {
            var l = data.obj;
            $("#auForm input[name='recommendId']").val(l.recommendId);
            $("#auForm input[name='userId']").val(l.userId);
            $("#auForm input[name='bunch']").val(l.bunch);
            $("#auForm input[name='price']").val(l.price);
            $("#auForm input[name='title']").val(l.title);
            $("#auForm input[name='reason']").val(l.reason);
            $("#auForm input[name='profitRate']").val(l.profitRate);
            $("#auForm select[name='state']").val(l.state);
            $("#auForm input[name='matchTypeName']").val(getMathTypeName(l.matchType));
            $("#auForm input[name='matchType']").val(l.matchType);
            $("#auForm input[name='createTime']").val(JY.Date.Default(l.createTime));
            $("#auForm input[name='updateTime']").val(JY.Date.Default(l.updateTime));
        }
        //查询
        function search() {
            $("#searchBtn").trigger("click");
        }
        //设置玩法
        function setBetType(matchType) {
            var betTypes = betTypeData[matchType]
            $("#betType").empty();
            var html = "";
            for (var i = 0; i < betTypes.length; i++) {
                var betType = betTypes[i];
                html += "<option value='" + betType['value'] + "'>" + betType['txt'] + "</option>"
            }
            $("#betType").html(html);
        }

        var dictData = JY.Dict.getData("matchType");
        var matchTypeData = dictData['matchType']['items']; //字典
        //console.log("matchTypeData", matchTypeData);
        function getMathTypeName(matchType) {
            for (var i = 0; i < matchTypeData.length; i++) {
                if (matchTypeData[i].value == matchType) {
                    return matchTypeData[i].name;
                }
            }
            return "";
        }

        $(function () {
            //加载数据
            if (recommendId) {
                JY.Ajax.req(null, jypath + '/jc/recommendbill/find', {recommendId: recommendId}, function (data) {
                    setForm(data);
                });
            } else {

            }
        })
    </script>

    <script type="text/javascript">
        var table;
        function showTable() {
            table = $('#dataTables').DataTable({
                ajax: {
                    type:"POST",
                    url: jypath + '/jc/recommenddetail/findByPage',
                   // "dataSrc": "obj",
                    data: function ( d ) {
                        //添加额外的参数传给服务器
                        d.recommendId = recommendId;
                    }
                },
                scrollY: 200,
                serverSide: true, //服务器端分页
                sort : false,
                filter : false,
                bLengthChange:false, //每页显示行数
//                bDisplayLength : 5, //默认10
                columns: [
                    {"data": "matchId"},
                    {"data": "matchGameId"},
                    {"data": "guessResult"},
                    {"data": "matchPreview"},
                    {"data": "strengthRatio"},
                    {"data": "strengthDesc"},
                    {"data": "dataRatio"},
                    {"data": "dataDesc"},
                    {"data": "expertComments"},
                    {"data": "techStatis"},
                    {"data": "profitRate"}
                ],
                // "order": [[ 4, "desc" ]],
                columnDefs: [
                ],
                language : {
                    url :  jypath + "/static/plugins/DataTables-1.10.12/language_chinese.json"
                },
                //工具栏
                initComplete: function () {//完成
                }

            });

        }



        function showBuyTable() {
            table = $('#buyTable').DataTable({
                ajax: {
                    type:"POST",
                    url: jypath + '/jc/recommendbill/findBuyByPage',
                    // "dataSrc": "obj",
                    data: function ( d ) {
                        //添加额外的参数传给服务器
                         d.recommendId = recommendId;
                    }
                },
                scrollY: 200,
                serverSide: true, //服务器端分页
                sort : false,
                filter : false,
                bLengthChange:false, //每页显示行数
//                bDisplayLength : 5, //默认10
                columns: [
                    {"data": "accountLogId"},
                    {"data": "recommendId"},
                    {"data": "userId"},
                    {"data": "createTime"}
                ],
                // "order": [[ 4, "desc" ]],
                columnDefs: [
                ],
                language : {
                    url :  jypath + "/static/plugins/DataTables-1.10.12/language_chinese.json"
                },
                //工具栏
                initComplete: function () {//完成
                    // $("#mytool").append('<button id="datainit" type="button" class="btn btn-primary btn-sm">增加基础数据</button>&nbsp');
                    //$("#mytool").append('<button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">添加</button>');
                }

            });

        }

        function getBuyInfo() {
          //  $("#buytip").html("该单被<font color='red'>100</font>人购买，总收益<font color='red'>100</font>球币。" +
           //         "本周收益<font color='red'>50</font>球币，当天收益<font color='red'>10</font>球币。")
        }



        //查询
        function doSearch() {
            table.ajax.reload();
        }
        $(function(){
            showTable();
            showBuyTable();
            getBuyInfo();
        })



        //导出excel
        function exportExcel() {
            var param = "recommendId=" + recommendId;
            window.location.href = jypath + '/jc/recommendbill/exportBuyExcel?'+param;
        }

    </script>


    <%@include file="../common/bottom.jsp" %>
</body>
</html>


