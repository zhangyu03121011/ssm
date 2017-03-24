package com.admin.api.model.role;

import com.common.base.annotation.Primary;
import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 系统角色菜单功能Model
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysRoleMenuFunctionModel extends BaseModel {

    private static final long serialVersionUID = 1579682517617778834L;

    /**
     * 角色菜单功能ID
     */
    @Primary
    private String roleMenuFunctionId;

    /**
     * 角色菜单ID
     */
    @Validate
    private String roleMenuId;
    
    /**
     * 功能ID
     */
    @Validate
    private String functionId;

    public String getRoleMenuFunctionId() {
        return roleMenuFunctionId;
    }

    public void setRoleMenuFunctionId(String roleMenuFunctionId) {
        this.roleMenuFunctionId = roleMenuFunctionId == null ? null : roleMenuFunctionId.trim();
    }

    public String getRoleMenuId() {
        return roleMenuId;
    }

    public void setRoleMenuId(String roleMenuId) {
        this.roleMenuId = roleMenuId == null ? null : roleMenuId.trim();
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

}