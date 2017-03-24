package com.mornsun.crm.manager.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.common.base.common.BaseLogger;
import com.common.base.vo.base.PageListVo;

/**
 * CRM切面拦截（主要拦截controller）
 * 
 * @author: HuiJunLuo
 * @date:2016年3月2日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Component
@Aspect
@Order(value = Ordered.LOWEST_PRECEDENCE + 1000)
public class CrmControllerAspect extends BaseLogger {

	@SuppressWarnings("rawtypes")
	@Around("(execution(* com.mornsun..controller..*.*(..)) or execution(* com.common..controller..*.*(..))) "
			+ "and !execution(* com..controller..initBinder(..)) and !execution(* com..controller..ListMethodArgumentsResolver.supportsParameter(..))")
	public Object around(ProceedingJoinPoint point) throws Throwable {

		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		// 传递参数
		Object params[] = point.getArgs();
		for (Object object : params) {
			if (object instanceof PageListVo) {
				PageListVo pageListVo = (PageListVo) object;
				// 获取分页参数（用于easyui，参数名称与定义不同）
				Object page = request.getParameter("page");
				if (page != null) {
					pageListVo.setCurrPage(Integer.parseInt(page.toString()));
				}
				Object rows = request.getParameter("rows");
				if (rows != null) {
					pageListVo.setPageSize(Integer.parseInt(rows.toString()));
				}
			}
		}

		logger.info("[CrmControllerAspect][around]" + point);

		return point.proceed();
	}

}
