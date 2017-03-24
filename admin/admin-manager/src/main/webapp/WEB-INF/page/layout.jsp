﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>企业信息化平台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

	<link href="resources/images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
	<link href="resources/css/themes/Default/style.css" rel="stylesheet" type="text/css" />
	<link href="resources/css/themes/Default/default.css" rel="stylesheet" type="text/css" />
	<!-- 添加对easyui的支持 --> 
	<script src="resources/js/tools/jquery-easyui-1.4.4/jquery.min.js" type="text/javascript"></script>
	<script src="resources/js/tools/jquery-easyui-1.4.4/jquery.easyui.min.js" type="text/javascript"></script>
	<script src="resources/js/tools/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<link href="resources/js/tools/jquery-easyui-1.4.4/themes/default/easyui.css" rel="stylesheet" type="text/css" />
	<link href="resources/js/tools/jquery-easyui-1.4.4/themes/icon.css" rel="stylesheet" type="text/css" />

</head>

<body class="easyui-layout">   
    <div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>   
    <div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div>   
    <div data-options="region:'east',iconCls:'icon-reload',title:'East',split:true" style="width:100px;"></div>   
    <div data-options="region:'west',title:'West',split:true" style="width:100px;"></div>   
    <div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;"></div>   
</body>

</html>
