﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width" />
    <title>企业信息化平台管理系统</title>
    <%@include file="../common/common.jsp"%>
    <script src="resources/js/admin/loginLog.js?r=${jsVersion }" type="text/javascript"></script>
    <link href="resources/css/admin/loginLog.css?r=${jsVersion }" rel="stylesheet" type="text/css" />
</head>
<body>
    <div id="loading" style="display: none;"><img alt="数据正在加载中..." src="resources/images/loading02.gif" /></div>
    <div class="easyui-layout" id="tb" style="padding:5px;height:auto">
        <!-------------------------------搜索框----------------------------------->
        <fieldset>
            <legend>信息查询</legend>
            <form id="ffSearch" method="post">
		        <div style="margin-bottom:5px">
                    <label for="userName">登录名称：</label>
                    <input type="text" name="userName" style="width:100px"  />&nbsp;&nbsp;&nbsp;

                    <label for="type">类别：</label>
                    <select name="operationType" style="width:100px">
                    	<option value="1">登录</option>
                    	<option value="2">注销</option>
                    </select>&nbsp;&nbsp;&nbsp;

                    <label for="descr">日志描述：</label>
                    <input type="text" name="descr" style="width:100px"  />&nbsp;&nbsp;&nbsp;
                    
                    <label for="ipAddress"> I P 地 址：</label>
                    <input type="text" name="ipAddress" style="width:100px"  />
                </div>
		        <div>
                    <label for="macAddress">Mac地址：</label>
                    <input type="text" name=""macAddress"" style="width:100px"  />&nbsp;&nbsp;&nbsp;

                    <label for="beginTime">开始时间：</label>
                    <input class="easyui-datebox" type="text" name="beginTime" style="width:100px"  />&nbsp;&nbsp;&nbsp;

                    <label for="endTime">结束时间：</label>
                    <input class="easyui-datebox" type="text" name="endTime" style="width:100px"  />&nbsp;&nbsp;&nbsp;

                    <a href="#" class="easyui-linkbutton" iconcls="icon-search" id="btnSearch">查询</a>
                </div>
            </form>
        </fieldset>
                
        <!-------------------------------详细信息展示表格----------------------------------->
        <table id="grid" style="width: 940px" title="用户操作" iconcls="icon-view" >
        </table>
    </div>

    <!--------------------------查看详细信息的弹出层---------------------------->
    <div id="DivView" class="easyui-dialog" style="width:580px;height:300px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-view',buttons: '#dlg-buttons'">
        <form id="ffView" method="post" novalidate="novalidate">
                <table id="tblView" class="view">
                    <tr>
                        <th>
                            <label for="userId">登录用户ID：</label></th>
                        <td>
                            <label name="userId" />
                        </td>
                        <th>
                            <label for="userName">登录名称：</label></th>
                        <td>
                            <label name="userName" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="operationType">操作类别：</label></th>
                        <td>
                            <label name="operationType" />
                        </td>
                        <th>
                            <label for="operationName">操作名称：</label></th>
                        <td>
                            <label name="operationName" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="ipAddress">IP地址：</label></th>
                        <td>
                            <label name="ipAddress" />
                        </td>
                        <th>
                            <label for="macAddress">Mac地址：</label></th>
                        <td>
                            <label name="macAddress" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="type">类别：</label></th>
                        <td>
                            <label name="type" />
                        </td>
                        <th>
                            <label for="createTime">创建时间：</label></th>
                        <td>
                            <label name="createTime" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="descr">描述：</label></th>
                        <td colspan="3">
                            <label name="descr" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" style="text-align:right; padding-top:10px">
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivView').dialog('close')">关闭</a>
                        </td>
                    </tr>
                </table>
        </form>
    </div>
</body>
</html>

