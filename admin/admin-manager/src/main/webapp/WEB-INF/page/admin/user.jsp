<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>企业信息化平台管理系统</title>
    <%@include file="../common/common.jsp"%>
    <script src="resources/js/admin/user.js?r=${jsVersion }"></script>
</head>
<body>
     <div id="loading" style="display: none;"><img alt="数据正在加载中..." src="resources/images/loading02.gif" /></div> 
        <table>
            <tr>
                <td valign="top">
                
                	<!--------------------------用户树列表---------------------------->
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
                                           	用户列表
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
                                            <a href="#" class="easyui-linkbutton" iconcls="icon-remove" id="A2" onclick="delData()" >删除</a>
                                            <a href="#" class="easyui-linkbutton" iconcls="icon-add" id="A1" onclick="addData()">添加</a>
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
                
                	<!--------------------------添加用户---------------------------->
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
                                            <span>用户详细信息</span>
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
                            
                            	<form id="addUserForm" method="post" novalidate="novalidate">
	                                <table id="tbRoleInfo" width="100%">
	                                    <tr height="10%">
	                                        <td align="left" style="width: 95px">
	                                           	用户编号(<font color="red">*</font>)：
	                                        </td>
	                                        <td align="left">
	                                            <input name="userId" style="width:100%;background-color:#F0EAEA;" readonly="readonly" type="text" >
	                                        </td>
	                                    </tr>
	                                    <tr height="10%">
	                                        <td align="left" style="width: 95px">
	                                           	用户名(<font color="red">*</font>)：
	                                        </td>
	                                        <td align="left" style="width: 195px">
	                                            <input name="userName" style="width: 100%" type="text" class="easyui-validatebox" data-options="required:true,validType:'length[1,50]'">
	                                        </td>
	                                    </tr>
	                                    <tr height="10%">
	                                        <td align="left" style="width: 95px">
	                                           	全名(<font color="red">*</font>)：
	                                        </td>
	                                        <td align="left">
	                                            <input name="fullName" style="width: 100%" type="text" class="easyui-validatebox" data-options="required:true,validType:['length[1,50]']">
	                                        </td>
	                                    </tr>
	                                    <tr height="10%">
	                                        <td align="left" style="width: 95px">
	                                           	默认机构/部门(<font color="red">*</font>)：
	                                        </td>
	                                        <td align="left">
	                                            <select class="easyui-combotree" style="width:180px;" name="deptId" id="addDeptParentIdTree" data-options="required:true"></select>
	                                        </td>
	                                    </tr>
	                                    <tr height="10%">
	                                        <td align="left" style="width: 95px">
	                                           	 密码(<font color="red">*</font>)：
	                                        </td>
	                                        <td align="left">
	                                            <input id="password" name="password" style="width: 100%" type="password" class="easyui-validatebox" data-options="required:true,validType:'length[6,50]'">
	                                        </td>
	                                    </tr>
	                                    <tr height="10%">
	                                        <td align="left" style="width: 95px">
	                                           	确认密码(<font color="red">*</font>)：
	                                        </td>
	                                        <td align="left">
	                                            <input name="surePassword" style="width: 100%" type="password" class="easyui-validatebox" required="true" validType="same['password']">
	                                        </td>
	                                    </tr>
	                                    <tr height="10%">
	                                        <td align="left" style="width: 95px">
	                                       		 性别：
	                                        </td>
	                                        <td align="left">
	                                            <input name="sex" type="radio" value="1">男
	                                            <input name="sex" type="radio" value="0">女
	                                        </td>
	                                    </tr>
	                                    <tr height="10%">
	                                        <td align="left" style="width: 95px">
	                                       		 生日：
	                                        </td>
	                                        <td align="left">
	                                            <input name="birthday" style="width: 100%" type="text" class="easyui-datebox" data-options="validType:'date'">
	                                        </td>
	                                    </tr>
	                                    <tr height="10%">
	                                        <td align="left" style="width: 95px">
	                                           	移动电话：
	                                        </td>
	                                        <td align="left">
	                                            <input name="mobile" style="width: 100%" type="text"  maxlength="100" class="easyui-numberbox" data-options="validType:'moblie'">
	                                        </td>
	                                    </tr>
	                                    <tr height="10%">
	                                        <td align="left" style="width: 95px">
	                                       		 办公电话：
	                                        </td>
	                                        <td align="left">
	                                            <input name="phone" style="width: 100%" type="text"  maxlength="100" class="easyui-validatebox" data-options="validType:['phone','length[5,20]']">
	                                        </td>
	                                    </tr>
	                                    <tr height="10%">
	                                        <td align="left" style="width: 95px">
	                                       		QQ：
	                                        </td>
	                                        <td align="left">
	                                            <input name="qq" style="width: 100%" type="text"  maxlength="100" class="easyui-numberbox" data-options="validType:['qq','length[5,15]']">
	                                        </td>
	                                    </tr>
	                                    <tr height="10%">
	                                        <td align="left" style="width: 95px">
	                                           	电子邮箱：
	                                        </td>
	                                        <td align="left">
	                                            <input name="email" style="width: 100%" type="text" maxlength="100" class="easyui-validatebox" data-options="validType:['email','length[1,50]']">
	                                        </td>
	                                    </tr>
	                                    <tr height="10%">
	                                        <td style="width: 70px; height: 9.61%" align="left" width="70">
	                                           	住址：
	                                        </td>
	                                        <td style="height: 9.61%" align="left">
	                                            <input name="address" style="width: 100%" type="text" maxlength="200" class="easyui-validatebox">
	                                        </td>
	                                    </tr>
	                                    <tr align="left" height="10%">
	                                        <td style="width: 70px">
	                                        </td>
	                                        <td align="right">
	                                            <a href="#" class="easyui-linkbutton" iconcls="icon-ok" id="btnResetPassword" onclick="resetPassword()">重置密码</a>
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
                    <table height="700px" width="200px" align="left">
                        <tr>
                            <td>
                            
                            	<!--------------------------用户应用列表---------------------------->
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
                                                        <span>所属应用</span>
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
                        <tr height="300px">
                            <td>
                                <select id="userAppSelect" multiple  style="height: 300px; width: 200px" />
                            </td>
                        </tr>
	                     <tr align="right">
	                        <td>
	                            <a href="#" class="easyui-linkbutton" iconcls="icon-edit" onclick="ShowEditTree('app')" >编辑</a>&nbsp;&nbsp;
	                            <a href="#" class="easyui-linkbutton" iconcls="icon-remove" onclick="deleteRelation('app')" >移除</a>
	                        </td>
	                    </tr>
                        <tr>
                            <td>
                            
                            	<!--------------------------用户角色列表---------------------------->
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
                        <tr height="300px">
                            <td>
                                <select id="userRoleSelect" multiple  style="height: 300px; width: 200px" />
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
        
       <!--------------------------编辑用户包含应用的弹出层---------------------------->
    <div id="DivEditApp" class="easyui-dialog" style="width:380px;height:530px;padding:10px 20px"
			closed="true" resizable="true" modal="true" buttons="#dlg-buttons">
        <form id="roleAppForm" method="post" novalidate="novalidate">
            <fieldset>
                <legend></legend>
                <table id="tblEditApp">
                    <tr>
                        <td>
                            <table border="0" width="100%">
                                <tr>
                                    <td nowrap>
                                        <img onclick="reloadEditTree('app');" style="cursor: pointer;" border="0" align="middle" src="resources/images/menu_refresh.gif" alt="刷新窗口">
                                        <input type="checkbox" onclick="CheckAll(this, 'app')">全选&nbsp;&nbsp; 
                                        <a href="#" class="easyui-linkbutton" iconcls="icon-ok" onclick="saveEditData('app')" >保存</a>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td valign="top" align="left" width="100%" height="400px" style="background-color: #ffffff">
                            <div>
                                <ul id="appTree" class="ztree">
                                </ul>
                            </div>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div>
    
           <!--------------------------编辑用户包含角色的弹出层---------------------------->
    <div id="DivEditRole" class="easyui-dialog" style="width:380px;height:530px;padding:10px 20px"
			closed="true" resizable="true" modal="true" buttons="#dlg-buttons">
        <form id="userRoleForm" method="post" novalidate="novalidate">
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
