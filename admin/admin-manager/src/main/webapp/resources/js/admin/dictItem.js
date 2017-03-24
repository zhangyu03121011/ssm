$(function() {
	
	//添加数据
	BindAddEvent();
	
	//编辑数据
	BindEditEvent();
	
	//批量添加数据
	BindBatchAddEvent();
});

//初始化grid  
function InitGrid(queryData) {
	$('#grid').datagrid({
		url : 'dict/item/pagination?sessionId='+sessionId,
		title : '通用字典明细项目信息',
		iconCls : 'icon-view',
//		height: 650,
		height : document.body.clientHeight * 0.848,
		width : function() {
			return document.body.clientWidth
		},
		nowrap : true,
		autoRowHeight : false,
		striped : true,
		collapsible : true,
		pagination : true,
		pageSize: 18,
		pageList: [18, 50, 100],
		rownumbers : true,
		sortOrder : 'asc',
		remoteSort : false,
		idField : 'dictItemId',
		queryParams : queryData,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			title : '系统名称',
			field : 'sysAppModel',
			width : 150,
			sortable : true,
			formatter: function (value, rec, index) {  
               if(value.appId=="1"){
            	   return "公共"; 
               }else{
            	   return value.appName;
               }
            }
		}, {
			title : '字典ID',
			field : 'dictId',
			hidden:true
		}, {
			title : '字典名称',
			field : 'name',
			width : 150,
			sortable : true
		}, {
			title : '字典值',
			field : 'value',
			width : 150
		}, {
			title : '描述',
			field : 'descr',
			width : 220
		}, {
			title : '排序',
			field : 'sort',
			width : 80,
			sortable : true
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
		} ] ],
		rowStyler : function(index, row) {
			if (index % 2 == 0) {
				return 'background-color:#F0F5FC;'
			} else {
				return 'background-color:#ffffff;'
			}
		},
		toolbar : [ {
			id : 'btnAdd',
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				ShowAddDialog();
			}
		}, '-', {
			id : 'btnBatchAdd',
			text : '批量添加',
			iconCls : 'icon-batch-add',
			handler : function() {
				ShowBatchAddDialog();
			}
		}, '-', {
			id : 'btnEdit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function() {
				ShowEditOrViewDialog();
			}
		}, '-', {
			id : 'btnDelete',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				Delete();
			}
		}, '-', {
			id : 'btnView',
			text : '查看',
			iconCls : 'icon-table',
			handler : function() {
				ShowEditOrViewDialog("view");
			}
		}, '-', {
			id : 'btnReload',
			text : '刷新',
			iconCls : 'icon-reload',
			handler : function() {
				$("#grid").datagrid("reload")
			}
		} ],
		onDblClickRow : function(rowIndex, rowData) {
			$('#grid').datagrid('uncheckAll');
			$('#grid').datagrid('checkRow', rowIndex);
			ShowEditOrViewDialog();
		}
	})
};

//显示添加对话框
function ShowAddDialog() {
	$("#ffAdd").form("clear");
	var treeObj = $.fn.zTree.getZTreeObj("dictTree");
	if (treeObj!=null && treeObj.getSelectedNodes().length > 0) {
		// 查找一个节点并选择它
		var combotreeObj=$('#addDictTypeTree').combotree('tree');
		var node=combotreeObj.tree('find', treeObj.getSelectedNodes()[0].dictId);
		combotreeObj.tree('select', node.target);
		$('#addDictTypeTree').combotree('setValue',treeObj.getSelectedNodes()[0].dictId);
	} 
	$("#DivAdd").dialog('open').dialog('setTitle', '添加字典数据')
};

//添加数据
function BindAddEvent() {
	$("#btnAddOK").unbind("click").click(function() {
		var validate = $("#ffAdd").form('validate');
		if (validate == false) {
			return false;
		}
		
		//应用列表
		var appArray=convertListToArray($('#searchAppCombo').combobox('getData'),"appId");
		
		//是否根节点
		var isRootNode=false;

		// 获取表单数据
		var postData = $("#ffAdd").serializeArray();
		$.each(postData, function(i, obj) {
			if (obj != null && obj.name == "dictId") {
				
				if(jQuery.inArray(obj.value, appArray)!=-1){
					
					return isRootNode=true;
					
				}else{
					
					//获取应用ID
					var tree=$("#addDictTypeTree").combotree('tree');
					//获取根ID
					var rootNode=getCombotreeRoot(tree,tree.tree("find",obj.value));
					
					//获取应用ID
					var tmpObj={};
					tmpObj.name="appId";
					tmpObj.value=rootNode.id;
					
				}
				
				postData.push(tmpObj);
				
			}
		})
		
		if(isRootNode){
			$.messager.alert("提示", "字典数据不能添加到根节点");
			return;
		}
		
		// 保存数据
		commPostRequest("dict/item/save",postData, function() {
			//$.messager.alert("提示", "添加成功");
			$("#DivAdd").dialog("close");
			$("#grid").datagrid("reload");
			$("#ffAdd").form("clear");
		}, function(msg) {
			$.messager.alert("提示", isEmpty(msg) ? "添加失败，请您检查" : msg);
		});
	})
};

