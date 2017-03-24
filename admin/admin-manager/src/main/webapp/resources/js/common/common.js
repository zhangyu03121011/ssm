//获取url参数
(function($) {
	$.getUrlParam = function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
})(jQuery);

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}

//补位
function pad(num, n) {  
  return Array(n>num?(n-(''+num).length+1):0).join(0)+num;  
} 

//关闭窗体
function close(divname) {
	$(divname).dialog('close')
};

// 判断是否为空
function isEmpty(str) {
	// undefined
	if (!str) {
		return true;
	}
	// null对象
	if (str == null) {
		return true;
	}
	// 空字符串
	if (typeof (str) == "string" && $.trim(str) == "") {
		return true;
	}
	// 数组为空
	if (str instanceof Array && str.length == 0) {
		return true;
	}
	// 空字符串
	if ($.trim(str) == "") {
		return true;
	}
	return false;
}

//公共POST请求方法
function commPostRequest(url,params,succFn,failFn){
	if (!isEmpty(sessionId)) {
		url+="?sessionId="+sessionId;
	}
	$.post(url,params,function(data){
		commResult(data, succFn, failFn);
	},"json");
}

//公共GET请求方法
function commGetRequest(url,params,succFn,failFn){
	if (!isEmpty(sessionId)) {
		url+="?sessionId="+sessionId;
	}
	$.get(url,params,function(data){
		commResult(data, succFn, failFn);
	},"json");
}

//公共AJAX请求方法
function commAjaxRequest(url,params,succFn,failFn){
	if (!isEmpty(sessionId)) {
		url+="?sessionId="+sessionId;
	}
	$.ajax({
		url:url,
		type:"post",
		data:params,
		dataType:"json",
		contentType:"application/json",
		success:succFn,
		error:failFn
	});
}

//表单转json数据
(function($){  
    $.fn.serializeJson=function(){  
        var serializeObj={};  
        $(this.serializeArray()).each(function(){  
            serializeObj[this.name]=this.value;  
        });  
        return serializeObj;  
    };  
})(jQuery);

// 公共结果反馈方法
// 失败-0,成功-1,系统未登录-2,不存在-3,已存在-4,异常-5,超时-6,code错误-7,数据错误-8
function commResult(data, succFn, failFn) {

	// 判断结果信息
	if (!isEmpty(data)) {

		// 调用成功
		if (data.res == 1) {

			// 成功方法
			if (succFn) {
				succFn(data);
			}
		} else {

			// 失败-0,成功-1,系统未登录-2,不存在-3,已存在-4,异常-5,超时-6,code错误-7,数据错误-8
			var msg;
			if (data.res == 2) {
				msg = "系统未登录,请先登录系统";
			} else if (data.res == 3) {
				msg = "数据不存在,请先添加数据";
			} else if (data.res == 4) {
				msg = "数据已经存在,请勿重复添加";
			} else if (data.res == 5) {
				msg = "系统异常";
			} else if (data.res == 6) {
				msg = "系统超时,请重新操作";
			} else if (data.res == 7) {
				msg = "验证码错误,请重新输入";
			} else if (data.res == 8) {
				msg = "数据错误,请重新输入";
			} else if (data.res == 9) {
				msg = "格式错误,请重新输入";
			} else if (data.res == 10) {
				msg = "数据无效,请重新输入";
			} else if (data.res == 11) {
				msg = "数据为空,请重新输入";
			}

			// 失败方法
			if (failFn) {
				failFn(msg,data.res);
			}
		}

	} else {

		// 失败方法
		if (failFn) {
			failFn();
		}

	}
}

//清空表单
function clearForm(formId){
	$(':input','#'+formId)  
	 .not(':button, :submit, :reset, :hidden')  
	 .val('')  
	 .removeAttr('checked')  
	 .removeAttr('selected');
}

//填充表单数据
function fillForm(formId,data){
    var key,value,tagName,type,arr;
    for(x in data){
        key = x;
        value = data[x];
         
        $("#"+formId+" [name='"+key+"'],[name='"+key+"[]']").each(function(){
            tagName = $(this)[0].tagName;
            type = $(this).attr('type');
            if(tagName=='INPUT'){
                if(type=='radio'){
                    $(this).attr('checked',$(this).val()==value);
                }else if(type=='checkbox'){
                    arr = value.split(',');
                    for(var i =0;i<arr.length;i++){
                        if($(this).val()==arr[i]){
                            $(this).attr('checked',true);
                            break;
                        }
                    }
                }else{
                    $(this).val(value);
                }
            }else if(tagName=='SELECT' || tagName=='TEXTAREA'){
                $(this).val(value);
            }
             
        });
    }
}

