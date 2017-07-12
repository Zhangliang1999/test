<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String compId = StringUtils.defaultIfEmpty(request.getParameter("compId"), "");
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
                            <td colspan="2" class="ui-state-error"><input type="hidden" name="compId"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>赛事名称：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="128" name="compName" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>比赛类型：</td>
                            <td class="DataTD" id="matchTypeId">&nbsp;
                                <select data-placeholder="类别" name="matchType" jyValidate="required"
                                        class="chosen-select isSelect147"></select>
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
<script type="text/javascript">
    var compId = '<%=compId%>';
    function doAdd() {
        var url = "";
        if (compId) {
            url = jypath + '/jc/matchdict/update';
        } else {
            url = jypath + '/jc/matchdict/add';
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
    function setForm(data) {
        var l = data.obj;
        JY.Dict.setSelect("matchTypeId", "matchType", 3, JY.Object.notEmpty(l.matchType));
        $("#auForm input[name='compId']").val(l.compId);
        $("#auForm input[name='compName']").val(JY.Object.notEmpty(l.compName));
        $("#auForm input[name='createTime']").val(JY.Date.Default(l.createTime));
        $("#auForm input[name='updateTime']").val(JY.Date.Default(l.updateTime));
        $("#auForm input[name='operId']").val(JY.Object.notEmpty(l.operId));
    }
    $(function () {
        //加载数据
        if (compId) {
            JY.Ajax.req(null, jypath + '/jc/matchdict/find', {compId: compId}, function (data) {
                setForm(data);
            });
        } else {
            JY.Dict.setSelect("matchTypeId", "matchType", 1);
        }
    })
    var ue;
</script>
<%@include file="../common/bottom.jsp" %>
</body>
</html>


