<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String userId = StringUtils.defaultIfEmpty(request.getParameter("userId"), "");
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
                            <td colspan="2" class="ui-state-error"><input type="hidden" name="userId"></td>
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
                            <td class="CaptionTD">是否是机器人：</td>
                            <td class="DataTD">&nbsp;
                                <select name="isRobot" class="isSelect75">
                                    <option value="0">否</option>
                                    <option value="1">是</option>
                                </select>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>手机号：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="11" name="mobile" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>真实姓名：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="128" name="realName" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>身份证：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="128" name="idCard" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>昵称：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="128" name="nickName" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>个人签名：</td>
                            <td class="DataTD">&nbsp;
                                 <textarea rows="2" cols="10" maxlength="256" name="signature" multiline="true"
                                           class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>邀请码：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="128" name="inviteCode" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>总关注数：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="128" name="followCount" jyValidate="required"
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
    var userId = '<%=userId%>';
    function doAdd() {
        var url = "";
        if (userId) {
            url = jypath + '/jc/user/update';
        } else {
            url = jypath + '/jc/user/add';
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
        JY.Tags.isValid("auForm",(JY.Object.notNull(l.isValid)?l.isValid:"0"));
        $("#auForm input[name$='userId']").val(l.userId);
        $("#auForm select[name$='isRobot']").val(l.isRobot);
        $("#auForm input[name$='mobile']").val(JY.Object.notEmpty(l.mobile));
        $("#auForm input[name$='realName']").val(JY.Object.notEmpty(l.realName));
        $("#auForm input[name$='idCard']").val(JY.Object.notEmpty(l.idCard));
        $("#auForm input[name$='nickName']").val(JY.Object.notEmpty(l.nickName));
        $("#auForm textarea[name$='signature']").val(JY.Object.notEmpty(l.signature));
        $("#auForm input[name$='inviteCode']").val(JY.Object.notEmpty(l.inviteCode));
        $("#auForm input[name$='followCount']").val(JY.Object.notEmpty(l.followCount));
        $("#auForm input[name$='createTime']").val(JY.Date.Default(l.createTime));
        $("#auForm input[name$='updateTime']").val(JY.Date.Default(l.updateTime));
        $("#auForm input[name$='operId']").val(JY.Object.notEmpty(l.operId));
    }
    $(function () {
        //加载数据
        if (userId) {
            JY.Ajax.doRequest(null, jypath + '/jc/user/find', {userId: userId}, function (data) {
                setForm(data);
            });
        }
    })


</script>
<%@include file="../common/bottom.jsp" %>
</body>
</html>


