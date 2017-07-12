<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
    <%@include file="../common/includeBaseSet.jsp" %>
    <%@include file="../common/includeSystemSet.jsp" %>
    <link rel="stylesheet" type="text/css" href="${jypath}/static/apidoc/JYUI/assets/css/dropzone.css"/>
    <script type="text/javascript" src="${jypath}/static/apidoc/JYUI/assets/js/dropzone.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row-fluid">
        <div class="col-xs-12 ui-dialog">
            <div class="ui-dialog-content ui-widget-content" style="margin-bottom:10px;">
                <div>
                    <div>
                        <form action="${jypath}/jc/sensitiveword/uploadFile" class="dropzone needsclick dz-clickable" id="demo-upload">
                            <div class="dz-message needsclick text-center">
                                <h3>拖动文件到这里或点击上传.</h3><br>
                                <i class="upload-icon icon-cloud-upload blue icon-5x"></i>
                            </div>
                        </form>
                    </div>
                    <div class="space-4"></div>
                    <div>
                        <button class="btn btn-default blue" title="模板下载" type="button"
                                onclick="window.location.href='${jypath}/jc/sensitiveword/download?fileName=sensitiveword.xls'">
                            <i class="icon-cloud-upload">模板下载</i></button>
                    </div>
                </div>
                <div id="tips" style="padding:10px;font-size:14px;color:red;"></div>
            </div>
            <!--底部按钮-->
            <div class="navbar-fixed-bottom ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
            </div>
        </div>
    </div>

</div>
<script type="text/javascript">
    $(function () {
        try {
            window.Dropzone;
            Dropzone.autoDiscover = false;
            $(".dropzone").dropzone({
                maxFiles: 10,
                maxFilesize: 20, // MB
                acceptedFiles: ".xls"
            });
        } catch(error){
        }
    });

</script>
<%@include file="../common/bottom.jsp" %>
</body>
</html>