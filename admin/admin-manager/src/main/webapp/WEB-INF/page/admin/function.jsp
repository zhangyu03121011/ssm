<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>企业信息化平台管理系统</title>
    <%@include file="../common/common.jsp"%>
    <script src="resources/js/admin/function.js?r=${jsVersion }"></script>
</head>
<body>
    <div id="loading" style="display: none;"><img alt="数据正在加载中..." src="resources/images/loading02.gif" /></div>
    <table>
        <tr>
            <td valign="top">
            
            	<!--------------------------功能树列表---------------------------->
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
                                       	功能列表
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
                                        <a href="#" class="easyui-linkbutton" iconcls="icon-add" id="A1" onclick="addData()" >添加</a>
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
                                        <span>功能详细信息</span>
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
                        	
                        	<!--------------------------添加功能---------------------------->
                        	<form id="addFunctionForm" method="post" novalidate="novalidate">
	                            <table id="tbFunctionInfo" width="100%">
	                                <tr>
	                                    <td align="left" width="95" style="width: 95px">
	                                       	 编号(<font color="red">*</font>)：
	                                    </td>
	                                    <td align="left">
	                                        <input name="functionId" style="width: 100%;background-color:#F0EAEA;" readonly="readonly" type="text" >
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td align="left" width="95" style="width: 95px">
	                                       	名称(<font color="red">*</font>)：
	                                    </td>
	                                    <td align="left">
	                                        <input name="functionName" style="width: 100%" type="text" class="easyui-validatebox" data-options="required:true,validType:'length[1,250]'">
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td align="left" width="95" style="width: 95px">
	                                       	 上层功能(<font color="red">*</font>)：
	                                    </td>
	                                    <td align="left">
	                                        <select class="easyui-combotree" style="width:180px;" name="parentId" id="addFunctionParentIdTree"></select>
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td align="left" width="95" style="width: 95px">
	                                       	功能控件ID(<font color="red">*</font>)：
	                                    </td>
	                                    <td align="left">
	                                        <input name="controlKey" style="width: 100%" type="text" class="easyui-validatebox" data-options="required:true,validType:'length[1,250]'">
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
	                                <tr>
	                                    <td align="left" width="95" style="width: 95px">
	                                    </td>
	                                    <td align="right">
	                                        <a href="#" class="easyui-linkbutton" iconcls="icon-ok" onclick="saveData()">保存</a>
	                                    </td>
	                                </tr>
	                            </table>
                            </form>
                            
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    
</body>
</html>