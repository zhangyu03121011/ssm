<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<title>秒懂详情页</title>
	<script type="text/javascript" src="./../../resources/js/flexible.debug.js"></script>
	<script type="text/javascript" src="./../../resources/js/flexible_css.debug.js"></script>
	<link rel="stylesheet" type="text/css" href="./../../resources/css/details.css" />
</head>
<body>
	<header>
		<a href="javascript:;" onclick="javascript:history.go(-1);"></a>
		<h4>详情</h4>
	</header>
	<div class="content">
		<div class="title">
			${resultVo.obj.title }
		</div>
		<div class="listion">
			<div class="money">
				<i><em>
				<c:choose>
		      		<c:when test="${resultVo.obj.payMoney!=null}">
		      			${resultVo.obj.payMoney }
		      		</c:when>
		      		<c:otherwise>
		      			0
		      		</c:otherwise>
		      	</c:choose>	
				元秒懂</em></i>
				<span class="time">
					<c:choose>
			      		<c:when test="${resultVo.obj.attaVos!=null}">
			      			${resultVo.obj.attaVos[0].fileDuration }
			      		</c:when>
			      		<c:otherwise>
			      			0
			      		</c:otherwise>
		      		</c:choose>	
				秒</span>
			</div>
		</div>
		<div class="values">
			<p class="st"><i></i>
	      		<c:when test="${resultVo.obj.readCount!=null}">
		      			${resultVo.obj.readCount }
		      		</c:when>
		      		<c:otherwise>
		      			0
		      		</c:otherwise>
	      		</c:choose>	
			</p>
			<p class="zan"><i></i>
	      		<c:when test="${resultVo.obj.praiseCount!=null}">
		      			${resultVo.obj.praiseCount }
		      		</c:when>
		      		<c:otherwise>
		      			0
		      		</c:otherwise>
	      		</c:choose>	
			</p>
		</div>
	</div>
	<div class="follow">
		<div class="user">
			<img src="./../../resources/images/change-user.png" alt="" />
			<p class="name">${resultVo.obj.userVo.userName }</p>
			<p>个人介绍</p>
			<p><i>
				<c:choose>
		      		<c:when test="${resultVo.obj.readCount!=null}">
		      			${resultVo.obj.readCount }
		      		</c:when>
		      		<c:otherwise>
		      			0
		      		</c:otherwise>
	      		</c:choose>	
			</i>人听过TA</p>
		</div>
		<div class="gz">
			<i></i>
			<p>加关注</p>
		</div>
	</div>
</body>
</html>