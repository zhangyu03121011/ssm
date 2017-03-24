package com.admin.core.service.permission;

/**
 * 系统权限控制Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface ISysPermissionService {

    /**
     * 判断是否有该模块的权限
     * 
     * @param sessionId
     * @param moduleSn
     * @param permission
     * @return
     */
    public boolean hasPermission(String sessionId, String systemSn, String moduleSn, String permission)
            throws Exception;

}
