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
			name:"userName"
		},
		simpleData: {
			enable: true,
			idKey: "userId"
		}
	},
	callback: {
		onClick: onClick
	}
};

//加载ztree
function reloadTree() {
	$("#loading").show();
	commPostRequest("user/list", {}, function(data) {

		$.fn.zTree.init($("#meibaTree"), setting, data.list);
		var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
		treeObj.expandAll(true);
		
		//加载部门信息
		commPostRequest("dept/list", {}, function(data) {
			
			$('#addDeptParentIdTree').combotree("loadData", convertToTree(data.list,"deptId","deptName"));
			
		})
		
		$("#loading").fadeOut(500);
	},function(){
		$("#loading").fadeOut(500);
	})
	$("#loading").fadeOut(500)
};

//单击显示用户详情
function onClick(event, treeId, treeNode, clickFlag) {
	loadData(treeNode.userId)
};

//加载用户信息
function loadData(userId) {
	
	$("#loading").show();
	$("#addUserForm").form("clear");
	
	//禁用验证
	$(":password").each(function(i,input){
		$(input).validatebox('disableValidation');
	})
	
	//加载部门详细信息
	commGetRequest("user/detail", {id:userId}, function(data) {
		data.obj.birthday=isEmpty(data.obj.birthday)?null:data.obj.birthday.split(" ")[0];
		//$("#addUserForm").form("disableValidation");
		$('#addUserForm').form('load', data.obj);
	})
	
	//加载角色列表信息
	loadUserRole(userId);
	
	//加载应用信息
	loadUserApp(userId);
	
	$("#loading").fadeOut(500)
};

//加载角色列表信息
function loadUserRole(userId){
	$('#userRoleSelect').empty();
	commPostRequest("user/role/list", {userId:userId}, function(data) {
		$.each(data.list, function(i, item) {
			$('#userRoleSelect').append('<option value="' + item.sysRoleModel.roleId + '">' + item.sysRoleModel.roleName + '</option>')
		})
	})
}

//加载部门信息
function loadUserApp(userId){
	$('#userAppSelect').empty();
	commPostRequest("user/app/list", {userId:userId}, function(data) {
		$.each(data.list, function(i, item) {
			$('#userAppSelect').append('<option value="' + item.sysAppModel.appId + '">' + item.sysAppModel.appName + '</option>')
		})
	})
}

//重置密码(默认为888888)
function resetPassword() {
	var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
	if (treeObj==null || treeObj.getSelectedNodes().length == 0) {
		$.messager.alert("提示", "请选择要重置密码的用户");
		return;
	}
	$.messager.confirm("确认信息", "您要确定重置该用户密码吗？", function(resetPasswordAction) {
		if (resetPasswordAction) {
			var obj={};
			obj.userId=treeObj.getSelectedNodes()[0].userId;
			obj.password="888888";
			commPostRequest("user/update",obj, function() {
				$.messager.alert("提示", "密码重置成功");
				$("#addUserForm").form("clear");
				//加载树
				reloadTree();
			}, function(msg) {
				$.messager.alert("提示", isEmpty(msg) ? "重置失败，请您检查" : msg);
			});
		}
	})
};

//保存用户数据
function saveData() {
	var validate = $("#addUserForm").form('validate');
	if (validate == false) {
		return false;
	};
	
	// 获取表单数据
	var postData = $("#addUserForm").serializeArray();
	
	var url="user/save";
	$.each(postData, function(i, obj) {
		if (obj != null && obj.name == "userId" && !isEmpty(obj.value)) {
			url="user/update";
		}
	})
	
	// 保存数据
	commPostRequest(url,postData, function() {
		//$.messager.alert("提示", "添加成功");
		$("#addUserForm").form("clear");
		//加载树
		reloadTree();
	}, function(msg) {
		$.messager.alert("提示", isEmpty(msg) ? "添加失败，请您检查" : msg);
	});
};

//添加功能信息
function addData() {
	//禁用验证
	$(":password").each(function(i,input){
		$(input).validatebox('enableValidation');
	})
	
	//取消选中的节点
	var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
	treeObj.cancelSelectedNode();
	
	//清空数据
	$('#userRoleSelect').empty();
	$('#userAppSelect').empty();
	
	$("#addUserForm").form("clear");
};

//删除用户数据
function delData() {
	var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
	if (treeObj==null || treeObj.getSelectedNodes().length == 0) {
		$.messager.alert("提示", "请选择要删除的记录");
		return;
	}
	if(treeObj.getSelectedNodes()[0].userName=="admin"){
		$.messager.alert("提示", "系统管理员不能删除");
		return;
	}
	$.messager.confirm("删除确认信息", "您要确定删除该条记录吗？", function(deleteAction) {
		if (deleteAction) {
			var obj={};
			obj.userId=treeObj.getSelectedNodes()[0].userId;
			obj.isavailable=0;
			commPostRequest("user/update",obj, function() {
				$("#addUserForm").form("clear");
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
	
	if (type == 'role') {
		$(divName).dialog('open').dialog('setTitle', '用户包含的角色')
	} else if (type == "app") {
		$(divName).dialog('open').dialog('setTitle', '用户包含的机构')
	}
}

//获取对话框DIV
function getDivName(type) {
	if (type == "role") {
		return "#DivEditRole";
	} else if (type == "app") {
		return "#DivEditApp";
	}
};

//获取tree对象DIV
function getTreeName(type) {
	if (type == "role") {
		return "roleTree";
	} else if (type == "app") {
		return "appTree";
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
	if (type == 'role') {
		
		url="role/list";
		selectType="userRoleSelect";
		//settingEdit.check.chkStyle="checkbox";
		settingEdit.data.key.name="roleName";
		settingEdit.data.simpleData.idKey="roleId";
		
	} else if (type == "app") {
		
		url="app/list";
		selectType="userAppSelect";
		//settingEdit.check.chkStyle="radio";
		settingEdit.data.key.name="appName";
		settingEdit.data.simpleData.idKey="appId";
		
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
	if (type == 'role') {
		
		url="user/role/batch";
		
	} else if (type == "app") {
		
		url="user/app/batch";
		
	}
	
	// 保存数据
	if (!isEmpty(url)) {
		
		var treeRoleObj = $.fn.zTree.getZTreeObj("meibaTree");
		var userId=treeRoleObj.getSelectedNodes()[0].userId;
		
		var objList=[];
		if(postData && postData.length>0){
			$.each(postData,function(i,objRow){
				var obj={};
				obj[idStr]=objRow[idStr];
				obj.userId=userId;
				obj.flag=true;
				obj.sysUserModel={};
				obj.sysUserModel.userId=userId;
				obj.deleteFlag=deleteFlag?deleteFlag:false;
				objList.push(obj);
			});
		}else{
			var obj={};
			obj.userId=userId;
			objList.push(obj);
		}
		
		commAjaxRequest(url,JSON.stringify(objList), function() {
			
			//$.messager.alert("提示", "操作成功！ ");
			$(getDivName(type)).dialog('close');
			
			if (type == 'role') {
				
				//加载角色列表信息
				loadUserRole(userId);
				
			} else if (type == "app") {
				
				//加载应用信息
				loadUserApp(userId);
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
	if (type == 'role') {
		
		selectType="userRoleSelect";
		idStr="roleId";
		
	} else if (type == "app") {
		
		selectType="userAppSelect";
		idStr="appId";
		
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