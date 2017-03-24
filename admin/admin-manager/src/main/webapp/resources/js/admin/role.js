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
			name:"roleName"
		},
		simpleData: {
			enable: true,
			idKey: "roleId"
		}
	},
	callback: {
		onClick: onClick
	}
};

//加载ztree
function reloadTree() {
	$("#loading").show();
	commPostRequest("role/list", {}, function(data) {

		$.fn.zTree.init($("#meibaTree"), setting, data.list);
		var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
		treeObj.expandAll(true);
		
		$("#loading").fadeOut(500);
	},function(){
		$("#loading").fadeOut(500);
	})
};

//单击显示角色详情
function onClick(event, treeId, treeNode, clickFlag) {
	loadData(treeNode.roleId);
}

//加载角色信息
function loadData(roleId) {
	$("#loading").show();
	
	//加载角色详细信息
	commGetRequest("role/detail", {id:roleId}, function(data) {
		if(data!=null && data.obj!=null && data.obj.roleKey=="admin"){
			$("#addRoleForm input[name=roleKey]").css("backgroundColor","#F0EAEA").attr("readonly","readonly");
		}else{
			$("#addRoleForm input[name=roleKey]").css("backgroundColor","").removeAttr("readonly");
		}
		$('#addRoleForm').form('load', data.obj);
	})
	
	//加载角色菜单功能信息
	loadRoleMenuFunctionTree(roleId);
	
	//加载用户信息
	loadRoleUser(roleId);
	
	//加载部门信息
	loadRoleDept(roleId);
	
	$("#loading").fadeOut(500);
};

//加载用户信息
function loadRoleUser(roleId){
	$('#roleUserSelect').empty();
	commPostRequest("user/role/list", {roleId:roleId}, function(data) {
		$.each(data.list, function(i, item) {
			$('#roleUserSelect').append('<option value="' + item.sysUserModel.userId + '">' + item.sysUserModel.userName + '</option>')
		})
	})
}

//加载部门信息
function loadRoleDept(roleId){
	$('#roleDeptSelect').empty();
	commPostRequest("dept/role/list", {roleId:roleId}, function(data) {
		$.each(data.list, function(i, item) {
			$('#roleDeptSelect').append('<option value="' + item.sysDeptModel.deptId + '">' + item.sysDeptModel.deptName + '</option>')
		})
	})
}

//保存角色
function saveData() {
	var validate = $("#addRoleForm").form('validate');
	if (validate == false) {
		return false;
	};
	
	// 获取表单数据
	var postData = $("#addRoleForm").serializeArray();
	
	var url="role/save";
	var validFlag=true;
	$.each(postData, function(i, obj) {
		if (obj != null && obj.name == "roleId" && !isEmpty(obj.value)) {
			url="role/update";
			validFlag=false;
		}
	})
	
	postData.push({name:"validFlag",value:validFlag});
	
	// 保存数据
	commPostRequest(url,postData, function() {
		//$.messager.alert("提示", "添加成功");
		$("#addRoleForm").form("clear");
		//加载树
		reloadTree();
	}, function(msg) {
		$.messager.alert("提示", isEmpty(msg) ? "添加失败，请您检查" : msg);
	});
};

//添加角色信息
function addData() {
	$("#addRoleForm").form("clear");
};

