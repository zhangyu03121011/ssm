$(function() {
	
	//初始化grid数据
	InitGrid();
	
	//搜索
	BindSearchEvent();
	
});

function InitGrid(queryData) {
	
	$('#grid').datagrid({
		url: 'login/log/pagination?sessionId='+sessionId,
		title: '用户登陆日志',
		iconCls: 'icon-view',
//		height: 650,
		height: document.body.clientHeight * 0.9,
		width: function() {
			return document.body.clientWidth * 0.9
		},
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible: true,
		pagination: true,
		pageSize: 18,
		pageList: [18, 50, 100],
		rownumbers: true,
		sortOrder: 'asc',
		remoteSort: false,
		idField: 'loginLogId',
		queryParams: queryData,
		singleSelect:true,
		columns: [
			[{
				field: 'ck',
				checkbox: true
			}, {
				title: '登录名称',
				field: 'userName',
				width: 80,
				sortable: true
			}, {
				title: 'IP地址',
				field: 'ipAddress',
				width: 100,
				sortable: true
			}, {
				title: 'Mac地址',
				field: 'macAddress',
				width: 120,
				sortable: true
			}, {
				title: '操作类别',
				field: 'operationType',
				width: 120,
				formatter: function (value, rec, index) {  
		               if(value=="1"){
		            	   return "登录"; 
		               }else{
		            	   return "注销";
		               }
		            }
			},{
				title: '操作名称',
				field: 'operationName',
				width: 120,
				sortable: true
			},{
				title: '类别',
				field: 'type',
				width: 120,
				sortable: true
			}, {
				title: '创建时间',
				field: 'createTime',
				width: 120,
				sortable: true
			}, {
				title: '描述',
				field: 'descr',
				width: 200
			} ]
		],
		toolbar: [{
			id: 'btnView',
			text: '查看',
			iconCls: 'icon-table',
			handler: function() {
				ShowViewDialog();
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
			ShowViewDialog()
		}
	})
};

//搜索数据
function BindSearchEvent() {
	$("#btnSearch").click(function() {
		InitGrid($("#ffSearch").serializeJson());
		return false
	})
};

//明细对话框
function ShowViewDialog(view) {
	var rows = $("#grid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择一条记录", "error");
		return
	} else if (rows.length > 1) {
		$.messager.alert("提示", "每次只能修改/查看一条记录，你已经选择了<font color='red'  size='6'>" + rows.length + "</font>条", "error");
		return
	};
	$("#DivView").dialog('open').dialog('setTitle', '查看详细信息');
	BindViewInfo()
};

//显示明细数据
function BindViewInfo() {
	fillData($("#ffView"), $("#grid").datagrid("getSelections")[0]);
	if($("#grid").datagrid("getSelections")[0].operationType==1){
		$("#ffView [name=operationType]").text("登录");
	}else{
		$("#ffView [name=operationType]").text("注销");
	}
};