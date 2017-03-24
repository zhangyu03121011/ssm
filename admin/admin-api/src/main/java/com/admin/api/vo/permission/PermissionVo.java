package com.admin.api.vo.permission;

import com.common.base.model.base.BaseModel;

/**
 * 权限VO
 * 
 * @author: HuiJunLuo
 * @date:2016年3月15日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class PermissionVo extends BaseModel {

    private static final long serialVersionUID = -6270365348986240956L;

    /**
     * 系统key
     */
    private String systemSn;

    /**
     * 模块key
     */
    private String moduleSn;

    /**
     * 权限值
     */
    private String permission;

    public String getSystemSn() {
        return systemSn;
    }

    public void setSystemSn(String systemSn) {
        this.systemSn = systemSn;
    }

    public String getModuleSn() {
        return moduleSn;
    }

    public void setModuleSn(String moduleSn) {
        this.moduleSn = moduleSn;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

}
