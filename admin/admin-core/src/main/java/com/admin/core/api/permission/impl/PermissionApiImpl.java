package com.admin.core.api.permission.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.api.api.permission.IPermissionApi;
import com.admin.api.vo.permission.PermissionVo;
import com.admin.core.service.permission.ISysPermissionService;
import com.alibaba.fastjson.JSON;
import com.common.base.common.BaseLogger;

/**
 * 权限API
 * 
 * @author: HuiJunLuo
 * @date:2016年3月15日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class PermissionApiImpl extends BaseLogger implements IPermissionApi {

    @Autowired
    private ISysPermissionService sysPermissionService;

    @Override
    public boolean hasPermission(String sessionId, String systemSn, String moduleSn, String permission) {
        boolean flag = false;
        try {
            flag = sysPermissionService.hasPermission(sessionId, systemSn, moduleSn, permission);
            logger.info("[PermissionApiImpl][hasPermission][sessionId=" + sessionId + "][systemSn=" + systemSn
                    + "][moduleSn=" + moduleSn + "][permission=" + permission + "][flag=" + flag + "]");
        } catch (Exception e) {
            logger.error("[PermissionApiImpl][hasPermission]" + e.getMessage(), e);
        }
        return flag;
    }

    @Override
    public boolean hasPermission(PermissionVo permissionVo) {
        boolean flag = false;
        try {
            flag = sysPermissionService.hasPermission(permissionVo.getSessionId(), permissionVo.getSystemSn(),
                    permissionVo.getModuleSn(), permissionVo.getPermission());
            logger.info("[PermissionApiImpl][hasPermission][permissionVo=" + JSON.toJSONString(permissionVo) + "][flag="
                    + flag + "]");
        } catch (Exception e) {
            logger.error("[PermissionApiImpl][hasPermission][permissionVo=" + JSON.toJSONString(permissionVo) + "]"
                    + e.getMessage(), e);
        }
        return flag;
    }

}
