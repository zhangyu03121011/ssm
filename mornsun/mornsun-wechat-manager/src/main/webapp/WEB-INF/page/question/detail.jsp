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
	<title>问答详情页</title>
	<script type="text/javascript" src="./../../resources/js/flexible.debug.js"></script>
	<script type="text/javascript" src="./../../resources/js/flexible_css.debug.js"></script>
	<link rel="stylesheet" type="text/css" href="./../../resources/css/details.css" />
</head>
<body>
	<header>
		<a href="javascript:;" onclick="javascript:history.go(-1);"></a>
		<h4>详情</h4>
	</header>
	<div class="content question">
		<p class="user-queation"><i>${resultVo.obj.userVo.userName }</i>问:</p>
		<p class="question-item">${resultVo.obj.title }</p>
		<div class="btn">
			<a href="javascript:;"><button>我的心得</button></a>
		</div>
	</div>
	<div class="number">
		<span>
			<c:choose>
	      		<c:when test="${resultVo.obj.answerCount!=null}">
	      			${resultVo.obj.answerCount }
	      		</c:when>
	      		<c:otherwise>
	      			0
	      		</c:otherwise>
	      	</c:choose>	
		人回答
		</span>
		<a href="javascript:;" class="rm">热门</a>
		<a href="javascript:;" class="zx">最新</a>
	</div>
	<c:forEach var="item" items="${resultVo.obj.userAnswerVos}" varStatus="status">
		<div class="content question-list">
			<div class="question-name">
				<img src="./../../resources/images/change-user.png" alt="" />
				<p class="name">${item.userVo.userName }</p>
			</div>
			<div class="listion tt-ting">
				<div class="money">
					<i><em>${item.payMoney }元偷偷听</em></i>
					<span class="time">
						<c:choose>
				      		<c:when test="${item.attaVos!=null}">
				      			${item.attaVos[0].fileDuration }
				      		</c:when>
				      		<c:otherwise>
				      			0
				      		</c:otherwise>
			      		</c:choose>	
						秒
					</span>
				</div>
			</div>
			<div class="values">
				<p class="st"><em>
					<c:choose>
			      		<c:when test="${item.readCount!=null}">
			      			${item.readCount }
			      		</c:when>
			      		<c:otherwise>
			      			0
			      		</c:otherwise>
		      		</c:choose>	
				</em>人听过</p>
				<p class="zan"><i></i>
					<c:choose>
			      		<c:when test="${item.praiseCount!=null}">
			      			${item.praiseCount }
			      		</c:when>
			      		<c:otherwise>
			      			0
			      		</c:otherwise>
		      		</c:choose>	
				</p>
			</div>
		</div>
	</c:forEach> 
</body>
</html>