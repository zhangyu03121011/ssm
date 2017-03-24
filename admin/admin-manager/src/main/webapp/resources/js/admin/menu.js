$(function() {
	
	//加载树
	reloadTree();
	
	//展开所有节点
	$("#expandAllBtn").bind("click", {
		type: "expandAll"
	}, expandNode);
	
	//折叠所有节点
	$("#collapseAllBtn").bind("click", {
		type: "collapseAll"
	}, expandNode);
	
	$("#loading").center();
	
	//搜索
	BindSearchEvent();
	
	//添加数据
	BindAddEvent();
	
	//编辑数据
	BindEditEvent();
});

//Ztree设置
var setting = {
	data: {
		key:{
			name:"menuName"
		},
		simpleData: {
			enable: true,
			idKey: "menuId",
			pIdKey: "parentId",
			rootPId: -1
		}
	},
	callback: {
		onClick: onClick,
		onDblClick: onDblClick
	}
};

//加载ztree
function reloadTree() {
	$("#loading").show();
	commPostRequest("menu/list", {}, function(data) {
		
		//加载应用列表信息
		commPostRequest("app/list", {}, function(appList) {
			
			//加载应用列表下拉框
			$('#searchAppCombo').combobox("loadData", appList.list);
			
			var result=jQuery.extend([], data.list);
			
			$.each(result,function(i,item){
				if(item.parentId==-1){
					item.parentId=item.appId;
				}
			})
			
			$.each(appList.list,function(i,item){
				item.parentId=-1;
				item.menuId=item.appId;
				item.menuName=item.appName;
				result.push(item);
			})
			
			$.fn.zTree.init($("#meibaTree"), setting, result);
			var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
			treeObj.expandAll(true);
			
			//初始化grid
			InitGrid();
			
			var treeData=convertToTree(result,"menuId","menuName");
			
			//加载菜单树列表
			$('#searchMenuParentIdTree').combotree("loadData", treeData);
			$('#addMenuParentIdTree').combotree("loadData", treeData);
			$('#editMenuParentIdTree').combotree("loadData", treeData);
			
			$("#ffSearch").form("clear");
			
			$("#loading").fadeOut(500);
			
		})

	},function(){
		$("#loading").fadeOut(500);
	})
};

//单击显示菜单详情
function onClick(event, treeId, treeNode, clickFlag) {
	loadData(treeNode.menuId,treeNode.parentId==-1?treeNode.appId:null);
};

//双击显示菜单详情
function onDblClick(event, treeId, treeNode) {
	loadData(treeNode.menuId,treeNode.parentId==-1?treeNode.appId:null);
};

//显示菜单详情
function loadData(menuId,appId) {
	$("#loading").show();
	var queryParams = $('#grid').datagrid('options').queryParams;
	
	//设置查询条件
	queryParams.parentId=null;
	queryParams.appId=null;
	if(isEmpty(appId)){
		queryParams.parentId = menuId;
	}else{
		queryParams.appId = appId;
	}
	
	$("#grid").datagrid("reload");
	$('#grid').datagrid('uncheckAll');
	queryParams.parentId=null;
	queryParams.appId=null;
	$("#loading").fadeOut(500);
};

