<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String matchId = StringUtils.defaultIfEmpty(request.getParameter("matchId"), "");
%>
<!DOCTYPE html >
<html lang="en">
<head>
    <%@include file="../common/includeBaseSet.jsp" %>
    <%@include file="../common/includeSystemSet.jsp" %>
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
                            <td colspan="2" class="ui-state-error">
                                <input type="hidden" name="matchId">
                                <input type="hidden" name="compId"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">状态：</td>
                            <td class="DataTD">&nbsp;
                                <label class="inline isValidCheckbox">
                                    <input type="checkbox" checked="checked" sh-isValid="" role="checkbox"
                                           class="FormElement ace ace-switch ace-switch-5"/>
                                    <span class="lbl"></span>
                                    <!-- cb-isValid和Yes和No选择框配套使用-->
                                    <input type="hidden" hi-isValid="" name="isValid" value="1"/>
                                </label>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>赛事名称：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="128" name="compName" jyValidate="required" readonly  onclick="showComp();"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>比赛类型：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="128" id="matchType" name="matchType" jyValidate="required" disabled
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <%--<tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>场次号：</td>
                            <td class="DataTD">&nbsp;
                                <input type="number" value="1" jyValidate="required,numrangeth" name="ccNo" min='1'
                                       max='999' jyValidate="required,numrangeth"
                                       onkeyup='this.value=this.value.replace(/\D/g,&apos;&apos;)'
                                       class="FormElement ui-widget-content ui-corner-all isSelect75">
                            </td>
                        </tr>--%>
                        <tr class="FormData">
                            <td class="CaptionTD">比赛开始时间：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" name="begintime"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">比赛结束时间：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" name="endtime"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">竞猜截止时间：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" name="deadline"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">队1：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="100" name="teamId1" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">队2：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="100" name="teamId2" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>比赛结果：</td>
                            <td class="DataTD">&nbsp;
                                <select class="isSelect145" name="matchResult" jyValidate="required">
                                    <option value="">暂无结果</option>
                                    <option value="1">主胜</option>
                                    <option value="2">主平</option>
                                    <option value="3">主负</option>
                                    <option value="4">取消</option>
                                </select>
<%--
                                <input type="text" maxlength="100" name="matchResult1"
                                       class="FormElement ui-widget-content ui-corner-all"/>--%>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>比分：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="100" name="score" placeholder="输入比赛最后比分如 0:0" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>网球必须填写所有节数的比分和：</td>
                            <td class="DataTD">&nbsp;
                                <input type="number" value="" placeholder="所有节数比分和" jyValidate="numrangeth" name="sumscore" min='1' max='99999'
                                       onkeyup='this.value=this.value.replace(/\D/g,&apos;&apos;)'
                                       class="FormElement ui-widget-content ui-corner-all isSelect145">
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
                            <tr class="FormData">
                                <td class="CaptionTD">操作人：</td>
                                <td class="DataTD">&nbsp;
                                    <input type="text" maxlength="128" name="operId" jyValidate="required" disabled
                                           class="FormElement ui-widget-content ui-corner-all"/>
                                </td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </form>

                <!--添加玩法-->
                <%--<a id="itemformAdd" class="lrspace3 aBtnNoTD" title="增加玩法" href="#">
                    <i class="icon-plus-sign color-green bigger-200">增加玩法</i>
                </a>--%>
                <div class="hr hr-dotted"></div>
                <table id="itemsTable" cellspacing="0" cellpadding="0" border="0"
                       class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th style="width:15%" class="center">玩法</th>
                        <th style="width:20%" class="center">数据1</th>
                        <th style="width:20%" class="center">数据2</th>
                        <th style="width:20%" class="center">数据3</th>
<%--
                        <th style="width:5%" class="center">排序</th>
--%>
                        <th style="width:20%" class="center">玩法结果</th>
<%--
                        <th style="width:20%" class="center">操作</th>
