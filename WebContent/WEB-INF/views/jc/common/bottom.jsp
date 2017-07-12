<%--
  Created by IntelliJ IDEA.
  User: susen-pc
  Date: 2016/9/8
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    //auForm 表单高度自适应
    $(window).resize(function () {
       /* $('.panel.full-height').each(function () {
            $(this).height($(window).height() - $(this).offset().top - 20);
        });*/
        //$('.ui-dialog').height($(window).height() - $('.ui-dialog').offset().top - 10);
        $('.ui-dialog').css("min-height",$(window).height() - $('.ui-dialog').offset().top - 10);
        $('.ui-dialog-content').css("min-height", $('.ui-dialog').height() - 70);
    });
    $(".ui-dialog-buttonset").css("padding-right", "10px");
    setTimeout(function () {
        $(window).resize();
    }, 100);
    function closeWin() {
        art.dialog.close();
    }
</script>
