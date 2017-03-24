package com.mornsun.crm.manager.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.common.base.common.BaseLogger;
import com.common.base.constant.PrivilegeConstant;
import com.common.base.model.user.BaseUserModel;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.util.ProjectUtil;
import com.common.util.WebUtil;
import com.mornsun.crm.core.util.LoginUtil;

/**
 * 日志切面拦截（主要拦截controller）
 * 
 * @author: HuiJunLuo
 * @date:2016年3月2日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Component
@Aspect
@Order(value = Ordered.LOWEST_PRECEDENCE + 10000)
public class BaseControllerAspect extends BaseLogger {

	@Autowired
	private IMemcacheService memcacheService;

	@Around("execution(* com.common..controller..BaseController.redirect(..))")
	public Object around(ProceedingJoinPoint point) throws Throwable {

		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		// 获取sessionId
		String sessionId = request.getParameter("sessionId");
		// 获取用户信息
		BaseUserModel baseUserModel = LoginUtil.getInstance().getLoginUser(BaseUserModel.class, memcacheService,
				sessionId.toString(), request);

		request.setAttribute(PrivilegeConstant.SESSION_LOGIN_USER, baseUserModel);
		request.setAttribute("sessionId", sessionId);
		request.setAttribute("basePath", WebUtil.getInstance().getRealBasePath(request));
		request.setAttribute("jsVersion", ProjectUtil.getInstance().getJsVersion(request));

		logger.info("[BaseControllerAspect][around]" + point);

		return point.proceed();
	}

}
