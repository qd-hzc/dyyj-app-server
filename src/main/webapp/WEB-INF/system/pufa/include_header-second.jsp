<%--
  Created by IntelliJ IDEA.
  User: LiuJY
  Date: 2015/5/6
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" >
<head>
    <%@include file="../../pages/common_jstl.jsp" %>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>李沧区干部普法学习系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="bookmark" type="image/x-icon" href="${basePath}assets/system/pufa/img/logo.ico"/>
    <link rel="shortcut icon" href="${basePath}assets/system/pufa/img/logo.ico"/>
    <link rel="icon" href="${basePath}assets/system/pufa/img/logo.ico"/>

    <!-- basic styles -->

    <link href="${basePath}assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${basePath}assets/css/font-awesome.min.css"/>

    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="${basePath}assets/css/jquery-ui-1.10.3.custom.min.css"/>
    <!-- ace styles -->

    <link rel="stylesheet" href="${basePath}assets/css/ace.min.css"/>
    <link rel="stylesheet" href="${basePath}assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="${basePath}assets/css/ace-skins.min.css"/>

    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${basePath}assets/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- inline styles related to this page -->

    <style type="text/css">

        .dropdown-preview > .dropdown-menu {
            display: block;
            position: static;
            margin-bottom: 5px;
        }

        .my-menu li {
            width: 140px;
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


        .page-content {
            padding: 0px !important;
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
        .button {
            display: inline-block;
            outline: none;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            padding: .3em 1em .35em;
            text-shadow: 0 1px 1px rgba(0, 0, 0, .3);
            -webkit-border-radius: .5em;
            -moz-border-radius: .5em;
            border-radius: .5em;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
            box-shadow: 0 2px 3px rgba(0, 0, 0, .2);
            width: 260px;
            border: 3px solid #e4e4e4;
            background: #1687D5;
            float: right;
            margin-right: 60px;
            line-height: 35px;
            margin-top: 30px;
            color: white;
            font-family: "微软雅黑";
            letter-spacing: 2px;
            font-size: 18px;
        }

        a{
            text-decoration: none;
        }
        a:hover{
            color: #ffffff;
            text-decoration: none;
        }
    </style>

    <!--
      判断IE浏览器版本小于IE8，则直接提示更新。
    -->
    <!--[if lte IE 7]>
    <script>window.location.href = 'http://cdn.dmeng.net/upgrade-your-browser.html?referrer=' + location.href;</script>
    <![endif]-->

    <script src="${basePath}assets/js/ace-extra.min.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]>
    <script src="${basePath}assets/js/html5shiv.js"></script>
    <script src="${basePath}assets/js/respond.min.js"></script>
    <![endif]-->


</head>
