<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width" />
    <title>品牌列表</title>
 	<%@include file="../common/common.jsp"%>
    <script src="resources/js/brand/brand.js?r=${jsVersion }" type="text/javascript"></script>
    <link href="resources/css/brand/brand.css?r=${jsVersion }" rel="stylesheet" type="text/css" />
</head>
<body>

    <div id="loading" style="display: none;"><img alt="数据正在加载中..." src="resources/images/loading02.gif" /></div>
    <div class="easyui-layout" id="tb" style="padding:5px;height:auto">
        <!-------------------------------搜索框----------------------------------->
        <fieldset>
            <legend>信息查询</legend>
            <form id="ffSearch" method="post">
		        <div style="margin-bottom:5px">
                    <label for="brandName">品牌名称：</label>
                    <input type="text" name="brandName" style="width:100px"  />&nbsp;&nbsp;&nbsp;
                    <label for="brandNameEn">品牌英文名称：</label>
                    <input type="text" name="brandNameEn" style="width:100px"  />&nbsp;&nbsp;&nbsp;
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
                            <label for="brandName">品牌名称：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入品牌名称"  style="width: 465px;" type="text" name="brandName" data-options="required:true,validType:'length[1,50]'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="brandNameEn">品牌英文名称：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入品牌英文名称"  style="width: 465px;" type="text" name="brandNameEn" data-options="required:true,validType:'length[1,50]'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="brandUrl">品牌URL：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入品牌URL"  style="width: 465px;" type="text" name="brandUrl" data-options="required:true,validType:'url'"/>
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
                            <label for="brandName">品牌名称：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入品牌名称"  style="width: 465px;" type="text" name="brandName" data-options="required:true,validType:'length[1,50]'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="brandNameEn">品牌英文名称：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入品牌英文名称"  style="width: 465px;" type="text" name="brandNameEn" data-options="required:true,validType:'length[1,50]'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="brandUrl">品牌URL：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入品牌URL"  style="width: 465px;" type="text" name="brandUrl" data-options="required:true,validType:'url'"/>
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
                            <label for="brandName">品牌名称：</label>
                        </th>
                        <td colspan="3">
                            <label name="brandName" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="brandNameEn">品牌英文名称：</label>
                        </th>
                        <td colspan="3">
                            <label name="brandNameEn" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="brandUrl">品牌URL：</label>
                        </th>
                        <td colspan="3">
                            <label name="brandUrl" />
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