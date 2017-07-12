<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String id = StringUtils.defaultIfEmpty(request.getParameter("id"), "");
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
                            <td colspan="2" class="ui-state-error"><input type="hidden" name="id"></td>
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
                            <td class="CaptionTD"><font color="red">*</font>用户编码：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="11" name="userId" jyValidate="required" onclick="selectUser()"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData form-inline">
                            <td class="CaptionTD"><font color="red">*</font>类别：</td>
                            <td class="DataTD" id="matchTypeId">&nbsp;
                                <select data-placeholder="类别" name="matchType" jyValidate="required" disabled
                                        class="chosen-select isSelect147"></select>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>升级方式：</td>
                            <td class="DataTD" id="upgradeTypeId">&nbsp;
                                <select data-placeholder="升级方式" name="upgradeType" jyValidate="required"
                                        class="chosen-select isSelect147">
                                </select>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>级别：</td>
                            <td class="DataTD" id="userLevelId">&nbsp;
                                <select data-placeholder="级别" name="level" jyValidate="required"
                                        class="chosen-select isSelect147">
                                </select>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">排序（从小到大排序）<br/>有值就会显示在大咖及更多中：</td>
                            <td class="DataTD">&nbsp;
                                <input type="number" value="" jyValidate="numrangeth" name="sort" min='1' max='99999' onkeyup='this.value=this.value.replace(/\D/g,&apos;&apos;)'  class="FormElement ui-widget-content ui-corner-all isSelect75"></td>
                        </tr>

                        <c:if test="${param.type == 'find'}">
                            <tr class="FormData">
                                <td class="CaptionTD">竞猜场次：</td>
                                <td class="DataTD">&nbsp;
                                    <input type="text" maxlength="128" name="betCnt" jyValidate="required"
                                           class="FormElement ui-widget-content ui-corner-all"/>
                                </td>
                            </tr>
                            <tr class="FormData">
                                <td class="CaptionTD">赢场次：</td>
                                <td class="DataTD">&nbsp;
                                    <input type="text" maxlength="128" name="winCnt" jyValidate="required"
                                           class="FormElement ui-widget-content ui-corner-all"/>
                                </td>
                            </tr>
                            <tr class="FormData">
                                <td class="CaptionTD">负场次：</td>
                                <td class="DataTD">&nbsp;
                                    <input type="text" maxlength="128" name="loseCnt" jyValidate="required"
                                           class="FormElement ui-widget-content ui-corner-all"/>
                                </td>
                            </tr>
                            <tr class="FormData">
                                <td class="CaptionTD">盈利率：</td>
                                <td class="DataTD">&nbsp;
                                    <input type="text" maxlength="128" name="profitRate" jyValidate="required"
                                           class="FormElement ui-widget-content ui-corner-all"/>
                                </td>
                            </tr>
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
    var id = '<%=id%>';
    function doAdd() {
        var url = "";
        if (id) {
            url = jypath + '/jc/userlevel/update';
        } else {
            url = jypath + '/jc/userlevel/add';
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
        var values = JY.Object.notEmpty(l.matchType) + "," + JY.Object.notEmpty(l.level) + "," + JY.Object.notEmpty(l.upgradeType);
        JY.Dict.setSelect("matchTypeId,userLevelId,upgradeTypeId", "matchType,userLevel,upgradeType", 3, values);
        $("#auForm input[name$='id']").val(l.id);
        JY.Tags.isValid("auForm", (JY.Object.notNull(l.isValid) ? l.isValid : "0"));
        $("#auForm input[name$='userId']").val(JY.Object.notEmpty(l.userId));
//        $("#auForm select[name$='upgradeType']").val(JY.Object.notEmpty(l.upgradeType));
//        $("#auForm select[name$='matchType']").val(JY.Object.notEmpty(l.matchType));
//        $("#auForm select[name$='level']").val(JY.Object.notEmpty(l.level));
        $("#auForm input[name$='operId']").val(JY.Object.notEmpty(l.operId));
        $("#auForm input[name$='betCnt']").val(JY.Object.notEmpty(l.betCnt));
        $("#auForm input[name$='winCnt']").val(JY.Object.notEmpty(l.winCnt));
        $("#auForm input[name$='loseCnt']").val(JY.Object.notEmpty(l.loseCnt));
        $("#auForm input[name$='profitRate']").val(JY.Object.notEmpty(l.profitRate));
        $("#auForm input[name$='createTime']").val(JY.Date.Default(l.createTime));
        $("#auForm input[name$='updateTime']").val(JY.Date.Default(l.updateTime));
    }
    $(function () {
        //加载数据
        if (id) {
            JY.Ajax.req(null, jypath + '/jc/userlevel/find', {id: id}, function (data) {
                setForm(data);
            });
        } else {
            JY.Dict.setSelect("matchTypeId,userLevelId,upgradeTypeId", "matchType,userLevel,upgradeType", 1);
        }
    })

    //选择用户
    function selectUser() {
        var url = jypath+'/jc/user/index?type=select'
        art.dialog.open(url, {
            title: '查看', width: 1000, height: 600
        });
    }

    //回调
    function setUserId(userId) {
        $("#auForm input[name$='userId']").val(JY.Object.notEmpty(userId));
    }
</script>
<%@include file="../common/bottom.jsp" %>
</body>
</html>


