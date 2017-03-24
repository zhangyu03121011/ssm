$(function() {
	//加载树
	reloadTree();
	
	//初始化grid
	InitGridBase();
	
	InitGridAtta();
	
	InitGridParam();
	
	InitGridReplace();
	
	//搜索数据
	BindSearchEvent();
	
	//编辑数据
	BindEditEvent();
	
	//导入数据
	BindUploadEvent();
	
	//tab切换
	$("#tabs").tabs({
		onSelect:function(title,index){
			if(index==0){
				$("#tabFormType").val("Base");
			}else if(index==1){
				$("#tabFormType").val("Atta");
			}else if(index==2){
				$("#tabFormType").val("Param");
			}else if(index==3){
				$("#tabFormType").val("Replace");
			}
		}
	});
	
});

//加载ztree
function reloadTree() {
	$("#ffEditBase").form("clear");
	commPostRequest("catalog/list", {}, function(data) {
		
		var result=jQuery.extend([], data.list);
		var treeDataEdit=convertToTree(result,"id","catalogName");
		
		//加载菜单树列表
		$('#editCatalogTree').combotree("loadData", treeDataEdit);
		
	})
	commPostRequest("brand/list", {}, function(data) {
		
		var result=jQuery.extend([], data.list);
		var treeDataEdit=convertToTree(result,"id","brandName");
		
		//加载菜单树列表
		$('#editBrandTree').combotree("loadData", treeDataEdit);
		
	})
	commPostRequest("param/list", {}, function(data) {
		
		var result=jQuery.extend([], data.list);
		var treeDataEdit=convertToTree(result,"id","paramName");
		
		//加载菜单树列表
		$('#editParamTree').combotree("loadData", treeDataEdit);
		
	})
};

var productColumns=
	[{
		field: 'ck',
		checkbox: true
	}, {
		title: '品牌名称',
		field: 'brandName',
		width: 100,
		sortable: true,
		formatter: function (value, rec, index) { 
			if(rec.productCommonVo){
				return rec.productCommonVo.brandName;
			}
		}
	}, {
		title: '分类名称',
		field: 'catalogName',
		width: 100,
		sortable: true,
		formatter: function (value, rec, index) { 
			if(rec.productCommonVo){
				return rec.productCommonVo.catalogName;
			}
		}
	}, {
		title: '产品型号',
		field: 'productNo',
		width: 100,
		sortable: true
	}];

var baseColumns=
	[{
		title: '产品状态',
		field: 'productState',
		width: 80,
		sortable: true,
		formatter: function (value, rec, index) {  
	           if(value=="0"){
	        	   return "预售"; 
	           }else if(value=="1"){
	        	   return "现行";
	           }else if(value=="2"){
	        	   return "不推荐";
	           }else if(value=="3"){
	        	   return "停产";
	           }
	        }
	}, {
		title: '产品描述',
		field: 'productDesc',
		width: 120,
		sortable: true
	}];

var attaColumns=
	[{
		title: '附件名称',
		field: 'attaName',
		width: 120,
		sortable: true,
		formatter: function (value, rec, index) { 
			if(rec.baseAttaModel){
				return rec.baseAttaModel.fileName;
			}
		}
	}];

var paramColumns=
	[{
		title: '参数名称',
		field: 'paramName',
		width: 120,
		sortable: true,
		formatter: function (value, rec, index) { 
			if(rec.paramVo){
				return rec.paramVo.paramName;
			}
		}
	}, {
		title: '参数值',
		field: 'paramValue',
		width: 50,
		sortable: true
	}];

var replaceColumns=
	[{
		title: '替换产品型号',
		field: 'replaceProductNo',
		width: 120,
		sortable: true
	}, {
		title: '替换产品分类名称',
		field: 'replaceCatalogName',
		width: 120,
		sortable: true,
		formatter: function (value, rec, index) { 
			if(rec.productCommonReplaceVo){
				return rec.productCommonReplaceVo.catalogName;
			}
		}
	},{
		title: '替换产品品牌名称',
		field: 'replaceBrandName',
		width: 120,
		sortable: true,
		formatter: function (value, rec, index) { 
			if(rec.productCommonReplaceVo){
				return rec.productCommonReplaceVo.brandName;
			}
		}
	}, {
		title: '替换产品描述',
		field: 'replaceProductDesc',
		width: 120,
		sortable: true
	}, {
		title: '匹配度',
		field: 'matchType',
		width: 80,
		sortable: true,
		formatter: function (value, rec, index) {  
	           if(value=="1"){
	        	   return "直接替换"; 
	           }else if(value=="2"){
	        	   return "功能相同";
	           }else if(value=="3"){
	        	   return "功能相似";
	           }
	        }
	}];

