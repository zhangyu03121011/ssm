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
<title>芯得</title>
<link rel="stylesheet" type="text/css" href="./../resources/css/share/share.css" />
</head>
<body>
    <div class="warp">
        <div class="tit">
            <p class="ptit">超多电子专业人士集结于此</p>
            <img src="./../resources/images/phone.png" alt="" />
        </div>
        
        <div class="content">
            <div class="user">
            	<c:choose>
            		<c:when test="${resultVo.res==1 }">
            			<c:choose>
		            		<c:when test="${fn:contains(resultVo.obj.headImage,'http://')}">
		            			<img src="${resultVo.obj.headImage }" alt="" />
		            		</c:when>
		            		<c:otherwise>
		            			<img src="http://120.76.65.211/images/${resultVo.obj.headImage }" alt="" />
		            		</c:otherwise>
		            	</c:choose>	
            		</c:when>
            		<c:otherwise>
            			<img src="./../resources/images/user.png" alt="" />
            		</c:otherwise>
            	</c:choose>
                <p class="name">
                	<c:if test="${resultVo.res==1 }">
                		${resultVo.obj.userName }
                	</c:if>
                </p>
                <p class="pre">邀请你</p>
            </div>
            <div class="link">
                <a href="javascript:;">开启知识赚钱<i></i></a>
            </div>
        </div>
    </div>
</body>
</html>