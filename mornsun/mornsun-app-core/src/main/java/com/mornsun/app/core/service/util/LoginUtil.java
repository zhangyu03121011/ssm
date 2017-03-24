package com.mornsun.app.core.service.util;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import com.common.base.common.BaseLogger;
import com.common.base.model.base.BaseModel;
import com.common.base.model.user.BaseUserModel;
import com.common.base.vo.base.PageListVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.util.SessionUtil;

/**
 * 登录工具类
 * 
 * @author: HuiJunLuo
 * @date:2016年2月2日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class LoginUtil extends BaseLogger {

    private static LoginUtil loginUtil = null;

    public synchronized static LoginUtil getInstance() {
        if (loginUtil == null) {
            loginUtil = new LoginUtil();
        }
        return loginUtil;
    }

    private LoginUtil() {
    }

    /**
     * 获取用户登录信息
     * 
     * @param request
     * @return
     */
    public BaseUserModel getLoginUser(IMemcacheService memcacheService, String sessionId, HttpServletRequest request) {
        if (StringUtils.isEmpty(sessionId)) {
            sessionId = SessionUtil.getInstance().getSessionId(request);
        }
        Object object = null;
        try {
            object = memcacheService.get(sessionId);
        } catch (Exception e) {
        }
        if (object != null) {
            BaseUserModel baseUserModel = (BaseUserModel) object;
            return baseUserModel;
        } else {
            logger.error("[LoginUtil][setCreateBy]session is null...");
        }
        return null;
    }

    /**
     * 获取用户登录信息
     * 
     * @param request
     * @return
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends BaseUserModel> T getLoginUser(Class<T> obj, IMemcacheService memcacheService, String sessionId,
            HttpServletRequest request) {
        if (StringUtils.isEmpty(sessionId)) {
            sessionId = SessionUtil.getInstance().getSessionId(request);
        }
        Object object = null;
        try {
            object = memcacheService.get(sessionId);
        } catch (Exception e) {
        }
        if (object != null) {
            return (T) object;
        } else {
            logger.error("[LoginUtil][setCreateBy]session is null...");
        }
        return null;
    }

    /**
     * 设置createBy
     * 
     * @param request
     * @return
     */
    public <T extends BaseModel> void setCreateBy(T obj, IMemcacheService memcacheService, String sessionId,
            HttpServletRequest request) {
        if (StringUtils.isEmpty(sessionId)) {
            sessionId = SessionUtil.getInstance().getSessionId(request);
        }
        if (obj.getCreateTime() == null) {
            obj.setCreateTime(Calendar.getInstance().getTime());
        }
        Object object = null;
        try {
            object = memcacheService.get(sessionId);
        } catch (Exception e) {
        }
        if (object != null) {
            BaseUserModel baseUserModel = (BaseUserModel) object;
            if (StringUtils.isEmpty(obj.getAppId())) {
                obj.setAppId(baseUserModel.getAppId());
            }
            if (StringUtils.isEmpty(obj.getCreateBy())) {
                obj.setCreateBy(baseUserModel.getUserId());
            }
        } else {
            logger.error("[LoginUtil][setCreateBy]session is null...");
        }
    }

    /**
     * 设置createBy
     * 
     * @param request
     * @return
     */
    public <T extends BaseModel> void setUpdateBy(T obj, IMemcacheService memcacheService, String sessionId,
            HttpServletRequest request) {
        if (StringUtils.isEmpty(sessionId)) {
            sessionId = SessionUtil.getInstance().getSessionId(request);
        }
        if (obj.getUpdateTime() == null) {
            obj.setUpdateTime(Calendar.getInstance().getTime());
        }
        Object object = null;
        try {
            object = memcacheService.get(sessionId);
        } catch (Exception e) {
        }
        if (object != null) {
            BaseUserModel baseUserModel = (BaseUserModel) object;
            if (StringUtils.isEmpty(obj.getAppId())) {
                obj.setAppId(baseUserModel.getAppId());
            }
            if (StringUtils.isEmpty(obj.getUpdateBy())) {
                obj.setUpdateBy(baseUserModel.getUserId());
            }
        } else {
            logger.error("[LoginUtil][setUpdateBy]session is null...");
        }
    }

    /**
     * 判断是否有ResponseBody注解
     * 
     * @param handler
     * @return
     */
    public boolean hasResponseBody(Object handler) {
        boolean flag = false;
        // 判断是否有ResponseBody注解，如果有则跳过拦截（ResponseBody注解返回流，拦截器无法跳转到外部url）
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ResponseBody responseBody = handlerMethod.getMethod().getAnnotation(ResponseBody.class);
            if (responseBody != null) {
                // logger.info(handlerMethod.getBean() + "----" +
                // handlerMethod.getMethod().getName());
                // logger.info(handlerMethod.getMethod().getName());
                // logger.info(handlerMethod.getMethod().getReturnType());
                // throw new Exception();
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 判断是否有ResponseBody注解
     * 
     * @param handler
     * @return
     */
    public boolean hasResponseBodyAndPage(Object handler) {
        boolean flag = false;
        // 判断是否有ResponseBody注解，如果有则跳过拦截（ResponseBody注解返回流，拦截器无法跳转到外部url）
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ResponseBody responseBody = handlerMethod.getMethod().getAnnotation(ResponseBody.class);
            if (responseBody != null
                    && PageListVo.class.getName().equals(handlerMethod.getMethod().getReturnType().getName())) {
                flag = true;
            }
        }
        return flag;
    }

}