//初始化grid  
function InitGrid(queryData) {
	$('#grid').datagrid({
		url: 'menu/pagination?sessionId='+sessionId,
		title: '功能菜单',
		iconCls: 'icon-view',
		height: 650,
//		height: document.body.clientHeight * 0.8,
		width: function() {
			return document.body.clientWidth * 0.9
		},
		nowrap: true,
		autoRowHeight: true,
		striped: true,
		collapsible: true,
		pagination: true,
		fit:true,
//		rowStyler:function(index,row){
//		    return 'height:2.9em;';
//		},
		pageSize: 18,
		pageList: [18, 50, 100],
		rownumbers: true,
		sortOrder: 'asc',
		remoteSort: false,
		idField: 'menuId',
		queryParams: queryData,
		columns: [
			[{
				field: 'ck',
				checkbox: true
			}, {
				title: '系统名称',
				field: 'sysAppModel',
				width: 80,
				formatter: function(val, rowdata, index) {
					return val.appName;
				}
			}, {
				title: '父级菜单',
				field: 'parentId',
				width: 80,
				hidden:true
			}, {
				title: '菜单名称',
				field: 'menuName',
				width: 150
			}, {
				title: '菜单图标',
				field: 'menuIcon',
				width: 80,
				align:"center",
				formatter: function(val, rowdata, index) {
					return '<a class="menuIconClass" icon="'+val+'" href="javascript:void(0)" ></a>';
				}
			}, {
				title: '窗体类别',
				field: 'winType',
				width: 80,
				formatter: function (value, rec, index) {  
	               if(value=="_self"){
	            	   return "当前窗体"; 
	               }else{
	            	   return "新窗体";
	               }
	            }
			}, {
				title: '菜单URL',
				field: 'menuUrl',
				width: 200
			}, {
				title: '排序',
				field: 'sort',
				width: 50
			}, {
				title: '是否可见',
				field: 'visible',
				width: 80,
				formatter: function(val, rowdata, index) {
					if (val=="1") {
						return '<a class="grid_visible" href="javascript:void(0)" >' + val + '</a>'
					} else {
						return '<a class="grid_unvisible" href="javascript:void(0)" >' + val + '</a>'
					}
				}
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
				title : '描述',
				field : 'descr',
				width : 180
			}, {
				title : '创建人',
				field : 'createBy',
				width : 80,
				sortable : true
			}, {
				title : '创建时间',
				field : 'createTime',
				width : 120,
				sortable : true
			}, {
				title : '更新人',
				field : 'updateBy',
				width : 80,
				sortable : true
			}, {
				title : '更新时间',
				field : 'updateTime',
				width : 120,
				sortable : true
			}]
		],
		onLoadSuccess: function() {
			$(".grid_visible").linkbutton({
				text: '可见',
				plain: true,
				iconCls: 'icon-ok'
			});
			$(".grid_unvisible").linkbutton({
				text: '不可见',
				plain: true,
				iconCls: 'icon-stop'
			});
			$(".menuIconClass").linkbutton({
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
				//初始化grid
				InitGrid();
			}
		}],
		onDblClickRow: function(rowIndex, rowData) {
			$('#grid').datagrid('uncheckAll');
			$('#grid').datagrid('checkRow', rowIndex);
			ShowEditOrViewDialog();
		}
	})
};

//搜索
function BindSearchEvent() {
	$("#btnSearch").unbind("click").click(function() {
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
					obj.menuId=objRow.menuId;
					obj.isavailable=0;
					objList.push(obj);
				});
				commAjaxRequest("menu/batch",JSON.stringify(objList), function(data){
					//$.messager.alert("提示", "删除选定的记录成功");
					//加载树
					reloadTree();
					rows.length = "";
					$("#grid").datagrid("clearSelections");
				},function(msg){
					$.messager.alert("提示", isEmpty(msg) ? "删除失败，请您检查" : msg);
				});
			}
		})
	} else {
		$.messager.alert("提示", "请选择你要删除的数据")
	}
};

