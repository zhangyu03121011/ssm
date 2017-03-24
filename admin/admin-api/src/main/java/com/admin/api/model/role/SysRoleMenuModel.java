package com.admin.api.model.role;

import com.common.base.annotation.Primary;
import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 系统角色菜单Model
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysRoleMenuModel extends BaseModel {

    private static final long serialVersionUID = 8492131871435728943L;

    /**
     * 角色菜单ID
     */
    @Primary
    private String roleMenuId;

    /**
     * 角色ID
     */
    @Validate
    private String roleId;

    /**
     * 菜单ID
     */
    @Validate
    private String menuId;

    public String getRoleMenuId() {
        return roleMenuId;
    }

    public void setRoleMenuId(String roleMenuId) {
        this.roleMenuId = roleMenuId == null ? null : roleMenuId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

}