var commonColumns=
	[{
		title: '状态',
		field: 'state',
		align:"center",
		width: 80,
		sortable: true,
		formatter: function (value, rec, index) {  
           if(value=="1"){
        	   return "已审核"; 
           }else{
        	   return "待审核";
           }
        }
	},{
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
		title: '创建时间',
		field: 'createTime',
		width: 120,
		sortable: true
	}, {
		title: '创建人',
		field: 'createBy',
		width: 120
	}, {
		title: '更新时间',
		field: 'updateTime',
		width: 120,
		sortable: true
	}, {
		title: '更新人',
		field: 'updateBy',
		width: 120
	} ];

var gridObj={
	iconCls: 'icon-view',
	//	height: 650,
	height:document.body.clientHeight * 0.8,
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
//	queryParams:queryData,
//	columns: ,
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
		id: 'btnUpload',
		text: '导入',
		iconCls: 'icon-excel',
		handler: function() {
			ShowUploadDialog();
		}
	}, '-',
	{
		id: 'btnReload',
		text: '刷新',
		iconCls: 'icon-reload',
		handler: function() {
			$("#grid"+$("#tabFormType").val()).datagrid("reload")
		}
	}],
	onDblClickRow: function(rowIndex, rowData) {
		$("#grid"+$("#tabFormType").val()).datagrid('uncheckAll');
		$("#grid"+$("#tabFormType").val()).datagrid('checkRow', rowIndex);
		ShowEditOrViewDialog();
	}
};

//初始化grid
function InitGridBase(queryData) {
	var gridObjBase=jQuery.extend({}, gridObj);
	gridObjBase.url="product/base/pagination?sessionId="+sessionId;
	gridObjBase.title="产品基本信息列表";
	gridObjBase.columns=[$.merge($.merge($.merge([],productColumns),baseColumns),commonColumns)];
	gridObjBase.queryParams=queryData;
	$('#gridBase').datagrid(gridObjBase);
};

function InitGridAtta(queryData) {
	var gridObjAtta=jQuery.extend({}, gridObj);
	gridObjAtta.url="product/atta/pagination?sessionId="+sessionId;
	gridObjAtta.title="产品附件信息列表";
	gridObjAtta.columns=[$.merge($.merge($.merge([],productColumns),attaColumns),commonColumns)];
	gridObjAtta.queryParams=queryData;
	$('#gridAtta').datagrid(gridObjAtta);
};

function InitGridParam(queryData) {
	var gridObjParam=jQuery.extend({}, gridObj);
	gridObjParam.url="product/param/pagination?sessionId="+sessionId;
	gridObjParam.title="产品参数信息列表";
	gridObjParam.columns=[$.merge($.merge($.merge([],productColumns),paramColumns),commonColumns)];
	gridObjParam.queryParams=queryData;
	$('#gridParam').datagrid(gridObjParam);
};

function InitGridReplace(queryData) {
	var gridObjReplace=jQuery.extend({}, gridObj);
	gridObjReplace.url="product/replace/pagination?sessionId="+sessionId;
	gridObjReplace.title="产品替换信息列表";
	gridObjReplace.columns=[$.merge($.merge($.merge([],productColumns),replaceColumns),commonColumns)];
	gridObjReplace.queryParams=queryData;
	$('#gridReplace').datagrid(gridObjReplace);
};

//搜索
function BindSearchEvent() {
	$("#ffSearchBase").form("clear");
	$("#ffSearchAtta").form("clear");
	$("#ffSearchParam").form("clear");
	$("#ffSearchReplace").form("clear");
	$("#ffSearchBase #btnSearch").unbind("click").click(function() {
		InitGridBase($("#ffSearchBase").serializeJson());
		return false;
	})
	$("#ffSearchAtta #btnSearch").unbind("click").click(function() {
		InitGridAtta($("#ffSearchAtta").serializeJson());
		return false;
	})
	$("#ffSearchParam #btnSearch").unbind("click").click(function() {
		InitGridParam($("#ffSearchParam").serializeJson());
		return false;
	})
	$("#ffSearchReplace #btnSearch").unbind("click").click(function() {
		InitGridReplace($("#ffSearchReplace").serializeJson());
		return false;
	})
};

