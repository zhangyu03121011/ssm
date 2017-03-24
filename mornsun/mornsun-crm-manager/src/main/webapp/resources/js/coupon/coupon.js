$(function() {
	
	//初始化grid
	InitGrid();
	
	//搜索数据
	BindSearchEvent();
	
	//编辑数据
	BindEditEvent();
	
	//添加数据
	BindAddEvent();
	
});

//初始化grid
function InitGrid(queryData) {
	$('#grid').datagrid({
		url:"coupon/pagination?sessionId="+sessionId,
		title: '优惠券列表',
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
				title: '优惠券名称',
				field: 'couponName',
				width: 180,
				sortable: true
			}, {
				title: '开始时间',
				field: 'beginTime',
				width: 120,
				sortable: true,
				formatter: function (value, rec, index) {  
	                return value.split(" ")[0];
                }
			}, {
				title: '结束时间',
				field: 'endTime',
				width: 120,
				sortable: true,
				formatter: function (value, rec, index) {  
	                return value.split(" ")[0];
                }
			}, {
				title: '优惠金额',
				field: 'couponMoney',
				width: 150,
				sortable: true
			}, {
				title: '限制金额',
				field: 'limitMoney',
				width: 150,
				sortable: true
			}, {
				title: '描述',
				field: 'descr',
				width: 200,
				sortable: true
			}, {
				title: '类别',
				field: 'couponType',
				align:"center",
				width: 120,
				sortable: true,
				formatter: function (value, rec, index) {  
                   if(value=="0"){
                	   return "注册赠送"; 
                   }else if(value=="1"){
                	   return "邀请赠送";
                   }else if(value=="2"){
                	   return "系统赠送";
                   }else if(value=="3"){
                	   return "活动赠送";
                   }else if(value=="4"){
                	   return "消费赠送";
                   }else if(value=="5"){
                	   return "提交产品/品牌等数据";
                   }else if(value=="6"){
                	   return "维护数据奖励";
                   }else if(value=="7"){
                	   return "充值奖励";
                   }else{
                	   return "其它";
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
                   }else if(value=="2"){
                	   return "审核失败";
                   } else{
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
			} ]
		],
		onLoadSuccess: function() {
			$(".appIconClass").linkbutton({
				plain: true,
				iconCls: $(this).attr("icon")
			});
		},
		toolbar: [{
			id: 'btnAdd',
			text: '添加',
			iconCls: 'icon-add',
			handler: function() {
				ShowAddDialog();
			}
		}, '-',
		{
			id: 'btnEdit',
			text: '修改',
			iconCls: 'icon-edit',
			handler: function() {
				ShowEditOrViewDialog();
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

//显示添加对话框
function ShowAddDialog() {
	$("#ffAdd").form("clear");
	$("#DivAdd").dialog('open').dialog('setTitle', '添加信息');
};

//添加数据
function BindAddEvent() {
	$("#btnAddOK").unbind("click").click(function() {
		var validate = $("#ffAdd").form('validate');
		if (validate == false) {
			return false;
		};
		var postData = $("#ffAdd").serializeArray();
		commPostRequest("coupon/save", postData, function(){
			//$.messager.alert("提示", "添加成功");
			$("#DivAdd").dialog("close");
			$("#grid").datagrid("reload");
			$("#ffAdd").form("clear");
		}, function(msg){
			$.messager.alert("提示", isEmpty(msg) ? "添加失败，请您检查" :msg);
		})
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

//显示编辑数据
function BindEditInfo() {
	var data=$("#grid").datagrid("getSelections")[0];
	$('#ffEdit').form('load', data);
};

//显示详情数据
function BindViewInfo() {
	var dataItem=$("#grid").datagrid("getSelections")[0];
	var data=jQuery.extend({}, dataItem);
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
	fillData($("#ffView"), data);
};

//编辑数据
function BindEditEvent() {
	$("#btnEditOK").unbind("click").click(function() {
		var validate = $("#ffEdit").form("validate");
		if (validate == false) {
			return false;
		};
		var postData = $("#ffEdit").serializeArray();
		commPostRequest("coupon/update", postData, function(data) {
			//$.messager.alert("提示", "修改成功");
			$("#DivEdit").dialog('close');
			$("#grid").datagrid("reload");
		},function(msg){
			$.messager.alert("提示", isEmpty(msg) ? "修改失败，请您检查" : msg);
		})
	})
}