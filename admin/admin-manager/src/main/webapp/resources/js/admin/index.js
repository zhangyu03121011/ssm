$(function() {
	
	//显示系统时间
	ReadDateTimeShow();
	var setTimeInterval = setInterval(ReadDateTimeShow, 1000);
	
	//通知窗体大小
	windowResize();
	$(window).resize(function() {
		windowResize()
	})
	
	//tab操作
    tabClose();
    tabCloseEven();
    
    //生成首页tab
    $('#tabs').tabs('add', {
        title: '首页',
        content: createFrame('')//指定首页显示页面
    }).tabs({
        onSelect: function (title) {
            var currTab = $('#tabs').tabs('getTab', title);
            var iframe = $(currTab.panel('options').content);

            var exist = $('#tabs').tabs('exists', title); //返回false或true
            if (exist)
                return;

            var src = iframe.attr('src');
            if (src) {
                $('#tabs').tabs('update', { tab: currTab, options: { content: createFrame(src)} });
            }

        }
    });

});

//菜单列表
var _menus;

//加载菜单列表
commPostRequest("app/menu", {}, function(data) {
	
	_menus = data.list;
	
	//菜单数据转换
	$.each(_menus,function(i,item){
		
		var data=convertToMenu(item.sysMenuModels,"menuId","menuName");
		item.sysMenuModels=data;
		
		if(i==0){
			//初始化菜单	
			addNav(data,item.appUrl);
		}
	})

})

//获取窗体高度
function getWindowHeight() {
	return $(window).height()
};

//获取窗体宽度
function getWindowWidth() {
	return $(window).width()
};

//重新调整窗体大小
function windowResize() {
	var width = getWindowWidth();
	var height = getWindowHeight();
	$('form#form1').width(width);
	$('form#form1').height(height);
	$('form#form1').layout()
};

//显示子菜单
function showSubMenu(url, title, menuCategory, defaultIcon) {
	if (defaultIcon == null || defaultIcon == "") {
		defaultIcon = "icon-table"
	};
	addTab(title, url, "icon " + defaultIcon);
	Clearnav();
	if (menuCategory != "") {
		$.each(_menus,function(i,item){
			if(item.appId==menuCategory){
				addNav(item.sysMenuModels,item.appUrl);
			}
		})
	}
};

//清空导航
function Clearnav() {
    var pp = $('#nav').accordion('panels');

    $.each(pp, function (i, n) {
        if (n) {
            var t = n.panel('options').title;
            $('#nav').accordion('remove', t);
        }
    });

    pp = $('#nav').accordion('getSelected');
    if (pp) {
        var title = pp.panel('options').title;
        $('#nav').accordion('remove', title);
    }
}

//获取左侧导航的图标
function getIcon(data, menuId) {
    var icon = 'icon ';
    $.each(data, function (k, item) {        
        $.each(item.children, function (i, o) {
            if (o.menuId == menuId) {
                icon += o.menuIcon;
            }
        })
    })
    return icon;
}

//创建tab
function addTab(subtitle,url,icon){
	if(!$('#tabs').tabs('exists',subtitle)){
		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true,
			icon:icon
		});
	}else{
		$('#tabs').tabs('select',subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();
}

//创建iframe
function createFrame(url){
	var str = '<iframe scrolling="no" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return str;
}

//关闭tab
function tabClose(){
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}

//菜单项鼠标Hover
function hoverMenuItem() {
    $(".easyui-accordion").find('a').hover(function () {
        $(this).parent().addClass("hover");
    }, function () {
        $(this).parent().removeClass("hover");
    });
}

//绑定右键菜单事件
function tabCloseEven(){
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#tabs').tabs('update',{
			tab:currTab,
			options:{
				content:createFrame(url)
			}
		})
	})
	//关闭当前
	$('#mm-tabclose').click(function(){
		var t = $('#mm').data("currtab");
		if (t !== "首页") {
		    $('#tabs').tabs('close', t);//currtab_title
		}
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t !== "首页")
				$('#tabs').tabs('close',t);
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			if(t !== "首页")
			   $('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			//alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			if(t !== "首页")
			    $('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

//修改密码对话框
function ShowPasswordDialog() {
	$("#divModPass").dialog('open').dialog('setTitle', '修改用户密码');
};

//修改密码
function ModifyPass() {
	var validate = $("#ffModPass").form("validate");
	if (validate == false) {
		return false;
	};
	// 获取表单数据
	var postData = $("#ffModPass").serializeArray();	
	commPostRequest("user/update",postData, function() {
		$.messager.alert("提示", "操作成功！ ");
		$("#divModPass").dialog('close');
	}, function(msg,res) {
		if(res==8){
			msg="原密码错误，清重新输入";
		}
		$.messager.alert("提示", isEmpty(msg) ? "修改失败，请您检查" : msg);
	});
}

//显示系统时间
function ReadDateTimeShow() {
	var year = new Date().getFullYear();
	var month = new Date().getMonth() + 1;
	var day = new Date().getDate();
	var time = new Date().toLocaleTimeString();
	var addDate = year + "年" + month + "月" + day + "日 " + time;
	$("#date").text(addDate)
};

//初始化左侧
function addNav(data,appUrl) {
	
	if(isEmpty(appUrl)){
		appUrl="";
	}
	
    //hoverMenuItem();
	$("#nav").accordion({animate:false});

    $.each(data, function(i, n) {
		var menuList='<ul>';
		if(n.children){
			$.each(n.children, function (j, o) {
				
				var url=appUrl+o.menuUrl;
				if(url.indexOf("?")!=-1){
					url+="&";
				}else{
					url+="?";
				}
				url+="sessionId="+sessionId;
				
	            //如果是脚本，连接的方式不同
			    if (o.menuUrl && o.menuUrl.indexOf("javascript") >= 0) {
			    	menuList += '<li><div><a ref="' + o.menuId + '" href="javascript:void(0)" onclick="' + url + '" ><span class="icon ' + o.menuIcon + '" >&nbsp;</span><span class="nav">' + o.menuName + '</span></a></div></li> ';
			    }
			    else {
			    	menuList += '<li><div><a ref="' + o.menuId + '" href="#" rel="' + url + '" ><span class="icon ' + o.menuIcon + '" >&nbsp;</span><span class="nav">' + o.menuName + '</span></a></div></li> ';
			    }
	        })
		}
		menuList += '</ul>';
		//console.log(menuList);
		
		$('#nav').accordion('add', {
            title: n.menuName,
            content: menuList,
            iconCls: 'icon ' + n.menuIcon
        });

    });

	$('.easyui-accordion li a').click(function(){
		var title = $(this).children('.nav').text();

		var url = $(this).attr("rel");
		var menuId = $(this).attr("ref");
		var icon = getIcon(data, menuId);
		
		var exist = $('#tabs').tabs('exists', title);//返回false或true
		if (url) {
		    if (!exist) {
		        addTab(title, url, icon);
		    }
		    else {
		        $('#tabs').tabs('select', title);
		    }
		}
		
		$('.easyui-accordion li div').removeClass("selected");
		$(this).parent().addClass("selected");
	}).hover(function(){
		$(this).parent().addClass("hover");
	},function(){
		$(this).parent().removeClass("hover");
	});

	//选中第一个
	var panels = $('#nav').accordion('panels');
	var t = panels[0].panel('options').title;
    $('#nav').accordion('select', t);
}