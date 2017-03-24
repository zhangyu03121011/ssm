package com.mornsun.app.manager.aop;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.common.base.common.BaseLogger;
import com.common.base.constant.BaseConstant;
import com.common.base.constant.PrivilegeConstant;
import com.common.base.constant.ResultConstant;
import com.common.base.model.base.BaseModel;
import com.common.base.vo.base.ResultVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.config.common.MethodInterceptorConfigure;
import com.common.util.CommUtil;
import com.common.util.SignUtil;
import com.mornsun.app.api.constant.AppConstant;
import com.mornsun.app.manager.controller.user.base.UserController;
import com.mornsun.jsw.api.vo.user.base.UserVo;

/**
 * 签名切面拦截（主要拦截controller）
 * 
 * @author: HuiJunLuo
 * @date:2016年3月2日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Component
@Aspect
@Order(value = Ordered.HIGHEST_PRECEDENCE + 1000)
public class SignAspect extends BaseLogger {

	@Autowired
	private IMemcacheService memcacheService;

	@Autowired
	private MethodInterceptorConfigure methodInterceptorConfigure;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Around("execution(* com..controller..*.*(..)) and !execution(* com..controller..initBinder(..))")
	public Object around(ProceedingJoinPoint point) throws Throwable {

		Object result = null;

		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		// 请求方法
		String reqMethod = request.getServletPath();

		// 判断是否是不过来方法
		boolean validFlag = methodInterceptorConfigure.matches(reqMethod);
		if (!validFlag) {

			// 判断是否有token
			String token = request.getParameter("token");
			logger.info("==============[token=" + token + "]================");

			// 判断用户是否登录
			if (StringUtils.isNotEmpty(token)) {
				String sessionId = CommUtil.getInstance().getTokenVal(token);
				// 获取用户信息
				UserVo obj = (UserVo) memcacheService.get(sessionId);
				if (obj != null) {

					logger.info("================[sessionId=" + sessionId + "]auth ok=====================");
					request.setAttribute(PrivilegeConstant.SESSION_LOGIN_USER_SESSION_ID, sessionId);
					request.setAttribute("token", token);

					// 传递参数
					Object paramsTmp[] = point.getArgs();
					for (Object object : paramsTmp) {
						if (object instanceof BaseModel) {
							BaseModel baseModel = (BaseModel) object;
							baseModel.setToken(token);
							baseModel.setSessionId(sessionId);

							if ((point.getSignature().getName().equalsIgnoreCase("save")
									|| point.getSignature().getName().equalsIgnoreCase("batch"))
									&& StringUtils.isEmpty(baseModel.getCreateBy())) {
								baseModel.setCreateBy(obj.getId());
							}
							if ((point.getSignature().getName().equalsIgnoreCase("update")
									|| point.getSignature().getName().equalsIgnoreCase("batch"))
									&& StringUtils.isEmpty(baseModel.getUpdateBy())) {
								baseModel.setUpdateBy(obj.getId());
							}
							if ((point.getSignature().getName().equalsIgnoreCase("page")
									|| point.getSignature().getName().equalsIgnoreCase("list"))) {
								if (StringUtils.isEmpty(baseModel.getIsavailable())) {
									baseModel.setIsavailable(BaseConstant.STATUS_YES);
								}
								if (StringUtils.isEmpty(baseModel.getState())) {
									baseModel.setState(AppConstant.STATE_PASS);
								}
							}
						}
					}
				}
			}

			logger.info("[SignAspect][around][no check]" + point);
			return point.proceed();
		}

		// 验证签名算法
		boolean flag = false;

		// 签名请求参数
		Map<String, Object> params = new HashMap<>();
		String sign = request.getParameter("sign");
		String appKey = request.getParameter("appKey");
		String timestamp = request.getParameter("timestamp");

		// 验证参数是否正确
		if (StringUtils.isNotEmpty(timestamp) && StringUtils.isNotEmpty(appKey) && StringUtils.isNotEmpty(sign)) {

			// 获取所有参数
			Map<String, String[]> map = request.getParameterMap();
			if (MapUtils.isNotEmpty(map)) {
				Set<Entry<String, String[]>> set = map.entrySet();
				for (Entry<String, String[]> entry : set) {

					if (StringUtils.isNotEmpty(entry.getKey()) && !entry.getKey().equals("sign")
							&& !entry.getKey().equals("appKey") && !entry.getKey().equals("timestamp")) {
						StringBuffer buffer = new StringBuffer();
						for (String str : entry.getValue()) {
							buffer.append(str);
						}
						params.put(entry.getKey(), buffer.toString());
					}

				}
			}

			// 获取签名算法，比较是否一致
			String signStr = SignUtil.getInstance().getSign(appKey, timestamp, params);
			if (StringUtils.isNotEmpty(signStr) && StringUtils.isNotEmpty(timestamp) && StringUtils.isNotEmpty(appKey)
					&& StringUtils.isNotEmpty(sign)
					&& ((Calendar.getInstance().getTimeInMillis() - Long.parseLong(timestamp)) / (1000 * 60 * 60)) <= 3
					&& sign.substring(3, sign.length() - 5).equals(signStr.substring(3, signStr.length() - 5))) {
				flag = true;
			}
		}

		// 验证通过
		if (flag) {

			// 判断用户是否登录(排除用户注册，登录方法)
			boolean isCheck = true;
			if (point.getTarget().getClass().getName().equals(UserController.class.getName())
					&& !point.getSignature().getName().equalsIgnoreCase("update")) {
				isCheck = false;
			}

			if (isCheck) {

				// 判断是否有token
				String token = request.getParameter("token");
				logger.info("==============[token=" + token + "]================");

				// 判断用户是否登录
				if (StringUtils.isEmpty(token)) {
					logger.info("==============[token=null]拒绝访问================");
					flag = false;
				} else {
					String sessionId = CommUtil.getInstance().getTokenVal(token);
					// 获取用户信息
					UserVo obj = (UserVo) memcacheService.get(sessionId);
					if (obj != null) {

						logger.info("================[sessionId=" + sessionId + "]auth ok=====================");
						request.setAttribute(PrivilegeConstant.SESSION_LOGIN_USER_SESSION_ID, sessionId);
						request.setAttribute("token", token);

						// 传递参数
						Object paramsTmp[] = point.getArgs();
						for (Object object : paramsTmp) {
							if (object instanceof BaseModel) {
								BaseModel baseModel = (BaseModel) object;
								baseModel.setToken(token);
								baseModel.setSessionId(sessionId);

								if ((point.getSignature().getName().equalsIgnoreCase("save")
										|| point.getSignature().getName().equalsIgnoreCase("batch"))
										&& StringUtils.isEmpty(baseModel.getCreateBy())) {
									baseModel.setCreateBy(obj.getId());
								}
								if ((point.getSignature().getName().equalsIgnoreCase("update")
										|| point.getSignature().getName().equalsIgnoreCase("batch"))
										&& StringUtils.isEmpty(baseModel.getUpdateBy())) {
									baseModel.setUpdateBy(obj.getId());
								}
								if ((point.getSignature().getName().equalsIgnoreCase("page")
										|| point.getSignature().getName().equalsIgnoreCase("list"))) {
									if (StringUtils.isEmpty(baseModel.getIsavailable())) {
										baseModel.setIsavailable(BaseConstant.STATUS_YES);
									}
									if (StringUtils.isEmpty(baseModel.getState())) {
										baseModel.setState(AppConstant.STATE_PASS);
									}
								}
							}
						}

						flag = true;

					} else {
						flag = false;
						logger.info("==============token无效，拒绝访问================");
					}
				}
			}

			if (!flag) {
				logger.error("[SignAspect][around]登录信息检查失败...");

				MethodSignature methodSignature = (MethodSignature) (point.getStaticPart().getSignature());
				if (methodSignature.getReturnType().getName().equals(ResultVo.class.getName())) {
					ResultVo resultVo = new ResultVo<>();
					resultVo.setRes(ResultConstant.RESULT_NO_LOGIN);
					result = resultVo;
				} else {
					result = point.proceed();
				}

			} else {
				result = point.proceed();
			}

		} else {

			logger.error("[SignAspect][around]签名认证失败...");

			MethodSignature methodSignature = (MethodSignature) (point.getStaticPart().getSignature());
			if (methodSignature.getReturnType().getName().equals(ResultVo.class.getName())) {
				ResultVo resultVo = new ResultVo<>();
				resultVo.setRes(ResultConstant.RESULT_SIGN_ERROR);
				result = resultVo;
			} else {
				result = point.proceed();
			}

		}

		logger.info("[SignAspect][around]" + point);
		return result;
	}

}
