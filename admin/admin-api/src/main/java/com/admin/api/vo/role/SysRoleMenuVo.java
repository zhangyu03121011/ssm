package com.admin.api.vo.role;

import com.admin.api.model.menu.SysMenuModel;
import com.admin.api.model.role.SysRoleMenuModel;
import com.admin.api.model.role.SysRoleModel;

/**
 * 系统角色菜单VO
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysRoleMenuVo extends SysRoleMenuModel {

    private static final long serialVersionUID = 8492131871435728943L;

    /**
     * 角色Model
     */
    private SysRoleModel sysRoleModel;

    /**
     * 菜单Model
     */
    private SysMenuModel sysMenuModel;

    public SysRoleModel getSysRoleModel() {
        return sysRoleModel;
    }

    public void setSysRoleModel(SysRoleModel sysRoleModel) {
        this.sysRoleModel = sysRoleModel;
    }

    public SysMenuModel getSysMenuModel() {
        return sysMenuModel;
    }

    public void setSysMenuModel(SysMenuModel sysMenuModel) {
        this.sysMenuModel = sysMenuModel;
    }

}