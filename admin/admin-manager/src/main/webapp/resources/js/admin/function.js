$(function(){
	
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
});

//Ztree设置
var setting = {
	data: {
		key:{
			name:"functionName"
		},
		simpleData: {
			enable: true,
			idKey: "functionId",
			pIdKey: "parentId",
			rootPId: -1
		}
	},
	callback: {
		onClick: onClick
	}
};

//加载ztree
function reloadTree() {
	$("#loading").show();
	commPostRequest("function/list", {}, function(data) {

		$.fn.zTree.init($("#meibaTree"), setting, data.list);
		var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
		treeObj.expandAll(true);
		
		var treeData=convertToTree(data.list,"functionId","functionName");
		
		$('#addFunctionParentIdTree').combotree("loadData", treeData);
		
		$("#loading").fadeOut(500);
	},function(){
		$("#loading").fadeOut(500);
	})
};

//单击显示功能详情
function onClick(event, treeId, treeNode, clickFlag) {
	loadData(treeNode.functionId)
};

//加载功能信息
function loadData(functionId) {
	
	$("#loading").show();
	
	//加载功能详细信息
	commGetRequest("function/detail", {id:functionId}, function(data) {
		$('#addFunctionForm').form('load', data.obj);
		
		if($("#addFunctionParentIdTree").combo("getValue")=="-1"){
			$("#addFunctionParentIdTree").combo("clear");
		}
	})
	
	$("#loading").fadeOut(500)
};

//保存功能数据
function saveData() {
	var validate = $("#addFunctionForm").form('validate');
	if (validate == false) {
		return false;
	};
	
	// 获取表单数据
	var postData = $("#addFunctionForm").serializeArray();
	
	var url="function/save";
	var validFlag=true;
	$.each(postData, function(i, obj) {
		if (obj != null && obj.name == "parentId" && isEmpty(obj.value)) {
			obj.value = "-1";
		}
		if (obj != null && obj.name == "functionId" && !isEmpty(obj.value)) {
			url="function/update";
			validFlag=false;
		}
	})
	
	postData.push({name:"validFlag",value:validFlag});
	
	// 保存数据
	commPostRequest(url,postData, function() {
		//$.messager.alert("提示", "添加成功");
		$("#addFunctionForm").form("clear");
		//加载树
		reloadTree();
	}, function(msg) {
		$.messager.alert("提示", isEmpty(msg) ? "添加失败，请您检查" : msg);
	});
};

//添加功能信息
function addData() {
	$("#addFunctionForm").form("clear");
};

//删除功能数据
function delData() {
	var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
	if (treeObj==null || treeObj.getSelectedNodes().length == 0) {
		$.messager.alert("提示", "请选择要删除的记录");
		return;
	}
	$.messager.confirm("删除确认信息", "您要确定删除该条记录吗？", function(deleteAction) {
		if (deleteAction) {
			var obj={};
			obj.functionId=treeObj.getSelectedNodes()[0].functionId;
			obj.isavailable=0;
			commPostRequest("function/update",obj, function() {
				$("#addFunctionForm").form("clear");
				//加载树
				reloadTree();
			}, function(msg) {
				$.messager.alert("提示", isEmpty(msg) ? "删除失败，请您检查" : msg);
			});
		}
	})
};