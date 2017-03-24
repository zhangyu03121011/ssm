<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
	var sessionId="${sessionId}";
	var adminFlag="${common_login_user.adminFlag }";
	var basePath="${basePath}";
</script>

<link href="resources/images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="resources/js/tools/jquery-easyui-1.4.4/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="resources/js/tools/jquery-easyui-1.4.4/themes/icon.css" rel="stylesheet" type="text/css" />

<link href="resources/css/themes/Default/style.css" rel="stylesheet" type="text/css" />
<link href="resources/css/themes/Default/default.css" rel="stylesheet" type="text/css" />

<!-- 添加对easyui的支持 --> 
<script src="resources/js/tools/jquery-easyui-1.4.4/jquery.min.js" type="text/javascript"></script>
<script src="resources/js/tools/jquery-easyui-1.4.4/jquery.easyui.min.js" type="text/javascript"></script>
<script src="resources/js/tools/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>

<!-- 添加对ztree控件的支持 --> 
<link href="resources/js/tools/zTree_v3/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<script src="resources/js/tools/zTree_v3/js/jquery.ztree.core-3.5.min.js" type="text/javascript"></script>
<script src="resources/js/tools/zTree_v3/js/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>

<!-- 添加对uploadify控件的支持 --> 
<script src="resources/js/tools/uploadify/jquery.uploadify.js" type="text/javascript"></script>
<link href="resources/js/tools/uploadify/uploadify.css" rel="stylesheet" type="text/css" />

<!-- 添加对ckeditor的支持 -->
<script src="resources/js/tools/ckeditor/ckeditor.js"></script>
<script src="resources/js/tools/ckeditor/adapters/jquery.js"></script>

<!-- 日期格式的引用 -->
<script src="resources/js/datapattern.js" type="text/javascript"></script>

<script src="resources/js/common/common.js?r=${jsVersion }" type="text/javascript"></script>
    
</head>
</html>