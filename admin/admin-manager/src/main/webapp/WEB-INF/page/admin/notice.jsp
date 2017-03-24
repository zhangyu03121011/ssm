﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width" />
    <title>通知公告</title>
	<%@include file="../common/common.jsp"%>
    <script src="resources/js/admin/notice.js?r=${jsVersion }"></script>
    <script src="resources/js/common/uuid.js?r=${jsVersion }"></script>
    <link href="resources/css/admin/notice.css?r=${jsVersion }" rel="stylesheet" type="text/css" />
    
	<!-- 添加对LODOP控件的支持 -->
<!--     <script type="text/javascript" src="resources/js/JQueryTools/LODOP/CheckActivX.js"></script> -->
<!--     <object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>  -->
<!-- 	    <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0 pluginspage="install_lodop.exe"></embed> -->
<!--     </object>  -->
</head>
<body>
    <div id="loading" style="display: none;"><img alt="数据正在加载中..." src="resources/images/loading02.gif" /></div>
    <div id="gridtoolbar" style="padding: 5px; height: auto">
        <div style="margin-bottom: 5px">
        	<a href="javascript:void(0)" class ="easyui-linkbutton" onclick="ShowAddDialog()" data-options="iconCls:'icon-add', plain:true">添加</a>
        	<a href="javascript:void(0)" class ="easyui-linkbutton" onclick="ShowEditOrViewDialog()" data-options="iconCls:'icon-edit', plain:true">修改</a>
        	<a href="javascript:void(0)" class ="easyui-linkbutton" onclick="Delete()" data-options="iconCls:'icon-remove', plain:true">删除</a>
        	<a href="javascript:void(0)" class ="easyui-linkbutton" onclick="ShowEditOrViewDialog('view')" data-options="iconCls:'icon-table', plain:true">查看</a>
        	<a href="javascript:void(0)" class ="easyui-linkbutton" onclick="$('#grid').datagrid('reload');" data-options="iconCls:'icon-reload', plain:true">刷新</a>
        </div>
    </div>
    <div class="easyui-layout" id="tb" style="padding:5px;height:auto">
        <!-------------------------------搜索框----------------------------------->
        <fieldset>
            <legend>信息查询</legend>
            <form id="ffSearch" method="post">
		        <div style="margin-bottom:5px">
                    <label for="title">标题：</label>
                    <input type="text" name="title" style="width:100px"  />&nbsp;&nbsp;&nbsp;
                    <label for="content">内容：</label>
                    <input type="text" name="content" style="width:100px"  />&nbsp;&nbsp;&nbsp;
                    <label for="createBy">创建人：</label>
                    <input type="text" name="createBy" style="width:100px"  />&nbsp;&nbsp;&nbsp;
                    <label for="beginTime">创建时间：</label>
                    <input class="easyui-datebox" type="text" name="beginTime" style="width:100px"  />
                    <label for="endTime">~</label>
                    <input class="easyui-datebox" type="text" name="endTime" style="width:100px"  />&nbsp;&nbsp;&nbsp;

                    <a href="#" class="easyui-linkbutton" iconcls="icon-search" id="btnSearch">查询</a>
                </div>
            </form>
        </fieldset>
                
        <!-------------------------------详细信息展示表格----------------------------------->
        <table id="grid" style="width: 940px" title="用户操作" iconcls="icon-view">
        </table>
    </div>

    <!--------------------------添加信息的弹出层---------------------------->
    <div id="DivAdd" class="easyui-dialog" style="width:1020px;height:650px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-add',buttons: '#dlg-buttons'">
        <form id="ffAdd" method="post" novalidate="novalidate">
                <table id="tblAdd" class="view">
                    <tr>
                        <th>
                            <label for="title">标题：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" type="text" name="title" style="Width:860px;" data-options="required:true,validType:'length[1,100]'"/>
                        </td>
                    </tr>   
                    <tr>
                        <th>
                            <label for="content">内容：</label>
                        </th>
                        <td>
                            <textarea class="easyui-validatebox" style="width:100%;height: 180px;" id="add_content" name="content" data-options="required:true"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="attachment">附件上传：</label>
                        </th>
                        <td>                            
                            <div>
                                <input class="easyui-validatebox" type="hidden" name="attaId" />
                                <input id="file_upload" name="fileUpload" type="file" multiple="multiple">
                                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" onclick="javascript: $('#file_upload').uploadify('upload', '*')">上传</a>
                                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="javascript: $('#file_upload').uploadify('cancel', '*')">取消</a>

                                <div id="fileQueue" class="fileQueue"></div>
                                <div id="div_files"></div>
                                <br />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align:right; padding-top:10px">
                            <a href="javascript:void(0)" class="easyui-linkbutton" id="btnAddOK" iconcls="icon-ok" >确定</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivAdd').dialog('close')">关闭</a>
                        </td>
                    </tr>
                </table>
        </form>
    </div>

    <!--------------------------编辑信息的弹出层---------------------------->
    <div id="DivEdit" class="easyui-dialog" style="width:1020px;height:650px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-edit',buttons: '#dlg-buttons'">
        <form id="ffEdit" method="post" novalidate="novalidate">
                <table id="tblEdit" class="view">
                    <tr> 
                        <th>
                            <label for="title">标题：</label>
                        </th>
                        <td>
                            <input class="easyui-validatebox" type="text" name="title" style="Width:860px;" data-options="required:true,validType:'length[1,100]'"> 
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="content">内容：</label>
                        </th>
                        <td>
                            <textarea class="easyui-validatebox" style="width:100%;height: 180px;" id="edit_content" name="content" data-options="required:true" ></textarea>
                        </td>
                        </tr>
                    <tr>
 
                        <th>
                            <label for="attachment">附件上传：</label>
                        </th>
                        <td>
                            <div>
                                <input class="easyui-validatebox" type="hidden" name="Attachment_GUID" />
                                <input id="file_uploadEdit" name="file_uploadEdit" type="file" multiple="multiple">
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnUploadEdit" data-options="plain:true,iconCls:'icon-save'" onclick="javascript: $('#file_uploadEdit').uploadify('upload', '*')">上传</a>
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnCancelUploadEdit" data-options="plain:true,iconCls:'icon-cancel'" onclick="javascript: $('#file_uploadEdit').uploadify('cancel', '*')">取消</a>
                                <div id="fileQueueEdit" class="fileQueue"></div>
                                <div id="div_filesEdit"></div>
                                <br />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" style="text-align:right; padding-top:10px">
                            <input type="hidden" name="noticeId" />
                            <a href="javascript:void(0)" class="easyui-linkbutton" id="btnEditOK" iconcls="icon-ok" >确定</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#DivEdit').dialog('close')">关闭</a>
                        </td>
                    </tr>
                </table>
        </form>
    </div>

    <!--------------------------查看详细信息的弹出层---------------------------->
    <div id="DivView" class="easyui-dialog" style="width:1020px;height:650px;padding:10px 20px"
			closed="true" resizable="true" modal="true" data-options="iconCls: 'icon-view',buttons: '#dlg-buttons'">
        <form id="ffView" method="post" novalidate="novalidate">
            <div class="toolbar">
                <a href="#" onclick="javascript:Preview();"><img alt="打印预览" src="resources/images/print.gif" /><br />打印预览</a>
                <a href="#" onclick="javascript:PrintA();"><img alt="直接打印" src="resources/images/print.gif" /><br />直接打印</a>
                <a href="#" onclick="javascript:SaveAs();"><img alt="另存为" src="resources/images/saveas.gif" /><br />另存为</a>         
            </div>
            <div id="printContent">
                <table border="0" cellspacing="0" cellpadding="0" width="95%" height="40" align="center">
                    <tbody>
                        <tr>
                            <td height="20" colspan="4" align="middle" valign="center">&nbsp; </td>
                        </tr>
                        <tr>
                            <td align="center" colspan="4">
                                <h3>
                                    <label name="title" ></label>
                                </h3>
                            </td>
                        </tr>
                        <tr>
                            <td height="35" colspan="4" align="right" valign="center">                               
						                                发布者：<label name="createBy" ></label>&nbsp;&nbsp;&nbsp;&nbsp;
						                                发布时间：<label name="createTime" ></label>&nbsp;
                            </td>
                        </tr>
                        <tr bgcolor="#cfcdbe">
                            <td height="1"></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr bgcolor="#f2efea">
                            <td height="4"></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td height="4"></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
                <table class="t4" border="0" cellspacing="0" cellpadding="0" width="95%" height="28" align="center">
                    <tbody>
                        <tr>
                            <td width="20px">&nbsp;
                            </td>
                            <td width="100%">
                                <table cellspacing="0" cellpadding="0" width="95%">
                                    <tr>
                                        <td valign="top" align="left">
                                            <lable name="content" contenteditable="false"></lable>
                                            <br /><br />
                                            <hr />
                                            <div id="divViewAttach" ></div>
                                            <br /><br />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </form>
    </div>
</body>
</html>
