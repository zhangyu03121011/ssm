<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width" />
    <title>系统类型定义</title>
 	<%@include file="../common/common.jsp"%>
    <script src="resources/js/admin/app.js?r=${jsVersion }" type="text/javascript"></script>
    <link href="resources/css/admin/app.css?r=${jsVersion }" rel="stylesheet" type="text/css" />
</head>
<body>

    <div id="loading" style="display: none;"><img alt="数据正在加载中..." src="resources/images/loading02.gif" /></div>
    <div class="easyui-layout" id="tb" style="padding:5px;height:auto">
        <!-------------------------------搜索框----------------------------------->
        <fieldset>
            <legend>信息查询</legend>
            <form id="ffSearch" method="post">
		        <div style="margin-bottom:5px">
		        
                    <label for="appKey">系统标识：</label>
                    <input type="text" name="appKey" style="width:100px"  />&nbsp;&nbsp;&nbsp;
                    <label for="appName">系统名称：</label>
                    <input type="text" name="appName" style="width:100px"  />&nbsp;&nbsp;&nbsp;
                     <a href="#" class="easyui-linkbutton" iconcls="icon-search" id="btnSearch">查询</a>
                </div>

            </form>
        </fieldset>
                
        <!-------------------------------详细信息展示表格----------------------------------->
        <table id="grid" style="width: 940px" title="用户操作" iconcls="icon-view" >
        </table>
    </div>

    <!--------------------------添加信息的弹出层---------------------------->
    <div id="DivAdd" class="easyui-dialog" style="width:650px;height:300px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-add',buttons: '#dlg-buttons'">
        <form id="ffAdd" method="post" novalidate="novalidate">
                <table id="tblAdd" class="view">
                    <tr>
                        <th>
                            <label for="appKey">系统标识：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" type="text" name="appKey" data-options="required:true,validType:'length[3,50]'"/> 
                        </td>

                        <th>
                            <label for="appName">系统名称：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" type="text" name="appName" data-options="required:true,validType:'length[3,50]'"/> 
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="appIndex">系统首页：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" type="text" name="appIndex" data-options="validType:'length[1,50]'"/> 
                        </td>
 
                        <th>
                            <label for="appIcon">系统图标：</label>
                        </th>
                        <td>
							<table>
                                <tr>
                                    <td>
                                        <select name="appIcon" onchange="javascript:changeIcon(this)">
                                            <option>icon-blank</option>
                                            <option>icon-add</option>
                                            <option>icon-edit</option>
                                            <option>icon-remove</option>
                                            <option>icon-save</option>
                                            <option>icon-cut</option>
                                            <option>icon-ok</option>
                                            <option>icon-no</option>
                                            <option>icon-cancel</option>
                                            <option>icon-reload</option>
                                            <option>icon-search</option>
                                            <option>icon-print</option>
                                            <option>icon-help</option>
                                            <option>icon-undo</option>
                                            <option>icon-redo</option>
                                            <option>icon-back</option>
                                            <option>icon-sum</option>
                                            <option>icon-tip</option>
                                            <option>icon-mini-add</option>
                                            <option>icon-mini-edit</option>
                                            <option>icon-mini-refresh</option>
                                            <option>icon-excel</option>
                                            <option>icon-word</option>
                                            <option>icon-organ</option>
                                            <option>icon-lock</option>
                                            <option>icon-alarm</option> 
                                            <option>icon-view</option>
                                            <option>icon-pie</option>
                                            <option>icon-bar</option>
                                            <option>icon-curve</option>
                                            <option>icon-computer</option>
                                            <option>icon-house</option>
                                            <option>icon-key</option>
                                            <option>icon-photo</option>
                                            <option>icon-user</option>
                                            <option>icon-group</option>
                                            <option>icon-group-key</option>
                                            <option>icon-telephone</option>
                                            <option>icon-phone</option>
                                            <option>icon-table</option>
                                            <option>icon-book</option>
                                            <option>icon-comment</option>
                                            <option>icon-date</option>
                                            <option>icon-email</option>
                                            <option>icon-first</option>
                                            <option>icon-last</option>
                                            <option>icon-next</option>
                                            <option>icon-previous</option>
                                            <option>icon-stop</option>
                                            <option>icon-setting</option>
                                        </select>
                                    </td>
                                    <td><div class="icon-blank" icon="img" style="width:24px;height:24px"/></td>
                                </tr>
                            </table>  
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="appUrl">系统URL：</label>
                        </th>
                        <td colspan="3">
                        	<input class="easyui-validatebox" style="width: 400px;" type="text" name="appUrl" data-options="required:true,validType:'length[1,50]'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="descr">备注：</label>
                        </th>
                        <td colspan="3">
                        	<textarea rows="4" cols="63" name="descr" class="textarea easyui-validatebox"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" style="text-align:right; padding-top:10px">
                            <a href="javascript:void(0)" class="easyui-linkbutton" id="btnAddOK" iconcls="icon-ok" >确定</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivAdd').dialog('close')">关闭</a>
                        </td>
                    </tr>
                </table>
        </form>
    </div>

    <!--------------------------编辑信息的弹出层---------------------------->
    <div id="DivEdit" class="easyui-dialog" style="width:650px;height:300px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-edit',buttons: '#dlg-buttons'">
        <form id="ffEdit" method="post" novalidate="novalidate">
                <table id="tblEdit" class="view">
                    <tr>
                        <th>
                            <label for="appKey">系统标识：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" type="text" name="appKey" data-options="required:true,validType:'length[1,50]'"/> 
                        </td>
 
                        <th>
                            <label for="appName">系统名称：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" type="text" name="appName" data-options="required:true,validType:'length[1,50]'"/> 
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="appIndex">系统首页：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" type="text" name="appIndex" data-options="validType:'length[1,50]'"/> 
                        </td>
 
                        <th>
                            <label for="appIcon">系统图标：</label>
                        </th>
                        <td>
							<table>
                                <tr>
                                    <td>
                                        <select name="appIcon" onchange="javascript:changeIcon(this)">
                                            <option>icon-blank</option>
                                            <option>icon-add</option>
                                            <option>icon-edit</option>
                                            <option>icon-remove</option>
                                            <option>icon-save</option>
                                            <option>icon-cut</option>
                                            <option>icon-ok</option>
                                            <option>icon-no</option>
                                            <option>icon-cancel</option>
                                            <option>icon-reload</option>
                                            <option>icon-search</option>
                                            <option>icon-print</option>
                                            <option>icon-help</option>
                                            <option>icon-undo</option>
                                            <option>icon-redo</option>
                                            <option>icon-back</option>
                                            <option>icon-sum</option>
                                            <option>icon-tip</option>
                                            <option>icon-mini-add</option>
                                            <option>icon-mini-edit</option>
                                            <option>icon-mini-refresh</option>
                                            <option>icon-excel</option>
                                            <option>icon-word</option>
                                            <option>icon-organ</option>
                                            <option>icon-lock</option>
                                            <option>icon-alarm</option> 
                                            <option>icon-view</option>
                                            <option>icon-pie</option>
                                            <option>icon-bar</option>
                                            <option>icon-curve</option>
                                            <option>icon-computer</option>
                                            <option>icon-house</option>
                                            <option>icon-key</option>
                                            <option>icon-photo</option>
                                            <option>icon-user</option>
                                            <option>icon-group</option>
                                            <option>icon-group-key</option>
                                            <option>icon-telephone</option>
                                            <option>icon-phone</option>
                                            <option>icon-table</option>
                                            <option>icon-book</option>
                                            <option>icon-comment</option>
                                            <option>icon-date</option>
                                            <option>icon-email</option>
                                            <option>icon-first</option>
                                            <option>icon-last</option>
                                            <option>icon-next</option>
                                            <option>icon-previous</option>
                                            <option>icon-stop</option>
                                            <option>icon-setting</option>
                                        </select>
                                    </td>
                                    <td><div class="icon-blank" icon="img" style="width:24px;height:24px"/></td>
                                </tr>
                            </table>  
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="appUrl">系统URL：</label>
                        </th>
                        <td colspan="3">
                        	<input class="easyui-validatebox" style="width: 400px;" type="text" name="appUrl" data-options="required:true,validType:'length[1,50]'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="descr">备注：</label>
                        </th>
                        <td colspan="3">
                        	<textarea rows="4" cols="63" name="descr" class="textarea easyui-validatebox"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" style="text-align:right; padding-top:10px">
                        	<input type="hidden" name="appId" />
                            <a href="javascript:void(0)" class="easyui-linkbutton" id="btnEditOK" iconcls="icon-ok" >确定</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivEdit').dialog('close')">关闭</a>
                        </td>
                    </tr>
                </table>
        </form>
    </div>

    <!--------------------------查看详细信息的弹出层---------------------------->
    <div id="DivView" class="easyui-dialog" style="width:600px;height:300px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-view',buttons: '#dlg-buttons'">
        <form id="ffView" method="post" novalidate="novalidate">
                <table id="tblView" class="view">
                    <tr>
                        <th>
                            <label for="appKey">系统标识：</label>
                        </th>
                        <td>
                            <label name="appKey" />
                        </td>

                        <th>
                            <label for="appName">系统名称：</label>
                        </th>
                        <td>
                            <label name="appName" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="appIndex">系统首页：</label>
                        </th>
                        <td>
                            <label name="appIndex" />
                        </td>

                        <th>
                            <label for="appIcon">系统图标：</label>
                        </th>
                        <td>
                            <div class="icon-blank" icon="img" style="width:24px;height:24px"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="appUrl">系统Url：</label>
                        </th>
                        <td colspan="3">
                            <label name="appUrl" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="descr">备注：</label>
                        </th>
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
