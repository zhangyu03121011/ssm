<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width" />
    <title>标签列表</title>
 	<%@include file="../common/common.jsp"%>
    <script src="resources/js/member/member.js?r=${jsVersion }" type="text/javascript"></script>
    <link href="resources/css/member/member.css?r=${jsVersion }" rel="stylesheet" type="text/css" />
</head>
<body>

    <div id="loading" style="display: none;"><img alt="数据正在加载中..." src="resources/images/loading02.gif" /></div>
    <div class="easyui-layout" id="tb" style="padding:5px;height:auto">
        <!-------------------------------搜索框----------------------------------->
        <fieldset>
            <legend>信息查询</legend>
            <form id="ffSearch" method="post">
		        <div style="margin-bottom:5px">
                    <label for="userName">用户账号：</label>
                    <input type="text" name="userName" style="width:100px"  />&nbsp;&nbsp;&nbsp;
                    <label for="userAlias">昵称：</label>
                    <input type="text" name="userAlias" style="width:100px"  />&nbsp;&nbsp;&nbsp;
                    <label for="userType">用户类别：</label>
                    <select class="easyui-combobox" name="userType" style="width:150px;">   
					    <option value="1">普通用户</option>   
					    <option value="2">专家用户</option>  
					    <option value="3">校长用户</option>
					</select>
                    <label for="authType">授权类别：</label>
                    <select class="easyui-combobox" name="authType" style="width:150px;">   
					    <option value="1">Android</option>   
					    <option value="2">IOS</option>   
					</select>
                    <label for="isEmployee">是否内部员工：</label>
                    <select class="easyui-combobox" name="isEmployee" style="width:150px;">   
					    <option value="1">是</option>   
					    <option value="0">否</option>   
					</select>
                     <a href="#" class="easyui-linkbutton" iconcls="icon-search" id="btnSearch">查询</a>
                </div>
                
            </form>
        </fieldset>
                
        <!-------------------------------详细信息展示表格----------------------------------->
        <table id="grid" style="width: 940px" title="用户操作" iconcls="icon-view" >
        </table>
    </div>
    
    <!--------------------------编辑信息的弹出层---------------------------->
    <div id="DivEdit" class="easyui-dialog" style="width:650px;height:380px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-edit',buttons: '#dlg-buttons'">
        <form id="ffEdit" method="post" novalidate="novalidate">
                <table id="tblEdit" class="view">
                    <tr>
                        <th>
                            <label for="userName">用户账号：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入用户账号"  style="width: 465px;" type="text" name="userName" data-options="required:true,validType:'length[1,50]'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="userAlias">昵称：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入昵称"  style="width: 465px;" type="text" name="userAlias" data-options="required:true,validType:'length[1,50]'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="userType">用户类别：</label>
                        </th>
                        <td colspan="3">
                            <select class="easyui-combobox" name="userType" style="width:150px;">   
							    <option value="1">普通用户</option>   
							    <option value="2">专家用户</option>  
							    <option value="3">校长用户</option>  
							</select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="isPushMessage">是否推送消息：</label>
                        </th>
                        <td colspan="3">
                            <select class="easyui-combobox" name="isPushMessage" style="width:150px;">   
							    <option value="1">推送</option>   
							    <option value="0">不推送</option>   
							</select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="isavailable">是否有效：</label>
                        </th>
                        <td colspan="3">
                            <select class="easyui-combobox" name="isavailable" style="width:150px;">   
							    <option value="1">有效</option>   
							    <option value="0">无效</option>   
							</select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="intro">简介：</label>
                        </th>
                        <td colspan="3">
                            <textarea rows="4" cols="63" name="intro" class="textarea easyui-validatebox"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" style="text-align:right; padding-top:10px">
                        	<input type="hidden" name="id" />
                            <a href="javascript:void(0)" class="easyui-linkbutton" id="btnEditOK" iconcls="icon-ok" >确定</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivEdit').dialog('close')">关闭</a>
                        </td>
                    </tr>
                </table>
        </form>
    </div>
    
    <!--------------------------编辑内部员工信息信息的弹出层---------------------------->
    <div id="DivSet" class="easyui-dialog" style="width:650px;height:380px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-edit',buttons: '#dlg-buttons'">
        <form id="ffEditSet" method="post" novalidate="novalidate">
                <table id="tblEdit" class="view">
                    <tr>
                        <th>
                            <label for="userName">用户账号：</label>
                        </th>
                        <td colspan="3">
                            <label name="userName" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="userAlias">昵称：</label>
                        </th>
                        <td colspan="3">
                            <label name="userAlias" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="employeeName">员工姓名：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入员工姓名"  style="width: 465px;" type="text" name="employeeName" data-options="required:true,validType:'length[1,50]'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="employeeNo">员工编号：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入员工编号"  style="width: 465px;" type="text" name="employeeNo" data-options="required:true,validType:'length[1,50]'"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" style="text-align:right; padding-top:10px">
                        	<input type="hidden" name="userId" />
                        	<input type="hidden" name="id" />
                            <a href="javascript:void(0)" class="easyui-linkbutton" id="btnEditEmployeeOK" iconcls="icon-ok" >确定</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivSet').dialog('close')">关闭</a>
                        </td>
                    </tr>
                </table>
        </form>
    </div>
    
    <!--------------------------查看详细信息的弹出层---------------------------->
    <div id="DivView" class="easyui-dialog" style="width:600px;height:450px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-view',buttons: '#dlg-buttons'">
        <form id="ffView" method="post" novalidate="novalidate">
                <table id="tblView" class="view">
                    <tr>
                        <th>
                            <label for="userName">用户账号：</label>
                        </th>
                        <td colspan="3">
                            <label name="userName" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="userAlias">昵称：</label>
                        </th>
                        <td colspan="3">
                            <label name="userAlias" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="headImage">头像：</label>
                        </th>
                        <td>
                            <a class="menuIconClass" id="headImageView" href="#" target="_blank">查看</a>
                        </td>
                        <th>
                            <label for="mobile">手机：</label>
                        </th>
                        <td>
                            <label name="mobile" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="positionName">头衔：</label>
                        </th>
                        <td>
                            <label name="positionName" />
                        </td>
                        <th>
                            <label for="userType">用户类别：</label>
                        </th>
                        <td>
                            <label name="userType" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="authType">授权类别：</label>
                        </th>
                        <td>
                            <label name="authType" />
                        </td>
                        <th>
                            <label for="isPushMessage">是否推送消息：</label>
                        </th>
                        <td>
                            <label name="isPushMessage" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="isEmployeeInfo">是否内部员工：</label>
                        </th>
                        <td colspan="3">
                            <label name="isEmployeeInfo" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="employeeName">内部员工姓名：</label>
                        </th>
                        <td>
                            <label name="employeeName" />
                        </td>
                        <th>
                            <label for="employeeNo">内部员工工号：</label>
                        </th>
                        <td>
                            <label name="employeeNo" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="state">状态：</label>
                        </th>
                        <td>
                            <label name="state" />
                        </td>
                        <th>
                            <label for="isavailable">是否有效：</label>
                        </th>
                        <td>
                            <label name="isavailable" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="createTime">创建时间：</label>
                        </th>
                        <td>
                            <label name="createTime" />
                        </td>
                        <th>
                            <label for="updateTime">更新时间 ：</label>
                        </th>
                        <td>
                            <label name="updateTime" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="intro">简介：</label>
                        </th>
                        <td colspan="3">
                            <label name="intro" />
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