//填充非表单数据
function fillData(fillArea,data){
	var key,value,tagName,type,arr;
	fillArea.find("[name]").each(function(i,obj){
		$(obj).text("");
	})
    for(x in data){
        key = x;
        value = data[x];
        
        var obj=fillArea.find("[name='"+key+"'],[name='"+key+"[]']");
        if(obj){
        	obj.each(function(){
            	$(this).text(value);
            });
        }
    }
}

//转换数据为combotree结构
function convertToTree(rows,treeId,treeName,treeParentId) {
	
	if(isEmpty(treeId)){
		treeId="id";
	}
	if(isEmpty(treeName)){
		treeName="name";
	}
	if(isEmpty(treeParentId)){
		treeParentId="parentId";
	}

	function exists(rows, parentId) {
		for (var i = 0; i < rows.length; i++) {
			if (rows[i][treeId] == parentId)
				return true;
		}
		return false;
	}

	var nodes = [];
	// 得到顶层节点
	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		if (!exists(rows, row[treeParentId])) {
			nodes.push({
				id : row[treeId],
				text : row[treeName]
			});
		}
	}

	var toDo = [];
	for (var i = 0; i < nodes.length; i++) {
		toDo.push(nodes[i]);
	}
	while (toDo.length) {
		var node = toDo.shift(); // 父节点
		// 得到子节点
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			if (row[treeParentId] == node.id) {
				var child = {
					id : row[treeId],
					text : row[treeName]
				};
				if (node.children) {
					node.children.push(child);
				} else {
					node.children = [ child ];
				}
				toDo.push(child);
			}
		}
	}
	return nodes;
}

//转换对象列表为数组
function convertListToArray(objList,key){
	var result=[];
	$.each(objList, function(i, item) {
		result.push(item[key]);
	})
	return result;
}

//转换数据为menu结构
function convertToMenu(rows,treeId,treeName,treeParentId) {
	
	if(isEmpty(treeId)){
		treeId="id";
	}
	if(isEmpty(treeName)){
		treeName="name";
	}
	if(isEmpty(treeParentId)){
		treeParentId="parentId";
	}

	function exists(rows, parentId) {
		for (var i = 0; i < rows.length; i++) {
			if (rows[i][treeId] == parentId)
				return true;
		}
		return false;
	}

	var nodes = [];
	// 得到顶层节点
	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		if (!exists(rows, row[treeParentId])) {
			nodes.push(row);
		}
	}

	var toDo = [];
	for (var i = 0; i < nodes.length; i++) {
		toDo.push(nodes[i]);
	}
	while (toDo.length) {
		var node = toDo.shift(); // 父节点
		// 得到子节点
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			if (row[treeParentId] == node[treeId]) {
				var child = row;
				if (node.children) {
					node.children.push(child);
				} else {
					node.children = [ child ];
				}
				toDo.push(child);
			}
		}
	}
	return nodes;
}

//设置居中
jQuery.fn.center = function() {
	this.css("position", "absolute");
	this.css("top", Math.max(0, (($(window).height() - this.outerHeight()) / 2)
			+ $(window).scrollTop())
			+ "px");
	this.css("left", Math.max(0, (($(window).width() - this.outerWidth()) / 2)
			+ $(window).scrollLeft())
			+ "px");
	return this
};

//展开/折叠节点
function expandNode(e) {
	var zTree = $.fn.zTree.getZTreeObj("meibaTree"), type = e.data.type, nodes = zTree.getSelectedNodes();
	if (type.indexOf("All") < 0 && nodes.length == 0) {
		$.messager.alert("提示", "请先选择一个父节点");
	}
	if (type == "expandAll") {
		zTree.expandAll(true);
	} else if (type == "collapseAll") {
		zTree.expandAll(false);
	} else {
		var callbackFlag = $("#callbackTrigger").attr("checked");
		for (var i = 0, l = nodes.length; i < l; i++) {
			zTree.setting.view.fontCss = {};
			if (type == "expand") {
				zTree.expandNode(nodes[i], true, null, null, callbackFlag);
			} else if (type == "collapse") {
				zTree.expandNode(nodes[i], false, null, null, callbackFlag);
			} else if (type == "toggle") {
				zTree.expandNode(nodes[i], null, null, null, callbackFlag);
			} else if (type == "expandSon") {
				zTree.expandNode(nodes[i], true, true, null, callbackFlag);
			} else if (type == "collapseSon") {
				zTree.expandNode(nodes[i], false, true, null, callbackFlag);
			}
		}
	}
};

