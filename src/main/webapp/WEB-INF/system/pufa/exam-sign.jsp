<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include_header-second.jsp"%>
<style>
    select {
        width:290px;
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
    .date-selector {
        position: relative;
        margin: 0px;
    }
    .date-select-box {
        position: absolute;
        top: 22px;
        left: 40px;
        padding: 8px 5px;
        display: none;
        background-color: #fff;
        z-index: 9999;
        font-size:20px;
    }
    .date-selected-box, .date-select-box, .date-select-box select {
        border: 1px solid #1687d5;
    }
    input[readonly] {
        background: none repeat scroll 0 0 #ffffff !important;
    }
    #imghead {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);width:42;}
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
<body style="min-width: 1000px;background-color: #ffffff;">
<div class="row">
    <div class="col-sm-1">
    </div>
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


                    <div class="page-content" style="background:none!important;min-height:348px;text-align: center;">
                        <div style="font-size: 20px;padding:30px;">如果您已经报名，修改报名信息请点击
                            <a href="CommonCtrl.goTo.do?path=/WEB-INF/system/pufa/exam-pre-Modify information.jsp"><strong>本链接</strong></a></div>
                        <!--表单信息-->
                        <div class="row">
                            <div class="col-sm-2"></div>
                            <!--左边-->
                            <div class="col-sm-4" >
                               <form action="" method="post" style="font-family:微软雅黑;font-size: 20px;line-height:48px;text-align: left;padding-left:30px;">
                                   您的姓名:&nbsp;<input name="username" type="text"  style="border:1px solid #1687d5; width:290px;"><br/>
                                     <div class="date-selector" id="date-selector-1">
                                       出生日期:&nbsp;<input type="text"  class="date-selected-box" value="点击此处选择日期" readonly="readonly" style="border:1px solid #1687d5; width:290px;"/>
                                       <div class="date-select-box">
                                           <select class="date-select-year"></select>年<select class="date-select-month"></select>月<select class="date-select-day"></select>日
                                           <input class="date-select-sure" type="button" value="确认"/>
                                       </div>
                                   </div>

                                   工作单位:&nbsp;<select name="usercompany"><option >李沧区司法局</option></select><br/>
                                   您的职级:&nbsp;<select name="userrank"  ><option value="0">区级</option>
                                   <option value="1" >处级</option>
                                   <option value="2">副处</option>
                                   <option value="3">正科</option>
                                   <option value="4">副科及其他</option></select>
                               </form>
                            </div>
                            <!--右边-->
                            <div class="col-sm-4">
                                <form action="" method="post" style="font-family:微软雅黑;font-size: 20px;line-height:48px;text-align: left;padding-left:30px;">
                                    您的性别:&nbsp;<input name="sex" id="man" type="radio" value="" />
                                    <label for="man" style="font-size:20px;">男</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input name="sex" id="woman" type="radio" value="" />
                                    <label for="woman" style="font-size:20px;">女</label><br/>
                                    身份证号:&nbsp;<input name="userborn" type="text"  style="border:1px solid #1687d5; width:290px;"><br/>
                                    手机号码:&nbsp;<input name="usercompany" type="text"  style="border:1px solid #1687d5; width:290px;"><br/>
                                    上传照片:&nbsp;<input type="file" onchange="previewImage(this)" value="浏览" id="imghead" style="display:inline-block;line-height: normal!important; width:290px;" >
                                   </form>
                            </div>
                            <div class="col-sm-2"></div>

                        </div>
                    </div>
                </div>
                <!-- /.main-content -->

            </div>
            <!-- /.main-container-inner -->
            <div class="button blue" style="color:white!important;text-align: center;"><strong>确定报名</strong></div>
        </div>
        <!-- /.main-container -->
        <div class="footer" style="background-color: #1687D5!important;color: #ffffff;font-weight: bold;margin-top:2.4%;">
            李沧区委组织部&nbsp;&nbsp;&nbsp;&nbsp;李沧区人力资源和社会保障局&nbsp;&nbsp;&nbsp;&nbsp;李沧区司法局<br>
            技术支持：宇威科技发展（青岛）有限公司&nbsp;&nbsp;&nbsp;&nbsp;(Copyright@2014—2015 All rights reserved.)
        </div>
    </div>
    <div class="col-sm-1">
    </div>
</div>
<script src="${basePath}assets/system/pufa/js/birthday.js"></script>
<script src="${basePath}assets/system/pufa/js/sendphoto.js"></script>
</body>
</html>

