package com.admin.manager.function;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.admin.core.service.permission.ISysPermissionService;

/**
 * 系统权限控制Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年2月19日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller("permissionFunction")
public class PermissionFunction {

    private static ISysPermissionService sysPermissionService;

    /**
     * @param userId
     *            userId
     * @param systemSn
     *            系统标示
     * @param moduleSn
     *            模块标示
     * @param permission
     *            权限值
     * @return
     * @Description: 通过上面的值判断是否有该权限
     */
    public static boolean hasPermission(String userId, String appKey, String menuKey, String permission) {
        boolean flag = false;
        if (StringUtils.isNotEmpty(userId)) {
            try {
                flag = sysPermissionService.hasPermission(userId, appKey, menuKey, permission);
            } catch (Exception e) {
            }
        }
        return flag;
    }

    @Resource(name = "sysPermissionServiceImpl")
    public void setSysPermissionService(ISysPermissionService sysPermissionService) {
        PermissionFunction.sysPermissionService = sysPermissionService;
    }

}