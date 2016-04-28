<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>托普云课堂</title>
</head>
<body class="page-body" data-ga-account="UA-11792865-43" data-wt-dcsid="dcsgkesx8adv0hs1hkt0njxo6_3r7c">
<link rel="stylesheet" href="<%=basePath%>assets/css/bootstrap.css">
<link rel="stylesheet" href="<%=basePath%>assets/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>assets/css/bootstrap-theme.min.css">
<script src="<%=basePath%>assets/js/jquery-1.11.0.min.js"></script>
<script src="<%=basePath%>assets/js/bootstrap.min.js"></script>
<style>
    .message{
        height: 20px;
    }
</style>

<script type="text/javascript">
    //登录
    function submitLogin() {
        var userName = $.trim($("#userName").val());
        var password = $.trim($("#password").val());
        if (userName == '') {
            $("#errorUser").text('请输入用户名');
            clearMessage();
            return;
        }
        if (password == '') {
            $("#errorPassword").text('请输入密码');
            clearMessage();
            return;
        }
        $("#login_form").submit();
    }
    //清空错误信息
    function clearMessage(){
        setTimeout(function () {
            $(".message").text('');
        }, 3000);
    }
    document.onkeydown = function (e) {
        if (!e) e = window.event;//火狐中是 window.event
        if ((e.keyCode || e.which) == 13) {
            submitLogin();
        }
    };
    $(function(){
        clearMessage();
    })
</script>
<div class="container">
    <div class="row col-xs-3" style="margin: 0px auto;">
        <form id="login_form" action="UserCtrl.login.do" method="post">
            <h2 class="form-signin-heading" style="text-align: center">登录</h2>
            <div class="form-group">
                <div class="message" id="error" style="color: red;font-size: 14px">${requestScope.message}</div>
            </div>
            <div class="form-group">
                <input regexExpress="^[\u4e00-\u9fa5A-Za-z0-9._-]{5,20}$"
                       type="text" name="userName" id="userName"
                       class="form-control" placeholder="用户名" autofocus>
                <div class="message" id="errorUser" style="color: red;font-size: 14px;"></div>
            </div>
            <div class="form-group">
                <input type="password" name="password"
                       class="form-control" id="password" placeholder="密码">
                <div class="message" id="errorPassword" style="color: red;font-size: 14px"></div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <button type="button" onclick="submitLogin()" class="btn btn-primary btn-lg btn-block">登录</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>