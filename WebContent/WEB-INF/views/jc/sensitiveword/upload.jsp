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
    <!--引入CSS-->
    <link rel="stylesheet" type="text/css" href="${jypath}/static/plugins/webuploader/css/webuploader.css">
    <!--引入JS-->
    <script type="text/javascript" src="${jypath}/static/plugins/webuploader/js/webuploader.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row-fluid">
        <div class="col-xs-12 ui-dialog">
            <div class="ui-dialog-content ui-widget-content" style="margin-bottom:10px;">
                <div id="uploader" class="wu-example">
                    <!--用来存放文件信息-->
                    <div id="thelist" class="uploader-list"></div>
                    <div class="btns">
                        <div id="picker">选择文件</div>
                        <button id="ctlBtn" class="btn btn-default">开始上传</button>
                    </div>
                </div>
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
</script>
<%@include file="../common/bottom.jsp" %>
</body>
</html>


