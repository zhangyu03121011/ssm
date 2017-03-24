$(function() {
	
	//初始化grid
	InitGrid();
	
	//搜索数据
	BindSearchEvent();
	
	//编辑数据
	BindEditEvent();
	
});

//初始化grid
function InitGrid(queryData) {
	$('#grid').datagrid({
		url:"member/pagination?sessionId="+sessionId,
		title: '会员列表',
		iconCls: 'icon-view',
//		height: 650,
		height:document.body.clientHeight * 0.9,
		width: function() {
			return document.body.clientWidth * 0.9
		},
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible: true,
		pagination: true,
		singleSelect:true,
		pageSize: 18,
		pageList: [18, 50, 100],
		rownumbers: true,
		sortOrder: 'asc',
		remoteSort: false,
		idField: 'id',
		queryParams:queryData,
		columns: [
			[{
				field: 'ck',
				checkbox: true
			}, {
				title: '用户账号',
				field: 'userName',
				width: 120,
				sortable: true
			}, {
				title: '昵称',
				field: 'userAlias',
				width: 120,
				sortable: true
			}, {
				title: '头像',
				field: 'headImage',
				width: 40,
				sortable: true,
				formatter: function(val, rowdata, index) {
					if(!isEmpty(val)){
						if (val.startWith("http://")) {
							return '<a class="menuIconClass" href="'+val+'" target="_blank">查看</a>';
						}else{
							return '<a class="menuIconClass" href="'+currHost+'/images/'+val+'" target="_blank">查看</a>';
						}
					}else{
						return '';
					}
				}
			}, {
				title: '手机',
				field: 'mobile',
				width: 80,
				sortable: true
			}, {
				title: '头衔',
				field: 'positionVo',
				width: 100,
				sortable: true,
				formatter: function (value, rec, index) {  
	                   return value.positionName;
	                }
			}, {
				title: '用户类别',
				field: 'userType',
				width: 80,
				sortable: true,
				formatter: function (value, rec, index) {  
	                   if(value=="2"){
	                	   return "专家用户"; 
	                   }else if(value=="3"){
	                	   return "校长用户";
	                   }else if(value=="1"){
	                	   return "普通用户";
	                   }
	                }
			}, {
				title: '授权类别',
				field: 'authType',
				width: 80,
				sortable: true,
				formatter: function (value, rec, index) {  
                   if(value=="1"){
                	   return "Android"; 
                   }else if(value=="2"){
                	   return "IOS";
                   }
                }
			}, {
				title: '简介',
				field: 'intro',
				width: 200
			}, {
				title: '内部员工',
				field: 'userEmployeeVo',
				align:"center",
				width: 80,
				sortable: true,
				formatter: function (value, rec, index) {  
                   if(rec.userEmployeeVo && rec.userEmployeeVo.employeeName){
                	   return "是"; 
                   }else{
                	   return "否";
                   }
                }
			},{
				title: '内部员工工号',
				field: 'employeeNo',
				align:"center",
				width: 120,
				sortable: true,
				formatter: function (value, rec, index) {  
					if (rec.userEmployeeVo) {
						return rec.userEmployeeVo.employeeNo;
					}
                }
				
			},{
				title: '内部员工姓名',
				field: 'employeeName',
				align:"center",
				width: 120,
				sortable: true,
				formatter: function (value, rec, index) { 
					if (rec.userEmployeeVo) {
						return rec.userEmployeeVo.employeeName;
					}
                }
				
			},{
				title: '内部员工ID',
				field: 'employeeId',
				align:"center",
				width: 120,
				sortable: false,
				hidden:true,
				formatter: function (value, rec, index) {  
					if (rec.userEmployeeVo) {
						return rec.userEmployeeVo.id;
					}
                }
				
			},{
				title: '是否推送消息',
				field: 'isPushMessage',
				align:"center",
				width: 120,
				sortable: true,
				formatter: function (value, rec, index) {  
                   if(value=="1"){
                	   return "推送"; 
                   }else{
                	   return "不推送";
                   }
                }
			}, {
				title: '状态',
				field: 'state',
				align:"center",
				width: 120,
				sortable: true,
				formatter: function (value, rec, index) {  
                   if(value=="1"){
                	   return "已审核"; 
                   }else{
                	   return "待审核";
                   }
                }
			}, {
				title: '是否有效',
				field: 'isavailable',
				align:"center",
				width: 120,
				sortable: true,
				formatter: function (value, rec, index) {  
                   if(value=="1"){
                	   return "有效"; 
                   }else{
                	   return "无效";
                   }
                }
			}, { 
				title: '创建时间',
				field: 'createTime',
				width: 120,
				sortable: true
			}, {
				title: '创建人',
				field: 'createBy',
				width: 80
			}, {
				title: '更新时间',
				field: 'updateTime',
				width: 120,
				sortable: true
			}, {
				title: '更新人',
				field: 'updateBy',
				width: 80
			} ]
		],
		onLoadSuccess: function() {
			$(".appIconClass").linkbutton({
				plain: true,
				iconCls: $(this).attr("icon")
			});
		},
		toolbar: [{
			id: 'btnEdit',
			text: '修改',
			iconCls: 'icon-edit',
			handler: function() {
				ShowEditOrViewDialog();
			}
		}, '-',
		{
			id: 'btnSet',
			text: '设置内部员工',
			iconCls: 'icon-edit',
			handler: function() {
				ShowSetDialog();
			}
		}, '-',
		{
			id: 'btnView',
			text: '查看',
			iconCls: 'icon-table',
			handler: function() {
				ShowEditOrViewDialog("view");
			}
		}, '-',
		{
			id: 'btnReload',
			text: '刷新',
			iconCls: 'icon-reload',
			handler: function() {
				$("#grid").datagrid("reload")
			}
		}],
		onDblClickRow: function(rowIndex, rowData) {
			$('#grid').datagrid('uncheckAll');
			$('#grid').datagrid('checkRow', rowIndex);
			ShowEditOrViewDialog("view")
		}
	})
	
};

