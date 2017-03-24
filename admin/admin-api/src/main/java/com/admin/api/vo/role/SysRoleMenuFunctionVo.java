package com.admin.api.vo.role;

import com.admin.api.model.function.SysFunctionModel;
import com.admin.api.model.role.SysRoleMenuFunctionModel;
import com.admin.api.model.role.SysRoleMenuModel;

/**
 * 系统角色菜单功能VO
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysRoleMenuFunctionVo extends SysRoleMenuFunctionModel {

    private static final long serialVersionUID = 1579682517617778834L;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 菜单Key
     */
    private String menuKey;

    /**
     * 应用KEY
     */
    private String appKey;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 功能Model
     */
    private SysFunctionModel sysFunctionModel;

    /**
     * 角色菜单Model
     */
    private SysRoleMenuModel sysRoleMenuModel;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public SysRoleMenuModel getSysRoleMenuModel() {
        return sysRoleMenuModel;
    }

    public void setSysRoleMenuModel(SysRoleMenuModel sysRoleMenuModel) {
        this.sysRoleMenuModel = sysRoleMenuModel;
    }

    public SysFunctionModel getSysFunctionModel() {
        return sysFunctionModel;
    }

    public void setSysFunctionModel(SysFunctionModel sysFunctionModel) {
        this.sysFunctionModel = sysFunctionModel;
    }

    public String getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}