<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include_header-second.jsp"%>
<body style="min-width: 1000px;background-color: #ffffff;">
<div class="row">
    <div class="col-sm-1">
    </div>
    <%--<div class="col-sm-10" style="background-image:url(${basePath}assets/system/pufa/img/bgImg.png)!important;background-repeat:no-repeat!important;background-position: center;background:#ebf6f5;padding-left: 0px;padding-right: 0px;border-left: 1px solid #cccccc;border-right:1px solid #cccccc;">--%>
    <div class="col-sm-10" style="background:#d0f6e3!important;padding-left: 0px;padding-right: 0px;border-left: 1px solid #cccccc;border-right:1px solid #cccccc;">
        <div class="navbar navbar-default" id="navbar" style="height: 150px;">
            <script type="text/javascript">
                try {
                    ace.settings.check('navbar', 'fixed')
                } catch (e) {
                }
            </script>

            <div class="navbar-container" id="navbar-container"
                 style="height: 160px; background: none repeat scroll 0px 0px #1687D5;text-align: center;">

                <div>
                    <img src="${basePath}assets/system/pufa/img/biaozi.png">
                    <small style="font-size: 40px;font-family: 微软雅黑;font-weight: 200;line-height:120px;letter-spacing:0.2em;color: #ffffff">
                        李沧区干部法律法规学习系统
                    </small>
                    <div style="color: #ffffff;">
                        当前登录用户：${sessionScope.username}
                    </div>
                </div>
                <div class="navbar-header" style="display: none;">
                    <a href="#" class="navbar-brand" style="cursor: default;height: 80px;">
                        <small style="font-size: 40px;font-family: 微软雅黑;font-weight: 200;line-height:120px;letter-spacing:0.2em;">
                            李沧区干部法律法规学习系统
                        </small>
                    </a><!-- /.brand -->
                    <div class="" style="margin-top:120px;margin-left: 8px;color: #f5f5f5;">
                        当前登录用户：${sessionScope.username}
                    </div>
                </div>
                <!-- /.navbar-header -->
            </div>
            <!-- /.container -->
        </div>
        <div class="main-container" id="main-container" style="">
            <script type="text/javascript">
                try {
                    ace.settings.check('main-container', 'fixed')
                } catch (e) {
                }
            </script>

            <div class="main-container-inner">


                <div id="main-content-id" class="main-content" style="margin-left:0px;margin-top: 10px;">


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


                    <style>
                        .my-btn-menu {
                            width: 180px !important;
                            margin-bottom: 40px !important;
                            height: 73px;
                            width: 260px!important;
                            padding-top: 22px;
                            font-size: 23px;
                            font-family: 微软雅黑;
                            border-radius:15px;
                            background-color: #1687D5!important;
                            border: 0px;
                        }
                        .my-min{
                            font-size: 25px!important;
                        }
                    </style>
                    <div class="page-content" style="background:none!important;min-height:348px;text-align: center;">
                        <div class="row" style="margin-top: 10%;">
                            <a
                               href="${basePath}CommonCtrl.goTo.do?path=/WEB-INF/system/pufa/study-main.jsp"
                               class="btn btn-info my-btn-menu"
                               style="margin-right: 70px !important;">学习题库
                            </a>
                            <a
                               href="${basePath}CommonCtrl.goTo.do?path=/WEB-INF/system/pufa/pre-test.jsp"
                               class="btn btn-info my-btn-menu">模拟考试
                            </a>
                        </div>
                        <div class="row" style="margin-top: 50px;">
                            <a
                               href="${basePath}CommonCtrl.goTo.do?path=/WEB-INF/system/pufa/collect.jsp"
                               class="btn btn-info my-btn-menu"
                               style="margin-right: 70px !important;">收藏题库
                            </a>
                            <a
                               href="${basePath}CommonCtrl.goTo.do?path=/WEB-INF/system/pufa/error.jsp"
                               class="btn btn-info my-btn-menu">错题题库
                            </a>
                        </div>
                    </div>
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
        <div class="footer" style="background-color: #1687D5!important;color: #ffffff;font-weight: bold;margin-top:2.4%;">
            李沧区委组织部&nbsp;&nbsp;&nbsp;&nbsp;李沧区人力资源和社会保障局&nbsp;&nbsp;&nbsp;&nbsp;李沧区司法局<br>
            技术支持：宇威科技发展（青岛）有限公司&nbsp;&nbsp;&nbsp;&nbsp;(Copyright@2014—2015 All rights reserved.)
        </div>
    </div>
    <div class="col-sm-1">
    </div>
</div>
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

