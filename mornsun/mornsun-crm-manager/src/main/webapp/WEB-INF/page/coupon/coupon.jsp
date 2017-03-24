<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width" />
    <title>优惠券列表</title>
 	<%@include file="../common/common.jsp"%>
    <script src="resources/js/coupon/coupon.js?r=${jsVersion }" type="text/javascript"></script>
    <link href="resources/css/coupon/coupon.css?r=${jsVersion }" rel="stylesheet" type="text/css" />
</head>
<body>

    <div id="loading" style="display: none;"><img alt="数据正在加载中..." src="resources/images/loading02.gif" /></div>
    <div class="easyui-layout" id="tb" style="padding:5px;height:auto">
        <!-------------------------------搜索框----------------------------------->
        <fieldset>
            <legend>信息查询</legend>
            <form id="ffSearch" method="post">
		        <div style="margin-bottom:5px">
                    <label for="couponName">优惠券名称：</label>
                    <input type="text" name="couponName" style="width:100px"  />&nbsp;&nbsp;&nbsp;
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
                            <label for="couponName">优惠券名称：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入优惠券名称"  style="width: 465px;" type="text" name="couponName" data-options="required:true,validType:'length[3,50]'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="beginTime">开始时间：</label>
                        </th>
                        <td>
                            <input class="easyui-datetimebox" type="text" name="beginTime" data-options="required:true,showSeconds:false,validType:['length[1,50]']"/>
                        </td>
                        <th>
                            <label for="endTime">结束时间：</label>
                        </th>
                        <td>
                            <input class="easyui-datetimebox" type="text" name="endTime" data-options="required:true,showSeconds:false,validType:['length[1,200]']"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="couponMoney">优惠金额：</label>
                        </th>
                        <td>
                            <input class="easyui-numberbox" type="text" placeholder="请输入优惠金额" name="couponMoney" data-options="required:true,validType:['intOrFloat','length[1,50]']"/>
                        </td>
                        <th>
                            <label for="limitMoney">限制金额：</label>
                        </th>
                        <td>
                            <input class="easyui-numberbox" placeholder="请输入限制金额" type="text" name="limitMoney" data-options="required:true,validType:['intOrFloat','length[1,200]']"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="couponType">类别：</label>
                        </th>
                        <td colspan="3">
                            <select class="easyui-combobox" name="couponType" style="width:150px;">   
							    <option value="0">注册赠送</option>   
							    <option value="1">邀请赠送</option>   
							    <option value="2">系统赠送</option>   
							    <option value="3">活动赠送</option> 
							    <option value="4">消费赠送</option>   
								<option value="5">提交产品/品牌等数据</option> 
								<option value="6">维护数据奖励</option> 
								<option value="7">充值奖励</option> 
							    <option value="8">其它</option> 
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
                            <label for="couponName">优惠券名称：</label>
                        </th>
                        <td colspan="3">
                            <input class="easyui-validatebox" placeholder="请输入优惠券名称"  style="width: 465px;" type="text" name="couponName" data-options="required:true,validType:'length[3,50]'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="beginTime">开始时间：</label>
                        </th>
                        <td>
                            <input class="easyui-datebox" type="text" name="beginTime" data-options="required:true,validType:['date','length[1,50]']"/>
                        </td>
                        <th>
                            <label for="endTime">结束时间：</label>
                        </th>
                        <td>
                            <input class="easyui-datebox" type="text" name="endTime" data-options="required:true,validType:['date','length[1,200]']"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="couponMoney">优惠金额：</label>
                        </th>
                        <td>
                            <input class="easyui-numberbox" type="text" placeholder="请输入优惠金额" name="couponMoney" data-options="required:true,validType:['intOrFloat','length[1,50]']"/>
                        </td>
                        <th>
                            <label for="limitMoney">限制金额：</label>
                        </th>
                        <td>
                            <input class="easyui-numberbox" placeholder="请输入限制金额" type="text" name="limitMoney" data-options="required:true,validType:['intOrFloat','length[1,200]']"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="couponType">类别：</label>
                        </th>
                        <td>
                            <select class="easyui-combobox" name="couponType" style="width:150px;">   
							    <option value="0">注册赠送</option>   
							    <option value="1">邀请赠送</option>   
							    <option value="2">系统赠送</option>   
							    <option value="3">活动赠送</option> 
							    <option value="4">消费赠送</option>   
								<option value="5">提交产品/品牌等数据</option> 
								<option value="6">维护数据奖励</option> 
								<option value="7">充值奖励</option> 
							    <option value="8">其它</option> 
							</select>
                        </td>
                        <th>
                            <label for="isavailable">是否有效：</label>
                        </th>
                        <td>
                            <select class="easyui-combobox" name="isavailable" style="width:150px;">   
							    <option value="1">有效</option>   
							    <option value="0">无效</option>   
							</select>
                        </td>
                    </tr>
                    
                     <tr>
                    	<th>
                            <label for="state">审核状态：</label>
                        </th>
                        <td>
                            <select class="easyui-combobox" name="state" style="width:80px;">   
							    <option value="0">待审核</option>   
							    <option value="1">已审核</option> 
							    <option value="2">审核失败</option>  
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
                            <label for="couponName">优惠券名称：</label>
                        </th>
                        <td colspan="3">
                            <label name="couponName" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="beginTime">开始时间：</label>
                        </th>
                        <td>
                            <label name="beginTime" />
                        </td>

                        <th>
                            <label for="endTime">结束时间：</label>
                        </th>
                        <td>
                            <label name="endTime" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="couponMoney">优惠金额：</label>
                        </th>
                        <td>
                            <label name="couponMoney" />
                        </td>

                        <th>
                            <label for="limitMoney">限制金额：</label>
                        </th>
                        <td>
                            <label name="limitMoney" />
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