<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width" />
    <title>字典明细项目信息</title>
    
    <%@include file="../common/common_old.jsp"%>
    
    <link href="resources/css/admin/dictData.css?r=${jsVersion }" rel="stylesheet" type="text/css" />
	<script src="resources/js/admin/dict.js?r=${jsVersion }" type="text/javascript"></script>
	<script src="resources/js/admin/dictItem.js?r=${jsVersion }" type="text/javascript"></script>
    
</head>
<body>
    <div id="loading" style="display: none;"><img alt="数据正在加载中..." src="resources/images/loading02.gif" /></div>
    <div class="easyui-layout" style="width:700px;height:700px;" fit="true">
        <div data-options="region:'west',split:true,title:'字典类别',iconCls:'icon-book'" style="width: 365px; padding: 1px;">
            <div style="padding: 1px; border: 1px solid #ddd;">                
                <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" id="A1" onclick="ShowDictType('add');">添加</a>
                <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" id="A2" onclick="ShowDictType('edit');">修改</a>
                <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" id="A3" onclick="deleteTypeData()">删除</a>
                <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" id="A4" onclick="reloadTree()">刷新</a>
                <a id="expandAllBtn" href="#" title="展开所有" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-expand'" id="A5" onclick="return false;">展开</a>
                <a id="collapseAllBtn" href="#" title="关闭所有" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-collapse'" id="A6" onclick="return false;">折叠</a>
            </div>
            <div>
                <ul id="meibaTree" class="ztree">
                </ul>
            </div>
        </div>
        <div id="tb" data-options="region:'center',title:'字典数据',iconCls:'icon-book'" style="padding:5px;height:auto"> 
        
            <!-------------------------------搜索框----------------------------------->
            <fieldset>
                <legend>信息查询</legend>
                <form id="ffSearch" method="post">
		            <div style="margin-bottom:5px">	                     
                        <label for="appName">系统名称：</label>
                        <input class="easyui-combobox" type="text" name="appName" style="width:180px" id="searchAppCombo" data-options="valueField: 'appId',textField: 'appName'"/>&nbsp;&nbsp;&nbsp;
                        <label for="name">字典名称：</label>
                        <input type="text" name="name" style="width:150px"  />&nbsp;&nbsp;&nbsp;
                    </div>
                    <div>  
                    	<label for="dictId">字典类别：</label>
                    	<select class="easyui-combotree" style="width:180px;" name="dictId" id="searchDictIdTree"></select>&nbsp;&nbsp;&nbsp;
                        <label for="descr">描述：</label>
                        <input type="text" name="descr" style="width:150px"  />&nbsp;&nbsp;&nbsp;
                         <a href="#" class="easyui-linkbutton" iconcls="icon-search" id="btnSearch">查询</a>
                    </div>

                </form>
            </fieldset>
                       
            <!-------------------------------详细信息展示表格----------------------------------->
            <table id="grid" style="width: 940px" title="用户操作" iconcls="icon-view">
            </table>
        </div>
    </div>
   
    <!--------------------------添加字典大类信息的弹出层---------------------------->
    <div id="DivAddType" class="easyui-dialog" style="width:380px;height:330px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-add',buttons: '#dlg-buttons'">
        <form id="ffAddType" method="post" novalidate="novalidate">
                <table id="tblAddType"  class="view">
                    <tr>
                        <th>
                            <label for="name">类型名称：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" type="text" name="name" data-options="required:true,validType:'length[1,250]'" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="parentId">父节点：</label>
                        </th>
                        <td>
                            <select class="easyui-combotree" style="width:150px;" name="parentId" id="addDictParentIdTree"></select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="sort">排序：</label>
                        </th>
                        <td>
                            <input class="easyui-numberbox" type="text" name="sort" min="0" max="100" maxlength="3" precision="0"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="descr">备注：</label>
                        </th>
                        <td>
                           <textarea style="height:60px;width:200px" name="descr"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" style="text-align:right; padding-top:10px">
                            <a href="javascript:void(0)" class="easyui-linkbutton" id="btnAddOKType" iconcls="icon-ok" >确定</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivAddType').dialog('close')">关闭</a>
                        </td>
                    </tr>
                </table>
        </form>
    </div>

    <!--------------------------编辑字典大类信息的弹出层---------------------------->
    <div id="DivEditType" class="easyui-dialog" style="width:380px;height:330px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-edit',buttons: '#dlg-buttons'">
        <form id="ffEditType" method="post" novalidate="novalidate">
                <table id="tblEditType"  class="view">
                    <tr>
                        <th>
                            <label for="name">类型名称：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" type="text" name="name" data-options="required:true,validType:'length[1,250]'" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="parentId">父节点：</label>
                        </th>
                        <td>
                            <select class="easyui-combotree" style="width:150px;" name="parentId" id="editDictParentIdTree"></select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="sort">排序：</label>
                        </th>
                        <td>
                            <input class="easyui-numberbox" type="text" name="sort" min="0" max="100" maxlength="3" precision="0"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="descr">备注：</label>
                        </th>
                        <td>
                            <textarea style="height:60px;width:200px" name="descr"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" style="text-align:right; padding-top:10px">
                            <input type="hidden" name="dictId" />
                            <a href="javascript:void(0)" class="easyui-linkbutton" id="btnEditOKType" iconcls="icon-ok" >确定</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivEditType').dialog('close')">关闭</a>
                        </td>
                    </tr>
                </table>
        </form>
    </div>

    <!--------------------------批量添加字典信息的弹出层---------------------------->
    <div id="DivBatchAdd" class="easyui-dialog" style="width:685px;height:590px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-batch-add',buttons: '#dlg-buttons'">
        <form id="ffBatchAdd" method="post" novalidate="novalidate">
                <table id="tblAddBatch" class="view" style="width:98%">
                    <tr>
                        <th>
                            <label for="dictId">字典类别：</label>
                        </th>
                        <td>
                            <select class="easyui-combotree" style="width:150px;" name="dictId" id="batchDictTypeTree" data-options="required:true"></select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="sort">排序：</label>
                        </th>
                        <td>
                            <input class="easyui-numberbox" type="text" name="sort" />(数值开始或者字符作为前缀)
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="name">字典名称：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" type="text" name="name" data-options="required:true,validType:'length[1,50]'"/> <!-- data-options="required:true,validType:'length[1,50]'" -->
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label>字典数据：</label>
                        </th>
                        <td>
                            <textarea style="height:260px;width:500px" name="value" data-options="required:true" ></textarea>
                        </td>
                   </tr>
                    <tr>
                        <th>
                            <label>分割方式：</label>
                        </th>
                        <td>
                            <input name="splitType" type="radio" class="easyui-validatebox" checked="checked" required="true" value="split">分隔符方式，多个数据中英文逗号，分号，斜杠或顿号[, ， ; ； / 、]分开　
                            <br />
                            <input name="splitType" type="radio" class="easyui-validatebox" required="true" value="line">一行一个记录模式，忽略所有分隔符号
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="descr">描述：</label>
                        </th>
                        <td>
                            <textarea style="height:40px;width:500px" name="descr" ></textarea> <!-- data-options="required:true,validType:'length[1,50]'" -->
                        </td>
                    </tr> 
                    <tr>
                        <td colspan="2" style="text-align:right; padding-top:10px">
                            <a href="javascript:void(0)" class="easyui-linkbutton" id="btnBatchAddOK" iconcls="icon-ok" >确定</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivBatchAdd').dialog('close')">关闭</a>
                        </td>
                    </tr>
                </table>
        </form>
    </div>
    
    <!--------------------------添加字典信息的弹出层---------------------------->
    <div id="DivAdd" class="easyui-dialog" style="width:580px;height:270px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-add',buttons: '#dlg-buttons'">
        <form id="ffAdd" method="post" novalidate="novalidate">
                <table id="tblAdd"  class="view">
                    <tr>                        
                        <th>
                            <label for="dictId">字典类别：</label>
                        </th>
                        <td>
                        	<select class="easyui-combotree" style="width:150px;" name="dictId" id="addDictTypeTree" data-options="required:true"></select>
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
                            <label for="name">字典名称：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" type="text" name="name" data-options="required:true,validType:'length[1,50]'"/> <!-- data-options="required:true,validType:'length[1,50]'" -->
                        </td>
                        <th>
                            <label for="value">字典值：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" type="text" name="value" data-options="required:true,validType:'length[1,50]'"/> <!-- data-options="required:true,validType:'length[1,50]'" -->
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="descr">描述：</label>
                        </th>
                        <td colspan="3">
                            <textarea style="height:60px;width:300px" name="descr"></textarea>
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

    <!--------------------------编辑字典信息的弹出层---------------------------->
    <div id="DivEdit" class="easyui-dialog" style="width:580px;height:270px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-edit',buttons: '#dlg-buttons'">
        <form id="ffEdit" method="post" novalidate="novalidate">
                <table id="tblEdit" class="view">
                    <tr>
                        <th>
                            <label for="dictId">字典类别：</label>
                        </th>
                        <td>
                            <select class="easyui-combotree" style="width:150px;" name="dictId" id="editDictTypeTree" data-options="required:true"></select>
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
                            <label for="name">字典名称：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" type="text" name="name" />
                            <!--data-options="required:true,validType:'length[1,50]'"-->
                        </td>
                        <th>
                            <label for="value">字典值：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" type="text" name="value" />
                            <!--data-options="required:true,validType:'length[1,50]'"-->
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="descr">描述：</label>
                        </th>
                        <td colspan="3">
                            <textarea style="height: 60px; width: 300px" name="descr"></textarea>
                        </td>
                    </tr> 
                    <tr>
                        <td colspan="4" style="text-align:right; padding-top:10px">
                            <input type="hidden" name="dictItemId" />
                            <a href="javascript:void(0)" class="easyui-linkbutton" id="btnEditOK" iconcls="icon-ok" >确定</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivEdit').dialog('close')">关闭</a>
                        </td>
                    </tr>
                </table>
        </form>
    </div>

    <!--------------------------查看字典详细信息的弹出层---------------------------->
    <div id="DivView" class="easyui-dialog" style="width:580px;height:270px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-view',buttons: '#dlg-buttons'">
        <form id="ffView" method="post" novalidate="novalidate">
            <table id="tblView" class="view">
                <tr>
                    <th>
                        <label for="dictId">字典类别：</label>
                    </th>
                    <td>
                        <label name="dictId" />
                    </td>
                    <th>
                        <label for="sort">排序：</label>
                    </th>
                    <td>
                        <label name="sort" />
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="name">字典名称：</label>
                    </th>
                    <td>
                        <label name="name" />
                    </td>
                    <th>
                        <label for="value">字典值：</label>
                    </th>
                    <td>
                        <label name="value" />
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="descr">描述：</label>
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
                    <td colspan="4" style="text-align: right; padding-top: 10px">
                        <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivView').dialog('close')">关闭</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
