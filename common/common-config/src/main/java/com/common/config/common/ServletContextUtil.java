package com.common.config.common;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

/**
 * 获取ServletContext信息
 * 
 * @author: HuiJunLuo
 * @date:2015年12月28日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ServletContextUtil implements ServletContextAware {

    private static ServletContext servletContext;

    /**
     * 设置ServletContext对象
     */
    @SuppressWarnings("static-access")
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * 得到ServletContext
     * 
     * @return
     */
    public static ServletContext getServletContext() {
        return servletContext;
    }

    /**
     * 得到servletContext中的对象
     * 
     * @param name
     * @return
     */
    public static Object getAppObject(String name) {
        if (servletContext != null) {
            return servletContext.getAttribute(name);
        }
        return null;
    }
}
