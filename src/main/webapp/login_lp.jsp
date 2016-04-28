<%--
  Created by IntelliJ IDEA.
  User: yinbin
  Date: 2015/4/28
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>李沧区干部普法学习系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="bookmark" type="image/x-icon" href="${basePath}assets/system/pufa/img/logo.ico"/>
    <link rel="shortcut icon" href="${basePath}assets/system/pufa/img/logo.ico"/>
    <link rel="icon" href="${basePath}assets/system/pufa/img/logo.ico"/>

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!-- basic styles -->

    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="assets/css/font-awesome.min.css"/>

    <!--[if IE 7]>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->

    <!-- page specific plugin styles -->

    <!-- fonts -->

    <!-- ace styles -->

    <link rel="stylesheet" href="assets/css/ace.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-rtl.min.css"/>

    <!--[if lte IE 8]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->

    <style type="text/css">
        .bg {
            /*background: url(assets/system/pufa/img/dw0.jpg);*/
            filter: "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale')";
            -moz-background-size: 100% 100%;
            background-size: 100% 100%;
            background-color: #1687d5;
        }


        .my-button {
            width: 190px;
            height: 40px;
            color: white;
            font-weight: 500;
            text-align: center;
            line-height: 18px;
            font-size: 18px;
            background:#004c82!important;
            border-radius: 5px;
            border: 2px solid #004c82!important;
            font-family: "Microsoft YaHei";
            letter-spacing: 0.3em!important;
        }

        .my-button-join {
            width: 100px;
            height: 35px;
            color: white;
            text-align: center;
            background: #428bca;
            font-weight: 800;
            /*float: right;*/
            border-radius: 5px;
        }

        .my-title-font {
            font-size: 38px;
            font-weight: 800;
            color: white;
            line-height: 48px;
            font-family: "微软雅黑";
            letter-spacing: 3px;
            text-shadow: 1px 1px 3px #cccccc;
            -webkit-text-shadow: #ccc 1px 0 0, #ccc 0 1px 0, #959494 -1px 0 0, #959494 0 -1px 0;
            -moz-text-shadow: #ccc 1px 0 0, #ccc 0 1px 0, #000 -1px 0 0, #000 0 -1px 0;
            text-shadow: #ccc 1px 0 0, #ccc 0 1px 0, #000 -1px 0 0, #000 0 -1px 0;
            *filter: Glow(Color=#000, Strength=1);
        }

        .my-border-radius {
            border-radius: 20px;
        }

    </style>
    <!--[if lte IE 8]>
    <style>
        .my-login-btns-for-ie{
            top: 50px!important;
        }
    </style>
    <![endif]-->

</head>

<body class="login-layout bg" style="margin-top:100px;">
<div class="main-container">
    <div class="main-content">
        <div>
            <div class="center">
                <h1>
                    <img src="assets/system/pufa/img/biaozi.png">
                    <span class="my-title-font">李沧区干部法律法规学习系统</span>
                </h1>
            </div>
        </div>
        <div class="login-container" style="width: 700px !important;">

            <div class="position-relative">
                <div id="login-box" class="login-box visible widget-box no-border my-border-radius"
                     style="background-color: #B0B0B0 !important;">
                    <div class="widget-body my-border-radius">
                        <div class="widget-main my-border-radius">
                            <%--<div class="row" style="height: 100px;"></div>--%>
                            <div class="row" style="height: 260px !important;">
                                <div class="col-xs-6">
                                    <h4 class="header blue lighter bigger" style="font-weight: 800;">
                                        <i class="icon-coffee blue"></i>
                                        学员登录
                                    </h4>

                                    <div class="space-6"></div>
                                    <form action="UserCtrl.lcpf.do" method="post"
                                          onsubmit="return validate(this);"
                                          style="padding: 0px 40px 10px 0px !important;border-right: 1px solid lightgray;margin-right: -10px;">
                                        <fieldset>
                                            <div style="margin-bottom: 10px;font-size: 15px;font-weight: bold;">
                                                身份证号码：
                                            </div>
                                            <label class="block clearfix" style="margin-top: 15px !important;">
														<span class="block input-icon input-icon-right">
															<input id="idCard" name="username" type="text"
                                                                   class="form-control"
                                                                   placeholder="请输入您的身份证号"/>
															<i class="icon-user"></i>
														</span>
                                            </label>

                                            <div class="space"></div>

                                            <div style="color: red;text-align: center;">您还没有登录，请先登录！</div>
                                            <div class="clearfix"
                                                 style="margin-top: 25px !important;text-align: center;">
                                                <%--<label class="inline" style="display: none;">--%>
                                                <%--<input id="remember-me-id" type="checkbox" class="ace"/>--%>
                                                <%--<span class="lbl"> 记住我</span>--%>
                                                <%--</label>--%>
                                                <button id="submit-btn-id" type="submit"
                                                        class="my-button-join btn-info"
                                                <%--disabled="disabled"--%>
                                                        style="background-color: #2490D7!important;">
                                                    进&nbsp;&nbsp;入
                                                </button>
                                            </div>

                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>
                                </div>
                                <div class="col-xs-6">
                                    <h4 class="header blue lighter bigger" style="font-weight: 800;">
                                        <i class="icon-group blue"></i>
                                        请扫描二维码直接下载安装
                                    </h4>

                                    <div class="space-6"></div>
                                    <div class="row">
                                        <div class="col-xs-6">安卓手机:</br>
                                            <img src="assets/system/pufa/img/erweima/android-ccstudy-8090.png"
                                                 style="width: 100%;">
                                        </div>
                                        <div class="col-xs-6">苹果手机:</br>
                                            <img src="assets/system/pufa/img/erweima/lp-ios.png"
                                                 style="width: 100%;">
                                        </div>
                                        <!--
                                    <div class="col-xs-3"></div>
                                    <div class="col-xs-6" style="text-align: center;">
                                        手机端扫描下载:</br>
                                        <img src="assets/system/pufa/img/erweima/licangsifa.png"
                                             style="width: 100%;">
                                    </div>
                                        -->
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /widget-body -->
            </div>
            <!-- /login-box -->
            <div class="center position-relative my-login-btns-for-ie" style="top: 350px;">
                <div class="row">
                    <div class="col-xs-4">
                        <a class="btn my-button"
                           href="CommonCtrl.goTo.do?path=/WEB-INF/system/pufa/exam-sign.jsp">网上报名</a>
                    </div>
                    <div class="col-xs-4">
                        <%--<button class="my-button" style="background-color: #76959B;" disabled>打印准考证</button>--%>
                        <a class="btn my-button" href="CommonCtrl.goTo.do?path=/WEB-INF/system/pufa/print-card.jsp" >打印准考证</a>
                    </div>
                    <div class="col-xs-4">
                        <a class="btn my-button  disabled"
                           href="javascript:openForFullScreen('CommonCtrl.goTo.do?path=/WEB-INF/system/pufa/exam-login.jsp');">网上考试</a>
                    </div>
                </div>
            </div>

        </div>
        <!-- /.col -->


    </div>
</div>
<!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->

<script src="assets/js/jquery-2.0.3.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="assets/js/jquery-1.10.2.min.js"></script>
<![endif]-->

<!--[if !IE]> -->

<script type="text/javascript">
    window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>" + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>" + "<" + "/script>");
