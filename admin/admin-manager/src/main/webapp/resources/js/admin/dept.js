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
			name:"deptName"
		},
		simpleData: {
			enable: true,
			idKey: "deptId",
			pIdKey: "parentId",
			rootPId: -1
		}
	},
	callback: {
		onClick: onClick
	}
};

function reloadRelation() {
	var id = $("#txtID").val();
	loadData(id)
};

//加载ztree
function reloadTree() {
	$("#loading").show();
	commPostRequest("dept/list", {}, function(data) {

		$.fn.zTree.init($("#meibaTree"), setting, data.list);
		var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
		treeObj.expandAll(true);
		
		var treeData=convertToTree(data.list,"deptId","deptName");
		
		$('#addDeptParentIdTree').combotree("loadData", treeData);
		
		$("#loading").fadeOut(500);
	},function(){
		$("#loading").fadeOut(500);
	})
};

//单击显示部门详情
function onClick(event, treeId, treeNode, clickFlag) {
	loadData(treeNode.deptId)
};

//加载部门信息
function loadData(deptId) {
	$("#loading").show();
	
	//加载部门详细信息
	commGetRequest("dept/detail", {id:deptId}, function(data) {
		$('#addDeptForm').form('load', data.obj);
		
		if($("#addDeptParentIdTree").combo("getValue")=="-1"){
			$("#addDeptParentIdTree").combo("clear");
		}
	})
	
	//加载用户信息
	loadDeptUser(deptId);
	
	//加载角色列表信息
	loadDeptRole(deptId);
	
	$("#loading").fadeOut(500)
};

//加载角色列表信息
function loadDeptRole(deptId){
	$('#deptRoleSelect').empty();
	commPostRequest("dept/role/list", {deptId:deptId}, function(data) {
		$.each(data.list, function(i, item) {
			$('#deptRoleSelect').append('<option value="' + item.sysRoleModel.roleId + '">' + item.sysRoleModel.roleName + '</option>')
		})
	})
}

//加载用户信息
function loadDeptUser(deptId){
	$('#deptUserSelect').empty();
	commPostRequest("user/dept", {deptId:deptId}, function(data) {
		$.each(data.list, function(i, item) {
			$('#deptUserSelect').append('<option value="' + item.userId + '">' + item.userName + '</option>')
		})
	})
}

//保存部门数据
function saveData() {
	var validate = $("#addDeptForm").form('validate');
	if (validate == false) {
		return false;
	};
	
	// 获取表单数据
	var postData = $("#addDeptForm").serializeArray();
	
	var url="dept/save";
	var validFlag=true;
	$.each(postData, function(i, obj) {
		if (obj != null && obj.name == "parentId" && isEmpty(obj.value)) {
			obj.value = "-1";
		}
		if (obj != null && obj.name == "deptId" && !isEmpty(obj.value)) {
			url="dept/update";
			validFlag=false;
		}
	})
	
	postData.push({name:"validFlag",value:validFlag});
	
	// 保存数据
	commPostRequest(url,postData, function() {
		//$.messager.alert("提示", "添加成功");
		$("#addDeptForm").form("clear");
		//加载树
		reloadTree();
	}, function(msg) {
		$.messager.alert("提示", isEmpty(msg) ? "添加失败，请您检查" : msg);
	});
};

//添加部门信息
function addData() {
	$("#addDeptForm").form("clear");
};

//删除部门数据
function delData() {
	var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
	if (treeObj==null || treeObj.getSelectedNodes().length == 0) {
		$.messager.alert("提示", "请选择要删除的记录");
		return;
	}
	$.messager.confirm("删除确认信息", "您要确定删除该条记录吗？", function(deleteAction) {
		if (deleteAction) {
			var obj={};
			obj.deptId=treeObj.getSelectedNodes()[0].deptId;
			obj.isavailable=0;
			commPostRequest("dept/update",obj, function() {
				$("#addDeptForm").form("clear");
				//加载树
				reloadTree();
			}, function(msg) {
				$.messager.alert("提示", isEmpty(msg) ? "删除失败，请您检查" : msg);
			});
		}
	})
};

//显示树编辑对话框
function ShowEditTree(type) {
	
	var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
	//当前没有有选中角色对象
	if (treeObj==null || treeObj.getSelectedNodes().length == 0) {
		$.messager.alert("提示", "请选择要添加的节点");
		return;
	}
	
	//加载tree
	reloadEditTree(type);
	
	//获取对话框DIV对象
	var divName = getDivName(type);
	
	if (type == 'user') {
		$(divName).dialog('open').dialog('setTitle', '部门包含的用户')
	} else if (type == "role") {
		$(divName).dialog('open').dialog('setTitle', '部门包含的角色')
	} 
}

//获取对话框DIV
function getDivName(type) {
	if (type == "user") {
		return "#DivEditUser";
	} else if (type == "role") {
		return "#DivEditRole";
	} 
};

//获取tree对象DIV
function getTreeName(type) {
	if (type == "user") {
		return "userTree";
	} else if (type == "role") {
		return "roleTree";
	}
};

