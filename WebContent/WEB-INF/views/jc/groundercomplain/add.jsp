<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String complainId = StringUtils.defaultIfEmpty(request.getParameter("complainId"), "");
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
                            <td colspan="2" class="ui-state-error"><input type="hidden" name="complainId"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">滚球单编码：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="grounderId"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">被投诉人：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="compainedUserId"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">投诉人：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="userId"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>是否处理：</td>
                            <td class="DataTD">&nbsp;
                                <select name="state" class="isSelect145" jyValidate="required">
                                    <option value="0">未处理</option>
                                    <option value="1">已处理</option>
                                </select>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>回复记录：</td>
                            <td class="DataTD">&nbsp;
                                <textarea rows="2" cols="10" maxlength="100" name="replyContent" multiline="true" jyValidate="required"
                                          class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
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
                                    <input type="text" maxlength="128" name="operId"
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
    var complainId = '<%=complainId%>';
    function doAdd() {
        var url = "";
        if (complainId) {
            url = jypath + '/jc/groundercomplain/update';
        } else {
            url = jypath + '/jc/groundercomplain/add';
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
        $("#auForm input[name='complainId']").val(l.complainId);
        $("#auForm input[name='grounderId']").val(JY.Object.notEmpty(l.grounderId));
        $("#auForm input[name='compainedUserId']").val(JY.Object.notEmpty(l.compainedUserId));
        $("#auForm input[name='userId']").val(JY.Object.notEmpty(l.userId));
        $("#auForm select[name='state']").val(JY.Object.notEmpty(l.state));
        $("#auForm textarea[name='replyContent']").val(JY.Object.notEmpty(l.replyContent));
        $("#auForm input[name='createTime']").val(JY.Date.Default(l.createTime));
        $("#auForm input[name='updateTime']").val(JY.Date.Default(l.updateTime));
        $("#auForm input[name='operId']").val(JY.Object.notEmpty(l.operId));
    }
    $(function () {
        //加载数据
        if (complainId) {
            JY.Ajax.req(null, jypath + '/jc/groundercomplain/find', {complainId: complainId}, function (data) {
                setForm(data);
            });
        }
    })
    var ue;
</script>
<%@include file="../common/bottom.jsp" %>
</body>
</html>