--%>
                    </tr>
                    </thead>
                </table>
                <br/><br/>

                <div id="itemDiv" class="hide">
                    <table cellspacing="0" cellpadding="0" border="0" class="customTable">
                        <tbody>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>玩法：</td>
                            <td class="DataTD">&nbsp;
                                <!--足球-->
                                <select data-placeholder="玩法" id="betType" jyValidate="required"
                                        class="isSelect115">
                                    <%--<optgroup label="足球">
                                        <option value="1">亚洲让分盘</option>
                                        <option value="2">进球大小盘</option>
                                        <option value="3">全场赛果</option>
                                    </optgroup>
                                    <optgroup label="篮球">
                                        <option value="4">让分盘</option>
                                        <option value="5">比赛总分</option>
                                        <option value="6">全场赛果</option>
                                    </optgroup>
                                    <optgroup label="网球">
                                        <option value="7">赛局让分盘</option>
                                        <option value="8">总局数</option>
                                        <option value="9">赛盘投注</option>
                                        <option value="10">比赛获胜</option>
                                    </optgroup>
                                    <optgroup label="棒球">
                                        <option value="11">让分盘</option>
                                        <option value="12">比赛总分</option>
                                        <option value="13">全场赛果</option>
                                    </optgroup>
                                    <optgroup label="斯洛克">
                                        <option value="14">比赛让局盘</option>
                                        <option value="15">比赛总局数</option>
                                        <option value="16">比赛获胜</option>
                                    </optgroup>--%>

                                </select>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>数据1：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" jyValidate="required" name="data1"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>数据2：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" jyValidate="required" name="data2"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>数据3：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" jyValidate="required" name="data3"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>顺序：</td>
                            <td class="DataTD">&nbsp;
                                <input type="number" value="1" jyValidate="required,numrangeth" name="sort" min='1'
                                       max='99999' jyValidate="required,numrangeth"
                                       onkeyup='this.value=this.value.replace(/\D/g,&apos;&apos;)'
                                       class="FormElement ui-widget-content ui-corner-all isSelect75"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">玩法结果：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="matchResult"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        </tbody>
                    </table>
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
<script type="text/javascript">
    //玩法字典
    var betTypeData = {
        //足球
        "1": [{value: 1, txt: '亚洲让分盘'}, {value: 2, txt: '进球大小盘'}, {value: 3, txt: '全场赛果'}],
        //篮球
        "2": [{value: 4, txt: '让分盘'}, {value: 5, txt: '比赛总分'}, {value: 6, txt: '全场赛果'}],
        //网球
        "3": [{value: 7, txt: '赛局让分盘'}, {value: 8, txt: '总局数'}, {value: 9, txt: '赛盘投注'}, {value: 10, txt: '比赛获胜'}],
        //棒球
        "4": [{value: 11, txt: '让垒盘'}, {value: 12, txt: '比赛获胜'}, {value: 13, txt: '比赛总分'}],
        //斯洛克
        "5": [{value: 14, txt: '比赛让局盘'}, {value: 15, txt: '比赛总局数'}, {value: 16, txt: '比赛获胜'}]
    };

    var matchId = '<%=matchId%>';
    function doAdd() {
        var url = "";
        if (matchId) {
            url = jypath + '/jc/match/update';
        } else {
            url = jypath + '/jc/match/add';
        }
        if (JY.Validate.form("auForm", 2)) {
            var params = $.extend({}, JY.Object.serialize($("#auForm")));
            if(params.matchResult == "") {
                artDialog.alert("比赛结果不能为空");
                return;
            }
            if(params.matchResult != 4 && params.socre == "") {
                artDialog.alert("比分不能为空");
                return;
            }
            if(params.matchResult != 4 && g_matchType == 3 && params.sumscore == "") {
                artDialog.alert("网球必须填写所有节数的比分和");
                return;
            }
            //setItemArry();
            params.items = itemArry;
            //console.log("params:", params);
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

    function getMatchResult(matchResult) {
        if(matchResult == 1){
            return "主胜";
        } else if(matchResult == 2){
            return "主平";
        } else if(matchResult == 3){
            return "主负";
        }  else if(matchResult == 4){
            return "取消";
        }
        return "";
    }
    var g_matchType = 0;
    //赋值
    function setForm(data) {
        var l = data.obj;
        $("#auForm input[name='matchId']").val(l.matchId);
        $("#auForm input[name='isValid']").val(l.isValid);
        $("#auForm input[name='compId']").val(l.compId);
        $("#auForm input[name='compName']").val(l.compName);
        $("#auForm input[name='ccNo']").val(l.ccNo);
        $("#auForm input[name='teamId1']").val(l.teamId1).attr("disabled", "true");
        $("#auForm input[name='teamId2']").val(l.teamId2).attr("disabled", "true");
        $("#auForm input[name='begintime']").val(JY.Date.Default(l.begintime)).attr("disabled", "true");
        $("#auForm input[name='endtime']").val(JY.Date.Default(l.endtime));
        $("#auForm input[name='deadline']").val(JY.Date.Default(l.deadline)).attr("disabled", "true");
        $("#auForm input[name='createTime']").val(JY.Date.Default(l.createTime));
        $("#auForm input[name='updateTime']").val(JY.Date.Default(l.updateTime));
        $("#auForm input[name='operId']").val(JY.Object.notEmpty(l.operId));
        $("#auForm input[name='sumscore']").val(JY.Object.notEmpty(l.sumscore));
        $("#auForm input[name='matchType']").val(getMathTypeName(l.matchType));
        $("#auForm select[name='matchResult']").val(l.matchResult)
        $("#auForm input[name='score']").val(l.score)
        var matchType = l.matchType;//
        g_matchType = l.matchType;//
        var items = l.items;
        if (items != null) {
            var html = "";
            for (var i = 0; i < items.length; i++) {
                var item = items[i];
                var betTypeTxt = getBetTypeTxt(matchType, item.betType);
                html += "<tr id='temtr" + itemNum + "' >";
                html += "<td class='center'>" + betTypeTxt + "</td>";
                html += "<td class='center'>" + item.data1 + "</td>";
                html += "<td class='center'>" + item.data2 + "</td>";
                html += "<td class='center'>" + item.data3 + "</td>";
/*
                html += "<td>" + item.sort + "</td>";
*/
                html += "<td class='center'>"+getMatchResult(item.matchResult)+"</td>";
                /*html += "<td>";
                html += "<div class='inline position-relative'>";
                html += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
                html += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
                html += "<li><a class='aBtnNoTD' onclick='editItem(&apos;" + itemNum + "&apos;)' title='修改' href='#'><i class='icon-edit color-blue bigger-120'></i></a></li>";
                html += "<li><a class='aBtnNoTD' onclick='delItem(&apos;" + itemNum + "&apos;)' title='删除' href='#'><i class='icon-remove-sign color-red bigger-120'></i></a></li>";
                html += "</ul></div>";
                html += "</td>";*/
                html += "</tr>";
                item.matchGameId = itemNum;
                itemArry.push(item);
                itemNum++;
            }
            $("#itemsTable").append(html);
        }
        setBetType(l.matchType); //
    }
    //获取玩法中文名
    function getBetTypeTxt(matchType, betType) {
        var betTypes = betTypeData[matchType];
        for(var i=0;i<betTypes.length;i++) {
            if(betTypes[i].value == betType) {
                return betTypes[i].txt;
            }
        }
        return "";
    }

    //显示赛事
    function showComp() {
        var url = jypath + '/jc/matchdict/index?type=select';
        art.dialog.open(url, {title: '选择赛事', width: 850, height: 500});
    }
    //选择赛事
    function setComp(compId) {
        var url = jypath + '/jc/matchdict/find';
        $.post(url, {compId: compId}, function (retData) {
            var obj = retData.obj;
            if (obj) {
                $("#auForm input[name='compId']").val(obj.compId);
                $("#auForm input[name='compName']").val(obj.compName);
                $("#auForm input[name='ccNo']").val(obj.maxCcNo + 1);
                $("#auForm input[name='matchType']").val(getMathTypeName(obj.matchType));
                setBetType(obj.matchType);
                cleanItemForm();
                cleanItemTable();
            }
        }, 'json')
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
    //字典集合
    var itemArry = new Array();
    var itemNum = 1;

    function cleanItemTable() {
        itemArry = [];
        itemNum = 1;
        $("#itemsTable tbody").remove();
    }

    function cleanItemForm() {
        JY.Tags.cleanForm("itemDiv");
        $("#itemDiv input[name$='sort']").val('1');
    }
    //删除玩法
    function delItem(id) {
        if (confirm("确认要删除字段吗?")) {
            $("#temtr" + id).remove();
            for (var i = 0; i < itemArry.length; i++) {
                if (itemArry[i].matchGameId == id) {
                    itemArry.splice(i, 1);
                    break;
                }
            }
        }
    }
    //修改玩法
    function editItem(id) {
        cleanItemForm();
        var item = null;
        var idx = 0;
        for (var i = 0; i < itemArry.length; i++) {
            if (itemArry[i].matchGameId == id) {
                item = itemArry[i];
                idx = i;
                break;
            }
        }
        //赋值
        $("#betType").val(item.betType);
        $("#itemDiv input[name='data1']").val(item.data1);
        $("#itemDiv input[name='data2']").val(item.data2);
        $("#itemDiv input[name='data3']").val(item.data3);
        $("#itemDiv input[name='sort']").val(item.sort);
        $("#itemDiv input[name='matchResult']").val(item.matchResult);
        JY.Model.edit("itemDiv", "修改玩法", function () {
            //取值
            var betType = $("#betType").val();
            var betTypeTxt = $("#betType").find("option:selected").text();
            var data1 = $("#itemDiv input[name='data1']").val();
            var data2 = $("#itemDiv input[name='data2']").val();
            var data3 = $("#itemDiv input[name='data3']").val();
            var sort = $("#itemDiv input[name='sort']").val();
            var matchResult = $("#itemDiv input[name='matchResult']").val();
            if (JY.Validate.form("itemDiv")) {
                $("#temtr" + id).find("td").eq(0).html(betTypeTxt);
                $("#temtr" + id).find("td").eq(1).html(data1);
                $("#temtr" + id).find("td").eq(2).html(data2);
                $("#temtr" + id).find("td").eq(3).html(data3);
                $("#temtr" + id).find("td").eq(4).html(sort);
                $("#temtr" + id).find("td").eq(5).html(matchResult);
                item = {
                    matchGameId: itemNum,
                    betType: betType,
                    data1: data1,
                    data2: data2,
                    data3: data3,
                    sort: sort,
                    matchResult: matchResult
                };
                itemArry.splice(idx, 1, item);//替换
                //console.log("itemArry:", itemArry);
                $(this).dialog("close");
            }
        });
    }

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

    $(function () {

        //时间控件
        $("input[name='begintime']").datetimepicker({
            format: 'yyyy-mm-dd hh:ii:00',
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 0,
        }).on('changeDate', function (ev) {
            var beginTime = $("input[name='begintime']").val();
            $("input[name='endtime']").datetimepicker('setStartDate', beginTime);
            $("input[name='deadline']").val(beginTime);
        });
        $("input[name='endtime']").datetimepicker({
            format: 'yyyy-mm-dd hh:ii:00',
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 0,
        }).on('changeDate', function (ev) {
            var endTime = $("input[name='endtime']").val();
            $("input[name='begintime']").datetimepicker('setEndDate', endTime);
            $("input[name='deadline']").datetimepicker('setEndDate', endTime);
        });
        $("input[name='deadline']").datetimepicker({
            format: 'yyyy-mm-dd hh:ii:00',
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 0,
        }).on('changeDate', function (ev) {
            var deadline = $("input[name='deadline']").val();
            $("input[name='endtime']").datetimepicker('setStartDate', deadline);
        });
        //加载数据
        if (matchId) {
            JY.Ajax.req(null, jypath + '/jc/match/find', {matchId: matchId}, function (data) {
                setForm(data);
            });
        } else {

        }

        //添加玩法
        $('#itemformAdd').on('click', function (e) {
            //通知浏览器不要执行与事件关联的默认动作
            e.preventDefault();
            cleanItemForm(); //清空表单
            $("#itemDiv input[name='sort']").val(itemNum);
            JY.Model.edit("itemDiv", "新增玩法", function () {
                var html = "";
                var betType = $("#betType").val();
                var betTypeTxt = $("#betType").find("option:selected").text();
                var data1 = $("#itemDiv input[name='data1']").val();
                var data2 = $("#itemDiv input[name='data2']").val();
                var data3 = $("#itemDiv input[name='data3']").val();
                var sort = $("#itemDiv input[name='sort']").val();
                var matchResult = $("#itemDiv input[name='matchResult']").val();
                if (JY.Validate.form("itemDiv")) {
                    html += "<tr id='temtr" + itemNum + "' >";
                    html += "<td>" + betTypeTxt + "</td>";
                    html += "<td>" + data1 + "</td>";
                    html += "<td>" + data2 + "</td>";
                    html += "<td>" + data3 + "</td>";
                    html += "<td>" + sort + "</td>";
                    if(matchResult == 1) {
                        html += "<td>主胜</td>";
                    } else if(matchResult == 2) {
                        html += "<td>主平</td>";
                    } else if(matchResult == 3) {
                        html += "<td>主负</td>";
                    } else {
                        html += "<td>&nbsp;</td>";
                    }
                    html += "<div class='inline position-relative'>";
                    html += "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown'><i class='icon-cog icon-only bigger-110'></i></button>";
                    html += "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close'>";
                    html += "<li><a class='aBtnNoTD' onclick='editItem(&apos;" + itemNum + "&apos;)' title='修改' href='#'><i class='icon-edit color-blue bigger-120'></i></a></li>";
                    html += "<li><a class='aBtnNoTD' onclick='delItem(&apos;" + itemNum + "&apos;)' title='删除' href='#'><i class='icon-remove-sign color-red bigger-120'></i></a></li>";
                    html += "</ul></div>";
                    html += "</td>";
                    html += "</tr>";
                    var data = {
                        matchGameId: itemNum,
                        betType: betType,
                        data1: data1,
                        data2: data2,
                        data3: data3,
                        sort: sort,
                        matchResult: matchResult
                    };
                    itemArry.push(data);
                    itemNum++;
                    $("#itemsTable").append(html);
                    $(this).dialog("close");
                }
            });
        });
    })
</script>
<%@include file="../common/bottom.jsp" %>
</body>
</html>


