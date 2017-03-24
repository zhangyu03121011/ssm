$(function() {
		
	//加载树
	reloadTree();
	
	//初始化grid
	InitGrid();
	
	//搜索数据
	BindSearchEvent();
	
	//编辑数据
	BindEditEvent();
	
	//添加数据
	BindAddEvent();
	
	//加载uploadify组件
	loadUploadify();
	
	//导入数据
	BindUploadEvent();
	
});

//加载ztree
function reloadTree() {

	commPostRequest("company/list", {}, function(data) {
		
		var result=jQuery.extend([], data.list);
		var treeDataAdd=convertToTree(result,"id","companyName");
		var treeDataEdit=convertToTree(result,"id","companyName");
		
		//加载菜单树列表
		$('#addCompnayTree').combotree("loadData", treeDataAdd);
		$('#editCompnayTree').combotree("loadData", treeDataEdit);

	})
	commPostRequest("product/list", {}, function(data) {
		
		var result=jQuery.extend([], data.list);
		var treeDataAdd=convertToTree(result,"id","productNo");
		var treeDataEdit=convertToTree(result,"id","productNo");
		
		//加载菜单树列表
		$('#addProductTree').combotree("loadData", treeDataAdd);
		$('#editProductTree').combotree("loadData", treeDataEdit);

	})
};

//初始化grid
function InitGrid(queryData) {
	$('#grid').datagrid({
		url:"company/course/pagination?sessionId="+sessionId,
		title: '企业语音列表',
		iconCls: 'icon-view',
//		height: 650,
		height:document.body.clientHeight * 0.9,
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
		queryParams:queryData,
		columns: [	
			[{
				field: 'ck',
				checkbox: true
			}, {
				title: '企业名称',
				field: 'companyName',
				width: 120,
				sortable: true,
				formatter: function (value, rec, index) {  
					if (rec.companyVo) {
						return rec.companyVo.companyName;
					}
                }
			}, {
				title: '企业品牌',
				field: 'companyBrand',
				width: 120,
				sortable: true,
				formatter: function (value, rec, index) {  
					if (rec.companyVo) {
						return rec.companyVo.companyBrand;
					}
                }
			}, {
				title: '产品型号',
				field: 'productNo',
				width: 150,
				sortable: true
			}, {
				title: '标题',
				field: 'title',
				width: 150,
				sortable: true
			}, {
				title: '支付金额',
				field: 'payMoney',
				width: 150,
				sortable: true
			}, {
				title: '教程类别',
				field: 'courseType',
				width: 150,
				sortable: true,
				formatter: function (value, rec, index) {  
                   if(value=="1"){
                	   return "文字"; 
                   }else if(value=="2"){
                	   return "声音";
                   }else if(value=="3"){
                	   return "视频";
                   }else if(value=="4"){
                	   return "图片";
                   }
                }
			}, {
				title: '附件',
				field: 'atta',
				width: 60,
				sortable: true,
				formatter: function(val, rowdata, index) {
					if(rowdata.baseAttaModel && rowdata.baseAttaModel.filePath){
						return '<a class="menuIconClass" href="'+currHost+'/images/'+rowdata.baseAttaModel.filePath+'" target="_blank">查看</a>';
					}else{
						return '';
					}
				}
			}, {
				title: '阅读人数',
				field: 'read_count',
				width: 150,
				sortable: true
			}, {
				title: '描述',
				field: 'descr',
				width: 200
			}, {
				title: '状态',
				field: 'state',
				align:"center",
				width: 80,
				sortable: true,
				formatter: function (value, rec, index) {  
                   if(value=="1"){
                	   return "审核通过"; 
                   }else if(value=="2"){
                	   return "审核失败";
                   }else{
                	   return "待审核";
                   }
                }
			}, {
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
			} ]
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
			id: 'btnView',
			text: '查看',
			iconCls: 'icon-table',
			handler: function() {
				ShowEditOrViewDialog("view");
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
				$("#grid").datagrid("reload")
			}
		}],
		onDblClickRow: function(rowIndex, rowData) {
			$('#grid').datagrid('uncheckAll');
			$('#grid').datagrid('checkRow', rowIndex);
			ShowEditOrViewDialog("view")
		}
	})
	
};

