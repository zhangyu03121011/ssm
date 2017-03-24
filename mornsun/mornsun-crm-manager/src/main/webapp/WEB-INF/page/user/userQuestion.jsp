<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width" />
    <title>用户问题列表</title>
 	<%@include file="../common/common.jsp"%>
    <script src="resources/js/user/userQuestion.js?r=${jsVersion }" type="text/javascript"></script>
    <link href="resources/css/user/userQuestion.css?r=${jsVersion }" rel="stylesheet" type="text/css" />
</head>
<body>

    <div id="loading" style="display: none;"><img alt="数据正在加载中..." src="resources/images/loading02.gif" /></div>
    <div class="easyui-layout" id="tb" style="padding:5px;height:auto">
        <!-------------------------------搜索框----------------------------------->
        <fieldset>
            <legend>信息查询</legend>
            <form id="ffSearch" method="post">
		        <div style="margin-bottom:5px">
                    <label for="userVo.userName">用户名称：</label>
                    <input type="text" name="userVo.userName" style="width:100px"  />&nbsp;&nbsp;&nbsp;
                    <label for="title">标题：</label>
                    <input type="text" name="title" style="width:100px"  />&nbsp;&nbsp;&nbsp;
                    <label for="answerUserVo.userName">回答用户：</label>
                    <input type="text" name="answerUserVo.userName" style="width:100px"  />&nbsp;&nbsp;&nbsp;
					<label for="isavailable">是否有效：</label>
					<select class="easyui-combobox" name="isavailable" style="width:80px;">    
						<option value="1">有效</option> 
						<option value="0">无效</option>  
					</select>&nbsp;&nbsp;&nbsp;
					<label for="isPublic">是否公开：</label>
					<select class="easyui-combobox" name="isPublic" style="width:80px;">    
						<option value="1">公开</option> 
						<option value="0">不公开</option>  
					</select>&nbsp;&nbsp;&nbsp;
                     <a href="#" class="easyui-linkbutton" iconcls="icon-search" id="btnSearch">查询</a>
                </div>

            </form>
        </fieldset>
                
        <!-------------------------------详细信息展示表格----------------------------------->
        <table id="grid" style="width: 940px" title="用户操作" iconcls="icon-view" >
        </table>
    </div>
    
    <!--------------------------编辑信息的弹出层---------------------------->
    <div id="DivEdit" class="easyui-dialog" style="width:650px;height:350px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-edit',buttons: '#dlg-buttons'">
        <form id="ffEdit" method="post" novalidate="novalidate">
                <table id="tblEdit" class="view">                   
                    <tr>
                        <th>
                            <label for="userName">用户名称：</label>
                        </th>
                        <td>
                            <label name="userName" />
                        </td>
                        <th>
                            <label for="answerUserName">回答用户：</label>
                        </th>
                        <td>
                            <label name="answerUserName" />
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
                            <label for="readCount">阅读人数：</label>
                        </th>
                        <td>
                            <label name="readCount" />
                        </td>
                        <th>
                            <label for="answerCount">回答人数：</label>
                        </th>
                        <td>
                            <label name="answerCount" />
                        </td>
                    </tr>
                    <tr>                   	
                        <th>
                            <label for="isavailable">是否有效：</label>
                        </th>
                        <td>
                            <select class="easyui-combobox" name="isavailable">   
							    <option value="1">有效</option>   
							    <option value="0">无效</option>   
							</select>
                        </td> 
                        <th>
                            <label for="isPublic">是否公开：</label>
                        </th>
                        <td>
                            <select class="easyui-combobox" name="isPublic" style="width:150px;">   
							    <option value="1">公开</option>   
							    <option value="0">不公开</option>   
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
    <div id="DivView" class="easyui-dialog" style="width:600px;height:350px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-view',buttons: '#dlg-buttons'">
        <form id="ffView" method="post" novalidate="novalidate">
                <table id="tblView" class="view">
                    <tr>
                        <th>
                            <label for="userName">用户名称：</label>
                        </th>
                        <td>
                            <label name="userName" />
                        </td>
                        <th>
                            <label for="answerUserName">回答用户：</label>
                        </th>
                        <td>
                            <label name="answerUserName" />
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
                            <label for="readCount">阅读人数：</label>
                        </th>
                        <td>
                            <label name="readCount" />
                        </td>
                        <th>
                            <label for="answerCount">回答人数：</label>
                        </th>
                        <td>
                            <label name="answerCount" />
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
                            <label for="isPublic">是否公开：</label>
                        </th>
                        <td>
                            <label name="isPublic" />
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
                            <label for="createBy">创建人：</label>
                        </th>
                        <td>
                            <label name="createBy" />
                        </td>
                    </tr> 
                    <tr>
                        <th>
                            <label for="updateTime">更新时间：</label>
                        </th>
                        <td>
                            <label name="updateTime" />
                        </td>
                        <th>
                            <label for="updateBy">更新人：</label>
                        </th>
                        <td>
                            <label name="updateBy" />
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