//搜索
function BindSearchEvent() {
	$("#ffSearch").form("clear");
	$("#btnSearch").click(function() {
		InitGrid($("#ffSearch").serializeJson());
		return false;
	})
};

//编辑/详情对话框
function ShowEditOrViewDialog(view) {
	var rows = $("#grid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择一条记录", "error");
		return;
	} else if (rows.length > 1) {
		$.messager.alert("提示", "每次只能修改/查看一条记录，你已经选择了<font color='red'  size='6'>" + rows.length + "</font>条", "error");
		return;
	};
	if (view == null) {
		clearForm("DivEdit");
		$("#DivEdit").dialog('open').dialog('setTitle', '修改信息');
		BindEditInfo();
	} else {
		$("#DivView").dialog('open').dialog('setTitle', '查看详细信息');
		BindViewInfo();
	}
};

function ShowSetDialog(){
	var rows = $("#grid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择一条记录", "error");
		return;
	} else if (rows.length > 1) {
		$.messager.alert("提示", "每次只能修改/查看一条记录，你已经选择了<font color='red'  size='6'>" + rows.length + "</font>条", "error");
		return;
	};
	clearForm("DivSet");
	$("#DivSet").dialog('open').dialog('setTitle', '设置内部员工');
	var data=$("#grid").datagrid("getSelections")[0];
	data.userId=data.id;
	data.employeeNo = data.userEmployeeVo.employeeNo;
	data.employeeName = data.userEmployeeVo.employeeName;
	data.id = data.userEmployeeVo.id;
	fillData($("#ffEditSet"), data);
	$('#ffEditSet').form('load', data);
}

//显示编辑数据
function BindEditInfo() {
	var data=$("#grid").datagrid("getSelections")[0];
	$('#ffEdit').form('load', data);
};

//显示详情数据
function BindViewInfo() {
	var dataItem=$("#grid").datagrid("getSelections")[0];
	var data=jQuery.extend({}, dataItem);
	data.positionName=data.positionVo.positionName;
	if(data.isavailable==1){
		data.isavailable="有效";
	}else{
		data.isavailable="无效";
	}
	if(data.state==1){
		data.state="已审核";
	}else{
		data.state="待审核";
	}
	if(data.isPushMessage==1){
		data.isPushMessage="推送";
	}else{
		data.isPushMessage="不推送";
	}
	if(data.userType==2){
		data.userType="专家用户";
	}else if(data.userType==3){
		data.userType="校长用户";
	}else {
		data.userType="普通用户";
	}
	if(data.authType==1){
		data.authType="Android";
	}else if(data.authType==1){
		data.authType="IOS";
	}
	
	data.employeeNo = data.userEmployeeVo.employeeNo;
	data.employeeName = data.userEmployeeVo.employeeName;
	if (data.employeeName) {
		data.isEmployeeInfo="是";
	}else{
		data.isEmployeeInfo="否";
	}
	
	if(!isEmpty(data.headImage)){
		if (data.headImage.startWith("http://")) {
			$("#headImageView").attr("href",data.headImage);
		}else{
			$("#headImageView").attr("href",currHost+'/images/'+data.headImage);
		}
	}
	
	fillData($("#ffView"), data);
};

//编辑数据
function BindEditEvent() {
	//更新会员信息
	$("#btnEditOK").unbind("click").click(function() {
		var validate = $("#ffEdit").form("validate");
		if (validate == false) {
			return false;
		};
		var postData = $("#ffEdit").serializeArray();
		commPostRequest("member/update", postData, function(data) {
			//$.messager.alert("提示", "修改成功");
			$("#DivEdit").dialog('close');
			$("#grid").datagrid("reload");
		},function(msg){
			$.messager.alert("提示", isEmpty(msg) ? "修改失败，请您检查" : msg);
		})
	})
	
	//更新内部员工信息
	$("#btnEditEmployeeOK").unbind("click").click(function() {
		var validate = $("#ffEditSet").form("validate");
		if (validate == false) {
			return false;
		};
		var postData = $("#ffEditSet").serializeArray();
		commPostRequest("member/employe/update", postData, function(data) {
			$.messager.alert("提示", "修改成功");
			$("#DivEdit").dialog('close');
			$("#grid").datagrid("reload");
		},function(msg){
			$.messager.alert("提示", isEmpty(msg) ? "修改失败，请您检查" : msg);
		})
	})
}