<%--
  Created by IntelliJ IDEA.
  User: amy
  Date: 2015/5/22
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include_header-second.jsp"%>
<style>
  select{
    width:300px;
    border:1px solid #1687d5;
    margin-left:200px;
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
  .ss{
    width:100%;
    height:60px;
    font-size:20px;
    background: #91cbf3;
    margin-top:10px!important;
    line-height: 60px;padding:0 30px;
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
  .td1{width:10%;
    padding-left:50px;}
  .td2{width:20%;
    padding-bottom: 20px;
    padding-top: 20px;}
  .td3{width:35%;
    font-size:20px;
    text-align :left ;
    padding-left:30px;}
  .td4{width:10%;}
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
      <!--单位名与搜索-->
      <div class="ss"><span style="color:red;float:left;margin-left:50px;">您已经选择了下面N位闭卷考试人员 <select><option value="姓名">姓名</option>
        <option value="单位">身份证号</option></select>&nbsp;<button style="display:inline-block;line-height: normal!important; width:80px;border:1px solid #cccccc;padding:3px 0px 1px 0px;box-shadow: 1px 1px 2px rgba(0,0,0,.2);">搜索</button></div>
       <div class="main-container-inner">
        <div id="main-content-id" class="main-content" style="margin-left:0px;margin-top: 10px;">
          <div class="page-content" style="background:none!important;min-height:348px;text-align: center;">
            <!--表单信息-->
            <table style="float: left;width:100%;"><tr style="border-bottom: 1px solid white;">
              <td class="td1">
              <form><input type="checkbox" /></form></td>
              <td class=" td2">
                <img src='${basePath}assets/system/pufa/img/photo.jpg'; style="width:30%;height:auto;" /></td>
              <td class="td3">
                姓&nbsp;&nbsp;&nbsp;&nbsp;名:张三三<br/>
                性&nbsp;&nbsp;&nbsp;&nbsp;别:男<br/>
                出生日期：1980.10.10</td>
              <td class="td3">
                职&nbsp;&nbsp;&nbsp;&nbsp;级:正科<br/>
                身&nbsp;份&nbsp;证:123456789123456789<br/>
                手机号码：12345678912</td>
              <td class="td4"></td></tr>
              <tr style="border-bottom: 1px solid white;">
                <td class="td1">
                  <form><input type="checkbox" /></form>
                <td class=" td2">
                  <img src='${basePath}assets/system/pufa/img/photo.jpg'; style="width:30%;height:auto;" /></td>
                <td class="td3">
                  姓&nbsp;&nbsp;&nbsp;&nbsp;名:张三三<br/>
                  性&nbsp;&nbsp;&nbsp;&nbsp;别:男<br/>
                  出生日期:1980.10.10</td>
                <td class="td3">
                  职&nbsp;&nbsp;&nbsp;&nbsp;级:正科<br/>
                  身&nbsp;份&nbsp;证:123456789123456789<br/>
                  手机号码:12345678912</td>
             </tr></table>

          </div>
        </div>
        <!-- /.main-content -->

      </div>
      <!-- /.main-container-inner -->
     <a class="button blue" style="width:140px!important;color:white!important;text-align: center;"
         href="CommonCtrl.goTo.do?path=/WEB-INF/system/pufa/select-ok.jsp"><strong>确&nbsp;&nbsp;&nbsp;&nbsp;定</strong></a>
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
</body>
</html>
