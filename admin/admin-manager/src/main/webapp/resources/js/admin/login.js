$(function() {
	
	// 登录
	$("#btnLogin").unbind("click").click(function() {

		// 验证表单
		var validate = $("#loginForm").form('validate');
		if (validate == false) {
			return false
		}

		// 获取表单数据
		var postData = $("#loginForm").serializeArray();
		commPostRequest("user/login", postData, function(data) {
			location.href="index";
			//$("#loginForm").form("clear");
		},function(msg){
			$.messager.alert("提示", isEmpty(msg) ? "用户名或密码错误,请重新登录" : msg);
		})
	})

	// 重置
	$("#btnRest").unbind("click").click(function() {
		$("#loginForm").form("reset");
	})
	
});

//回车事件
document.onkeydown=function(event){ 
	e = event ? event :(window.event ? window.event : null); 
	if(e.keyCode==13){ 
		//执行的方法 
		$("#btnLogin").click();
	} 
} 