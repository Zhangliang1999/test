<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String questionId = StringUtils.defaultIfEmpty(request.getParameter("questionId"), "");
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
                            <td colspan="2" class="ui-state-error"><input type="hidden" name="questionId"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">用户编码：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="userId" jyValidate="required" readonly
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">联系方式：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="contact" jyValidate="required" readonly
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">问题描述：</td>
                            <td class="DataTD">&nbsp;
                                <textarea rows="2" cols="10" maxlength="100" name="question" multiline="true" readonly
                                          class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>

                        </tr>
                       <tr class="FormData">
                            <td class="CaptionTD">提问时间：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" name="createTime" jyValidate="required" readonly
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        <c:if test="${param.type == 'find'}">
                            <tr class="FormData">
                                <td class="CaptionTD">回复人：</td>
                                <td class="DataTD">&nbsp;
                                    <input type="text" jyValidate="required" maxlength="16" name="replyId"
                                           class="FormElement ui-widget-content ui-corner-all"></td>
                            </tr>
                        </c:if>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>回复内容：</td>
                            <td class="DataTD">&nbsp;
                            <textarea rows="2" cols="10" maxlength="100" name="replyContent" multiline="true"
                                      class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
                        </tr>
                        <c:if test="${param.type == 'find'}">
                            <tr class="FormData">
                                <td class="CaptionTD">回复时间：</td>
                                <td class="DataTD">&nbsp;
                                    <input type="text" jyValidate="required" maxlength="16" name="updateTime"
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
    var questionId = '<%=questionId%>';
    function doAdd() {
        var url = "";
        if (questionId) {
            url = jypath + '/jc/questionfeedback/update';
        } else {
            url = jypath + '/jc/questionfeedback/add';
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
        $("#auForm input[name$='questionId']").val(l.questionId);
        $("#auForm input[name$='userId']").val(JY.Object.notEmpty(l.userId));
        $("#auForm input[name$='contact']").val(JY.Object.notEmpty(l.contact));
        $("#auForm textarea[name$='question']").val(JY.Object.notEmpty(l.question));
        $("#auForm input[name$='createTime']").val(JY.Date.Default(l.createTime));
        $("#auForm input[name$='replyId']").val(JY.Object.notEmpty(l.replyId));
        $("#auForm textarea[name$='replyContent']").val(JY.Object.notEmpty(l.replyContent));
        $("#auForm input[name$='updateTime']").val(JY.Date.Default(l.updateTime));
    }
    $(function () {
        //加载数据
        if (questionId) {
            JY.Ajax.doRequest(null, jypath + '/jc/questionfeedback/find', {questionId: questionId}, function (data) {
                setForm(data);
            });
        }
    })


</script>
<%@include file="../common/bottom.jsp" %>
</body>
</html>


