<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include_header-second.jsp"%>
<style type="text/css">
  /*loading加载遮罩层css*/
  #black_overlay
  {
    position: fixed;
    z-index: 1000;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    filter: alpha(opacity=60);
    opacity: 0.6;
    overflow: hidden;
    background-color: #000;
  }
  *html
  {
    background: url(*) fixed;
  }
  *html body
  {
    margin: 0;
    height: 100%;
  }
  /*IE6*/
  *html #black_overlay {
    position: absolute;
    left: expression(documentElement.scrollLeft + documentElement.clientWidth - this.offsetWidth);
    top: expression(documentElement.scrollTop + documentElement.clientHeight - this.offsetHeight);
  }
  .my-tishikuang{
    width: 500px;
    height:250px;
    border:10px solid #1687d5;
    border-radius: 1em;
    margin: 0 auto;
    margin-top:15%;
    background: #ffffff;
    z-index:2000;
    opacity: 1;
    -webkit-box-shadow: 4px 4px 10px rgba(255,255,255,0.3);
    -moz-box-shadow: 4px 4px 10px rgba(255,255,255,0.3);

  }
  p{
    font-size:25px;
    color:#1687d5;
    font-family: Arial, Helvetica, sans-serif;
    padding:20px 30px;
    font-weight:600;
  }
  i{
    width: 80px!important;
    height:80px!important;;
    padding:0px 30px 20px 30px;
   }
</style>
<body>
<div id="black_overlay">
<div class="my-tishikuang">
  <p>提示信息</p>
  <i class="icon-exclamation-sign"></i>
</div>
</div>
</body>
</html>
