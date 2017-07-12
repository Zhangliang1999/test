<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String actId = StringUtils.defaultIfEmpty(request.getParameter("actId"), "");
%>
<!DOCTYPE html >
<html lang="en">
<head>
    <%@include file="../common/includeBaseSet.jsp" %>
    <%@include file="../common/includeSystemSet.jsp" %>
    <script type="text/javascript" charset="utf-8" src="${jypath}/static/plugins/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${jypath}/static/plugins/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
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
                        <tr style="display:none">
                            <td colspan="2" class="ui-state-error"><input type="hidden" name="actId"></td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD" style="width:80px;">状态：</td>
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
                            <td class="CaptionTD"><font color="red">*</font>活动开始：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="begintime" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>活动结束：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="32" name="endtime" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>活动封面：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="128" name="cover" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>活动名称：</td>
                            <td class="DataTD">&nbsp;
                                <input type="text" maxlength="128" name="title" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>
                            </td>
                        </tr>
                        <tr class="FormData">
                            <td class="CaptionTD"><font color="red">*</font>描述：</td>
                            <td class="DataTD">&nbsp;
                               <%-- <input type="text" maxlength="128" name="content" jyValidate="required"
                                       class="FormElement ui-widget-content ui-corner-all"/>--%>
                                <div class="form-group">
                                    <div class="col-sm-11 col-xs-12">
                                        <script id="editor" type="text/plain" style="width:100%;height:300px;"></script>
                                    </div>
                                </div>
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
    var actId = '<%=actId%>';
    function doAdd() {
        var url = "";
        if (actId) {
            url = jypath + '/jc/activitycenter/update';
        } else {
            url = jypath + '/jc/activitycenter/add';
        }
        if (JY.Validate.form("auForm",2)) {
            var body=ue.getContent();
            var param = {content: body};
            JY.Ajax.req("auForm", url, param, function (data){
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
        $("#auForm input[name='actId']").val(l.actId);
        JY.Tags.isValid("auForm", (JY.Object.notNull(l.isValid) ? l.isValid : "0"));
        $("#auForm input[name='cover']").val(JY.Object.notEmpty(l.cover));
        $("#auForm input[name='title']").val(JY.Object.notEmpty(l.title));
//        $("#auForm input[name='content']").val(JY.Object.notEmpty(l.content));
        $("#auForm input[name='begintime']").val(JY.Date.Default(l.begintime));
        $("#auForm input[name='endtime']").val(JY.Date.Default(l.endtime));
        $("#auForm input[name='createTime']").val(JY.Date.Default(l.createTime));
        $("#auForm input[name='updateTime']").val(JY.Date.Default(l.updateTime));
        $("#auForm input[name='operId']").val(JY.Object.notEmpty(l.operId));
        ue.addListener("ready", function () {
            // editor准备好之后才可以使用
            ue.setContent(JY.Object.notEmpty(l.content));
        });
    }
    $(function () {
        //时间控件
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
        });

        var toolbars=[['source', '|','undo', 'redo', '|','simpleupload','insertimage','scrawl', 'insertframe','|',,'bold','italic',
            'underline', 'fontborder', 'forecolor', 'backcolor', 'fontsize', 'fontfamily', 'paragraph',
            'imagecenter','justifyleft', 'justifyright', 'justifycenter', 'justifyjustify',
            'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch',
            'autotypeset','pasteplain', '|','touppercase', 'tolowercase','|',
            'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', 'link',
            'unlink','spechars','emotion','map','inserttable',
            'edittd','edittable','date','time','print','preview','insertcode','searchreplace']];
        ue = UE.getEditor('editor',{toolbars:toolbars});
        //加载数据
        if (actId) {
            JY.Ajax.req(null, jypath + '/jc/activitycenter/find', {actId: actId}, function (data) {
                setForm(data);

            });
        }
    })
    var ue;
</script>
<%@include file="../common/bottom.jsp" %>
</body>
</html>


