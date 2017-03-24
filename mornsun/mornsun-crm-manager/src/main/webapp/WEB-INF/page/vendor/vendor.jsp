<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width" />
    <title>品牌列表</title>
 	<%@include file="../common/common.jsp"%>
    <script src="resources/js/vendor/vendor.js?r=${jsVersion }" type="text/javascript"></script>
    <script src="resources/js/common/uuid.js?r=${jsVersion }" type="text/javascript"></script>
    <link href="resources/css/vendor/vendor.css?r=${jsVersion }" rel="stylesheet" type="text/css" />
</head>
<body>

    <div id="loading" style="display: none;"><img alt="数据正在加载中..." src="resources/images/loading02.gif" /></div>
    <div class="easyui-layout" id="tb" style="padding:5px;height:auto">
        <!-------------------------------搜索框----------------------------------->
        <fieldset>
            <legend>信息查询</legend>
            <form id="ffSearch" method="post">
		        <div style="margin-bottom:5px">
                    <label for="companyVo.companyName">企业名称：</label>
                    <input type="text" name="companyVo.companyName" style="width:100px"  />&nbsp;&nbsp;&nbsp;
                    <label for="companyVo.companyBrand">企业品牌：</label>
                    <input type="text" name="companyVo.companyBrand" style="width:100px"  />&nbsp;&nbsp;&nbsp;
                    <label for="productNo">产品型号：</label>
                    <input type="text" name="productNo" style="width:100px"  />&nbsp;&nbsp;&nbsp;
                    <label for="title">标题：</label>
                    <input type="text" name="title" style="width:100px"  />&nbsp;&nbsp;&nbsp;
					<label for="courseType">教程类别：</label>
					<select class="easyui-combobox" name="courseType" style="width:80px;">    
						<option value="1">文字</option> 
						<option value="2">声音</option> 
						<option value="3">视频</option> 
						<option value="4">图片</option> 					
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
    <div id="DivAdd" class="easyui-dialog" style="width:650px;height:400px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-add',buttons: '#dlg-buttons'">
        <form id="ffAdd" method="post" novalidate="novalidate">
                <table id="tblAdd" class="view">
                    <tr>
                        <th>
                            <label for="companyId">企业名称：</label>
                        </th>
                        <td colspan="3">
                            <select class="easyui-combotree" style="width:300px;" name="companyId" id="addCompnayTree"></select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="productNo">产品型号：</label>
                        </th>
                        <td colspan="3">
                        	<input class="easyui-validatebox" placeholder="产品型号"  style="width: 465px;" type="text" name="productNo" data-options="required:true,validType:'length[1,50]'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="productId">产品型号(关联选择)：</label>
                        </th>
                        <td colspan="3">
                            <select class="easyui-combotree" style="width:300px;" name="productId" id="addProductTree" ></select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="title">标题：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入标题"  style="width: 465px;" type="text" name="title" data-options="required:true,validType:'length[1,50]'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="payMoney">支付金额：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入支付金额"  style="width: 465px;" type="text" name="payMoney" data-options="required:true,validType:'number'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="courseType">教程类别：</label>
                        </th>
                        <td colspan="3">
							<select class="easyui-combobox" name="courseType" style="width:80px;">    
								<option value="1">文字</option> 
								<option value="2">声音</option> 
								<option value="3">视频</option> 
								<option value="4">图片</option> 					
							</select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="atta">附件：</label>
                        </th>
                        <td colspan="3">
							<div>
                                <input type="hidden" name="id" />
                                <input id="file_uploadAdd" type="file" multiple="multiple">
                                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" onclick="javascript: $('#file_uploadAdd').uploadify('upload', '*')">上传</a>
                                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="javascript: $('#file_uploadAdd').uploadify('cancel', '*')">取消</a>
                                <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-view'" target="_blank" name="attaView" style="display: none;">预览</a>
                                <div id="fileQueueAdd" class="fileQueue"></div>
                                <div id="div_filesAdd"></div>
                            </div>
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
    <div id="DivEdit" class="easyui-dialog" style="width:650px;height:470px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-edit',buttons: '#dlg-buttons'">
        <form id="ffEdit" method="post" novalidate="novalidate">
                <table id="tblEdit" class="view">
                    <tr>
                        <th>
                            <label for="companyId">企业名称：</label>
                        </th>
                        <td colspan="3">
                            <select class="easyui-combotree" style="width:300px;" name="companyId" id="editCompnayTree"></select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="productId">产品型号：</label>
                        </th>
                        <td colspan="3">
                        	<input class="easyui-validatebox" placeholder="产品型号"  style="width: 465px;" type="text" name="productNo" data-options="required:true,validType:'length[1,50]'"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <th>
                            <label for="productId">产品型号（关联选择）：</label>
                        </th>
                        <td colspan="3">
                            <select class="easyui-combotree" style="width:300px;" name="productId" id="editProductTree"></select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="title">标题：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入标题"  style="width: 465px;" type="text" name="title" data-options="required:true,validType:'length[1,50]'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="payMoney">支付金额：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入支付金额"  style="width: 465px;" type="text" name="payMoney" data-options="required:true,validType:'number'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="courseType">教程类别：</label>
                        </th>
                        <td colspan="3">
							<select class="easyui-combobox" name="courseType" style="width:80px;">    
								<option value="1">文字</option> 
								<option value="2">声音</option> 
								<option value="3">视频</option> 
								<option value="4">图片</option> 					
							</select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="atta">附件：</label>
                        </th>
                        <td colspan="3">
                        	<div>
                                <input id="file_uploadEdit" type="file" multiple="multiple">
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnUploadEdit" data-options="plain:true,iconCls:'icon-save'" onclick="javascript: $('#file_uploadEdit').uploadify('upload', '*')">上传</a>
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnCancelUploadEdit" data-options="plain:true,iconCls:'icon-cancel'" onclick="javascript: $('#file_uploadEdit').uploadify('cancel', '*')">取消</a>
                                <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-view'" target="_blank" name="attaView" style="display: none;">预览</a>
                                <div id="fileQueueEdit" class="fileQueue"></div>
                                <div id="div_filesEdit"></div>
                           </div>
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
    <div id="DivView" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-view',buttons: '#dlg-buttons'">
        <form id="ffView" method="post" novalidate="novalidate">
                <table id="tblView" class="view">
                    <tr>
                        <th>
                            <label for="companyId">企业名称：</label>
                        </th>
                        <td colspan="3">
                            <label name="companyName" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="productNo">产品型号：</label>
                        </th>
                        <td colspan="3">
                            <label name="productNo" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="title">标题：</label>
                        </th>
                        <td colspan="3">
                            <label name="title" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="payMoney">支付金额：</label>
                        </th>
                        <td colspan="3">
                            <label name="payMoney" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="courseType">教程类别：</label>
                        </th>
                        <td colspan="3">
							<label name="courseType" />
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
    
	<!--------------------------添加信息的弹出层---------------------------->
    <div id="DivUpload" class="easyui-dialog" style="width:550px;height:150px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-add',buttons: '#dlg-buttons'">
        <form id="ffUpload" method="post" novalidate="novalidate" enctype="multipart/form-data" action="atta/import?type=7&sessionId=${sessionId}">
                <table id="tblUpload" class="view">
                    <tr>
                        <th>
                            <label for="atta">附件：</label>
                        </th>
                        <td colspan="3">
                        	<input name="atta" type="file"  multiple="multiple" style="width: 300px;">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" style="text-align:right; padding-top:10px">
                            <a href="javascript:void(0)" class="easyui-linkbutton" id="btnUploadOK" iconcls="icon-ok" >确定</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivUpload').dialog('close')">关闭</a>
                        </td>
                    </tr>
                </table>
        </form>
    </div>
    
</body>
</html>