//编辑/详情对话框
function ShowEditOrViewDialog(view) {
	var rows = $("#grid"+$("#tabFormType").val()).datagrid("getSelections");
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

//显示添加对话框
function ShowUploadDialog() {
	$("#ffUpload").form("clear");
	$("#DivUpload").dialog('open').dialog('setTitle', '导入附件');
};

//添加数据
function BindUploadEvent() {
	$("#btnUploadOK").unbind("click").click(function() {
		if(!$("#ffUpload input[name=atta]").val()){
			alert("请选择要导入的文件");
			return false;
		}
		var fileName = $("#ffUpload input[name=atta]").val().split('.');
		fileName = fileName[fileName.length-1];
		if(fileName && (fileName=="xlsx" || fileName=="xls")){
			$("#ffUpload")[0].action="product/"+$("#tabFormType").val().toLowerCase()+"/import?sessionId="+sessionId;
		}else{
			$("#ffUpload")[0].action="atta/import?type=1&sessionId="+sessionId;
		}
		$("#ffUpload").submit();
		$("#DivUpload").dialog("close");
		$("#grid").datagrid("reload");
		$("#ffUpload").form("clear");
		return false;
	})
};

//显示编辑数据
function BindEditInfo() {
	$("#ffEditBase").form("clear");
	$("#ffEditAtta").form("clear");
	$("#ffEditParam").form("clear");
	$("#ffEditReplace").form("clear");
	$("#ffEditBase").hide();
	$("#ffEditAtta").hide();
	$("#ffEditParam").hide();
	$("#ffEditReplace").hide();
	$("#ffEdit"+$("#tabFormType").val()).show();
	var data=$("#grid"+$("#tabFormType").val()).datagrid("getSelections")[0];
	fillData($("#ffEdit"+$("#tabFormType").val()), data);
	$("#ffEdit"+$("#tabFormType").val()).form('load', data);
};

//编辑数据
function BindEditEvent() {
	$("#ffEditBase #btnEditOK").unbind("click").click(function() {
		var validate = $("#ffEditBase").form("validate");
		if (validate == false) {
			return false;
		};
		var postData = $("#ffEditBase").serializeArray();
		commPostRequest("product/base/update", postData, function(data) {
			//$.messager.alert("提示", "修改成功");
			$("#DivEdit").dialog('close');
			$("#gridBase").datagrid("reload");
		},function(msg){
			$.messager.alert("提示", isEmpty(msg) ? "修改失败，请您检查" : msg);
		})
	})
	$("#ffEditAtta #btnEditOK").unbind("click").click(function() {
		var validate = $("#ffEditAtta").form("validate");
		if (validate == false) {
			return false;
		};
		var postData = $("#ffEditAtta").serializeArray();
		commPostRequest("product/atta/update", postData, function(data) {
			//$.messager.alert("提示", "修改成功");
			$("#DivEdit").dialog('close');
			$("#gridAtta").datagrid("reload");
		},function(msg){
			$.messager.alert("提示", isEmpty(msg) ? "修改失败，请您检查" : msg);
		})
	})
	$("#ffEditParam #btnEditOK").unbind("click").click(function() {
		var validate = $("#ffEditParam").form("validate");
		if (validate == false) {
			return false;
		};
		var postData = $("#ffEditParam").serializeArray();
		commPostRequest("product/param/update", postData, function(data) {
			//$.messager.alert("提示", "修改成功");
			$("#DivEdit").dialog('close');
			$("#gridParam").datagrid("reload");
		},function(msg){
			$.messager.alert("提示", isEmpty(msg) ? "修改失败，请您检查" : msg);
		})
	})
	$("#ffEditReplace #btnEditOK").unbind("click").click(function() {
		var validate = $("#ffEditReplace").form("validate");
		if (validate == false) {
			return false;
		};
		var postData = $("#ffEditReplace").serializeArray();
		commPostRequest("product/replace/update", postData, function(data) {
			//$.messager.alert("提示", "修改成功");
			$("#DivEdit").dialog('close');
			$("#gridReplace").datagrid("reload");
		},function(msg){
			$.messager.alert("提示", isEmpty(msg) ? "修改失败，请您检查" : msg);
		})
	})
}