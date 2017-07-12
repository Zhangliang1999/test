<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String eggid = StringUtils.defaultIfEmpty(request.getParameter("eggid"), "");
%>
<!DOCTYPE html >
<html lang="en">
<head>
    <%@include file="../common/includeBaseSet.jsp" %>
    <%@include file="../common/includeSystemSet.jsp" %>
    <script type="text/javascript" charset="utf-8" src="${jypath}/static/plugins/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${jypath}/static/plugins/ueditor/ueditor.all.min.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <link href="${jypath}/static/css/jobmanager.css" rel="stylesheet"/>
    <script type="text/javascript" charset="utf-8" src="${jypath}/static/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>
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
                            <tr class="FormData">
                                <td class="CaptionTD"><font color="red">*</font>彩蛋编码(不能重复)：</td>
                                <td class="DataTD">&nbsp;
                                    <input type="text" name="eggid"
                                           class="FormElement ui-widget-content ui-corner-all">
                                </td>
                            </tr>
                            <tr class="FormData">
                                <td class="CaptionTD"><font color="red">*</font>商品名称：</td>
                                <td class="DataTD">&nbsp;
                                    <input type="text" maxlength="32" name="goodsName" onclick="selectGoods()"
                                           jyValidate="required"
                                           class="FormElement ui-widget-content ui-corner-all"/>
                                    <input type="hidden" maxlength="32" name="goodsId"/>
                                    <a href="#" title="清空" onclick="emptyGoods(); return false;"
                                       class="lrspace3 aBtnNoTD"
                                       data-toggle="modal"><i class='icon-remove bigger-120 red'></i></a>
                                </td>
                            </tr>
                            <tr class="FormData">
                                <td class="CaptionTD"><font color="red">*</font>赛事名称：</td>
                                <td class="DataTD" style="margin-left: -50px;">&nbsp;
                                    <input type="text" maxlength="32" name="matchName" onclick="selectMatch()"
                                           jyValidate="required"
                                           class="FormElement ui-widget-content ui-corner-all"/>
                                    <input type="hidden" maxlength="32" name="matchId"/>
                                    <a href="#" title="清空" onclick="emptyMatch(); return false;"
                                       class="lrspace3 aBtnNoTD"
                                       data-toggle="modal"><i class='icon-remove bigger-120 red'></i></a>
                                </td>
                            </tr>
                            <tr class="FormData">
                                <td class="CaptionTD"><font color="red">*</font>状态：</td>
                                <td class="DataTD" id="paintedEggId">&nbsp;
                                    <select data-placeholder="玩法" name="state" jyValidate="required"
                                            class="isSelect145" id="state">
                                        <option value="0">未开始</option>
                                        <option value="1">进行中</option>
                                        <option value="2">已揭晓</option>
                                    </select>
                                </td>
                            </tr>
                            <tr class="FormData">
                                <td class="CaptionTD"><font color="red">*</font>购买价格：</td>
                                <td class="DataTD">&nbsp;
                                    <input type="text" maxlength="20" name="price" jyValidate="required"
                                           onkeyup="this.value=this.value.replace(/\D/g,'')"
                                           onafterpaste="this.value=this.value.replace(/\D/g,'')"
                                           class="FormElement ui-widget-content ui-corner-all"/>
                                </td>
                            </tr>
                            <tr class="FormData">
                                <td class="CaptionTD"><font color="red">*</font>总需下线：</td>
                                <td class="DataTD">&nbsp;
                                    <input type="text" maxlength="32" name="lowerlimit" jyValidate="required"
                                           onkeyup="this.value=this.value.replace(/\D/g,'')"
                                           onafterpaste="this.value=this.value.replace(/\D/g,'')"
                                           class="FormElement ui-widget-content ui-corner-all"/>
                                </td>
                            </tr>
                            <tr class="FormData">
                           <%-- <td class="CaptionTD"><font color="red">*</font>结束时间：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="endtime" jyValidate="required" readonly
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>--%>
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
                    </form>
                </div>
                <div class="col-xs-7">
                    <h3 class="header smaller lighter blue">题目</h3>
                    <table cellspacing="0" cellpadding="0" border="0" class="customTable">
                        <tbody>
                        <tr class="FormData">
                            <td><font color="red">*</font>题目内容：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" name="content" maxlength="25" id="content" style="width:400px;"/>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <table id="myTable"
                           class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center" witdh="100">序号</th>
                            <th class="center" width="75%">答案</th>
                            <th class="center" width="15%">操作</th>
                        </thead>
                        <tbody>
                        <tbody>
                            <!--答案内容-->
                        </tbody>
                    </table>
                    <div style="margin-bottom: 60px;"><input type="button" class="btn btn-primary" onclick="insRow()" value="增加答案"></div>
                    <input type="hidden" name="selectAnserId" id="selectAnserId"/>
                    <input type="hidden" name="inputAnswers" id="inputAnswers"/>
                </div>
            </div>
            <!--底部按钮-->
            <div class="navbar-fixed-bottom ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
                <div class="ui-dialog-buttonset">
                    <button type="button" onclick="doAdd()"
                            class="btn btn-primary btn-xs ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
                            role="button" aria-disabled="false"><span class="ui-button-text"><i
                            class="icon-ok bigger-110"></i>&nbsp;保存</span></button>
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
    function selectGoods() {
        var url = jypath + '/jc/goods/index?type=select';
        art.dialog.open(url, {title: '选择商品', width: '90%', height: 500});
    }

    function selectMatch() {
        var url = jypath + '/jc/match/index?type=select';
        art.dialog.open(url, {title: '选择赛事', width: '90%', height: 500});
    }
    function setValue(chks, title) {
        $("#auForm input[name$='goodsName']").val(title);
        $("#auForm input[name$='goodsId']").val(chks);
    }

    function setMatch(chks, title) {
        console.log(chks,title);
        $("#auForm input[name='matchName']").val(title);
        $("#auForm input[name='matchId']").val(chks);
    }
    //清空图片路径
    function emptyGoods() {
        $("#auForm input[name$='goodsId']").val("");
        $("#auForm input[name$='goodsName']").val("");

    }


    function emptyMatch() {
        $("#auForm input[name$='matchId']").val("");
        $("#auForm input[name$='matchName']").val("");
    }
    function doAdd() {
        var url = jypath + '/jc/paintedEgg/add';
        if (JY.Validate.form("auForm", 2)) {
            var body = $("#content").val();
            if (body == '') {
                artDialog.alert("请输入题目内容");
                return false;
            }
            var result = [];
            $("input[name^='name']").each(function (i) {
                result[i] = $(this).val();
                if ($(this).val() == '') {
                    artDialog.alert("请输入答案");
                    return false;
                }
            });
            $("#inputAnswers").val(result);
            var param = {content: body, inputAnswers: $("#inputAnswers").val()};
            JY.Ajax.req("auForm", url, param, function (data) {
                if (data.res == 1) {
                    artDialog.alert(data.resMsg, function () {
                        artDialog.close();
                    });
                } else {
                    artDialog.alert(data.resMsg);
                }
            });
        }
    }
