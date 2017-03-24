package com.mornsun.wechat.manager.aop;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.common.util.ProjectUtil;
import com.common.util.WebUtil;

/**
 * Wechat切面拦截（主要拦截controller）
 * 
 * @author: HuiJunLuo
 * @date:2016年3月2日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Component
@Aspect
@Order(value = Ordered.LOWEST_PRECEDENCE)
public class WechatControllerAspect {

	private static Logger logger = Logger.getLogger(WechatControllerAspect.class);

	@Around("execution(* com..controller..*.*(..)) and !execution(* com..controller..WechatController.notify(..)) and !execution(* com..controller..initBinder(..))")
	public Object around(ProceedingJoinPoint point) throws Throwable {

		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		request.setAttribute("basePath", WebUtil.getInstance().getRealBasePath(request));
		request.setAttribute("jsVersion", ProjectUtil.getInstance().getJsVersion(request));

		logger.info("[WechatControllerAspect][around]" + point);

		return point.proceed();
	}

}
