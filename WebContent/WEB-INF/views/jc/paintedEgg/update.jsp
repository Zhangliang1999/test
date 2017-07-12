<%@page import="java.util.List" %>
<%@page import="com.jy.entity.jc.paintedEgg.PaintedEggAnswer" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.jy.entity.jc.paintedEgg.PaintedEgg" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.text.SimpleDateFormat" %>
<%
    PaintedEgg acount = (PaintedEgg) request.getAttribute("acount");
    String eggid = acount.getEggid();
    String state = "";
    int count1 = 0;
    int count2 = 0;
    List<PaintedEggAnswer> list = new ArrayList<PaintedEggAnswer>();
    if (null != acount.getAnswers()) {
        count1 = acount.getAnswers().size() + 1;
        count2 = acount.getAnswers().size();
        list = acount.getAnswers();
    }


    String s3 = "";
    SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    if (null != acount.getEndtime()) {
        s3 = sdf.format(acount.getEndtime());
    }
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
                <form id="auForm" method="POST" onsubmit="return false;">
                    <table cellspacing="0" cellpadding="0" border="0" class="customTable">
                        <tbody>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>商品名称：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="goodsName" onclick="selectGoods()"
                                       jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all" value="${acount.title}"/>
                                <input type="hidden" maxlength="32" name="goodsId" value="${acount.goodsId }"/>
                                <a href="#" title="清空" onclick="emptyGoods(); return false;" class="lrspace3 aBtnNoTD"
                                   data-toggle="modal"><i class='icon-remove bigger-120 red'></i></a>
                            </td>
                            <td class="CaptionTD"><font color="red">*</font>赛事名称：</td>
                            <td class="DataTD" style="margin-left: -50px;">&nbsp;
                                <input type="text" maxlength="32" name="matchName" onclick="selectMatch()"
                                       jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"
                                       value=" ${acount.team_id1} vs  ${acount.team_id2}"/>
                                <input type="hidden" maxlength="32" name="matchId" value="${acount.matchId }"/>
                                <a href="#" title="清空" onclick="emptyMatch(); return false;" class="lrspace3 aBtnNoTD"
                                   data-toggle="modal"><i class='icon-remove bigger-120 red'></i></a>
                            </td>
                        </tr>


                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>状态：</td>
                            <td class="DataTD" id="paintedEggId">&nbsp;
                                <select data-placeholder="玩法" name="state" jyValidate="required" style="width:190px;" id="state">
                                        <option value="0" <c:if test="${acount.state == '0'}">selected="selected"</c:if>>未开始</option>
                                        <option value="1" <c:if test="${acount.state == '1'}">selected="selected"</c:if>>进行中</option>
                                        <option value="2" <c:if test="${acount.state == '2'}">selected="selected"</c:if>>已揭晓</option>
                                </select>
                            </td>
                            <td class="CaptionTD"><font color="red">*</font>购买价格：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="20" name="price" jyValidate="required"
                                       value="${acount.price }"
                                       onkeyup="this.value=this.value.replace(/\D/g,'')"
                                       onafterpaste="this.value=this.value.replace(/\D/g,'')"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">

                            <td class="CaptionTD"><font color="red">*</font>总需下线：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="lowerlimit" jyValidate="required"
                                       value="${acount.lowerlimit }"
                                       onkeyup="this.value=this.value.replace(/\D/g,'')"
                                       onafterpaste="this.value=this.value.replace(/\D/g,'')"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                            <td class="CaptionTD"><%--<font color="red">*</font>结束时间：--%></td>
                            <td class="DataTD">&nbsp;
                                <%--<input type="text" maxlength="32" name="endtime" jyValidate="required" value="<%=s3 %>"
                                       class="FormElement ui-widget-content ui-corner-all"/>--%>
                            </td>
                        </tr>
                        <tr>
                            <td class="CaptionTD"><font color="red">*</font>题目内容：</td>
                            <td class="DataTD" colspan="3">&nbsp;
                                <input type="text" name="content" maxlength="25"  id="content"
                                       value="${acount.content }"
                                       style="width: 600px;"></td>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="hr"></div>
                    <div style="margin:20px 50px;">
                    <table id="myTable"
                           class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center" width="100">序号</th>
                            <th class="center" width="75%">答案</th>
                            <th class="center" width="15%">操作</th>
                        </thead>
                        <tbody>
                        <%
                            for (int i = 0; i < list.size(); i++) {
                                PaintedEggAnswer answer = list.get(i);
                                String tr = "tr" + (i + 1);
                        %>
                        <tr id="<%=tr%>">
                            <td class="center"><%=i+1%> </td>
                            <td>&nbsp; <input type="text" name="name1" maxlength="15"
                                                             value="<%=answer.getAnswer() %>"
                                                             style="width: 90%;"></td>
                            <td class="center">
                                <button class="btn btn-white btn-warning" onclick="delRow('<%=i+1 %>')">
                                    <i class="icon-trash"></i>
                                </button>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                    <div style="margin-bottom: 60px;"><input type="button" class="btn btn-primary" onclick="insRow()" value="增加答案"></div>
                    <input type="hidden" name="selectAnserId" id="selectAnserId"/>
                    <input type="hidden" name="inputAnswers" id="inputAnswers"/>
                    <input type="hidden" name="eggid" id="eddid" value="<%=eggid%>"/>
                    </div>
                </form>
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
        art.dialog.open(url, {title: '选择商品', width: 850, height: 500});
    }

    function selectMatch() {
        var url = jypath + '/jc/match/index?type=select';
        art.dialog.open(url, {title: '选择赛事', width: 850, height: 500});
    }
    function setValue(chks, title) {
        $("#auForm input[name$='goodsName']").val(title);
        $("#auForm input[name$='goodsId']").val(chks);
    }

    function setMatch(chks, title) {
        $("#auForm input[name$='matchName']").val(title);
        $("#auForm input[name$='matchId']").val(chks);
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
        var url = jypath + '/jc/paintedEgg/update';
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
    var count = <%=count1%>;
    var kk = <%=count2%>;
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
    var goodsId = '${acount.goodsId }';

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
        });
        //加载数据
        if (goodsId) {
            /*JY.Ajax.req(null, jypath + '/jc/goods/find', {goodsId: goodsId}, function (data) {
                setForm(data);
            });*/
        }
    })
</script>
<%@include file="../common/bottom.jsp" %>
</body>
</html>


