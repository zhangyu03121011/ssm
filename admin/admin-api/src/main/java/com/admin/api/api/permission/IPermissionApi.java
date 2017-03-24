package com.admin.api.api.permission;

import com.admin.api.vo.permission.PermissionVo;

/**
 * 权限API
 * 
 * @author: HuiJunLuo
 * @date:2016年3月15日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IPermissionApi {

    /**
     * 判断是否有该模块的权限
     * 
     * @param sessionId
     * @param moduleSn
     * @param permission
     * @return
     */
    public boolean hasPermission(String sessionId, String systemSn, String moduleSn, String permission);

    /**
     * 判断是否有该模块的权限
     * 
     * @param permissionVo
     * @return
     */
    public boolean hasPermission(PermissionVo permissionVo);

}
