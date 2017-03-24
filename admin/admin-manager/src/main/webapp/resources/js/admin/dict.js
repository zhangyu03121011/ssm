$(function() {
	
	// 加载ztree
	reloadTree();
	
	//添加数据
	BindAddTypeEvent();
	
	//编辑数据
	BindEditTypeEvent();
	
	$("#loading").center();
	
	//展开所有节点
	$("#expandAllBtn").bind("click", {
		type : "expandAll"
	}, expandNode);

	//折叠所有节点
	$("#collapseAllBtn").bind("click", {
		type : "collapseAll"
	}, expandNode);
	
});

//Ztree设置
var setting = {
	data : {
		simpleData : {
			enable : true,
			idKey: "dictId",
			pIdKey: "parentId",
			rootPId: -1
		}
	},
	callback : {
		onClick : onClick,
		onDblClick : onDblClick
	}
};

// 加载ztree
function reloadTree() {

	$("#loading").show();
	commPostRequest("dict/list", {}, function(data) {
		
		//加载应用列表信息
		commPostRequest("app/list", {}, function(appList) {
			
			var appListTmp=[];
			
			//添加公共节点
			var item={};
			item.appId="1";
			item.appName="公共";
			appListTmp.push(item);
			
			var appListResult=jQuery.extend([],appList.list);
			
			//合并数组
			appListResult=appListTmp.concat(appListResult);
			
			//加载应用列表下拉框
			$('#searchAppCombo').combobox("loadData", appListResult);
			
			var result=jQuery.extend([], data.list);
			
			$.each(result,function(i,item){
				if(item.parentId==-1){
					item.parentId=item.appId;
				}
			})
			
			$.each(appListResult,function(i,item){
				item.parentId=-1;
				item.dictId=item.appId;
				item.name=item.appName;
				result.push(item);
			})
			
			$.fn.zTree.init($("#meibaTree"), setting, result);
			var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
			treeObj.expandAll(true);
			var treeNodes = treeObj.getNodes();
			if (treeNodes != null && treeNodes.length>0) {
				//选中添加的节点
				treeObj.selectNode(treeNodes[0]);
				//初始化grid
				InitGrid({dictId:treeNodes[0].dictId});
			}else{
				//初始化grid
				InitGrid();
			}

			var treeData=convertToTree(result,"dictId");
			$('#searchDictIdTree').combotree("loadData", treeData);
			$('#addDictParentIdTree').combotree("loadData", treeData);
			$('#editDictParentIdTree').combotree("loadData", treeData);
			$('#addDictTypeTree').combotree("loadData", treeData);
			$('#editDictTypeTree').combotree("loadData", treeData);
			$('#batchDictTypeTree').combotree("loadData", treeData);
			
			$("#loading").fadeOut(500);
			
		});

	},function(){
		$("#loading").fadeOut(500);
	})

};

//单击显示字典详情
function onClick(event, treeId, treeNode, clickFlag) {
	loadTypeData(treeNode.dictId)
};

//双击显示字典详情
function onDblClick(event, treeId, treeNode) {
	loadTypeData(treeNode.dictId);
	ShowDictType('edit')
};

// 添加数据字典
function ShowDictType(type) {
	if (type == 'add') {
		$("#ffAddType").form("clear");
		var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
		if (treeObj!=null && treeObj.getSelectedNodes().length > 0) {
			// 查找一个节点并选择它
			var combotreeObj=$('#addDictParentIdTree').combotree('tree');
			var node=combotreeObj.tree('find', treeObj.getSelectedNodes()[0].dictId);
			combotreeObj.tree('select', node.target);
			$('#addDictParentIdTree').combotree('setValue',treeObj.getSelectedNodes()[0].dictId);
		} 
		$("#DivAddType").dialog('open').dialog('setTitle', '添加字典大类')
	} else if (type == 'edit') {
		var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
		if (treeObj.getSelectedNodes().length == 0) {
			$.messager.alert("提示", "请选择要修改的节点", "error");
			return;
		} 
		$('#ffEditType').form('load', treeObj.getSelectedNodes()[0]);
		if($("#editDictParentIdTree").combo("getValue")=="-1"){
			$("#editDictParentIdTree").combo("clear");
		}
		$("#DivEditType").dialog('open').dialog('setTitle', '修改字典大类')
	}
};

// 添加数据字典
function BindAddTypeEvent() {
	$("#btnAddOKType").unbind("click").click(function() {
		// 验证表单
		var validate = $("#ffAddType").form('validate');
		if (validate == false) {
			return false;
		}
		
		//应用列表
		var appArray=convertListToArray($('#searchAppCombo').combobox('getData'),"appId");

		// 获取表单数据
		var postData = $("#ffAddType").serializeArray();
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
					var tree=$("#addDictParentIdTree").combotree('tree');
					//获取根ID
					var rootNode=getCombotreeRoot(tree,tree.tree("find",obj.value));
					tmpObj.value=rootNode.id;
					
				}
				
				postData.push(tmpObj);
				
			}
		})

		// 保存数据
		commPostRequest("dict/save",postData, function() {
			//$.messager.alert("提示", "添加成功");
			$("#DivAddType").dialog("close");
			reloadTree();
			$("#ffAddType").form("clear");
		}, function(msg) {
			$.messager.alert("提示", isEmpty(msg) ? "添加失败，请您检查" : msg);
		});

	});
};

// 编辑数据字典
function BindEditTypeEvent() {
	$("#btnEditOKType").unbind("click").click(function() {
		// 验证表单
		var validate = $("#ffEditType").form("validate");
		if (validate == false) {
			return false;
		}
		
		//应用列表
		var appArray=convertListToArray($('#searchAppCombo').combobox('getData'),"appId");

		// 获取表单数据
		var postData = $("#ffEditType").serializeArray();
		$.each(postData, function(i, obj) {
			if (obj != null && obj.name == "parentId") {
				
				if(isEmpty(obj.value)){
					obj.value = "-1";
				}
				
				//获取应用ID
				var tmpObj={};
				tmpObj.name="appId";
				
				if(jQuery.inArray(obj.value, appArray)!=-1){
					
					tmpObj.value=obj.value;
					obj.value = "-1";
					
				}else{
					
					//获取应用ID
					var tree=$("#addDictParentIdTree").combotree('tree');
					//获取根ID
					var rootNode=getCombotreeRoot(tree,tree.tree("find",obj.value));
					tmpObj.value=rootNode.id;
					
				}
				
				postData.push(tmpObj);
				
			}
		})

		commPostRequest("dict/update",postData, function() {
			//$.messager.alert("提示", "修改成功");
			$("#DivEditType").dialog('close');
			reloadTree()
		}, function(msg) {
			$.messager.alert("提示", isEmpty(msg) ? "修改失败，请您检查" : msg);
		});
	})
}

//删除数据字典
function deleteTypeData() {
	
	var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
	if (treeObj.getSelectedNodes().length == 0) {
		$.messager.alert("提示", "请选择要删除的节点", "error");
		return;
	} 
	
	$.messager.confirm("删除确认信息", "您要确定删除该条记录吗？", function(deleteAction) {
		if (deleteAction) {
			var obj={};
			obj.dictId=treeObj.getSelectedNodes()[0].dictId;
			obj.isavailable=0;
			commPostRequest("dict/update",obj, function() {
				//$.messager.alert("提示", "删除成功！ ");
				reloadTree()
			}, function(msg) {
				$.messager.alert("提示", isEmpty(msg) ?"删除失败，请您检查": msg);
			});
		}
	})
	
};
