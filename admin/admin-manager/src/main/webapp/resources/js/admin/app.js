$(function() {
	
	//初始化grid
	InitGrid();
	
	//搜索数据
	BindSearchEvent();
	
	//添加数据
	BindAddEvent();
	
	//编辑数据
	BindEditEvent();
	
});

//初始化grid
function InitGrid(queryData) {
	$('#grid').datagrid({
		url:"app/pagination?sessionId="+sessionId,
		title: '系统管理',
		iconCls: 'icon-view',
//		height: 650,
		height:document.body.clientHeight * 0.92,
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
		idField: 'appId',
		queryParams:queryData,
		columns: [
			[{
				field: 'ck',
				checkbox: true
			}, {
				title: '系统标识',
				field: 'appKey',
				width: 80,
				sortable: true
			}, {
				title: '系统名称',
				field: 'appName',
				width: 180,
				sortable: true
			}, {
				title: '系统图标',
				field: 'appIcon',
				width: 80,
				align:"center",
				formatter: function(val, rowdata, index) {
					return '<a class="appIconClass" icon="'+val+'" href="javascript:void(0)" ></a>';
				}
			}, {
				title: '系统URL',
				field: 'appUrl',
				width: 260,
				sortable: true
			}, {
				title: '系统首页',
				field: 'appIndex',
				width: 150,
				sortable: true
			}, {
				title: '是否有效',
				field: 'isavailable',
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
				title: '创建人',
				field: 'createBy',
				sortable: true
			}, {
				title: '创建时间',
				field: 'createTime',
				width: 150,
				sortable: true
			}, {
				title: '备注',
				field: 'descr'
			}, ]
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
			id: 'btnDelete',
			text: '删除',
			iconCls: 'icon-remove',
			handler: function() {
				Delete();
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
			ShowEditOrViewDialog()
		}
	})
	
};

//搜索
function BindSearchEvent() {
	$("#btnSearch").click(function() {
		InitGrid($("#ffSearch").serializeJson());
		return false;
	})
};

//删除数据
function Delete() {
	var rows = $("#grid").datagrid("getSelections");
	if (rows.length >= 1) {
		$.messager.confirm("删除确认", "您确认删除选定的记录吗？", function(deleteAction) {
			if (deleteAction) {
				var objList=[];
				$.each(rows,function(i,objRow){
					var obj={};
					obj.appId=objRow.appId;
					obj.isavailable=0;
					objList.push(obj);
				});
				commAjaxRequest("app/batch",JSON.stringify(objList), function(data){
					//$.messager.alert("提示", "删除选定的记录成功");
					$("#grid").datagrid("reload");
					rows.length = "";
					$("#grid").datagrid("clearSelections");
				},function(msg){
					$.messager.alert("提示", isEmpty(msg) ? "删除失败，请您检查" : msg);
				});
			}
		})
	} else {
		$.messager.alert("提示", "请选择你要删除的数据");
	}
};

//显示添加对话框
function ShowAddDialog() {
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
		var obj={};
		obj.name="createBy";
		obj.value="admin";
		postData.push(obj);
		commPostRequest("app/save", postData, function(){
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
		$("#DivEdit").dialog('open').dialog('setTitle', '修改信息');
		BindEditInfo();
	} else {
		$("#DivView").dialog('open').dialog('setTitle', '查看详细信息');
		BindViewInfo();
	}
};

//显示编辑数据
function BindEditInfo() {
	$('#ffEdit').form('load', $("#grid").datagrid("getSelections")[0]);
};

//显示详情数据
function BindViewInfo() {
	var data=$("#grid").datagrid("getSelections")[0];
	fillData($("#ffView"), data);
	$("#ffView").find("div[icon]").attr('class', data.appIcon);
};

//编辑数据
function BindEditEvent() {
	$("#btnEditOK").unbind("click").click(function() {
		var validate = $("#ffEdit").form("validate");
		if (validate == false) {
			return false;
		};
		var postData = $("#ffEdit").serializeArray();
		commPostRequest("app/update", postData, function(data) {
			//$.messager.alert("提示", "修改成功");
			$("#DivEdit").dialog('close');
			$("#grid").datagrid("reload");
		},function(msg){
			$.messager.alert("提示", isEmpty(msg) ? "修改失败，请您检查" : msg);
		})
	})
}

//切换图标
function changeIcon(obj) {
	$(obj).parent().parent().find("div[icon]").attr('class', $(obj).val());
}