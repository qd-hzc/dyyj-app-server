<%--
  Created by IntelliJ IDEA.
  User: yinbin
  Date: 2015/4/28
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../../pages/common_jstl.jsp" %>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>李沧区干部法律法规学习系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!-- basic styles -->

    <link href="${basePath}assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${basePath}assets/css/font-awesome.min.css"/>

    <!--[if IE 7]>
    <link rel="stylesheet" href="${basePath}assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->

    <!-- page specific plugin styles -->

    <link rel="stylesheet" href="${basePath}assets/css/jquery-ui-1.10.3.custom.min.css"/>
    <link rel="stylesheet" href="${basePath}assets/css/jquery.gritter.css"/>

    <!-- fonts -->

    <!--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />-->

    <!-- ace styles -->

    <link rel="stylesheet" href="${basePath}assets/css/ace.min.css"/>
    <link rel="stylesheet" href="${basePath}assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="${basePath}assets/css/ace-skins.min.css"/>

    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${basePath}assets/css/ace-ie.min.css"/>
    <![endif]-->

    <%-- 新手引导效果库 --%>
    <link rel="stylesheet" href="${basePath}assets/js/joyride/joyride-2.1.css">

    <!-- inline styles related to this page -->

    <style type="text/css">
        .modal-dialog {
            padding-top: 10%;
        }

        .spinner-preview {
            width: 100px;
            height: 100px;
            text-align: center;
            margin-top: 60px;
        }

        .dropdown-preview {
            margin: 0 5px;
            display: inline-block;
        }

        .dropdown-preview > .dropdown-menu {
            display: block;
            position: static;
            margin-bottom: 5px;
        }

        .my-menu {
            bottom: 2px;
            position: absolute;
            top: 112px;
            right: 50px;
        }

        .my-menu li {
            width: 120px;
            font-size: 18px;
            font-weight: bold;
        }

        input[type=radio].ace + .lbl::before {
            border-radius: 100%;
            font-size: 18px;
            font-family: FontAwesome;
            text-shadow: 0 0 1px #32a3ce;
            line-height: 28px;
            height: 28px;
            min-width: 28px;
        }

        input[type=checkbox].ace + .lbl::before {
            font-size: 18px;
            text-shadow: 0 0 1px #32a3ce;
            line-height: 28px;
            height: 28px;
            min-width: 28px;
        }

        input[type=radio].ace-all + .lbl-all::before {
            border-radius: 100%;
            font-size: 14px !important;
            font-family: FontAwesome;
            text-shadow: 0 0 1px #32a3ce;
            line-height: 25px !important;
            height: 25px !important;
            min-width: 25px !important;
        }

        input[type=checkbox].ace-all + .lbl-all::before {
            font-size: 14px !important;
            text-shadow: 0 0 1px #32a3ce;
            line-height: 25px !important;
            height: 25px !important;
            min-width: 25px !important;
        }

        .my-menu-item-active {
            height: 55px !important;
            margin-top: -8px;
            border-top-color: #f59942 !important;
            border-top-width: 5px !important;
        }

        .my-menu-item-active > a {
            padding-top: 8px !important;
        }

        .my-menu-item-active:active {
            opacity: 0.6 !important;
        }

        .tab-pane {
            text-align: center !important;
        }

        .my-test-card {
            min-width: 350px !important;
        }

        .my-test-info {
            min-width: 200px !important;
        }

        .my-radio-all {
            padding-top: 5px;
            padding-bottom: 5px;
            font-size: 15px;
        }

        .my-control-group-all {
            margin-top: -20px;
            margin-left: 2%;
        }

        .my-widget-main-all {
            border-top: 1px solid lightgray;
        }

        .my-widget-box-all {
            margin-top: 20px;
        }

        .my-nav-tabs {
            font-size: 15px;
        }

        .my-btn-fenye {
            border-radius: 30px;
            width: 40px;
            height: 40px;
            background-color: #d3d3d3 !important;
            border-color: #d3d3d3 !important;
        }

        .my-btn-fenye:focus {
            background-color: #d3d3d3 !important;
            border-color: #d3d3d3 !important;
        }

        .my-btn-tihao {
            color: black !important;
            font-weight: 700 !important;
            font-size: 12px !important;
            width: 44px !important;
            padding: 5px 0px !important;
            margin: 1px !important;
            border: 1px solid #d3d3d3 !important;
            background-color: #ffffff !important;
            background-image: -webkit-gradient(linear, left 0, left 100%, from(#ffffff), to(#ffffff)) !important;
            background: linear-gradient(to bottom, #ffffff, #ffffff 100%) repeat-x scroll 0 0 #ffffff !important;
            margin-bottom: 5px !important;
        }

        .my-btn-tihao-answer {
            color: black !important;
            font-weight: 700 !important;
            font-size: 12px !important;
            width: 44px !important;
            padding: 5px 0px !important;
            margin: 1px !important;
            border: 1px solid #E8E8E8 !important;
            background-color: red !important;
            background-image: -webkit-gradient(linear, left 0, left 100%, from(#E8E8E8), to(#E8E8E8)) !important;
            background: linear-gradient(to bottom, #E8E8E8, #E8E8E8 100%) repeat-x scroll 0 0 #E8E8E8 !important;
        }

        .my-btn-tihao:hover {
            color: #ffffff !important;
            font-weight: 700 !important;
            background-image: -webkit-gradient(linear, left 0, left 100%, from(#ffA500), to(#ffA500)) !important;
            background: linear-gradient(to bottom, #ffA500, #ffA500 100%) repeat-x scroll 0 0 #ffA500 !important;
        }

        .my-radio {
            padding-top: 10px;
            padding-bottom: 5px;
            font-size: 20px;
            border: 1px solid white;
            margin-left: 28px;
            min-height: 50px !important;
            max-height: 100px !important;
        }

        .my-radio-disable {
            padding-top: 10px;
            padding-bottom: 5px;
            font-size: 20px;
            border: 1px solid white;
            margin-left: 28px;
            height: 50px !important;
        }

        .my-radio:hover {
            border: 1px solid #002a80;
            cursor: pointer;
        }

        .my-col-xs {
            text-align: center;
            width: auto !important;
        }

        .my-content-height {
            min-height: 480px !important;
        }

        .my-content-height_test {
            min-height: 510px !important;
        }

        .my-btn-qiehuanti {
            font-size: 16px;
            width: 100% !important;
            margin-top: 20px;
            /*height: 30px !important;*/
            width: 80px !important;
            background-color: #1687D5 !important;
            border-color: #1687D5 !important;
        }

        .my-btn-qiehuanti:hover {
            background-color: #0C72B8 !important;
            /*border-color: #0C72B8 !important;*/
        }

        .my-btn-qiehuanti:focus {
            background-color: #0C72B8 !important;
            /*border-color: #0C72B8 !important;*/
        }

        .page-content {
            padding: 0px !important;
        }

        .logo {
            width: 95px;
            height: 100px;
            float: left;
            margin-top: 25px;
            margin-left: 40px;
            background-image: url("${basePath}assets/system/pufa/img/biaozi.png");
        }

        .my-label-info {
            background-color: #1687D5 !important;
        }

        .my-label-info::before {
            border-bottom-color: #1687D5 !important;
            border-left-color: transparent !important;
            border-right-color: #1687D5 !important;
            border-top-color: #1687D5 !important;
        }

        .my-label-info::before {
            -moz-border-bottom-colors: none;
            -moz-border-left-colors: none;
            -moz-border-right-colors: none;
            -moz-border-top-colors: none;
            border-color: transparent #1687D5 transparent transparent;
            border-image: none;
            border-style: solid;
            border-width: 1px;
            content: "";
            display: inline-block;
            position: absolute;
            top: 0;
            z-index: -1;
        }

        .my-badge-info {
            border:1px solid #1687d5 !important;
            background-color: white !important;
        }

        .my-tab-pane {
            overflow: auto;
        }

        .my-menu-item {
            width: 22px;
            height: 22px;
            margin-top: -5px;
        }

        .my-span-question-title {
            border: 1px solid #000000;
            padding: 10px 50px;
            margin: 0px;
            text-align: center;
            font-size: 25px;
            font-weight: 900;
            font-family: 黑体;
        }

        .my-current-question {
            background: linear-gradient(to bottom, #FCC584, #FCC584 100%) repeat-x scroll 0 0 #FCC584 !important;
        }

        .my-question:hover {
            cursor: pointer;
            background-color: #ffA500 !important;
            color: #ffffff !important;
        }


        /*.nav-tabs>li.active>a, .nav-tabs>li.active>a:hover, .nav-tabs>li.active>a:focus {*/
        /*border-top: 2px solid #f59942 ;*/
        /*}*/
    </style>

    <!-- ace settings handler -->

    <!--
      判断IE浏览器版本小于IE8，则直接提示更新。
    -->
    <!--[if lte IE 8]>
    <script>window.location.href = 'http://cdn.dmeng.net/upgrade-your-browser.html?referrer=' + location.href;</script>
    <![endif]-->

    <script src="${basePath}assets/js/ace-extra.min.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]>
    <script src="${basePath}assets/js/html5shiv.js"></script>
    <script src="${basePath}assets/js/respond.min.js"></script>
    <![endif]-->


</head>

<body style="min-width: 1000px;background-color: #ffffff;">
<div class="row">
    <div class="col-sm-1">
    </div>
    <div class="col-sm-10">

        <div class="navbar navbar-default" id="navbar" style="height: 150px;">
            <script type="text/javascript">
                try {
                    ace.settings.check('navbar', 'fixed')
                } catch (e) {
                }
            </script>

            <div class="navbar-container" id="navbar-container"
                 style="height: 160px; background: none repeat scroll 0px 0px rgb(22, 135, 213);">
                <div class="logo"></div>

                <div class="navbar-header pull-left">
                    <a href="#" class="navbar-brand" style="cursor: default;height: 80px;">
                        <small style="font-size: 40px;font-family: 微软雅黑;font-weight: 200;line-height:120px;letter-spacing:0.2em;">
                            李沧区干部法律法规学习系统
                        </small>
                    </a><!-- /.brand -->
                    <div class="" style="margin-top:120px;margin-left: 8px;color: #f5f5f5;">
                        当前登录用户：${username}
                    </div>
                </div>
                <!-- /.navbar-header -->


                <div class="navbar-header pull-right my-menu" role="navigation" id="yd-menu-id">
                    <ul class="nav ace-nav" style="top: 80px;">

                        <li class="my-menu-item-active">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <%--<i class="icon-tasks"></i>--%>
                                <img src="assets/system/pufa/img/menu/lx.png" class="my-menu-item">
                                练习题
                                <%--<span class="badge badge-grey">4</span>--%>
                            </a>
                        </li>

                        <li>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <%--<i class="icon-envelope icon-animated-vertical"></i>--%>
                                <img src="assets/system/pufa/img/menu/sc.png" class="my-menu-item">
                                收藏本
                                <%--<span class="badge badge-success">5</span>--%>
                            </a>
                        </li>

                        <li>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <%--<i class="icon-envelope icon-animated-vertical"></i>--%>
                                <img src="assets/system/pufa/img/menu/ct.png" class="my-menu-item">
                                错题本
                                <%--<span class="badge badge-success">5</span>--%>
                            </a>
                        </li>

                        <li data-is-modal="modal">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <%--<i class="icon-bell-alt icon-animated-bell"></i>--%>
                                <img src="assets/system/pufa/img/menu/mn.png" class="my-menu-item">
                                模拟考
                                <%--<span class="badge badge-important">8</span>--%>
                            </a>
                        </li>

                        <li data-is-modal="ztk">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <%--<i class="icon-tasks"></i>--%>
                                <img src="assets/system/pufa/img/menu/qt.png" class="my-menu-item">
                                总题库
                                <%--<span class="badge badge-grey">4</span>--%>
                            </a>
                        </li>
                        <li data-is-modal="logout" class="pull-right"
                            style="width: 50px;margin-left:45px;margin-right: -45px;background-color: inherit!important;border-left:0px!important;">
                            <a id="yd-guanji-id" data-toggle="dropdown" href="${basePath}UserCtrl.logoutForLp.do"
                               style="width: 45px;height: 45px;margin:0px;padding: 0px;background-color: inherit!important;">
                                <img title="退出系统" src="${basePath}assets/system/pufa/img/menu/exit.png"
                                     style="margin:0px;padding:0px;width: 45px;height: 45px;">
                            </a>
                        </li>
                        <li class="light-blue" style="display: none;">
                            <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                                <img class="nav-user-photo" src="${basePath}assets/avatars/user.jpg"
                                     alt="Jason's Photo"/>
								<span class="user-info">
									<small>个人中心,</small>
									隋丽丽
								</span>

                            </a>
                        </li>
                    </ul>
                    <!-- /.ace-nav -->
                </div>
                <!-- /.navbar-header -->
            </div>
            <!-- /.container -->
        </div>

        <div class="main-container" id="main-container">
            <script type="text/javascript">
                try {
                    ace.settings.check('main-container', 'fixed')
                } catch (e) {
                }
            </script>

            <div class="main-container-inner">
                <a class="menu-toggler" id="menu-toggler" href="#">
                    <span class="menu-text"></span>
                </a>

                <div id="main-content-id" class="main-content" style="margin-left:0px;margin-top: 10px;">
                    <%-- ######################################################################################################################################## --%>
                    <%--学习页--%>
                    <%@include file="study.jsp" %>
                    <%--收藏页--%>
                    <%@include file="collect.jsp" %>
                    <%--错题页--%>
                    <%@include file="error.jsp" %>
                    <%--模拟页--%>
                    <%@include file="test.jsp" %>
                    <%--全部题--%>
                    <%@include file="all.jsp" %>
                    <%-- ######################################################################################################################################## --%>
                    <!-- /.page-content -->
                </div>
                <!-- /.main-content -->

            </div>
            <!-- /.main-container-inner -->
        </div>
        <!-- /.main-container -->


        <style type="text/css">
            .gradient {
                background: -moz-linear-gradient(top, #438eb9 0%, #ffffff 100%);
                background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #438eb9), color-stop(100%, #ffffff));
                background: -webkit-linear-gradient(top, #438eb9 0%, #ffffff 100%);
                background: -o-linear-gradient(top, #438eb9 0%, #ffffff 100%);
                background: -ms-linear-gradient(top, #438eb9 0%, #ffffff 100%);
                background: linear-gradient(to bottom, #438eb9 0%, #ffffff 100%);
            }

            .footer {
                float: left;
                width: 100%;
                height: 110px;
                background-color: #1687D5;
                color: #fff;
                min-height: 40px;
                text-align: center;
                padding: 20px;
                font-size: 14px;
                line-height: 30px;
            }
        </style>
        <%--<footer class="gradient" style="height: 250px;">预留页脚区域</footer>--%>
        <div class="footer">
            李沧区干部法律法规学习测试系统&nbsp;&nbsp;&nbsp;&nbsp;李沧区委组织部&nbsp;&nbsp;&nbsp;&nbsp;李沧区人力资源和社会保障局&nbsp;&nbsp;&nbsp;&nbsp;李沧区司法局<br>
            技术支持：宇威科技（青岛）有限公司
        </div>
    </div>
    <div class="col-sm-1">
    </div>

</div>
<!-- basic scripts -->

<!--[if !IE]> -->

<script src="${basePath}assets/js/jquery-2.0.3.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="${basePath}assets/js/jquery-1.10.2.min.js"></script>
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
<script src="${basePath}assets/js/bootstrap.min.js"></script>
<script src="${basePath}assets/js/typeahead-bs2.min.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="${basePath}assets/js/excanvas.min.js"></script>
<![endif]-->

<script src="${basePath}assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="${basePath}assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${basePath}assets/js/bootbox.min.js"></script>
<script src="${basePath}assets/js/jquery.easy-pie-chart.min.js"></script>
<script src="${basePath}assets/js/jquery.gritter.min.js"></script>
<%--<script src="${basePath}assets/js/spin.min.js"></script>--%>
<script src="${basePath}assets/js/jquery.knob.min.js"></script>

<!-- ace scripts -->

<script src="${basePath}assets/js/ace-elements.min.js"></script>
<script src="${basePath}assets/js/ace.min.js"></script>

<%-- 新手引导效果脚本 --%>
<script type="text/javascript" src="${basePath}assets/js/joyride/jquery.cookie.js"></script>
<script type="text/javascript" src="${basePath}assets/js/joyride/modernizr.mq.js"></script>
<script type="text/javascript" src="${basePath}assets/js/joyride/jquery.joyride-2.1.js"></script>

<!-- inline scripts related to this page -->

<!--   OWN JS     -->
<script src="${basePath}config.js"></script>

<!-- 业务脚本 -->
<script src="${basePath}assets/system/pufa/js/study.js"></script>
<script src="${basePath}assets/system/pufa/js/collect.js"></script>
<script src="${basePath}assets/system/pufa/js/error.js"></script>
<script src="${basePath}assets/system/pufa/js/test.js"></script>
<script src="${basePath}assets/system/pufa/js/all.js"></script>


<script type="text/html" id="template-test-pre-msg-id">
    <pre style="font-size:18px;width: 100%;">
1、考试时间为50分钟，如果到时间未交卷，系统会自动收卷。

2、系统随机抽题，共80题。满分100分，及格线为80分。

3、题型及分值：
   判断30题，每题1分，共30分。
   单选30题，每题1分，共30分。
   多选20题，每题2分，共40分。</pre>
</script>
<input id="gritter-light" checked="" type="checkbox" class="ace ace-switch ace-switch-1 my-ace-switch-1" style="display: none;"/>
<script type="text/javascript">
    $(function () {
        initTime();
        eventMenuChange()
//        window.__zongtiku = false;
//         window.localStorage.setItem('__zongtiku','false');
    });

    function showContent() {
        var contents = $('#main-content-id').children();
        var index = window.localStorage.getItem('_index');
//        alert('showContent:'+index)
        if (!index)index = 0;
//        debugger;
//        $($(contents.get(index)).slideUp(800).get($('.my-menu > ul > li').index(self))).slideDown(800);
        contents.hide();
        $(contents.get(index)).slideDown(800);
        var menus = $('.my-menu > ul > li');
        menus.removeClass('my-menu-item-active');
        $(menus.get(index)).addClass('my-menu-item-active');
    }
    function eventMenuChange() {
        window.__canJumpOtherCard = true;

        var contentDoms = $('#main-content-id').children();

        $('.my-menu > ul').on('click', 'li', function () {
            var ___label = $(this).attr('data-is-modal');
            if (___label == 'logout') {
                window.location = '${basePath}login_lp.jsp';
                return;
            }
//            window.__zongtiku = false;
            window.localStorage.setItem('__zongtiku', 'false');
            var self = this;
            if ($(this).hasClass('my-menu-item-active'))return; //点击的是当前标签，则不做任何反应

//            if (!window.__canJumpOtherCard) {// 如果没有退出测试则不能跳出
//                $.gritter.add({
//                    title: '',
//                    text: '<h2 style="text-align: center;">考试过程中不能退出，请先交卷。</h2>',
//                    class_name: 'gritter-info gritter-center' + (!$('#gritter-light').get(0).checked ? ' gritter-light' : '')
//                });
//
////                $('#submit-exam-btn-id').css('backgroudColor', 'red');
////                var effectIter = setInterval(function () {
////                    if(window.__effToggle){
////                        window.__effToggle = false;
////                        $('#submit-exam-btn-id').css('border', '0px soild red');
////                    }else{
////                        window.__effToggle = true;
////                        $('#submit-exam-btn-id').css('border', '1px soild red');
////                    }
////                }, 500);
//                setTimeout(function () {
////                    $('#submit-exam-btn-id').css('backgroudColor', 'green');
////                    clearInterval(effectIter);
//                    $.gritter.removeAll();
//                }, 6000);
//                return;
//            }

            var menuItem = $(this);

            /**
             * 刷新页面后显示相应的内容
             */
            function flushContent(self) {
                var menus = $('.my-menu > ul > li');
//                var contents = $('#main-content-id').children();
//                var index = contents.index(contentDoms);
                var index = menus.index(self);
                window.localStorage.setItem('_index', index);
                window.location.reload();
            }


            var isTestModel = menuItem.attr('data-is-modal');
            if (isTestModel == 'modal') {
                var testPreMsg = $('#template-test-pre-msg-id').html();
                bootbox.confirm({
                    buttons: {
                        confirm: {
                            label: '确认',
                            style: 'background-color: #002a80;color: red;width:100px;'
                        },
                        cancel: {
                            label: '取消',
                            className: 'btn-default'
                        }
                    },
                    message: testPreMsg,
                    callback: function (result) {
                        if (result) {

                            flushContent(self);

                            window.__canJumpOtherCard = false;
                            updateTestTime(); // 开始 考试倒计时
                        } else {
                        }
                    },
                    title: "注意事项："
                });
            } else {
                if ('ztk' == isTestModel) {
//                    window.__zongtiku = true;
                    window.localStorage.setItem('__zongtiku', 'true');
                }

                flushContent(self);

            }
        });
    }
    /**
     * 初始化时间
     */
    function initTime() {
        $(".knob").knob();
    }

    /**
     * 自动更新时间
     */
    function updateTestTime() {
        var ipt = $('#sheng-yu-time-ipt-id');
        window._myTestIter = setInterval(function () {
            if (!window._fen_zhong) {
                window._fen_zhong = 50;
            }
            window._fen_zhong = window._fen_zhong - 1;
            if (window._fen_zhong == 10) {
                ipt.css('color', 'yellow');
            }
            if (window._fen_zhong == 5) {
                ipt.css('color', 'red');
            }
            ipt.val(window._fen_zhong).trigger('change');
            if (window._fen_zhong == 0) { //自动提交试卷
                clearInterval(window._myTestIter);
                $('#submit-exam-btn-id').trigger('click');
            }
        }, 1000 * 50);
    }

    /**
     * 一上来后，即进行初始化操作
     */
    showContent();
</script>

<!-- 下面代码是新手引导的代码 -->
<ol id="joyRideTipContent">
    <li data-button="下一步" data-options="tipLocation:center;tipAnimation:fade">
        <p>这您第一次登陆本系统，接下来将会引导您学会使用本系统</p>
    </li>
    <%--<li data-id="yd-menu-id" data-button="下一步" data-options="tipLocation:bottom;tipAnimation:fade">--%>
    <%--<p>这里是菜单栏</p>--%>
    <%--</li>--%>
    <li data-class="my-nav-tabs" data-button="下一步" data-options="tipLocation:bottom;tipAnimation:fade">
        <p>这里可以切换题的类型</p>
    </li>
    <li data-id="xuexi-card-1" data-button="下一步" data-options="tipLocation:bottom;tipAnimation:fade">

        <p>点击这里可以快速在答题卡区显示本题内容</p>
    </li>
    <li data-class="my-btn-xuexi-fenye-next" data-button="下一步" data-options="tipLocation:bottom;tipAnimation:fade">

        <p>点击这里可以查看更多的题号</p>
    </li>
    <li data-class="my-question" data-button="下一步" data-options="tipLocation:bottom;tipAnimation:fade">

        <p>点击这里可以自动高亮“答题卡”区相应的题号</p>
    </li>
    <li data-class="control-group" data-button="下一步" data-options="tipLocation:bottom;tipAnimation:fade">

        <p>做题时，请点击您想要选择的选项</p>
    </li>
    <li data-class="my-btn-xuexi-daan" data-button="下一步" data-options="tipLocation:top;tipAnimation:fade">

        <p>然后，点击“答案”按钮，才会判断您做的对错</p>
    </li>
    <li data-class="my-btn-xuexi-question-next" data-button="下一步" data-options="tipLocation:right;tipAnimation:fade">

        <p>可以直接点击这里，快速跳转到下一题</p>
    </li>
    <li data-id="xuexi-question-time-1" data-button="下一步" data-options="tipLocation:right;tipAnimation:fade">

        <p>这里显示的是您已做过得次数和做错的次数</p>
    </li>
    <li data-id="yd-sc-id" data-button="下一步" data-options="tipLocation:right;tipAnimation:fade">

        <p>这里可以收藏题目</p>
    </li>
    <li data-id="yd-guanji-id" data-button="结束" data-options="tipLocation:top;tipAnimation:fade">

        <p>退出系统，点击这里</p>
    </li>
</ol>
<script>
    $(window).load(function () {
        var _isAlreadyShow = window.localStorage.getItem('_isAlreadyShow');
        if (_isAlreadyShow == 'true') return;
        window.localStorage.setItem('_isAlreadyShow', 'true');
        $('#joyRideTipContent').joyride({
            autoStart: true,
            modal: true,
            expose: true
        });
    });
</script>
<script>
    // 设置jQuery Ajax全局的参数
    $.ajaxSetup({
        type: "POST",
        error: function (jqXHR, textStatus, errorThrown) {
            switch (jqXHR.status) {
                case(500):
//                    alert("服务器系统内部错误，点击确定后重新加载本页面");
                    bootbox.confirm({
                        buttons: {
                            confirm: {
                                label: '确认',
                                style: 'background-color: #002a80;color: red;width:100px;'
                            },
                            cancel: {
                                label: '取消',
                                className: 'btn-default'
                            }
                        },
                        message: '服务器系统内部错误，点击确定后重新加载本页面',
                        callback: function (result) {
                            if (result) {
                                window.location.reload();
                            } else {
                            }
                        },
                        title: "提示"
                    });
                    break;
                case(401):
//                    alert("您长时间未操作了，点击确定后重新登录");
                    bootbox.confirm({
                        buttons: {
                            confirm: {
                                label: '确认',
                                style: 'background-color: #002a80;color: red;width:100px;'
                            },
                            cancel: {
                                label: '取消',
                                className: 'btn-default'
                            }
                        },
                        message: '您长时间未操作了，点击确定后重新登录',
                        callback: function (result) {
                            if (result) {
                                window.location.href = '${basePath}UserCtrl.logoutForLp.do';
                            } else {
                            }
                        },
                        title: "提示"
                    });
                    break;
                default:
                    alert("未知错误");
            }
        },
        success: function (data) {
//            alert("操作成功");
        }
    });
</script>
</body>
</html>

