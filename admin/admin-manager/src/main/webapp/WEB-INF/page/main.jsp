﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>企业信息化平台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="resources/images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
	<link href="resources/js/JqueryEasyUI/themes/default/easyui.css" rel="stylesheet" type="text/css" />
	<link href="resources/js/JqueryEasyUI/themes/icon.css" rel="stylesheet" type="text/css" />
	
	<link href="resources/css/themes/Default/style.css" rel="stylesheet" type="text/css" />
	<link href="resources/css/themes/Default/default.css" rel="stylesheet" type="text/css" />
	
	<!-- 添加对easyui的支持 --> 
	<script src="resources/js/JqueryEasyUI/jquery.min.js" type="text/javascript"></script>
	<script src="resources/js/JqueryEasyUI/jquery.easyui.min.js" type="text/javascript"></script>
	<script src="resources/js/JqueryEasyUI/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	
	<!-- 添加对ztree控件的支持 --> 
	<link href="resources/js/JQueryTools/JQueryTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
	<script src="resources/js/JQueryTools/JQueryTree/js/jquery.ztree.core-3.5.min.js" type="text/javascript"></script>
	<script src="resources/js/JQueryTools/JQueryTree/js/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
	
	<!-- 添加对uploadify控件的支持 --> 
	<script src="resources/js/tools/uploadify/jquery.uploadify.js" type="text/javascript"></script>
	<link href="resources/js/tools/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
	
	<!-- 添加对ckeditor的支持 -->
	<script src="resources/js/tools/ckeditor/ckeditor.js"></script>
	<script src="resources/js/tools/ckeditor/adapters/jquery.js"></script>
	
	<!-- 日期格式的引用 -->
	<script src="resources/js/datapattern.js" type="text/javascript"></script>
	
	<script src="resources/js/common/common.js" type="text/javascript"></script>

    <script src="resources/js/admin/index.js"></script>
</head>

<body style="overflow-y:hidden"  scroll="no">

    <form id="form1" class="easyui-layout">
    
  				<%-- ${permission:hasPermission('aa','admin','role_manager', 'add')} --%>
<%-- 			<permission_tag:permission permission="add" menuKey="role_manager" appKey="admin"> --%>
<%-- 			</permission_tag:permission> --%>
    
	    <!--顶部Banner-->
		<div region="north" id="header" style="overflow: hidden;">
	         <div class="userinfo" style="display:block">
	             <font color="red" size="5">${common_login_user.userName }</font>，您好！
	         </div>
	        <ul class="navigation" style="display:block">
	        	<c:forEach items="${appList }" var="item">
		            <li><a onclick="showSubMenu('${item.appIndex }', '${item.appName }', '${item.appId }', '${item.appIcon }')" href="#">${item.appName }</a></li>
	        	</c:forEach>
	        </ul>
	        <ul class="button">
	            <li>
	               	当前时间：<b id="date"></b>
	            </li>
	            <li>
	                <a href="javascript:void(0)" onclick="javascript:ShowPasswordDialog()" title="修改用户密码">
	                    <img border="0" src="resources/css/themes/Default/btn_hd_support.gif" title="修改用户密码" alt="修改用户密码"/>
	                 </a>
	            </li>
	            <li>
	                <a href="#" onclick="addTab('在线帮助', 'userGuid.htm', 'icon icon-help')" title="在线帮助">
	                    <img border="0" src="resources/css/themes/Default/btn_hd_help.gif" title="在线帮助" alt="在线帮助" />
	                </a>
	            </li>
	            <li>
	                <a href="user/logout" title="注销">
		                <b>
		                	<img border="0" src="resources/css/themes/Default/btn_hd_exit.gif" title="注销" alt="注销" />
		                </b>
	                </a>
	            </li>
	        </ul>
		</div>
		
		<!--左侧导航菜单-->
		<div region="west" split="true" title="导航菜单" style="width:200px;padding:1px;overflow:hidden;">
			<div id="nav" class="easyui-accordion" fit="true" border="false">
			</div>
		</div>
		
		<!--主工作区-->
		<div id="mainPanle" region="center" title="" style="overflow:hidden;">
			<div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			</div>
		</div>
		
		<!--Tab上菜单操作-->
		<div id="mm" class="easyui-menu" style="width:150px;">
			<div id="mm-tabupdate">刷新</div>
			<div class="menu-sep"></div>
			<div id="mm-tabclose">关闭</div>
			<div id="mm-tabcloseall">全部关闭</div>
			<div id="mm-tabcloseother">除此之外全部关闭</div>
			<div class="menu-sep"></div>
			<div id="mm-tabcloseright">当前页右侧全部关闭</div>
			<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
			<div class="menu-sep"></div>
			<div id="mm-exit">退出</div>
		</div>
	
	    <!--底部版权标识-->
	    <div data-options="region:'south',split:true" style="height: 40px;background: #D2E0F2;">
	        <div class="easyui-layout" data-options="fit:true" style="background: #D2E0F2;text-align:center;color:#15428B;font-weight:bold;">
	           	版权所有： 深圳市芯芯之火有限公司 2017 官网：<a href="http://www.xxzh100.com">http://www.xxzh100.com</a>
	        </div>
	    </div>        
	</form>

	<!-- 修改密码 -->
    <div id="divModPass" class="easyui-dialog" style="width:350px;height:260px;padding:10px 20px"
			closed="true" resizable="true" modal="true" buttons="#dlg-buttons" iconCls="icon-setting"  >
        <form id="ffModPass" method="post" novalidate="novalidate">
            <table cellspacing="1" cellpadding="0" border="0" width="100%">
                <tr style="height: 40px;">
                    <th style="width: 100px; font-weight: bold; text-align: right;">原密码：</th>
                    <td style="width: 200px">
                        <input type="password" name="oldPassword" class="easyui-validatebox" data-options="required:true,validType:'length[6,50]'"/>
                    </td>
                </tr>
                <tr style="height: 40px;">
                    <th style="width: 100px; font-weight: bold; text-align: right;">新密码：</th>
                    <td style="width: 200px">
                        <div style="float: left; margin-top: 2px;">
                            <input type="password" id="password" name="password" class="easyui-validatebox" data-options="required:true,validType:'length[6,50]'"/>
                        </div>
                    </td>
                </tr>
                <tr style="height: 40px;">
                    <th style="width: 100px; font-weight: bold; text-align: right;">确认新密码：</th>
                    <td style="width: 200px">
                        <input type="password" name="surePassword" class="easyui-validatebox" required="true" validType="same['password']"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" height="20"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-lock" onclick="ModifyPass()">提交</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#divModPass').dialog('close')">关闭</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <!-- 修改密码 -->

</body>

</html>