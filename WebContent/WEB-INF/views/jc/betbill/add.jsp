<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String betId = StringUtils.defaultIfEmpty(request.getParameter("betId"), "");
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

                <div class="col-xs-4">
                    <h3 class="header smaller lighter blue">基本信息</h3>
                    <form id="auForm" method="POST" onsubmit="return false;">
                    <table cellspacing="0" cellpadding="0" border="0" class="customTable">
                        <tbody>
                        <tr style="display:none">
                            <td colspan="2" class="ui-state-error"><input type="hidden" name="betId"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">账户流水编码：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" name="accountLogId" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">投注笔数：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" name="items" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">预计奖金：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" name="bonus" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">几串几：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" name="bunch" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">比赛类型：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="128" name="matchType" jyValidate="required" disabled
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
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
                <div class="col-xs-8">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">发单明细 </h3>
                            <div class="table-responsive">
                                <table id="dataTables" class="table table-striped table-bordered table-hover dataTable"
                                    aria-describedby="dataTables">
                                    <thead>
                                    <tr role="row">
                                        <th>排序主键</th>
                                        <th>投注编码</th>
                                        <th>比赛编码</th>
                                        <th>玩法类型</th>
                                        <th>选中的值</th>
                                        <th>是否正确</th>
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
    var betId = '<%=betId%>';
    var dictData = JY.Dict.getData("matchType");
    var matchTypeData = dictData['matchType']['items']; //字典
    //console.log("matchTypeData", matchTypeData);
    function getMathTypeName(matchType) {
        for(var i=0;i<matchTypeData.length;i++) {
            if(matchTypeData[i].value == matchType) {
                return matchTypeData[i].name;
            }
        }
        return "";
    }

    //玩法字典
    var betTypeData = [
        //足球
        {value: 1, txt: '亚洲让分盘'}, {value: 2, txt: '进球大小盘'}, {value: 3, txt: '全场赛果'},
        //篮球
        {value: 4, txt: '让分盘'}, {value: 5, txt: '比赛总分'}, {value: 6, txt: '全场赛果'},
        //网球
        {value: 7, txt: '赛局让分盘'}, {value: 8, txt: '总局数'}, {value: 9, txt: '赛盘投注'}, {value: 10, txt: '比赛获胜'},
        //棒球
        {value: 11, txt: '让垒盘'}, {value: 12, txt: '比赛获胜'}, {value: 13, txt: '比赛总分'},
        //斯洛克
        {value: 14, txt: '比赛让局盘'}, {value: 15, txt: '比赛总局数'}, {value: 16, txt: '比赛获胜'}
    ];
    //获取玩法中文名
    function getBetTypeTxt(betType) {
        for(var i=0;i<betTypeData.length;i++) {
            if(betTypeData[i].value == betType) {
                return betTypeData[i].txt;
            }
        }
        return "";
    }

    function setForm(data) {
        var l = data.obj;
        $("#auForm input[name='betId']").val(l.betId);
        $("#auForm input[name='userId']").val(JY.Object.notEmpty(l.userId));
        $("#auForm input[name='accountLogId']").val(JY.Object.notEmpty(l.accountLogId));
        $("#auForm input[name='amount']").val(JY.Object.notEmpty(l.amount));
        $("#auForm input[name='items']").val(JY.Object.notEmpty(l.items));
        $("#auForm input[name='bonus']").val(JY.Object.notEmpty(l.bonus));
        $("#auForm input[name='bunch']").val(JY.Object.notEmpty(l.bunch));
        $("#auForm input[name='matchType']").val(getMathTypeName(l.matchType));
        $("#auForm select[name='status']").val(JY.Object.notEmpty(l.status));
        $("#auForm select[name='iscorrect']").val(JY.Object.notEmpty(l.iscorrect));
        $("#auForm input[name='createTime']").val(JY.Date.Default(l.createTime));
        $("#auForm input[name='updateTime']").val(JY.Date.Default(l.updateTime));

    }

    $(function () {
        //加载数据
        if (betId) {
            JY.Ajax.req(null, jypath + '/jc/betbill/find', {betId: betId}, function (data) {
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
                url: jypath + '/jc/betbill/findBillDetailByPage',
                data: function ( d ) {
                    //添加额外的参数传给服务器
                    d.betId = betId;
                }
            },
            scrollY: 200,
            serverSide: true, //服务器端分页
//            bPaginate : false, //翻页
            sort : false,
            filter : false,
            bLengthChange:false, //每页显示行数
//                bDisplayLength : 5, //默认10
            columns: [
                {"data": "orderId"},
                {"data": "betId"},
                {"data": "matchId"},
                {"data": "betType"},
                {"data": "betSelect"},
                {"data": "state"}
            ],
            // "order": [[ 4, "desc" ]],
            columnDefs: [
                {
                    targets: 3,
                    render: function (a, b, full, d) {
                        return "<font color='green'>"+getBetTypeTxt(a)+"</font>";
                    }
                },{
                    targets: 4,
                    render: function (a, b, full, d) {
                        if(a == 1) {
                            return "主胜";
                        } else if(a == 2) {
                            return "主平";
                        } else if(a == 3) {
                            return "主负";
                        } else {
                            return "";
                        }
                    }
                },{
                    targets: 5,
                    render: function (a, b, full, d) {
                        if(a == 1) {
                            return "正确";
                        } else if(a == 2) {
                            return "不正确";
                        } else if(a == 4) {
                            return "比赛取消";
                        } else {
                            return "未开赛";
                        }
                        return "";
                    }
                }

            ],
            language : {
                url :  jypath + "/static/plugins/DataTables-1.10.12/language_chinese.json"
            },
            //工具栏
            initComplete: function () {//完成
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

    $(document).ready(function() {
        showTable();
    });


</script>
<%@include file="../common/bottom.jsp" %>
</body>
</html>


