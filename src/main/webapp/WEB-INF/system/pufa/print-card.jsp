<%--
  Created by IntelliJ IDEA.
  User: amy
  Date: 2015/5/22
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
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
                    <style>
                        select{
                            width:292px;
                            border:1px solid #1687d5;
                            padding:0 5px;
                        }
                        .button {
                            width: 180px;
                            display: inline-block;
                            float: left;
                            margin-left:45%;
                            outline: none;
                            cursor: pointer;
                            text-align: center;
                            text-decoration: none;
                            font: 20px 'Microsoft yahei',Arial, Helvetica, sans-serif;
                            padding: .3em .5em;
                            text-shadow: 0 1px 1px rgba(0,0,0,.3);
                            -webkit-border-radius: .5em;
                            -moz-border-radius: .5em;
                            border-radius: .5em;
                            -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.2);
                            -moz-box-shadow: 0 1px 2px rgba(0,0,0,.2);
                            box-shadow: 0 1px 2px rgba(0,0,0,.2);
                        }
                        .button:hover {
                            text-decoration: none;
                        }
                        .button:active {
                            position: relative;
                            top: 1px;
                        }
                        /* blue */
                        .blue {
                            color: #d9eef7;
                            border: solid 1px #0076a3;
                            background: #0095cd;
                            background: -webkit-gradient(linear, left top, left bottom, from(#00adee), to(#0078a5));
                            background: -moz-linear-gradient(top,  #00adee,  #0078a5);
                            filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#00adee', endColorstr='#0078a5');
                        }
                        .blue:hover {
                            background: #007ead;
                            background: -webkit-gradient(linear, left top, left bottom, from(#0095cc), to(#00678e));
                            background: -moz-linear-gradient(top,  #0095cc,  #00678e);
                            filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#0095cc', endColorstr='#00678e');
                        }
                        .blue:active {
                            color: #80bed6;
                            background: -webkit-gradient(linear, left top, left bottom, from(#0078a5), to(#00adee));
                            background: -moz-linear-gradient(top,  #0078a5,  #00adee);
                            filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#0078a5', endColorstr='#00adee');
                        }
                         </style>
<div class="page-content" style="background:none!important;min-height:250px;text-align: center;">
       <!--登录信息-->
    <div class="row">
        <div class="col-sm-2"></div>
               <div class="col-sm-8" style="float:left;padding:100px 0 30px 0;">
            <form action="" method="post" style="font-family:微软雅黑;font-size: 20px;line-height:48px;">
                片区:&nbsp;<select name="userArea"><option value="xx">李沧区XX街道</option>
                <option value="yy">李沧区yy街道</option>
                <option value="zz">李沧区zz街道</option></select><br/>
                单位:&nbsp;<select name="usercompany"  >
                <option value="0">李沧区司法局XX</option>
                <option value="1" >李沧区司法局XX</option>
                <option value="2">李沧区司法局XX</option>
                <option value="3">李沧区司法局XX</option>
                <option value="4">李沧区司法局XX</option></select><br/>
                密码:&nbsp;<input name="username" type="text" size="42" style="border:1px solid #1687d5;width:290px;">
            </form>
        </div>
               <div class="col-sm-2"></div>
    </div>
</div>
</div>
<!-- /.main-content -->

</div>
<!-- /.main-container-inner -->
            <a class="button blue" style="color:white!important;text-align: center;"
               href="CommonCtrl.goTo.do?path=/WEB-INF/system/pufa/select-card.jsp"><strong>登&nbsp;&nbsp;&nbsp;录</strong></a>

            <style type="text/css">
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
<script>
    $(function () {
        $(".chosen-select").chosen();
    });
</script>