//显示字典详情
function loadTypeData(dictId) {
	$("#loading").show();
	var queryParams = $('#grid').datagrid('options').queryParams;
	queryParams.dictId = dictId;
	$("#grid").datagrid("reload");
	$('#grid').datagrid('uncheckAll');
	$("#loading").fadeOut(500);
};

//显示编辑/详情对话框
function ShowEditOrViewDialog(view) {
	var rows = $("#grid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择一条记录", "error");
		return;
	} else if (rows.length > 1) {
		$.messager.alert("提示","每次只能修改/查看一条记录，你已经选择了<font color='red'  size='6'>"+ rows.length + "</font>条", "error");
		return;
	}
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
	$('#ffEdit').form('load', $("#grid").datagrid("getSelections")[0]);
};

//显示详情信息
function BindViewInfo() {
	var data=$("#grid").datagrid("getSelections")[0];
	fillData($("#ffView"), data);
	//查找父级节点
	var treeObj = $.fn.zTree.getZTreeObj("dictTree");
	var node = treeObj.getNodeByParam("dictId", data.dictId, null);
	$("#ffView").find("[name='dictId']").text(node.name);
};

//编辑数据
function BindEditEvent() {
	$("#btnEditOK").unbind("click").click(function() {
		var validate = $("#ffEdit").form("validate");
		if (validate == false) {
			return false;
		}
		
		//应用列表
		var appArray=convertListToArray($('#searchAppCombo').combobox('getData'),"appId");
		
		//是否根节点
		var isRootNode=false;

		// 获取表单数据
		var postData = $("#ffEdit").serializeArray();
		$.each(postData, function(i, obj) {
			if (obj != null && obj.name == "dictId") {
				
				if(jQuery.inArray(obj.value, appArray)!=-1){
					
					return isRootNode=true;
					
				}else{
					
					//获取应用ID
					var tree=$("#editDictTypeTree").combotree('tree');
					//获取根ID
					var rootNode=getCombotreeRoot(tree,tree.tree("find",obj.value));
					
					//获取应用ID
					var tmpObj={};
					tmpObj.name="appId";
					tmpObj.value=rootNode.id;
					
				}
				
				postData.push(tmpObj);
				
			}
		})
		
		if(isRootNode){
			$.messager.alert("提示", "字典数据不能添加到根节点");
			return;
		}
		
		commPostRequest("dict/item/update", postData, function(data) {
			//$.messager.alert("提示", "修改成功");
			$("#DivEdit").dialog('close');
			$("#grid").datagrid("reload");
		},function(msg){
			$.messager.alert("提示", isEmpty(msg) ? "修改失败，请您检查" : msg);
		})
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
					obj.dictItemId=objRow.dictItemId;
					obj.isavailable=0;
					objList.push(obj);
				});
				commAjaxRequest("dict/item/batch",JSON.stringify(objList), function(data){
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
		$.messager.alert("提示", "请选择你要删除的数据")
	}
};

//显示批量添加数据
function ShowBatchAddDialog() {
	$("#ffBatchAdd").form("clear");
	$('input:radio[name="splitType"][value="split"]').prop('checked', true);
	$("#DivBatchAdd").dialog('open').dialog('setTitle', '批量添加字典数据');
};

//批量添加数据
function BindBatchAddEvent() {
	$("#btnBatchAddOK").unbind("click").click(function() {
		var validate = $("#ffBatchAdd").form('validate');
		if (validate == false) {
			return false;
		}
		var postData = $("#ffBatchAdd").serializeJson();
		var objList=[];
		var valueTmp=postData.value;
		var array=[];
		//分隔符方式，多个数据中英文逗号，分号，斜杠或顿号[, ， ; ； / 、]分开
		if(postData.splitType=="split"){
			array=valueTmp.split(/[ |，,；;、]/);
		}else{//一行一个记录模式，忽略所有分隔符号
			array=valueTmp.split("/n");
		}
		//排序为空，默认为0
		if(!postData.sort){
			postData.sort=0;
		}
		$.each(array,function(i,str){
			if(!isEmpty(str)){
				var obj=jQuery.extend({}, postData);
				obj.sort=postData.sort+i;
				obj.value=str;
				obj.flag=true;
				objList.push(obj);
			}
		});
		commAjaxRequest("dict/item/batch", JSON.stringify(objList), function(data) {
			//$.messager.alert("提示", "添加成功");
			$("#DivBatchAdd").dialog("close");
			$("#ffBatchAdd").form("clear");
			//选中添加的节点
			var treeObj = $.fn.zTree.getZTreeObj("dictTree");
			var node = treeObj.getNodeByParam("dictId", postData.dictId, null);
			//选中添加的节点
			treeObj.selectNode(node);
			loadTypeData(postData.dictId);
		},function(msg){
			$.messager.alert("提示", isEmpty(msg) ? "添加失败，请您检查" : msg);
		})
	})
};