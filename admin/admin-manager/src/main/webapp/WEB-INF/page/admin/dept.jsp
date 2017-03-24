<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>企业信息化平台管理系统</title>
    <%@include file="../common/common.jsp"%>
    <script src="resources/js/admin/dept.js?r=${jsVersion }"></script>
</head>
<body>
    <div id="loading" style="display: none;"><img alt="数据正在加载中..." src="resources/images/loading02.gif" /></div> 
    <table>
        <tr>
            <td valign="top">
            
            	<!--------------------------部门树列表---------------------------->
                <table height="700px" width="300px" align="left" style="table-layout: fixed">
                    <tr align="center" valign="top" height="25">
                        <td align="center">
                            <table cellspacing="0" cellpadding="0" width="100%" background="resources/css/themes/Default/droit-25.jpg"
                                border="0">
                                <tr>
                                    <td align="left" width="10%">
                                        <img height="25" src="resources/css/themes/Default/droit-24.jpg">
                                    </td>
                                    <td align="center" width="30%">
                                       	部门列表
                                    </td>
                                    <td align="right" width="10%">
                                        <img height="25" src="resources/css/themes/Default/droit-26.jpg">
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table border="0" width="100%">
                                <tr>
                                    <td nowrap>
                                        <img onclick="reloadTree();" style="cursor: pointer;" border="0" align="middle" src="resources/images/menu_refresh.gif" alt="刷新窗口">
                                        <a id="expandAllBtn" href="#" title="展开所有" onclick="return false;">展开</a> 
                                        <a id="collapseAllBtn" href="#" title="关闭所有" onclick="return false;">折叠</a>&nbsp;&nbsp;
                                        <a href="#" class="easyui-linkbutton" iconcls="icon-remove" id="btnDelete" onclick="delData()" >删除</a>
                                        <a href="#" class="easyui-linkbutton" iconcls="icon-add" id="btnAdd" onclick="addData()">添加</a>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td valign="top" align="left" width="100%" height="600px" style="background-color: #ffffff">
                            <div>
                                <ul id="meibaTree" class="ztree">
                                </ul>
                            </div>
                        </td>
                    </tr>
                </table>
                
            </td>
            <td valign="top">
                <table height="700px" width="300px" align="left" style="table-layout: fixed">
                    <tr>
                        <td>
                            <table cellspacing="0" cellpadding="0" width="100%" background="resources/css/themes/Default/droit-25.jpg"
                                border="0">
                                <tr>
                                    <td align="left" width="10%">
                                        <img height="25" src="resources/css/themes/Default/droit-24.jpg" width="90">
                                    </td>
                                    <td align="center" width="30%">
                                        <span>部门详细信息</span>
                                    </td>
                                    <td align="right" width="10%">
                                        <img height="25" src="resources/css/themes/Default/droit-26.jpg" width="85">
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        
                        	<!--------------------------添加部门---------------------------->
                        	<form id="addDeptForm" method="post" novalidate="novalidate">
	                            <table id="tbGroupInfo" height="200" width="100%">
	                                <tr height="10%">
	                                    <td align="left" style="width: 95px">
	                                       	编号(<font color="red">*</font>)：
	                                    </td>
	                                    <td align="left">
	                                        <input type="text" name="deptId"  style="width: 100%;background-color:#F0EAEA;" readonly="readonly">
	                                    </td>
	                                </tr>
	                                <tr height="10%">
	                                    <td align="left" style="width: 95px">
	                                       	上级机构(<font color="red">*</font>)：
	                                    </td>
	                                    <td align="left">
	                                        <select class="easyui-combotree" style="width:180px;" name="parentId" id="addDeptParentIdTree"></select>
	                                    </td>
	                                </tr>
	                                <tr height="10%">
	                                    <td width="95" align="left" style="width: 95px">
	                                       	 机构名(<font color="red">*</font>)：
	                                    </td>
	                                    <td align="left">
	                                        <input name="deptName" class="easyui-validatebox" style="width: 100%" type="text" data-options="required:true,validType:'length[1,250]'">
	                                    </td>
	                                </tr>
	                                <tr height="10%">
	                                    <td width="95" align="left" style="width: 95px">
	                                       	机构地址：
	                                    </td>
	                                    <td align="left">
	                                        <input name="address" class="easyui-validatebox" style="width: 100%" type="text" maxlength="200">
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td align="left" style="width: 95px">
	                                       	 备注：
	                                    </td>
	                                    <td align="left">
	                                        <textarea name="descr" style="width: 100%; height: 50px" ></textarea>
	                                    </td>
	                                </tr>
	                                <tr height="10%">
	                                    <td style="width: 95px">
	                                    </td>
	                                    <td align="right">
	                                        <a href="#" class="easyui-linkbutton" iconcls="icon-ok" id="btnSave" onclick="saveData()">保存</a>
	                                    </td>
	                                </tr>
	                            </table>
                            </form>
                            
                        </td>
                    </tr>
                </table>
            </td>
            <td valign="top">
                <table height="700px" width="200px">
                    <tr>
                        <td>
                        
                        	<!--------------------------部门用户列表---------------------------->
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td width="17%" align="left" background="resources/css/themes/Default/droit-42.jpg">
                                        <img src="resources/css/themes/Default/droit-41.jpg" width="50" height="31">
                                    </td>
                                    <td width="69%" background="resources/css/themes/Default/droit-42.jpg">
                                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                            <tr>
                                                <td width="5%">
                                                    <img src="resources/css/themes/Default/droit-42.jpg" width="11" height="31">
                                                </td>
                                                <td width="95%" align="center">
                                                    <span>包含用户</span>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td width="14%" align="right" background="resources/css/themes/Default/droit-42.jpg">
                                        <img src="resources/css/themes/Default/droit-43.jpg" width="42" height="31">
                                    </td>
                                </tr>
                            </table>
                            
                        </td>
                    </tr>
                    <tr height="30%">
                        <td>
                            <select id="deptUserSelect" multiple  style="height: 300px; width: 200px" />
                        </td>
                    </tr>
                    <tr align="right">
                        <td>
                            <a href="#" class="easyui-linkbutton" iconcls="icon-edit" onclick="ShowEditTree('user')" >编辑</a>&nbsp;&nbsp;
                            <a href="#" class="easyui-linkbutton" iconcls="icon-remove" onclick="deleteRelation('user')" >移除</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        
                        	<!--------------------------部门角色列表---------------------------->
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td width="17%" align="left" background="resources/css/themes/Default/droit-42.jpg">
                                        <img src="resources/css/themes/Default/droit-41.jpg" width="50" height="31">
                                    </td>
                                    <td width="69%" background="resources/css/themes/Default/droit-42.jpg">
                                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                            <tr>
                                                <td width="5%">
                                                    <img src="resources/css/themes/Default/droit-42.jpg" width="11" height="31">
                                                </td>
                                                <td width="95%" align="center">
                                                    <span>所属角色</span>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td width="14%" align="right" background="resources/css/themes/Default/droit-42.jpg">
                                        <img src="resources/css/themes/Default/droit-43.jpg" width="42" height="31">
                                    </td>
                                </tr>
                            </table>
                            
                        </td>
                    </tr>
                    <tr height="70%">
                        <td>
                            <select id="deptRoleSelect" multiple  style="height: 250px; width: 200px" />
                        </td>
                    </tr>
                    <tr align="right">
                        <td>
                            <a href="#" class="easyui-linkbutton" iconcls="icon-edit" onclick="ShowEditTree('role')" >编辑</a>&nbsp;&nbsp;
                            <a href="#" class="easyui-linkbutton" iconcls="icon-remove" onclick="deleteRelation('role')" >移除</a>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>

    <!--------------------------编辑组织机构包含用户的弹出层---------------------------->
    <div id="DivEditUser" class="easyui-dialog" style="width:380px;height:530px;padding:10px 20px"
			closed="true" resizable="true" modal="true" buttons="#dlg-buttons">
        <form id="deptUserForm" method="post" novalidate="novalidate">
            <fieldset>
                <legend>部门包含的用户</legend>
                <table id="tblEdit">
                    <tr>
                        <td>
                            <table border="0" width="100%">
                                <tr>
                                    <td nowrap>
                                        <img onclick="reloadEditTree('user');" style="cursor: pointer;" border="0" align="middle" src="resources/images/menu_refresh.gif" alt="刷新窗口">
                                        <input type="checkbox" onclick="CheckAll(this, 'user')">全选&nbsp;&nbsp; 
                                        <a href="#" class="easyui-linkbutton" iconcls="icon-ok" onclick="saveEditData('user')" >保存</a>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td valign="top" align="left" width="100%" height="400px" style="background-color: #ffffff">
                            <div>
                                <ul id="userTree" class="ztree">
                                </ul>
                            </div>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div>
    
            <!--------------------------编辑角色包含机构的弹出层---------------------------->
    <div id="DivEditRole" class="easyui-dialog" style="width:380px;height:530px;padding:10px 20px"
			closed="true" resizable="true" modal="true" buttons="#dlg-buttons">
        <form id="deptRoleForm" method="post" novalidate="novalidate">
            <fieldset>
                <legend></legend>
                <table id="tblEditOU">
                    <tr>
                        <td>
                            <table border="0" width="100%">
                                <tr>
                                    <td nowrap>
                                        <img onclick="reloadEditTree('role');" style="cursor: pointer;" border="0" align="middle" src="resources/images/menu_refresh.gif" alt="刷新窗口">
                                        <input type="checkbox" onclick="CheckAll(this, 'role')">全选&nbsp;&nbsp; 
                                        <a href="#" class="easyui-linkbutton" iconcls="icon-ok" onclick="saveEditData('role')" >保存</a>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td valign="top" align="left" width="100%" height="400px" style="background-color: #ffffff">
                            <div>
                                <ul id="roleTree" class="ztree">
                                </ul>
                            </div>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div>
    
</body>
</html>