</script>
<script>
    var count = 1;
    var kk = 0;
    function insRow() {
        if (kk >= 22) {
            return false;
        }
        var html = "";
        html += "<tr id='tr" + count + "'> <td class=\"center\">"+ count +"</td>";
        html += "<td>&nbsp; <input type=\"text\" name='name" + count + "' maxlength=\"15\" style=\"width: 90%;\"> ";
        html += "</td> ";
        html += "<td class='center'><button class=\"btn btn-white btn-warning\" onclick=\"delRow('" + count + "')\"><i class=\"icon-trash\"></i></button>";
        html += "</td> ";
        html += "</tr> ";
        $("#myTable").append(html);
        kk++;
        count++;
    }

    function delRow(v) {
        kk--;
        $("#tr" + v).remove();
    }

    $(function(){
        $("input[name='eggid']").datetimepicker({
            format: 'yyyymmdd',
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 'month'
        });

        /*//时间控件
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
        });*/

       /* //加载数据
        if (goodsId) {
            JY.Ajax.req(null, jypath + '/jc/goods/find', {goodsId: goodsId}, function (data) {
                setForm(data);
            });
        }*/
        insRow();
        insRow();
        insRow();
    })
</script>
<%@include file="../common/bottom.jsp" %>
</body>
</html>


