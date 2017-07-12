<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String adId = StringUtils.defaultIfEmpty(request.getParameter("adId"), "");
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
                            <td colspan="2" class="ui-state-error"><input type="hidden" name="adId"></td>
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
                            <td class="CaptionTD"><font color="red">*</font>广告类型：</td>
                            <td class="DataTD" id="adPositionTypeId">&nbsp;
                                <select data-placeholder="广告类型" name="adPositionType" jyValidate="required"
                                        class="chosen-select isSelect147"></select>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>图片路径：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="path" onclick="selectPicture()" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                                <a href="#" title="清空" onclick="emptyPath(); return false;" class="lrspace3 aBtnNoTD"
                                   data-toggle="modal"><i class='icon-remove bigger-120 red'></i></a>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>顺序：</td>
                            <td class="DataTD">&nbsp;
                                <input type="number" value="1" jyValidate="required,numrangeth" name="sort" min='1' max='99999' jyValidate="required,numrangeth" onkeyup='this.value=this.value.replace(/\D/g,&apos;&apos;)'  class="FormElement ui-widget-content ui-corner-all isSelect75"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>名称：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" jyValidate="required" maxlength="16" name="title" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">描述：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="comment"
                                       class="FormElement ui-widget-content ui-corner-all"></td>

                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD">链接：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="url"
                                       class="FormElement ui-widget-content ui-corner-all">
                            </td>
                        </tr>
                        </tbody>
                    </table>
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
<script type="text/javascript">
    var adId = '<%=adId%>';
    JY.Dict.setSelect("adPositionTypeId", "adPositionType");
    function selectPicture() {
        var url = jypath + '/jc/picture/index?type=select';
        art.dialog.open(url, {title: '选择图片', width: 850, height: 500});
    }
    //设置图片路径
    function setPath(chks) {
        $("#auForm input[name$='path']").val(chks);
    }
    //清空图片路径
    function emptyPath() {
        $("#auForm input[name$='path']").val("");
    }
    function doAdd() {
        var url = "";
        if(adId) {
            url = jypath + '/jc/adposition/update';
        } else {
            url = jypath + '/jc/adposition/add';
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
    function setForm(data){
        var l=data.obj;
        $("#auForm input[name$='adId']").val(l.adId);
        JY.Tags.isValid("auForm",(JY.Object.notNull(l.isValid)?l.isValid:"0"));
        $("#auForm input[name$='path']").val(JY.Object.notEmpty(l.path));
        $("#auForm input[name$='title']").val(JY.Object.notEmpty(l.title));
        $("#auForm input[name$='comment']").val(JY.Object.notEmpty(l.comment));
        $("#auForm input[name$='url']").val(JY.Object.notEmpty(l.url));
    }
    $(function(){
        //加载数据
        if(adId) {
            JY.Ajax.doRequest(null,jypath +'/jc/adposition/find',{adId:adId},function(data){
                setForm(data);
                JY.Model.check("auDiv");
            });
        }
    })


</script>
<%@include file="../common/bottom.jsp" %>
</body>
</html>


