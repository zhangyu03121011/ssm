package com.admin.manager.aop;

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

import com.admin.api.vo.app.SysAppVo;
import com.admin.core.util.LoginUtil;
import com.common.base.common.BaseLogger;
import com.common.base.model.user.BaseUserModel;
import com.common.base.vo.base.PageListVo;
import com.common.cache.memcache.service.IMemcacheService;

/**
 * CRM切面拦截（主要拦截controller）
 * 
 * @author: HuiJunLuo
 * @date:2016年3月2日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Component
@Aspect
@Order(value = Ordered.LOWEST_PRECEDENCE)
public class AdminControllerAspect extends BaseLogger {

	@Autowired
	private IMemcacheService memcacheService;

	@SuppressWarnings("rawtypes")
	@Around("execution(* com.admin..controller..*(..)) and !execution(* com.admin..controller..initBinder(..))")
	public Object around(ProceedingJoinPoint point) throws Throwable {

		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		// 传递参数
		Object params[] = point.getArgs();
		for (Object object : params) {
			if (object instanceof SysAppVo) {
				SysAppVo appVo = (SysAppVo) object;

				// 获取登录信息
				BaseUserModel baseUserModel = LoginUtil.getInstance().getLoginUser(BaseUserModel.class, memcacheService,
						appVo.getSessionId(), request);
				if (baseUserModel != null) {
					appVo.setAdminFlag(baseUserModel.isAdminFlag());
				}
			}

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

		logger.info("[AdminControllerAspect][around]" + point);

		return point.proceed();
	}

}
