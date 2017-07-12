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
                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            用户信息
                        </h1>
                    </div>
                    <!-- /.page-header -->

                    <div class="row">
                        <div class="col-xs-12">
                            <!-- PAGE CONTENT BEGINS -->
                            <div>
                                <div id="user-profile-1" class="user-profile row">
                                    <div class="col-xs-12 col-sm-3 center">
                                        <div>
											<span class="profile-picture">
                                                <!--照片-->
                                                <img id="avatar"
                                                     class="editable img-responsive editable-click editable-empty"
                                                     alt="Alex's Avatar"
                                                     src="/static/apidoc/JYUI/assets/avatars/profile-pic.jpg"><span
                                                    id="realName2">&nbsp;</span></img>
											</span>

                                            <div class="space-4"></div>

                                            <div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
                                                <div class="inline position-relative">
                                                    <a href="#" class="user-title-label dropdown-toggle"
                                                       data-toggle="dropdown"> <i
                                                            class="icon-circle light-green middle"></i> &nbsp; <span
                                                            class="white" id="username2"></span>
                                                    </a>

                                                    <ul class="align-left dropdown-menu dropdown-caret dropdown-lighter">
                                                        <li class="dropdown-header">Change Status</li>

                                                        <li><a href="#"> <i class="icon-circle green"></i>
                                                            &nbsp; <span class="green">Available</span>
                                                        </a></li>

                                                        <li><a href="#"> <i class="icon-circle red"></i>
                                                            &nbsp; <span class="red">Busy</span>
                                                        </a></li>

                                                        <li><a href="#"> <i class="icon-circle grey"></i>
                                                            &nbsp; <span class="grey">Invisible</span>
                                                        </a></li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="space-6"></div>

                                        <div class="profile-contact-info">
                                            <div class="profile-contact-links align-left">
                                                <%--<a class="btn btn-link" href="#"> <i class="icon-plus-sign bigger-120 green"></i> Add as a
                                                    friend
                                                </a> <a class="btn btn-link" href="#"> <i class="icon-envelope bigger-120 pink"></i> Send a message
                                            </a> <a class="btn btn-link" href="#"> <i class="icon-globe bigger-125 blue"></i> www.alexdoe.com
                                            </a>--%>
                                            </div>

                                            <div class="space-6"></div>

                                            <div class="profile-social-links center">
                                            </div>
                                        </div>

                                        <div class="hr hr12 dotted"></div>

                                        <div class="clearfix">
                                            <div class="grid2">
                                                <span class="bigger-175 blue" id="followCnt">&nbsp;</span> <br>关注
                                            </div>

                                            <div class="grid2">
                                                <span class="bigger-175 blue" id="fansCnt">&nbsp;</span> <br> 粉丝
                                            </div>
                                        </div>

                                        <div class="hr hr16 dotted"></div>
                                    </div>

                                    <div class="col-xs-12 col-sm-9">
                                        <div class="center">
											<span class="btn btn-app btn-sm btn-light no-hover"> <span
                                                    class="line-height-1 bigger-170 blue"
                                                    id="sendCnt"> &nbsp; </span> <br>
												<span class="line-height-1 smaller-90">总发单 </span>
											</span>
                                            <span class="btn btn-app btn-sm btn-primary no-hover">
												<span class="line-height-1 bigger-170" id="buyCnt"> &nbsp; </span> <br> <span
                                                    class="line-height-1 smaller-90"> 总买单 </span>
											</span>
                                            <span class="btn btn-app btn-sm btn-yellow no-hover">
												<span class="line-height-1 bigger-170"
                                                      id="sendCntByWeek"> &nbsp; </span> <br> <span
                                                    class="line-height-1 smaller-90"> 本周发单 </span>
											</span>
                                            <span class="btn btn-app btn-sm btn-pink no-hover"> <span
                                                    class="line-height-1 bigger-170" id="buyCntByWeek">  &nbsp; </span> <br> <span
                                                    class="line-height-1 smaller-90"> 本周卖单 </span>
											</span>
                                            <span class="btn btn-app btn-sm btn-grey no-hover"> <span
                                                    class="line-height-1 bigger-170"
                                                    id="sendCntByMonth">  &nbsp; </span> <br> <span
                                                    class="line-height-1 smaller-90"> 本月发单 </span>
											</span>
                                            <span class="btn btn-app btn-sm btn-success no-hover">
												<span class="line-height-1 bigger-170"
                                                      id="buyCntByMonth"> &nbsp; </span> <br> <span
                                                    class="line-height-1 smaller-90"> 本月买单 </span>
											</span>
                                        </div>

                                        <div class="space-12"></div>

                                        <div class="profile-user-info profile-user-info-striped">
                                            <div class="profile-info-row">
                                                <div class="profile-info-name">用户编码</div>
                                                <div class="profile-info-value" id="userId">&nbsp;</div>
                                            </div>
                                            <div class="profile-info-row">
                                                <div class="profile-info-name">手机号</div>
                                                <div class="profile-info-value" id="mobile">&nbsp;</div>
                                            </div>
                                            <div class="profile-info-row">
                                                <div class="profile-info-name">真实姓名</div>
                                                <div class="profile-info-value" id="realName">&nbsp;</div>
                                            </div>
                                            <div class="profile-info-row">
                                                <div class="profile-info-name">身份证</div>
                                                <div class="profile-info-value" id="idCard">&nbsp;</div>
                                            </div>
                                            <div class="profile-info-row">
                                                <div class="profile-info-name">昵称</div>
                                                <div class="profile-info-value" id="nickName">&nbsp;</div>
                                            </div>
                                            <div class="profile-info-row">
                                                <div class="profile-info-name">个人签名</div>
                                                <div class="profile-info-value" id="signature">&nbsp;</div>
                                            </div>
                                            <div class="profile-info-row">
                                                <div class="profile-info-name">邀请码</div>
                                                <div class="profile-info-value" id="inviteCode">&nbsp;</div>
                                            </div>
                                            <div class="profile-info-row">
                                                <div class="profile-info-name">创建时间</div>
                                                <div class="profile-info-value" id="createTime">&nbsp;</div>
                                            </div>
                                        </div>
                                        <div class="hr hr16 dotted"></div>
                                        <div id="address">
                                            <!--地址 -->
                                        </div>
                                        <div class="hr hr16 dotted"></div>
                                        <div class="space-6"></div>
                                    </div>
                                </div>
                            </div>

                            <!-- PAGE CONTENT ENDS -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.row -->
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
    var userId = '<%=userId%>';

    function setForm(data, userAddressList) {
        var l = data.obj;
        $("#userId").html(l.userId);
        $("#mobile").html("&nbsp;" + JY.Object.notEmpty(l.mobile));
        $("#username2").html("&nbsp;" + JY.Object.notEmpty(l.mobile));
        $("#realName").html("&nbsp;" + JY.Object.notEmpty(l.realName));
        $("#realName2").html("&nbsp;" + JY.Object.notEmpty(l.realName));
        $("#idCard").html("&nbsp;" + JY.Object.notEmpty(l.idCard));
        $("#nickName").html("&nbsp;" + JY.Object.notEmpty(l.nickName));
        $("#signature").html("&nbsp;" + JY.Object.notEmpty(l.signature));
        $("#inviteCode").html("&nbsp;" + JY.Object.notEmpty(l.inviteCode));
        $("#followCount").html("&nbsp;" + JY.Object.notEmpty(l.followCount));
        $("#createTime").html("&nbsp;" + JY.Date.Default(l.createTime));
        $("#updateTime").html("&nbsp;" + JY.Date.Default(l.updateTime));
        $("#operId").html("&nbsp;" + JY.Object.notEmpty(l.operId));
        var html = "";
        for (var i = 0; i < userAddressList.length; i++) {
            console.log(userAddressList[i]);
            var a = userAddressList[i];
            html += "<div class=\"profile-user-info profile-user-info-striped\">";
            html += " <div class=\"profile-info-row\">                                    ";
            html += "                    <div class=\"profile-info-name\">收件人</div>  ";
            html += "                    <div class=\"profile-info-value\">" + a.addressee + "</div>   ";
            html += "  </div>                                                   ";
            html += " <div class=\"profile-info-row\">                                    ";
            html += "                    <div class=\"profile-info-name\">联系方式</div>  ";
            html += "                    <div class=\"profile-info-value\">" + a.contact + "</div>   ";
            html += "  </div>                                                   ";
            html += " <div class=\"profile-info-row\">                                    ";
            html += "                    <div class=\"profile-info-name\">所在地区</div>  ";
            html += "                    <div class=\"profile-info-value\">" + a.region + "</div>   ";
            html += "  </div>                                                   ";
            html += " <div class=\"profile-info-row\">                                    ";
            html += "                    <div class=\"profile-info-name\">详细地址</div>  ";
            html += "                    <div class=\"profile-info-value\">" + a.address + "</div>   ";
            html += "  </div>                                                   ";
            html += " <div class=\"profile-info-row\">                                    ";
            html += "                    <div class=\"profile-info-name\">是否默认地址</div>  ";
            var isdefault = a.isdefault == 1 ? "是" : "否";
            html += "                    <div class=\"profile-info-value\">&nbsp;" + isdefault + "</div>   ";
            html += "  </div></div>                                                ";
            html += "   <div class=\"hr hr16 dotted\"></div><div class=\"space-6\"></div>";
        }
        $("#address").append(html);
    }
    $(function () {
        //加载数据
        if (userId) {
            JY.Ajax.doRequest(null, jypath + '/jc/user/getUserInfo', {userId: userId}, function (data) {
                var data = data.obj;
                setForm(data, data.userAddressList);
                $("#buyCnt").html(data.buyCnt);
                $("#sendCnt").html(data.sendCnt);
                $("#buyCntByWeek").html(data.buyCntByWeek);
                $("#sendCntByWeek").html(data.sendCntByWeek);
                $("#buyCntByMonth").html(data.buyCntByMonth);
                $("#sendCntByMonth").html(data.sendCntByMonth);
                $("#followCnt").html(data.followCnt);
                $("#fansCnt").html(data.fansCnt);
            });
        }
    })
</script>
<%@include file="../common/bottom.jsp" %>
</body>
</html>


