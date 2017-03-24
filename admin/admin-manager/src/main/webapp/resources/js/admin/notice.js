$(function() {
	
	//初始化grid
	InitGrid();
	
	//搜索数据
	BindSearchEvent();
	
	//添加数据
	BindAddEvent();
	
	//编辑数据
	BindEditEvent();
	
	//加载uploadify组件
	loadUploadify();
	
	//ckeditor初始化
	initEditor();
	
});

//初始化grid
function InitGrid(queryData) {
	$('#grid').datagrid({
		url: 'notice/pagination?sessionId='+sessionId,
		title: '通知公告',
		iconCls: 'icon-view',
		//height: 650,
		height: document.body.clientHeight * 0.915,
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
		idField: 'noticeId',
		queryParams: queryData,
		columns: [
			[{
				field: 'ck',
				checkbox: true
			}, {
				title: '标题',
				field: 'title',
				width: 350,
				sortable: true
			}, {
				title: '内容',
				field: 'content',
				width: 450
			}, {
				title: '创建人',
				field: 'createBy',
				width: 120,
				sortable: true
			}, {
				title: '创建时间',
				field: 'createTime',
				width: 150,
				sortable: true
			}, {
				title: '附件',
				field: 'Attachment_GUID',
				width: 250,
				sortable: true
			}]
		],
		toolbar: "#gridtoolbar",
		onDblClickRow: function(rowIndex, rowData) {
			$('#grid').datagrid('uncheckAll');
			$('#grid').datagrid('checkRow', rowIndex);
			ShowEditOrViewDialog();
		}
	})
};

//搜索数据
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
					obj.noticeId=objRow.noticeId;
					obj.isavailable=0;
					objList.push(obj);
				});
				commAjaxRequest("notice/batch",JSON.stringify(objList), function(data){
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
	$("#ffAdd input[name=attaId]").val(new UUID());
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
		commPostRequest("notice/save", postData, function(){
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
		return
	} else if (rows.length > 1) {
		$.messager.alert("提示", "每次只能修改/查看一条记录，你已经选择了<font color='red'  size='6'>" + rows.length + "</font>条", "error");
		return
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
	fillData($("#ffView"), $("#grid").datagrid("getSelections")[0]);
};

//编辑数据
function BindEditEvent() {
	$("#btnEditOK").unbind("click").click(function() {
		var validate = $("#ffEdit").form("validate");
		if (validate == false) {
			return false;
		};
		var postData = $("#ffEdit").serializeArray();
		commPostRequest("notice/update", postData, function(data) {
			//$.messager.alert("提示", "修改成功");
			$("#DivEdit").dialog('close');
			$("#grid").datagrid("reload");
		},function(msg){
			$.messager.alert("提示", isEmpty(msg) ? "修改失败，请您检查" : msg);
		})
	})
}

//显示上传文件
function ShowUpFiles(sourceId,show_div) {
	commPostRequest("atta/list", {sourceId:sourceId}, function(data) {
		
		var htmlStr="";
		$.each(data.list,function(i,item){
			htmlStr='<div class="uploadify-queue-item" id="SWFUpload_0_1">'					
					+'<div class="cancel">'						
					+'<a href="javascript:$(\'#file_upload\').uploadify(\'cancel\', \'SWFUpload_0_1\')">X</a>'					
					+'</div>'					
					+'<span class="fileName">17.png (84KB)</span>'
					+'</div>'
		})
		
		$("#" + show_div + "").html(htmlStr);
		
	})
};

//加载uploadify组件
function loadUploadify(){
	$('#file_upload').uploadify({
		'swf': 'resources/js/JQueryTools/uploadify/uploadify.swf',//uploadify.swf 文件的相对路径，该swf文件是一个带有文字BROWSE的按钮，点击后淡出打开文件对话框，默认值：uploadify.swf
		'buttonText': '浏  览',//浏览按钮的文本，默认值：BROWSE
		'uploader': 'atta/upload',//后台处理程序的相对路径
		'queueID': 'fileQueue',//文件队列的ID，该ID与存放文件队列的div的ID一致
		'queueSizeLimit': 10,//当允许多文件生成时，设置选择文件的个数，默认值：999
		'auto': false, //设置为true当选择文件后就直接上传了，为false需要点击上传按钮才上传
		'multi': true, //设置为true时可以上传多个文件
		'removeCompleted': true,//是否自动将已完成任务从队列中删除，如果设置为false则会一直保留此任务显示。
		'fileSizeLimit': '10MB',//	上传文件的大小限制 ，如果为整数型则表示以KB为单位的大小，如果是字符串，则可以使用(B, KB, MB, or GB)为单位，比如’2MB’；如果设置为0则表示无限制
		'fileTypeDesc': 'Image Files',//这个属性值必须设置fileTypeExts属性后才有效，用来设置选择文件对话框中的提示文本，如设置fileTypeDesc为“请选择rar doc pdf文件
		'fileTypeExts': '*.gif; *.jpg; *.png; *.bmp;*.tif;*.doc;*.xls;*.zip',//设置可以选择的文件的类型，格式如：’*.doc;*.pdf;*.rar’
		
		//文件上传队列处理完毕后触发。queueData对象包含如下属性：uploadsSuccessful – 上传成功的文件数量    uploadsErrored – 上传失败的文件数量
		'onQueueComplete': function(event, data) {
			ShowUpFiles($("#ffAdd input[name=attaId]").val(), "div_files");
			$.messager.alert("提示", "上传完毕！");
		},
		
		//当文件即将开始上传时立即触发
		'onUploadStart': function(file) {
			$("#file_upload").uploadify("settings", 'formData', {
				'sourceType': 'notice',
				'sourceId': $("#ffAdd input[name=attaId]").val()
			});
		},
		
		//文件上传出错时触发，参数由服务端程序返回
		'onUploadError': function(event, queueId, fileObj, errorObj) {}
	});
}

//ckeditor初始化
function initEditor() {
    $('#add_content').ckeditor();
    $('#edit_content').ckeditor();
}

var LODOP = getLodop(document.getElementById('LODOP'), document.getElementById('LODOP_EM'));

function Preview() {
	CreateLicenseData();
	LODOP.SET_SHOW_MODE("PREVIEW_IN_BROWSE", 1);
	LODOP.PREVIEW()
};

function PrintA() {
	CreateLicenseData();
	LODOP.PRINTA()
};

function Setup() {
	CreateLicenseData();
	LODOP.PRINT_SETUP()
};

function Design() {
	CreateLicenseData();
	LODOP.PRINT_DESIGN()
};

function CreateLicenseData() {
	LODOP.PRINT_INIT("政策法规");
	var strBodyStyle = "<link type='text/css' rel='stylesheet' href='/Content/Themes/Default/style.css' /><style><!--table { border:1;background-color: #CBCBCC } td {background-color:#FFFFFE;border: 1; } th { background-color:#F1F1F3;padding-left:5px;border:1}--></style>";
	var strFormHtml = strBodyStyle + "<body>" + document.getElementById("printContent").innerHTML + "</body>";
	LODOP.ADD_PRINT_HTM(20, 40, 610, 900, strFormHtml);
	LODOP.PREVIEW()
};

function SaveAs() {
	var id = $('#ID2').val();
	window.open('/Information/ExportWordById?id=' + id)
};