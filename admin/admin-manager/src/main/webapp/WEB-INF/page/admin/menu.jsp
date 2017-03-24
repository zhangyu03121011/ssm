<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width" />
    <title>功能菜单</title>
    
    <%@include file="../common/common_old.jsp"%>
	
    <link href="resources/css/admin/menu.css?r=${jsVersion }" rel="stylesheet" type="text/css" />
	<script src="resources/js/admin/menu.js?r=${jsVersion }" type="text/javascript"></script>
	
</head>
<body>
    <div id="loading" style="display: none;"><img alt="数据正在加载中..." src="resources/images/loading02.gif" /></div>
    <div class="easyui-layout" style="width:700px;height:700px;" fit="true">
        <div data-options="region:'west',split:true,title:'菜单管理',iconCls:'icon-book'" style="width: 250px; padding: 1px;">
            <div style="padding: 1px; border: 1px solid #ddd;">                
                <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" id="A4" onclick="reloadTree()">刷新</a>
                <a id="expandAllBtn" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-expand'" id="A5" onclick="return false;">展开</a>
                <a id="collapseAllBtn" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-collapse'" id="A6" onclick="return false;">折叠</a>
            </div>
            <div>
                <ul id="meibaTree" class="ztree">
                </ul>
            </div>
        </div>
        <div id="tb" data-options="region:'center',title:'',iconCls:'icon-book'" style="padding:5px;height:auto">       
            <!-------------------------------搜索框----------------------------------->
            <fieldset>
                <legend>信息查询</legend>
                <form id="ffSearch" method="post">
		            <div style="margin-bottom:5px">	                     
                        <label for="appName">系统名称：</label>
                        <input class="easyui-combobox" type="text" name="appName" style="width:180px" id="searchAppCombo" data-options="valueField: 'appId',textField: 'appName'"/>&nbsp;&nbsp;&nbsp;
                        <label for="menuName">菜单名称：</label>
                        <input type="text" name="menuName" style="width:150px"  />&nbsp;&nbsp;&nbsp;
                        <label for="menuKey">菜单Key：</label>
                        <input type="text" name="menuKey" style="width:150px"  />&nbsp;&nbsp;&nbsp;
                        <label for="winType">窗体类别：</label>
                        <select name="winType" style="width:145px">
                            <option value="_self">当前窗体</option>
                            <option value="_blank">新窗体</option>
                        </select>
                    </div>
                    <div>  
                    	<label for="parentId">父菜单：</label>
                    	<select class="easyui-combotree" style="width:180px;" name="parentId" id="searchMenuParentIdTree"></select>&nbsp;&nbsp;&nbsp;
                        <label for="descr">描述：</label>
                        <input type="text" name="descr" style="width:150px"  />&nbsp;&nbsp;&nbsp;
                        <label for="visible">是否可见：</label>
                        <select name="visible" style="width:145px">
                            <option value="1">可见</option>
                            <option value="0">不可见</option>
                        </select>
                         <a href="#" class="easyui-linkbutton" iconcls="icon-search" id="btnSearch">查询</a>
                    </div>

                </form>
            </fieldset>
                
            <!-------------------------------详细信息展示表格----------------------------------->
            <table id="grid" style="width: 1024px" title="用户操作" iconcls="icon-view">            
            </table>
        </div>
   </div>

    <!--------------------------添加信息的弹出层---------------------------->
    <div id="DivAdd" class="easyui-dialog" style="width:780px;height:370px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-add',buttons: '#dlg-buttons'">
        <form id="ffAdd" method="post" novalidate="novalidate">
                <table id="tblAdd" class="view">
                    <tr>
                        <th>
                            <label for="parentId">父菜单：</label>
                        </th>
                        <td colspan="3">
                            <select class="easyui-combotree" style="width:300px;" name="parentId" id="addMenuParentIdTree"></select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="menuName">菜单名称：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" type="text" name="menuName" data-options="required:true,validType:'length[1,50]'" />
                        </td>
                        <th>
                            <label for="menuKey">菜单Key：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" type="text" name="menuKey" data-options="required:true,validType:'length[1,50]'" />
                        </td>
                    </tr>
                    <tr>
 						<th>
                            <label for="winType">窗体类别：</label>
                        </th>
                        <td>
                            <select name="winType" style="width:145px">
                                <option value="_self" selected="selected">当前窗体</option>
                                <option value="_blank">新窗体</option>
                            </select>
                        </td>
                        <th>
                            <label for="menuIcon">菜单图标：</label>
                        </th>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <select name="menuIcon" onchange="javascript:changeIcon(this)">
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
                            <label for="visible">是否可见：</label>
                        </th>
                        <td>
                            <select name="visible" style="width:145px">
                                <option value="1" selected="selected">可见</option>
                                <option value="0">不可见</option>
                            </select>
                        </td>
                        <th>
                            <label for="sort">排序：</label>
                        </th>
                        <td>
                            <input class="easyui-numberbox" type="text" name="sort" min="0" max="100" maxlength="3" precision="0"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="menuUrl">菜单URL：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" type="text" style="width:500px" name="menuUrl"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="descr">备注：</label>
                        </th>
                        <td colspan="3">
                           <textarea style="height:60px;width:500px" name="descr"></textarea>
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
    <div id="DivEdit" class="easyui-dialog" style="width:780px;height:370px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-edit',buttons: '#dlg-buttons'">
        <form id="ffEdit" method="post" novalidate="novalidate">
                <table id="tblEdit" class="view">
                    <tr>
                        <th>
                            <label for="parentId">父菜单：</label>
                        </th>
                        <td colspan="3">
                        	<select class="easyui-combotree" style="width:300px;" name="parentId" id="editMenuParentIdTree"></select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="menuName">菜单名称：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" type="text" name="menuName" data-options="required:true,validType:'length[1,50]'" />
                        </td>
                        <th>
                            <label for="menuKey">菜单Key：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" type="text" name="menuKey" data-options="required:true,validType:'length[1,50]'" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="winType">窗体类别：</label>
                        </th>
                        <td>
                            <select name="winType" style="width:145px">
                                <option value="_self" selected="selected">当前窗体</option>
                                <option value="_blank">新窗体</option>
                            </select>
                        </td>
                        <th>
                            <label for="menuIcon">菜单图标：</label>
                        </th>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <select name="menuIcon" onchange="javascript:changeIcon(this)">
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
                            <label for="sort">排序：</label>
                        </th>
                        <td>
                            <input class="easyui-numberbox" type="text" name="sort" min="0" max="100" maxlength="3" precision="0"/>
                        </td>
                        <th>
                            <label for="visible">是否可见：</label>
                        </th>
                        <td>
                            <select name="visible" style="width:145px">
                                <option value="1" selected="selected">可见</option>
                                <option value="0">不可见</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="menuUrl">菜单URL：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" type="text" style="width:500px" name="menuUrl"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="descr">备注：</label>
                        </th>
                        <td colspan="3">
                           <textarea style="height:60px;width:500px" name="descr"></textarea>
                        </td>
                    </tr>
 
                    <tr>
                        <td colspan="4" style="text-align:right; padding-top:10px">
                            <input type="hidden" name="menuId" />
                            <input type="hidden" name="appId" />
                            <a href="javascript:void(0)" class="easyui-linkbutton" id="btnEditOK" iconcls="icon-ok" >确定</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivEdit').dialog('close')">关闭</a>
                        </td>
                    </tr>
                </table>
            </fieldset>

        </form>
    </div>

    <!--------------------------查看详细信息的弹出层---------------------------->
    <div id="DivView" class="easyui-dialog" style="width:720px;height:370px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-view',buttons: '#dlg-buttons'">
        <form id="ffView" method="post" novalidate="novalidate">
                <table id="tblView" class="view">
                    <tr>        
                        <th width="20%">
                            <label for="parentId">父菜单：</label>
                        </th>
                        <td width="30%">
                            <label name="parentId" />
                        </td>
                        <th width="20%">
                            <label for="appName">系统名称：</label>
                        </th>
                        <td width="30%">
                            <label name="appName" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="menuName">菜单名称：</label>
                        </th>
                        <td>
                            <label name="menuName" />
                        </td>

                        <th>
                            <label for="menuIcon">菜单图标：</label>
                        </th>
                        <td>
                            <div class="icon-blank" icon="img" style="width:24px;height:24px"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="winType">窗体类别：</label>
                        </th>
                        <td>
                            <label name="winType" />
                        </td>

                        <th>
                            <label for="visible">是否可见：</label>
                        </th>
                        <td>
                            <label name="visible" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="sort">排序：</label>
                        </th>
                        <td>
                            <label name="sort" />
                        </td>
                        <th>
                        </th>
                        <td>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="menuUrl">菜单URL：</label>
                        </th>
                        <td colspan="3">
                            <label name="menuUrl" />
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
                    <th>
                        <label for="createBy">创建人：</label>
                    </th>
                    <td>
                        <label name="createBy" />
                    </td>
                    <th>
                        <label for="createTime">创建时间：</label>
                    </th>
                    <td>
                        <label name="createTime" />
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="updateBy">更新人：</label>
                    </th>
                    <td>
                        <label name="updateBy" />
                    </td>
                    <th>
                        <label for="updateTime">更新时间：</label>
                    </th>
                    <td>
                        <label name="updateTime" />
                    </td>
                </tr>
                    <tr>
                        <td colspan="4" style="text-align:right; padding-top:10px">
                            <input type="hidden" name="menuId" />
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivView').dialog('close')">关闭</a>
                        </td>
                    </tr>
                </table>
        </form>
    </div>
</body>
</html>