</script>
<![endif]-->

<script type="text/javascript">
    if ("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>

<!-- inline scripts related to this page -->
<script type="text/javascript">
    function show_box(id) {
        jQuery('.widget-box.visible').removeClass('visible');
        jQuery('#' + id).addClass('visible');
    }

    function validate(form) {
        var idCardDom = $('#idCard');
        var idCard = idCardDom.val();
        var isCheck = $('#remember-me-id').val();
//        if(isCheck){
//            window.localStorage.setItem('_pho')
//        }
        var r = new RegExp('^[1-9]([0-9]{16}|[0-9]{13})[xX0-9]$');
        if (idCard.length != 18 || !r.test(idCard)) {
            alert('请输入合法的身份证号');
            idCardDom.focus();
            return false;
        }
    }

    //下面代码实现全屏显示
    function openForFullScreen(url) {
        var Request = new Array();//保存参数
        var s = location.search.substring(1);
        if (s && s != "") {
            var list = s.split("&");
            for (var i = 0; i < list.length; i++) {
                var pair = list[i].split("=");
                if (pair[0] && pair[0] != "") {
                    Request[unescape(pair[0])] = unescape(pair[1]);
                }
            }
        }
        var fullscreen = Request["fullscreen"];
        if (fullscreen != "yes") {
            var file = url;
            var a = window.open("about:blank", "", "fullscreen=yes")
            self.opener = null
            self.close()
            a.location = file + "?fullscreen=yes";
        }
    }
</script>
</body>
</html>
