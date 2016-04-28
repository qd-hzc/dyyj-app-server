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
    <title>李沧区干部普法考试系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="bookmark" type="image/x-icon" href="${basePath}assets/system/pufa/img/logo.ico"/>
    <link rel="shortcut icon" href="${basePath}assets/system/pufa/img/logo.ico"/>
    <link rel="icon" href="${basePath}assets/system/pufa/img/logo.ico"/>

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!-- basic styles -->

    <link href="${basePath}assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${basePath}assets/css/font-awesome.min.css"/>

    <!--[if IE 7]>
    <link rel="stylesheet" href="${basePath}assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->

    <!-- page specific plugin styles -->

    <!-- fonts -->

    <!-- ace styles -->

    <link rel="stylesheet" href="${basePath}assets/css/ace.min.css"/>
    <link rel="stylesheet" href="${basePath}assets/css/ace-rtl.min.css"/>

    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${basePath}assets/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]>
    <script src="${basePath}assets/js/html5shiv.js"></script>
    <script src="${basePath}assets/js/respond.min.js"></script>
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
            text-align: center;
            line-height: 18px;
            font-size: 18px;
            background: #1687D5;
            border-radius: 5px;
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

</head>

<body class="login-layout bg" style="margin-top:9%;">
<div class="main-container">
    <div class="main-content" style="margin-top: 4%;">
        <div style="margin-top: 30px;">
            <div class="center">
                <h1>
                    <img src="${basePath}assets/system/pufa/img/biaozi.png">
                    <span class="my-title-font">李沧区干部法律法规考试系统</span>
                </h1>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container" style="width: 400px !important;">

                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border my-border-radius"
                             style="background-color: #B0B0B0 !important;">
                            <div class="widget-body my-border-radius">
                                <div class="widget-main my-border-radius">
                                    <%--<div class="row" style="height: 100px;"></div>--%>
                                    <div class="row" style="height: 250px !important;">
                                        <div class="col-xs-12">
                                            <h4 class="header blue lighter bigger" style="font-weight: 800;">
                                                <i class="icon-coffee blue"></i>
                                                考试登录
                                            </h4>

                                            <div class="space-6"></div>
                                            <form role="form" class="form-horizontal" action="UserCtrl.lcpfForExam.do"
                                                  method="post"
                                                  onsubmit="return validate(this);"
                                                  style="padding: 0px 40px 10px 0px !important;">
                                                <div class="form-group">
                                                    <label for="idCard"
                                                           class="col-sm-4 control-label no-padding-right">身份证：</label>

                                                    <div class="col-sm-8">
                                                        <input id="idCard" type="text" name="username"
                                                               class="col-xs-12 col-sm-12"
                                                               placeholder="请输入您的身份证号">
                                                    </div>
                                                </div>

                                                <div class="space-4"></div>

                                                <div class="form-group">
                                                    <label for="idSeq"
                                                           class="col-sm-4 control-label no-padding-right">报名序号：</label>

                                                    <div class="col-sm-8">
                                                        <input id="idSeq" type="password" class="col-xs-12 col-sm-12"
                                                               placeholder="请输入报名序号">

                                                    </div>
                                                </div>
                                                <div style="color: red;text-align: center;">您还没有登录，请先登录！</div>
                                                <div class="clearfix"
                                                     style="margin-top: 20px !important;text-align: center;">
                                                    <%--<label class="inline" style="display: none;">--%>
                                                    <%--<input id="remember-me-id" type="checkbox" class="ace"/>--%>
                                                    <%--<span class="lbl"> 记住我</span>--%>
                                                    <%--</label>--%>
                                                    <button id="submit-exam-btn-id" type="submit"
                                                            class="my-button-join btn-info"
                                                    <%--disabled="disabled"--%>
                                                            style="background-color: #2490D7!important;">
                                                        进&nbsp;&nbsp;入
                                                    </button>
                                                </div>

                                                <div class="space-4"></div>
                                            </form>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /widget-body -->
                    </div>
                    <!-- /login-box -->

                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </div>
    </div>
</div>
<!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->

<script src="${basePath}assets/js/jquery-2.0.3.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="${basePath}assets/js/jquery-1.10.2.min.js"></script>
<![endif]-->

<!--[if !IE]> -->

<script type="text/javascript">
    window.jQuery || document.write("<script src='${basePath}assets/js/jquery-2.0.3.min.js'>" + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='${basePath}assets/js/jquery-1.10.2.min.js'>" + "<" + "/script>");
</script>
<![endif]-->

<script type="text/javascript">
    if ("ontouchend" in document) document.write("<script src='${basePath}assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
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
        var idSeqDom = $('#idSeq');
        var idSeq = idSeqDom.val();
        if (idSeq.length < 6 || idSeq.length > 8) {
            alert('请输入合法的报名序号');
            idSeqDom.focus();
            return false;
        }
    }
</script>
</body>
</html>