//easyUI验证类型
$.extend($.fn.validatebox.defaults.rules, {
    idcard: {// 验证身份证
        validator: function (value) {
            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
        },
        message: '身份证号码格式不正确'
    },
    minLength: {
        validator: function (value, param) {
            return value.length >= param[0];
        },
        message: '请输入至少（2）个字符.'
    },
    length: { validator: function (value, param) {
        var len = $.trim(value).length;
        return len >= param[0] && len <= param[1];
    },
        message: "输入内容长度必须介于{0}和{1}之间."
    },
    phone: {// 验证电话号码
        validator: function (value) {
            return /^((\d2,3)|(\d{3}\-))?(0\d2,3|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message: '格式不正确,请使用下面格式:020-88888888'
    },
    mobile: {// 验证手机号码
        validator: function (value) {
            return /^(13|15|18)\d{9}$/i.test(value);
        },
        message: '手机号码格式不正确'
    },
    intOrFloat: {// 验证整数或小数
        validator: function (value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message: '请输入数字，并确保格式正确'
    },
    currency: {// 验证货币
        validator: function (value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message: '货币格式不正确'
    },
    qq: {// 验证QQ,从10000开始
        validator: function (value) {
            return /^[1-9]\d{4,9}$/i.test(value);
        },
        message: 'QQ号码格式不正确'
    },
    integer: {// 验证整数 可正负数
        validator: function (value) {
            //return /^[+]?[1-9]+\d*$/i.test(value);

            return /^([+]?[0-9])|([-]?[0-9])+\d*$/i.test(value);
        },
        message: '请输入整数'
    },
    age: {// 验证年龄
        validator: function (value) {
            return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(value);
        },
        message: '年龄必须是0到120之间的整数'
    },

    chinese: {// 验证中文
        validator: function (value) {
            return /^[\Α-\￥]+$/i.test(value);
        },
        message: '请输入中文'
    },
    english: {// 验证英语
        validator: function (value) {
            return /^[A-Za-z]+$/i.test(value);
        },
        message: '请输入英文'
    },
    unnormal: {// 验证是否包含空格和非法字符
        validator: function (value) {
            return /.+/i.test(value);
        },
        message: '输入值不能为空和包含其他非法字符'
    },
    username: {// 验证用户名
        validator: function (value) {
            return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
        },
        message: '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
    },
    faxno: {// 验证传真
        validator: function (value) {
            //            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value);
            return /^((\d2,3)|(\d{3}\-))?(0\d2,3|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message: '传真号码不正确'
    },
    zip: {// 验证邮政编码
        validator: function (value) {
            return /^[1-9]\d{5}$/i.test(value);
        },
        message: '邮政编码格式不正确'
    },
    ip: {// 验证IP地址
        validator: function (value) {
            return /d+.d+.d+.d+/i.test(value);
        },
        message: 'IP地址格式不正确'
    },
    name: {// 验证姓名，可以是中文或英文
        validator: function (value) {
            return /^[\Α-\￥]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
        },
        message: '请输入姓名'
    },
    date: {// 验证姓名，可以是中文或英文
        validator: function (value) {
            //格式yyyy-MM-dd或yyyy-M-d
            return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i.test(value);
        },
        message: '清输入合适的日期格式'
    },
    msn: {
        validator: function (value) {
            return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
        },
        message: '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
    },
    same: {
        validator: function (value, param) {
            if ($("#" + param[0]).val() != "" && value != "") {
                return $("#" + param[0]).val() == value;
            } else {
                return true;
            }
        },
        message: '两次输入的密码不一致！'
    }
});

//获取combotree根节点
function getCombotreeRoot(tree,node){
	//获取根ID
	var parent=node;
	do {  
        parent = tree.tree('getParent', parent.target); 
        if(parent){
        	node=parent;
        }
    } while (parent);
	return node;
}