//编辑树对象设置
var settingEdit = {
	check:{
		enable: true,
		chkStyle: "checkbox",
		chkboxType: {
			"Y": "1",
			"N": "0"
		}
	},
	data: {
		simpleData: {
			enable: true
		},
		key:{}
	}
};

//加载编辑对象Tree
function reloadEditTree(type) {
	$("#loading").show();
	
	var url;
	var selectType;
	if (type == 'user') {
		
		url="user/list";
		selectType="deptUserSelect";
		settingEdit.data.key.name="userName";
		settingEdit.data.simpleData.idKey="userId";
		
	} else if (type == "role") {
		
		url="role/list";
		selectType="deptRoleSelect";
		settingEdit.data.key.name="roleName";
		settingEdit.data.simpleData.idKey="roleId";
		
	} 
	
	//加载tree对象
	if (!isEmpty(url)) {
		commPostRequest(url, {}, function(data) {

			//获取tree Div
			var treeName = getTreeName(type);
			
			$.fn.zTree.init($("#"+treeName), settingEdit, data.list);
			var treeObj = $.fn.zTree.getZTreeObj(treeName);
			treeObj.expandAll(true);

			var data = $("#"+selectType+" option").map(function(){return $(this).val();}).get().join(",").split(",");
			
			//选中已经添加的节点
			$.each(data, function(i, item) {
				var node = treeObj.getNodeByParam(settingEdit.data.simpleData.idKey, item);
				if (node != null) {
					treeObj.checkNode(node, true, false);
				}
			})
			
		})
	}
		
	$("#loading").fadeOut(500);
};

//全选Tree节点
function CheckAll(obj, type) {
	var checked = $(obj).prop("checked");
	var treeName = getTreeName(type);
	var treeObj = $.fn.zTree.getZTreeObj(treeName);
	//选中tree节点
	if (checked) {
		treeObj.checkAllNodes(true);
	} else {
		treeObj.checkAllNodes(false);
	}
};

//保存关系数据
function saveEditData(type) {
	$("#loading").show();
	
	//获取tree Div
	var treeName = getTreeName(type);
	//获取选中的tree节点
	var treeObj = $.fn.zTree.getZTreeObj(treeName);
	var postData = treeObj.transformToArray(treeObj.getCheckedNodes(true));
	
	//获取key
	var idStr=treeObj.setting.data.simpleData.idKey;
	
	//保存关系数据
	saveEditDataComm(type,postData,idStr);
};

//保存关系数据
function saveEditDataComm(type,postData,idStr,deleteFlag) {
	
	var url;
	if (type == 'user') {
		
		url="dept/user/batch";
		
	} else if (type == "role") {
		
		url="dept/role/batch";
		
	}
	
	// 保存数据
	if (!isEmpty(url)) {
		
		var treeRoleObj = $.fn.zTree.getZTreeObj("meibaTree");
		var deptId=treeRoleObj.getSelectedNodes()[0].deptId;
		
		var objList=[];
		if(postData && postData.length>0){
			$.each(postData,function(i,objRow){
				var obj={};
				obj[idStr]=objRow[idStr];
				obj.deptId=deptId;
				obj.flag=true;
				obj.sysDeptModel={};
				obj.sysDeptModel.deptId=deptId;
				obj.deleteFlag=deleteFlag?deleteFlag:false;
				objList.push(obj);
			});
		}else{
			var obj={};
			obj.deptId=deptId;
			objList.push(obj);
		}
		
		commAjaxRequest(url,JSON.stringify(objList), function() {
			
			//$.messager.alert("提示", "操作成功！ ");
			$(getDivName(type)).dialog('close');
			
			if (type == 'user') {
				
				//加载用户信息
				loadDeptUser(deptId);
				
			} else if (type == "role") {
				
				//加载角色列表信息
				loadDeptRole(deptId);
				
			} 
			
		}, function(msg) {
			$.messager.alert("提示", isEmpty(msg) ? "添加失败，请您检查" : msg);
		});
	}
	
	$("#loading").fadeOut(500);
};

//删除角色关系
function deleteRelation(type) {
	var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
	if (treeObj==null || treeObj.getSelectedNodes().length == 0) {
		$.messager.alert("提示", "请选择要操作的角色记录");
		return;
	}
	
	var selectType;
	var idStr;
	if (type == 'user') {
		
		selectType="deptUserSelect";
		idStr="userId";
		
	} else if (type == "role") {
		
		selectType="deptRoleSelect";
		idStr="roleId";
		
	}
	
	var postData = $("#"+selectType+" option:selected").map(function(){return $(this).val();}).get().join(",").split(",");
	if(isEmpty(postData)){
		$.messager.alert("提示", "请选择要删除的记录");
		return;
	}
	
	var dataArray=[];
	$.each(postData,function(i,str){
		var obj={};
		obj[idStr]=str;
		dataArray.push(obj);
	});
	
	$.messager.confirm("删除确认信息", "您要确定删除该条记录吗？", function(deleteAction) {
		if (deleteAction) {
			//保存关系数据
			saveEditDataComm(type,dataArray,idStr,true);
		}
	})
};