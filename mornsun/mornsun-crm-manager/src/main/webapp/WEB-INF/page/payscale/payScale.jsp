﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width" />
    <title>品牌列表</title>
 	<%@include file="../common/common.jsp"%>
    <script src="resources/js/payscale/payScale.js?r=${jsVersion }" type="text/javascript"></script>
    <link href="resources/css/payscale/payScale.css?r=${jsVersion }" rel="stylesheet" type="text/css" />
</head>
<body>

    <div id="loading" style="display: none;"><img alt="数据正在加载中..." src="resources/images/loading02.gif" /></div>
    <div class="easyui-layout" id="tb" style="padding:5px;height:auto">
        <!-------------------------------搜索框----------------------------------->
        <fieldset>
            <legend>信息查询</legend>
            <form id="ffSearch" method="post">
		        <div style="margin-bottom:5px">
					<label for="payType">分成类别：</label>
					<select class="easyui-combobox" name="payType" style="width:80px;">     
						<option value="1">秒懂</option> 
						<option value="2">问答</option>  
					</select>&nbsp;&nbsp;&nbsp;
					<label for="scaleType">分成用户：</label>
					<select class="easyui-combobox" name="scaleType" style="width:80px;">   
						<option value="1">平台</option>   
						<option value="2">发布方</option> 
						<option value="3">提问方</option> 
						<option value="4">分享方</option> 
						<option value="5">回答方</option> 					
					</select>&nbsp;&nbsp;&nbsp;
                     <a href="#" class="easyui-linkbutton" iconcls="icon-search" id="btnSearch">查询</a>
                </div>

            </form>
        </fieldset>
                
        <!-------------------------------详细信息展示表格----------------------------------->
        <table id="grid" style="width: 940px" title="用户操作" iconcls="icon-view" >
        </table>
    </div>
    
	<!--------------------------添加信息的弹出层---------------------------->
    <div id="DivAdd" class="easyui-dialog" style="width:650px;height:350px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-add',buttons: '#dlg-buttons'">
        <form id="ffAdd" method="post" novalidate="novalidate">
                <table id="tblAdd" class="view">
                    <tr>
                        <th>
                            <label for="payType">分成类别：</label>
                        </th>
                        <td colspan="3">
							<select class="easyui-combobox" name="payType" style="width:300px;">     
								<option value="1">秒懂</option> 
								<option value="2">问答</option>  
							</select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="scaleType">分成用户：</label>
                        </th>
                        <td colspan="3">
							<select class="easyui-combobox" name="scaleType" style="width:300px;">   
								<option value="1">平台</option>   
								<option value="2">发布方</option> 
								<option value="3">提问方</option> 
								<option value="4">分享方</option> 
								<option value="5">回答方</option> 					
							</select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="payScale">分成比例：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入分成比例"  style="width: 465px;" type="text" name="payScale" maxlength="2" data-options="required:true,validType:'integer'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="descr">描述：</label>
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
    <div id="DivEdit" class="easyui-dialog" style="width:650px;height:350px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-edit',buttons: '#dlg-buttons'">
        <form id="ffEdit" method="post" novalidate="novalidate">
                <table id="tblEdit" class="view">
                    <tr>
                        <th>
                            <label for="payType">分成类别：</label>
                        </th>
                        <td colspan="3">
							<select class="easyui-combobox" name="payType" style="width:300px;">     
								<option value="1">秒懂</option> 
								<option value="2">问答</option>  
							</select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="scaleType">分成用户：</label>
                        </th>
                        <td colspan="3">
							<select class="easyui-combobox" name="scaleType" style="width:300px;">   
								<option value="1">平台</option>   
								<option value="2">发布方</option> 
								<option value="3">提问方</option> 
								<option value="4">分享方</option> 
								<option value="5">回答方</option> 					
							</select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="payScale">分成比例：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入分成比例"  style="width: 465px;" type="text" name="payScale" maxlength="2" data-options="required:true,validType:'integer'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="isavailable">是否有效：</label>
                        </th>
                        <td>
                            <select class="easyui-combobox" name="isavailable" style="width:150px;">   
							    <option value="1">有效</option>   
							    <option value="0">无效</option>   
							</select>
                        </td>
                        <th>
                            <label for="state">状态：</label>
                        </th>
                        <td>
                            <select class="easyui-combobox" name="state" style="width:150px;">   
							    <option value="1">通过</option>   
							    <option value="2">不通过</option>   
							</select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="descr">描述：</label>
                        </th>
                        <td colspan="3">
                            <textarea rows="4" cols="63" name="descr" class="textarea easyui-validatebox"></textarea>
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

    <!--------------------------查看详细信息的弹出层---------------------------->
    <div id="DivView" class="easyui-dialog" style="width:600px;height:300px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-view',buttons: '#dlg-buttons'">
        <form id="ffView" method="post" novalidate="novalidate">
                <table id="tblView" class="view">
                    <tr>
                        <th>
                            <label for="payType">分成类别：</label>
                        </th>
                        <td colspan="3">
                            <label name="payType" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="scaleType">分成用户：</label>
                        </th>
                        <td colspan="3">
                            <label name="scaleType" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="payScale">分成比例：</label>
                        </th>
                        <td colspan="3">
                            <label name="payScale" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="isavailable">是否有效：</label>
                        </th>
                        <td>
                            <label name="isavailable" />
                        </td>
                        <th>
                            <label for="state">状态：</label>
                        </th>
                        <td>
                            <label name="state" />
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
                            <label for="descr">描述：</label>
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