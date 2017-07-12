<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String sensitiveId = StringUtils.defaultIfEmpty(request.getParameter("sensitiveId"), "");
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
                            <td colspan="2" class="ui-state-error"><input type="hidden" name="sensitiveId"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>敏感词：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="sensitiveWord" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>替换词：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="replaceWord" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
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
    var sensitiveId = '<%=sensitiveId%>';
    function doAdd() {
        var url = "";
        if (sensitiveId) {
            url = jypath + '/jc/sensitiveword/update';
        } else {
            url = jypath + '/jc/sensitiveword/add';
        }
        if (JY.Validate.form("auForm",2)) {
            JY.Ajax.doRequest("auForm", url, null, function (data){
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
        $("#auForm input[name$='sensitiveId']").val(l.sensitiveId);
        $("#auForm input[name$='sensitiveWord']").val(JY.Object.notEmpty(l.sensitiveWord));
        $("#auForm input[name$='replaceWord']").val(JY.Object.notEmpty(l.replaceWord));
        $("#auForm input[name$='createTime']").val(JY.Date.Default(l.createTime));
        $("#auForm input[name$='updateTime']").val(JY.Date.Default(l.updateTime));
        $("#auForm input[name$='operId']").val(JY.Object.notEmpty(l.operId));
    }
    $(function () {
        //加载数据
        if (sensitiveId) {
            JY.Ajax.doRequest(null, jypath + '/jc/sensitiveword/find', {sensitiveId: sensitiveId}, function (data) {
                setForm(data);
            });
        }
    })


</script>
<%@include file="../common/bottom.jsp" %>
</body>
</html>