//搜索
function BindSearchEvent() {
	$("#ffSearch").form("clear");
	$("#btnSearch").click(function() {
		InitGrid($("#ffSearch").serializeJson());
		return false;
	})
};

//显示添加对话框
function ShowAddDialog() {
	$("#ffAdd").form("clear");
	$("#DivAdd").dialog('open').dialog('setTitle', '添加信息');
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
			$("#ffUpload")[0].action="company/course/import?sessionId="+sessionId;
		}else{
			$("#ffUpload")[0].action="atta/import?type=7&sessionId="+sessionId;
		}
		$("#ffUpload").submit();
		$("#DivUpload").dialog("close");
		$("#grid").datagrid("reload");
		$("#ffUpload").form("clear");
		return false;
	})
};

//添加数据
function BindAddEvent() {
	$("#btnAddOK").unbind("click").click(function() {
		var validate = $("#ffAdd").form('validate');
		if (validate == false) {
			return false;
		};
		var postData = $("#ffAdd").serializeArray();
		commPostRequest("company/course/save", postData, function(){
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
		clearForm("DivEdit");
		$("#DivEdit").dialog('open').dialog('setTitle', '修改信息');
		BindEditInfo();
	} else {
		$("#DivView").dialog('open').dialog('setTitle', '查看详细信息');
		BindViewInfo();
	}
};

//显示编辑数据
function BindEditInfo() {
	var data=$("#grid").datagrid("getSelections")[0];
	$('#ffEdit').form('load', data);
};

//显示详情数据
function BindViewInfo() {
	var dataItem=$("#grid").datagrid("getSelections")[0];
	var data=jQuery.extend({}, dataItem);
	if(data.isavailable==1){
		data.isavailable="有效";
	}else{
		data.isavailable="无效";
	}
	if(data.state==1){
		data.state="审核通过";
	}else if(data.state==2){
		data.state="审核失败";
	}else{
		data.state="待审核";
	}
	if(data.courseType==1){
		data.courseType="文字";
	}else if(data.courseType==2){
		data.courseType="声音";
	}else if(data.courseType==3){
		data.courseType="视频";
	}else if(data.courseType==4){
		data.courseType="图片";
	}
	
	if (data.companyVo) {
		data.companyBrand=data.companyVo.companyBrand;
		data.companyName=data.companyVo.companyName;
	}

	fillData($("#ffView"), data);
};

//编辑数据
function BindEditEvent() {
	$("#btnEditOK").unbind("click").click(function() {
		var validate = $("#ffEdit").form("validate");
		if (validate == false) {
			return false;
		};
		var postData = $("#ffEdit").serializeArray();
		commPostRequest("company/course/update", postData, function(data) {
			//$.messager.alert("提示", "修改成功");
			$("#DivEdit").dialog('close');
			$("#grid").datagrid("reload");
		},function(msg){
			$.messager.alert("提示", isEmpty(msg) ? "修改失败，请您检查" : msg);
		})
	})
}

//加载uploadify组件
function loadUploadify(){
	$('#file_uploadAdd').uploadify({
		'swf': 'resources/js/tools/uploadify/uploadify.swf',//uploadify.swf 文件的相对路径，该swf文件是一个带有文字BROWSE的按钮，点击后淡出打开文件对话框，默认值：uploadify.swf
		'buttonText': '浏  览',//浏览按钮的文本，默认值：BROWSE
		'uploader': 'atta/upload?sessionId='+sessionId,//后台处理程序的相对路径
		'queueID': 'fileQueueAdd',//文件队列的ID，该ID与存放文件队列的div的ID一致
		'queueSizeLimit': 10,//当允许多文件生成时，设置选择文件的个数，默认值：999
		'auto': false, //设置为true当选择文件后就直接上传了，为false需要点击上传按钮才上传
		'multi': true, //设置为true时可以上传多个文件
		'removeCompleted': true,//是否自动将已完成任务从队列中删除，如果设置为false则会一直保留此任务显示。
		'fileSizeLimit': '10MB',//	上传文件的大小限制 ，如果为整数型则表示以KB为单位的大小，如果是字符串，则可以使用(B, KB, MB, or GB)为单位，比如’2MB’；如果设置为0则表示无限制
		'fileTypeDesc': 'All Files',//这个属性值必须设置fileTypeExts属性后才有效，用来设置选择文件对话框中的提示文本，如设置fileTypeDesc为“请选择rar doc pdf文件
		'fileTypeExts': '*.*',//设置可以选择的文件的类型，格式如：’*.doc;*.pdf;*.rar’
		
		//文件上传队列处理完毕后触发。queueData对象包含如下属性：uploadsSuccessful – 上传成功的文件数量    uploadsErrored – 上传失败的文件数量
		'onQueueComplete': function(event, data) {
			commGetRequest("atta/single", {sourceId:$("#ffAdd input[name=id]").val()}, function(data) {
				if(data && data.obj && !isEmpty(data.obj.filePath)){
					$("#ffAdd a[name=attaView]").attr("href",currHost+"/images/"+data.obj.filePath).show();
				}
				$.messager.alert("提示", "上传成功！");
			},function(msg){
				$.messager.alert("提示", isEmpty(msg) ? "修改失败，请您检查" : msg);
			})
		},
		
		//当文件即将开始上传时立即触发
		'onUploadStart': function(file) {
			var sourceId=new UUID();
			$("#ffAdd input[name=id]").val(sourceId);
			$("#file_uploadAdd").uploadify("settings", 'formData', {
				'id': $("#ffAdd input[name=id]").val(),
				'type':7
			});
		},
		
		//文件上传出错时触发，参数由服务端程序返回
		'onUploadError': function(event, queueId, fileObj, errorObj) {}
	});
	$('#file_uploadEdit').uploadify({
		'swf': 'resources/js/tools/uploadify/uploadify.swf',//uploadify.swf 文件的相对路径，该swf文件是一个带有文字BROWSE的按钮，点击后淡出打开文件对话框，默认值：uploadify.swf
		'buttonText': '浏  览',//浏览按钮的文本，默认值：BROWSE
		'uploader': 'atta/upload?sessionId='+sessionId,//后台处理程序的相对路径
		'queueID': 'fileQueueEdit',//文件队列的ID，该ID与存放文件队列的div的ID一致
		'queueSizeLimit': 10,//当允许多文件生成时，设置选择文件的个数，默认值：999
		'auto': false, //设置为true当选择文件后就直接上传了，为false需要点击上传按钮才上传
		'multi': true, //设置为true时可以上传多个文件
		'removeCompleted': true,//是否自动将已完成任务从队列中删除，如果设置为false则会一直保留此任务显示。
		'fileSizeLimit': '10MB',//	上传文件的大小限制 ，如果为整数型则表示以KB为单位的大小，如果是字符串，则可以使用(B, KB, MB, or GB)为单位，比如’2MB’；如果设置为0则表示无限制
		'fileTypeDesc': 'All Files',//这个属性值必须设置fileTypeExts属性后才有效，用来设置选择文件对话框中的提示文本，如设置fileTypeDesc为“请选择rar doc pdf文件
		'fileTypeExts': '*.*',//设置可以选择的文件的类型，格式如：’*.doc;*.pdf;*.rar’
		
		//文件上传队列处理完毕后触发。queueData对象包含如下属性：uploadsSuccessful – 上传成功的文件数量    uploadsErrored – 上传失败的文件数量
		'onQueueComplete': function(event, data) {
			commGetRequest("atta/single", {sourceId:$("#ffEdit input[name=id]").val()}, function(data) {
				if(data && data.obj && !isEmpty(data.obj.filePath)){
					$("#ffEdit a[name=attaView]").attr("href",currHost+"/images/"+data.obj.filePath).show();
				}
				$.messager.alert("提示", "上传成功！");
			},function(msg){
				$.messager.alert("提示", isEmpty(msg) ? "修改失败，请您检查" : msg);
			})
		},
		
		//当文件即将开始上传时立即触发
		'onUploadStart': function(file) {
			$("#file_uploadEdit").uploadify("settings", 'formData', {
				'id': $("#ffEdit input[name=id]").val(),
				'type':7
			});
		},
		
		//文件上传出错时触发，参数由服务端程序返回
		'onUploadError': function(event, queueId, fileObj, errorObj) {}
	});
}