//删除角色信息
function delData() {
	var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
	if (treeObj==null || treeObj.getSelectedNodes().length == 0) {
		$.messager.alert("提示", "请选择要删除的记录");
		return;
	}
	$.messager.confirm("删除确认信息", "您要确定删除该条记录吗？", function(deleteAction) {
		if (deleteAction) {
			var obj={};
			obj.roleId=treeObj.getSelectedNodes()[0].roleId;
			obj.isavailable=0;
			commPostRequest("role/update",obj, function() {
				$("#addRoleForm").form("clear");
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
		$(divName).dialog('open').dialog('setTitle', '角色包含的用户')
	} else if (type == "dept") {
		$(divName).dialog('open').dialog('setTitle', '角色包含的机构')
	}
}

//获取对话框DIV
function getDivName(type) {
	if (type == "user") {
		return "#DivEditUser";
	} else if (type == "dept") {
		return "#DivEditDept";
	}
};

//获取tree对象DIV
function getTreeName(type) {
	if (type == "user") {
		return "userTree";
	} else if (type == "dept") {
		return "deptTree";
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
		selectType="roleUserSelect";
		settingEdit.data.key.name="userName";
		settingEdit.data.simpleData.idKey="userId";
		
	} else if (type == "dept") {
		
		url="dept/list";
		selectType="roleDeptSelect";
		settingEdit.data.key.name="deptName";
		settingEdit.data.simpleData.idKey="deptId";
		
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
		
		url="user/role/batch";
		
	} else if (type == "dept") {
		
		url="dept/role/batch";
		
	}
	
	// 保存数据
	if (!isEmpty(url)) {
		
		var treeRoleObj = $.fn.zTree.getZTreeObj("meibaTree");
		var roleId=treeRoleObj.getSelectedNodes()[0].roleId;
		
		var objList=[];
		if(postData && postData.length>0){
			$.each(postData,function(i,objRow){
				var obj={};
				obj[idStr]=objRow[idStr];
				obj.roleId=roleId;
				obj.flag=true;
				obj.sysRoleModel={};
				obj.sysRoleModel.roleId=roleId;
				obj.deleteFlag=deleteFlag?deleteFlag:false;
				objList.push(obj);
			});
		}else{
			var obj={};
			obj.roleId=roleId;
			objList.push(obj);
		}
		
		commAjaxRequest(url,JSON.stringify(objList), function() {
			
			//$.messager.alert("提示", "操作成功！ ");
			$(getDivName(type)).dialog('close');
			
			if (type == 'user') {
				
				//加载用户信息
				loadRoleUser(roleId);
				
			} else if (type == "dept") {
				
				//加载部门信息
				loadRoleDept(roleId);
				
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
		
		selectType="roleUserSelect";
		idStr="userId";
		
	} else if (type == "dept") {
		
		selectType="roleDeptSelect";
		idStr="deptId";
		
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

//刷新角色菜单功能信息
function reloadRoleMenuFunctionTree(){
	var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
	if (treeObj==null || treeObj.getSelectedNodes().length == 0) {
		$.messager.alert("提示", "请选择要操作的角色记录");
		return;
	}
	
	//加载角色菜单功能信息
	loadRoleMenuFunctionTree(treeObj.getSelectedNodes()[0].roleId);
}

//折叠角色菜单功能所有节点
function collapseRoleMenuFunctionAll(){
	$("#roleMenuFunctionTree").tree("collapseAll");
}

//展开角色菜单功能所有节点
function expandRoleMenuFunctionAll(){
	$("#roleMenuFunctionTree").tree("expandAll");
}

//加载角色菜单功能信息
function loadRoleMenuFunctionTree(roleId){
	$("#loading").show();
	commPostRequest("menu/list", {}, function(data) {
		
		//加载应用列表信息
		commPostRequest("app/list", {}, function(appList) {
			
			//加载功能列表信息
			commPostRequest("function/list", {}, function(functionList) {
				
				//加载角色菜单功能列表信息
				commPostRequest("role/menu/function/list", {roleId:roleId}, function(roleMenuFunctionList) {
					
					var result=jQuery.extend([], data.list);
					
					$.each(result,function(i,item){
						if(item.parentId==-1){
							item.parentId=item.appId;
						}
						if(!isEmpty(item.menuUrl)){
							$.each(functionList.list,function(i,functionItem){
								var subItem=jQuery.extend([], functionItem);
								if(functionItem.parentId==-1){
									subItem.parentId=item.menuId;
								}else{
									subItem.parentId=item.menuId+"@"+functionItem.parentId;
								}
								subItem.menuId=item.menuId+"@"+functionItem.functionId;
								subItem.menuName=functionItem.functionName;
								result.push(subItem);
							})
						}
					})
					
					$.each(appList.list,function(i,item){
						item.parentId=-1;
						item.menuId=item.appId;
						item.menuName=item.appName;
						result.push(item);
					})
					
					var treeData=convertToTree(result,"menuId","menuName");
					
					//加载菜单树列表
					$("#roleMenuFunctionTree").tree("loadData",treeData);
					
					//选中节点
					$.each(roleMenuFunctionList.list,function(i,item){
						$("#roleMenuFunctionTree").tree("check",$("#roleMenuFunctionTree").tree("find",item.sysRoleMenuModel.menuId+"@"+item.functionId).target);
					})
					
					$("#loading").fadeOut(500);
					
				})
			})
		})
	},function(){
		$("#loading").fadeOut(500);
	})
}

//保存角色菜单功能信息
function saveRoleMenuFunctionTree(){
	
	//选中角色节点
	var treeObj = $.fn.zTree.getZTreeObj("meibaTree");
	if (treeObj==null || treeObj.getSelectedNodes().length == 0) {
		$.messager.alert("提示", "请选择要操作的角色记录");
		return;
	}
	
	$("#loading").show();
	
	var roleId=treeObj.getSelectedNodes()[0].roleId;
	var postData = $("#roleMenuFunctionTree").tree("getChecked", ['checked', 'indeterminate']);
	
	//加载应用列表信息
	commPostRequest("app/list", {}, function(appList) {
		
		//应用ID
		var appIdList=[];
		$.each(appList.list,function(i,obj){
			appIdList.push(obj.appId);
		})
		
		var objList=[];
		if(postData && postData.length>0){
			$.each(postData,function(i,objRow){
				
				//排除应用ID
				if(jQuery.inArray(objRow.id, appIdList)==-1){
					
					var obj={};
					obj.flag=true;
					obj.sysRoleMenuModel={};
					obj.sysRoleMenuModel.roleId=roleId;
					obj.sysRoleMenuModel.flag=true;
					
					//获取应用ID（根目录）
					obj.sysRoleMenuModel.appId=$("#roleMenuFunctionTree").tree("getRoot",objRow.target).id;
					
					if(objRow.id.indexOf("@")!=-1){
						obj.functionId=objRow.id.split("@")[1];
						obj.sysRoleMenuModel.menuId=objRow.id.split("@")[0];
					}else{
						obj.sysRoleMenuModel.menuId=objRow.id;
					}
					objList.push(obj);
					
				}
				
			});
		}else{
			var obj={};
			obj.sysRoleMenuModel={};
			obj.sysRoleMenuModel.roleId=roleId;
			objList.push(obj);
		}
		
		commAjaxRequest("role/menu/function/batch",JSON.stringify(objList), function() {
			$.messager.alert("提示", "更新数据成功");
		}, function(msg) {
			$.messager.alert("提示", isEmpty(msg) ? "添加失败，请您检查" : msg);
		});
		
		$("#loading").fadeOut(500);
	})

}