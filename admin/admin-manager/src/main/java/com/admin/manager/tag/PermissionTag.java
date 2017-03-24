package com.admin.manager.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.admin.core.service.permission.ISysPermissionService;
import com.admin.core.service.permission.impl.SysPermissionServiceImpl;
import com.admin.core.util.LoginUtil;
import com.common.base.model.user.BaseUserModel;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.cache.memcache.service.impl.MemcacheServiceImpl;
import com.common.config.common.SpringContextHolder;

/**
 * 权限控制Tag
 * 
 * @author: HuiJunLuo
 * @date:2016年2月19日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class PermissionTag extends TagSupport {

    private static final long serialVersionUID = -1507732126553082763L;

    private final static Logger logger = Logger.getLogger(PermissionTag.class);

    private ISysPermissionService sysPermissionService;

    private IMemcacheService memcacheService;

    // sessionId
    private String sessionId;

    // 应用
    private String appKey;

    // 模块
    private String menuKey;

    // 模块中具体权限
    private String permission;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public int doStartTag() throws JspException {

        try {
            sysPermissionService = SpringContextHolder
                    .getBean(StringUtils.uncapitalize(SysPermissionServiceImpl.class.getSimpleName()));
            memcacheService = SpringContextHolder
                    .getBean(StringUtils.uncapitalize(MemcacheServiceImpl.class.getSimpleName()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        // 获取登录信息
        BaseUserModel baseUserModel = LoginUtil.getInstance().getLoginUser(BaseUserModel.class, memcacheService, sessionId,
                ((HttpServletRequest) pageContext.getRequest()));
        boolean flag = false;
        try {
            flag = sysPermissionService.hasPermission(baseUserModel.getUserId(), appKey, menuKey, permission);
        } catch (Exception e) {
        }
        return flag ? EVAL_BODY_INCLUDE : SKIP_BODY;// 真：返回EVAL_BODY_INCLUDE（执行标签）；假：返回SKIP_BODY（跳过标签不执行）
    }

}
