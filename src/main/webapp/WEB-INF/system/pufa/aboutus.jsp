<%--
  Created by IntelliJ IDEA.
  User: LiuJY
  Date: 2015/5/6
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include_header-second.jsp"%>

<body style="min-width: 1000px;background-color: white;">
<div class="row" >
    <div class="col-sm-1">
    </div>
    <%--<div class="col-sm-10" style="background-image:url(${basePath}assets/system/pufa/img/bgImg.png)!important;background-repeat:no-repeat!important;background-position: center!important;background: #ebf6f5;padding-left: 0px;padding-right: 0px;border-left: 1px solid #cccccc;border-right:1px solid #cccccc;">--%>
    <div class="col-sm-10" style="background:#d0f6e3;padding-left: 0px;padding-right: 0px;border-left: 1px solid #cccccc;border-right:1px solid #cccccc;">
        <div class="navbar navbar-default" id="navbar" style="height: 150px;">
            <div class="navbar-container" id="navbar-container"
                 style="height: 160px; background: none repeat scroll 0px 0px #1687D5;text-align: center;">

                <div >
                    <img src="${basePath}assets/system/pufa/img/biaozi.png">
                    <small style="font-size: 40px;font-family: 微软雅黑;font-weight: 200;line-height:120px;letter-spacing:0.2em;color: #ffffff">
                        李沧区干部法律法规学习系统
                    </small>
                    <div style="color: #ffffff;font-size:15px;margin-top: -20px;">
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
            </div>
        </div>
        <div  style="min-height: 320px;font-size: 20px;padding:5% 10%;line-height:40px;">
            <p style="text-indent: 2em;text-align:left;">
                为深入贯彻党的十八大和十八届三中、四中全会精神，进一步提高全区干部依法行政能力，加快推进法治李沧建设进程，根据《李沧区2015年干部培训计划》和全区“六五”普法工作要求，区委组织部、区人力资源和社会保障局、区司法局联合开发了《李沧区干部法律法规学习测试系统》学习软件。该学习软件题库共有2000题，主要包括党的十八大、十八届三中、四中全会议精神和党内法规、干部廉洁自律的有关规定以及公务员依法履职应当掌握的法律法规等内容，旨在通过干部学法用法，带动全民学法守法，努力营造办事依法、遇事找法、解决问题用法、化解矛盾靠法的良好法治氛围。
            </p>


            <a class="button" href="${basePath}CommonCtrl.goTo.do?path=/WEB-INF/system/pufa/menu.jsp" style="margin-bottom:7.9%;margin-top:8%;">点击按钮进入学习系统
            </a>
        </div>
        <!-- /.main-container -->
        <div class="footer" style="color: #ffffff;font-weight: bold;">
            李沧区委组织部&nbsp;&nbsp;&nbsp;&nbsp;李沧区人力资源和社会保障局&nbsp;&nbsp;&nbsp;&nbsp;李沧区司法局<br>
            技术支持：宇威科技发展（青岛）有限公司&nbsp;&nbsp;&nbsp;&nbsp;(Copyright@2014—2015 All rights reserved.)
        </div>
    </div>
    <div class="col-sm-1" >
    </div>
</div>
</body>
</html>

