<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width" />
    <title>产品列表</title>
 	<%@include file="../common/common.jsp"%>
    <script src="resources/js/product/product.js?r=${jsVersion }" type="text/javascript"></script>
    <link href="resources/css/product/product.css?r=${jsVersion }" rel="stylesheet" type="text/css" />
</head>
<body>

    <div id="loading" style="display: none;"><img alt="数据正在加载中..." src="resources/images/loading02.gif" /></div>
    <div class="easyui-layout" id="tb" style="padding:5px;height:auto">
    
    	<input type="hidden" id="tabFormType" value="Base">
    	<div id="tabs" class="easyui-tabs">   
		    <div title="基本信息" style="padding:20px;">   
		    	
    	        <!-------------------------------搜索框----------------------------------->
		        <fieldset>
		            <legend>信息查询</legend>
		            <form id="ffSearchBase" method="post">
				        <div style="margin-bottom:5px">
		                    <label for="productCommonVo.brandName">品牌名称：</label>
		                    <input type="text" name="productCommonVo.brandName" style="width:100px"  />&nbsp;&nbsp;&nbsp;
		                    <label for="productCommonVo.catalogName">分类名称：</label>
		                    <input type="text" name="productCommonVo.catalogName" style="width:100px"  />&nbsp;&nbsp;&nbsp;
		                    <label for="productNo">产品型号：</label>
		                    <input type="text" name="productNo" style="width:100px"  />&nbsp;&nbsp;&nbsp;
		                    <label for="productState">产品状态：</label>
							<select class="easyui-combobox" name="productState" style="width:150px;">   
							    <option value="0">预售</option>   
							    <option value="1">现行</option> 
							    <option value="2">不推荐</option> 
							    <option value="3">停产</option> 
							</select>&nbsp;&nbsp;&nbsp;
							<label for="state">状态：</label>
							<select class="easyui-combobox" name="state" style="width:80px;">   
							    <option value="0">待审核</option>   
							    <option value="1">已审核</option> 
							    <option value="2">审核失败</option>  
							</select>&nbsp;&nbsp;&nbsp;
		                     <a href="#" class="easyui-linkbutton" iconcls="icon-search" id="btnSearch">查询</a>
		                </div>
		
		            </form>
		        </fieldset>
		                
		        <!-------------------------------详细信息展示表格----------------------------------->
		        <table id="gridBase" style="width: 940px" title="用户操作" iconcls="icon-view" >
		        </table>
		    	
		    </div>   
		    <div title="附件信息" style="overflow:auto;padding:20px;">   
		    	
    	        <!-------------------------------搜索框----------------------------------->
		        <fieldset>
		            <legend>信息查询</legend>
		            <form id="ffSearchAtta" method="post">
				        <div style="margin-bottom:5px">
		                    <label for="productCommonVo.brandName">品牌名称：</label>
		                    <input type="text" name="productCommonVo.brandName" style="width:100px"  />&nbsp;&nbsp;&nbsp;
		                    <label for="productCommonVo.catalogName">分类名称：</label>
		                    <input type="text" name="productCommonVo.catalogName" style="width:100px"  />&nbsp;&nbsp;&nbsp;
		                    <label for="productNo">产品型号：</label>
		                    <input type="text" name="productNo" style="width:100px"  />&nbsp;&nbsp;&nbsp;
		                    <label for="baseAttaModel.fileName">附件名称：</label>
		                    <input type="text" name="baseAttaModel.fileName" style="width:100px"  />&nbsp;&nbsp;&nbsp;
		                    <label for="state">状态：</label>
							<select class="easyui-combobox" name="state" style="width:80px;">   
							    <option value="0">待审核</option>   
							    <option value="1">已审核</option> 
							    <option value="2">审核失败</option>  
							</select>&nbsp;&nbsp;&nbsp;
		                     <a href="#" class="easyui-linkbutton" iconcls="icon-search" id="btnSearch">查询</a>
		                </div>
		
		            </form>
		        </fieldset>
		                
		        <!-------------------------------详细信息展示表格----------------------------------->
		        <table id="gridAtta" style="width: 940px" title="用户操作" iconcls="icon-view" >
		        </table>
		    
		    </div> 
		    <div title="参数信息" style="overflow:auto;padding:20px;">   
		    	
    	        <!-------------------------------搜索框----------------------------------->
		        <fieldset>
		            <legend>信息查询</legend>
		            <form id="ffSearchParam" method="post">
				        <div style="margin-bottom:5px">
		                    <label for="productCommonVo.brandName">品牌名称：</label>
		                    <input type="text" name="productCommonVo.brandName" style="width:100px"  />&nbsp;&nbsp;&nbsp;
		                    <label for="productCommonVo.catalogName">分类名称：</label>
		                    <input type="text" name="productCommonVo.catalogName" style="width:100px"  />&nbsp;&nbsp;&nbsp;
		                    <label for="productNo">产品型号：</label>
		                    <input type="text" name="productNo" style="width:100px"  />&nbsp;&nbsp;&nbsp;
		                    <label for="paramVo.paramName">参数名称：</label>
		                    <input type="text" name="paramVo.paramName" style="width:100px"  />&nbsp;&nbsp;&nbsp;
		                    <label for="paramValue">参数值：</label>
		                    <input type="text" name="paramValue" style="width:100px"  />&nbsp;&nbsp;&nbsp;
							<label for="state">状态：</label>
							<select class="easyui-combobox" name="state" style="width:80px;">   
							    <option value="0">待审核</option>   
							    <option value="1">已审核</option> 
							    <option value="2">审核失败</option>  
							</select>&nbsp;&nbsp;&nbsp;
		                     <a href="#" class="easyui-linkbutton" iconcls="icon-search" id="btnSearch">查询</a>
		                </div>
		
		            </form>
		        </fieldset>
		                
		        <!-------------------------------详细信息展示表格----------------------------------->
		        <table id="gridParam" style="width: 940px" title="用户操作" iconcls="icon-view" >
		        </table>
		    
		    </div> 
		    <div title="替换信息" style="overflow:auto;padding:20px;">   
		    	
    	        <!-------------------------------搜索框----------------------------------->
		        <fieldset>
		            <legend>信息查询</legend>
		            <form id="ffSearchReplace" method="post">
				        <div style="margin-bottom:5px">
		                    <label for="productCommonVo.brandName">品牌名称：</label>
		                    <input type="text" name="productCommonVo.brandName" style="width:100px"  />&nbsp;&nbsp;&nbsp;
		                    <label for="productCommonVo.catalogName">分类名称：</label>
		                    <input type="text" name="productCommonVo.catalogName" style="width:100px"  />&nbsp;&nbsp;&nbsp;
		                    <label for="productNo">产品型号：</label>
		                    <input type="text" name="productNo" style="width:100px"  />&nbsp;&nbsp;&nbsp;
		                    <label for="matchType">匹配度：</label>
							<select class="easyui-combobox" name="matchType" style="width:150px;">   
							    <option value="1">直接替换</option> 
							    <option value="2">功能相同</option> 
							    <option value="3">功能相似</option> 
							</select>&nbsp;&nbsp;&nbsp;
							<label for="state">状态：</label>
							<select class="easyui-combobox" name="state" style="width:80px;">   
							    <option value="0">待审核</option>   
							    <option value="1">已审核</option> 
							    <option value="2">审核失败</option>  
							</select>&nbsp;&nbsp;&nbsp;
		                     <a href="#" class="easyui-linkbutton" iconcls="icon-search" id="btnSearch">查询</a>
		                </div>
		
		            </form>
		        </fieldset>
		                
		        <!-------------------------------详细信息展示表格----------------------------------->
		        <table id="gridReplace" style="width: 940px" title="用户操作" iconcls="icon-view" >
		        </table>
		    
		    </div> 
		</div>

    </div>

    <!--------------------------编辑信息的弹出层---------------------------->
    <div id="DivEdit" class="easyui-dialog" style="width:650px;height:300px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-edit',buttons: '#dlg-buttons'">
        <form id="ffEditBase" method="post" novalidate="novalidate">
                <table id="tblEdit" class="view">
                    <tr>
                        <th>
                            <label for="brandId">品牌名称：</label>
                        </th>
                        <td colspan="3">
                        	<select class="easyui-combotree" style="width:300px;" name="brandId" id="editBrandTree"></select>
                        </td>
                    </tr>
                     <tr>
                        <th>
                            <label for="catalogId">分类名称：</label>
                        </th>
                        <td colspan="3">
                        	<select class="easyui-combotree" style="width:300px;" name="catalogId" id="editCatalogTree"></select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="productNo">产品型号：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" placeholder="请输入产品型号" type="text" name="productNo"  data-options="required:true,validType:'length[1,50]'"/>
                        </td>
                        <th>
                            <label for="productState">产品状态：</label>
                        </th>
                        <td>
 							<select class="easyui-combobox" name="productState" style="width:150px;">   
							    <option value="0">预售</option>   
							    <option value="1">现行</option> 
							    <option value="2">不推荐</option> 
							    <option value="3">停产</option> 
							</select>
                        </td>
                    </tr>
                     <tr>
                        <th>
                            <label for="productDesc">产品描述：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入产品描述" type="text" name="productDesc"  style="width:350px;"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="state">状态：</label>
                        </th>
                        <td>
 							<select class="easyui-combobox" name="state" style="width:150px;">
 								<option value="0">待审核</option>    
							    <option value="1">审核通过</option>   
							    <option value="2">审核不通过</option> 
							</select>
                        </td>
                        <th>
                            <label for="isavailable">是否有效 ：</label>
                        </th>
                        <td>
							<select class="easyui-combobox" name="isavailable" style="width:80px;">   
							    <option value="1">有效</option>   
							    <option value="0">无效</option>   
							</select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" style="text-align:right; padding-top:10px">
                        	<input type="hidden" name="id" />
                        	<input type="hidden" name="productId" />
                            <a href="javascript:void(0)" class="easyui-linkbutton" id="btnEditOK" iconcls="icon-ok" >确定</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivEdit').dialog('close')">关闭</a>
                        </td>
                    </tr>
                </table>
        </form>
        <form id="ffEditAtta" method="post" novalidate="novalidate">
                <table id="tblEdit" class="view">
                    <tr>
                        <th>
                            <label for="state">状态：</label>
                        </th>
                        <td>
 							<select class="easyui-combobox" name="state" style="width:150px;">
 								<option value="0">待审核</option>    
							    <option value="1">审核通过</option>   
							    <option value="2">审核不通过</option> 
							</select>
                        </td>
                        <th>
                            <label for="isavailable">是否有效 ：</label>
                        </th>
                        <td>
							<select class="easyui-combobox" name="isavailable" style="width:80px;">   
							    <option value="1">有效</option>   
							    <option value="0">无效</option>   
							</select>
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
        <form id="ffEditParam" method="post" novalidate="novalidate">
                <table id="tblEdit" class="view">
                     <tr>
                        <th>
                            <label for="paramId">参数名称：</label>
                        </th>
                        <td colspan="3">
                        	<select class="easyui-combotree" style="width:300px;" name="paramId" id="editParamTree"></select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="paramValue">参数值：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入参数值" type="text" name="paramValue"  data-options="required:true,validType:'length[1,50]'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="state">状态：</label>
                        </th>
                        <td>
 							<select class="easyui-combobox" name="state" style="width:150px;"> 
 								<option value="0">待审核</option>   
							    <option value="1">审核通过</option>   
							    <option value="2">审核不通过</option> 
							</select>
                        </td>
                        <th>
                            <label for="isavailable">是否有效 ：</label>
                        </th>
                        <td>
							<select class="easyui-combobox" name="isavailable" style="width:80px;">   
							    <option value="1">有效</option>   
							    <option value="0">无效</option>   
							</select>
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
        <form id="ffEditReplace" method="post" novalidate="novalidate">
                <table id="tblEdit" class="view">
                    <tr>
                        <th>
                            <label for="matchType">匹配度：</label>
                        </th>
                        <td colspan="3">
							<select class="easyui-combobox" name="matchType" style="width:150px;">   
							    <option value="1">直接替换</option> 
							    <option value="2">功能相同</option> 
							    <option value="3">功能相似</option> 
							</select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="state">状态：</label>
                        </th>
                        <td>
 							<select class="easyui-combobox" name="state" style="width:150px;"> 
 								<option value="0">待审核</option>   
							    <option value="1">审核通过</option>   
							    <option value="2">审核不通过</option> 
							</select>
                        </td>
                        <th>
                            <label for="isavailable">是否有效 ：</label>
                        </th>
                        <td>
							<select class="easyui-combobox" name="isavailable" style="width:80px;">   
							    <option value="1">有效</option>   
							    <option value="0">无效</option>   
							</select>
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
    
	<!--------------------------添加信息的弹出层---------------------------->
    <div id="DivUpload" class="easyui-dialog" style="width:550px;height:150px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-add',buttons: '#dlg-buttons'">
        <form id="ffUpload" method="post" novalidate="novalidate" enctype="multipart/form-data" action="product/import?sessionId=${sessionId}">
                <table id="tblUpload" class="view">
                    <tr>
                        <th>
                            <label for="atta">附件：</label>
                        </th>
                        <td colspan="3">
                        	<input name="atta" type="file" multiple="multiple" style="width: 300px;">
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