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
		url:"user/order/pagination?sessionId="+sessionId,
		title: '用户订单列表',
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
				title: '订单编码',
				field: 'orderNo',
				width: 150,
				sortable: true		
			}, {
				title: '用户名称',
				field: 'userName',
				width: 150,
				sortable: true,
				formatter: function (value, rec, index) {  
					if (rec.userVo) {
						return rec.userVo.userName;
					}
                }
			}, {
				title: '优惠券名称',
				field: 'couponName',
				width: 120,
				formatter: function (value, rec, index) {  
					if (rec.couponVo) {
						return rec.couponVo.couponName;
					}
                }
			}, {
				title: '来源类别',
				field: 'sourceType',
				align:"center",
				width: 80,
				sortable: true,
				formatter: function (value, rec, index) {  
                   if(value=="1"){
                	   return "收益"; 
                   }else if(value=="2"){
                	   return "交易";
                   }
                }
			}, {
				title: '状态',
				field: 'state',
				align:"center",
				width: 80,
				sortable: true,
				formatter: function (value, rec, index) {  
                   if(value=="1"){
                	   return "待支付"; 
                   }else if(value=="2"){
                	   return "已支付";
                   }
                }
			}, {
				title: '是否有效',
				field: 'isavailable',
				align:"center",
				width: 80,
				sortable: true,
				formatter: function (value, rec, index) {  
                   if(value=="1"){
                	   return "有效"; 
                   }else{
                	   return "无效";
                   }
                }
			}, {
				title: '总金额',
				field: 'totalMoney',
				width: 80,
				sortable: true
			}, {
				title: '优惠金额',
				field: 'couponMoney',
				width: 80
			}, {
				title: '交易金额',
				field: 'payMoney',
				width: 80,
				sortable: true
			}, {
				title: '分成金额',
				field: 'shareMoney',
				width: 80,
				sortable: true
			},{
				title: '分成比例',
				field: 'shareScale',
				width: 80,
				sortable: true
			},{
				title: '创建人',
				field: 'createBy',
				width: 120
			},{
				title: '创建时间',
				field: 'createTime',
				width: 120
			},{
				title: '描述',
				field: 'descr',
				width: 120
			} ]
		],
		onLoadSuccess: function() {
			$(".appIconClass").linkbutton({
				plain: true,
				iconCls: $(this).attr("icon")
			});
		},
		toolbar: [
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
		commPostRequest("user/order/save", postData, function(){
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
	if(data.sourceType==1){
		data.sourceType="收益";
	}else{
		data.sourceType="交易";
	}
	if(!data.totalMoney){
		data.totalMoney="0";
	}
	if(!data.couponMoney){
		data.couponMoney="0";
	}
	data.userName=data.userVo.userName;
	data.couponName=data.couponVo.couponName;
	fillData($("#ffEdit"), data);
	$('#ffEdit').form('load', data);
};

//显示详情数据
function BindViewInfo() {
	var dataItem=$("#grid").datagrid("getSelections")[0];
	var data=jQuery.extend({}, dataItem);
	if(data.sourceType==1){
		data.sourceType="收益";
	}else{
		data.sourceType="交易";
	}
	if(!data.totalMoney){
		data.totalMoney="0";
	}
	if(!data.couponMoney){
		data.couponMoney="0";
	}		
	if(data.isavailable==1){
		data.isavailable="有效";
	}else{
		data.isavailable="无效";
	}
	data.userName=data.userVo.userName;
	data.couponName=data.couponVo.couponName;
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
		commPostRequest("user/order/update", postData, function(data) {
			//$.messager.alert("提示", "修改成功");
			$("#DivEdit").dialog('close');
			$("#grid").datagrid("reload");
		},function(msg){
			$.messager.alert("提示", isEmpty(msg) ? "修改失败，请您检查" : msg);
		})
	})
}