//添加对话框
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
		
		//应用列表
		var appArray=convertListToArray($('#searchAppCombo').combobox('getData'),"appId");

		// 获取表单数据
		var postData = $("#ffAdd").serializeArray();
		$.each(postData, function(i, obj) {
			if (obj != null && obj.name == "parentId") {
				
				//获取应用ID
				var tmpObj={};
				tmpObj.name="appId";
				
				if(jQuery.inArray(obj.value, appArray)!=-1){
					
					tmpObj.value=obj.value;
					obj.value = "-1";
					
				}else{
					
					//获取应用ID
					var tree=$("#addMenuParentIdTree").combotree('tree');
					//获取根ID
					var rootNode=getCombotreeRoot(tree,tree.tree("find",obj.value));
					tmpObj.value=rootNode.id;
					
				}
				
				postData.push(tmpObj);
				
			}
		})
		
		// 保存数据
		commPostRequest("menu/save",postData, function() {
			//$.messager.alert("提示", "添加成功");
			$("#DivAdd").dialog("close");
			$("#ffAdd").form("clear");
			//加载树
			reloadTree();
		}, function(msg) {
			$.messager.alert("提示", isEmpty(msg) ? "添加失败，请您检查" : msg);
		});
	})
};

//显示编辑/详情对话框
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

//显示编辑信息
function BindEditInfo() {
	var data=$("#grid").datagrid("getSelections")[0];
	$('#ffEdit').form('load', data);
	$("#ffEdit").find("[name='menuIcon']")[0].onchange();
	
	if($("#editMenuParentIdTree").combo("getValue")=="-1"){
		//$("#editMenuParentIdTree").combo("clear");
		$("#editMenuParentIdTree").combo("setText",data.sysAppModel.appName);
		
		//选中树节点
		var node = $('#editMenuParentIdTree').combotree('tree').tree('find', data.sysAppModel.appId);
		$('#editMenuParentIdTree').combotree('tree').tree('select', node.target);

	}
	
	$('#editAppCombo').combobox('select',data.appId);
	
};

//显示详情
function BindViewInfo() {
	var data=$("#grid").datagrid("getSelections")[0];
	fillData($("#ffView"), data);
	
	$("label[name=appName]").text(data.sysAppModel.appName);
	
	//查找父级节点
	var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
	var node = treeObj.getNodeByParam("menuId", data.parentId, null);
	var nameStr="";
	if(node){
		nameStr=node.menuName;
	}
	$("#ffView").find("[name='parentId']").text(nameStr);
	$("#ffView").find("div[icon]").attr('class', data.menuIcon);
	
	var winTypeStr="新窗体";
	if(data.winType=="_self"){
		winTypeStr="当前窗体";
	}
	$("#ffView").find("[name='winType']").text(winTypeStr);
	
	var visibleStr="不可见";
	if(data.visible=="1"){
		visibleStr="可见";
	}
	$("#ffView").find("[name='visible']").text(visibleStr);
};

//编辑数据
function BindEditEvent() {
	$("#btnEditOK").unbind("click").click(function() {
		var validate = $("#ffEdit").form("validate");
		if (validate == false) {
			return false;
		};
		
		//应用列表
		var appArray=convertListToArray($('#searchAppCombo').combobox('getData'),"appId");

		// 获取表单数据
		var postData = $("#ffEdit").serializeArray();
		$.each(postData, function(i, obj) {
			if (obj != null && obj.name == "parentId") {
				
				//获取应用ID
				var tmpObj={};
				tmpObj.name="appId";
				
				if(jQuery.inArray(obj.value, appArray)!=-1){
					
					tmpObj.value=obj.value;
					obj.value = "-1";
					
				}else if(obj.value!=-1){
					
					//获取应用ID
					var tree=$("#editMenuParentIdTree").combotree('tree');
					//获取根ID
					var rootNode=getCombotreeRoot(tree,tree.tree("find",obj.value));
					tmpObj.value=rootNode.id;
					
				}
				
				postData.push(tmpObj);
				
			}
		})
		
		commPostRequest("menu/update", postData, function(data) {
			//$.messager.alert("提示", "修改成功");
			$("#DivEdit").dialog('close');
			//加载树
			reloadTree();
		},function(msg){
			$.messager.alert("提示", isEmpty(msg) ? "修改失败，请您检查" : msg);
		})
	})
};

//切换图标
function changeIcon(obj) {
	$(obj).parent().parent().find("div[icon]").attr('class', $(obj).val());
}