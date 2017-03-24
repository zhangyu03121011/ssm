package com.common.controller.resolver;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSON;
import com.common.base.annotation.ListBody;
import com.common.base.common.BaseLogger;

/**
 * 自定义参数解析
 * 
 * @author: HuiJunLuo
 * @date:2016年3月23日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ListMethodArgumentsResolver extends BaseLogger implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(ListBody.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		logger.info("[ListMethodArgumentsResolver]begin...");

		// 获取泛型类型
		ParameterizedType pType = (ParameterizedType) parameter.getGenericParameterType();
		Class<?> entityClass = null;
		if (pType.getActualTypeArguments()[0] instanceof Class) {
			entityClass = (Class<?>) pType.getActualTypeArguments()[0];
		} else {
			pType = (ParameterizedType) parameter.getContainingClass().getGenericSuperclass();
			entityClass = (Class<?>) pType.getActualTypeArguments()[0];
		}

		// 获取参数类型
		if (entityClass != null) {
			if (parameter.getParameterType().getName().equals(List.class.getName())) {

				logger.info("[ListMethodArgumentsResolver]开始解析list类型参数");

				// 获取参数值
				String jsonStr = webRequest.getParameter(List.class.getSimpleName().toLowerCase());
				return JSON.parseArray(jsonStr, entityClass);

			} else {

				logger.error("[ListMethodArgumentsResolver]非list类型参数不做解析");

				return BeanUtils.instantiate(parameter.getParameterType());

			}
		} else {
			logger.error("[ListMethodArgumentsResolver][entityClass=" + entityClass + "]类型错误");
			return null;
		}

	}

}
