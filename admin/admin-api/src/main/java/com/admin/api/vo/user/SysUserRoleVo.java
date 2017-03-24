package com.admin.api.vo.user;

import com.admin.api.model.role.SysRoleModel;
import com.admin.api.model.user.SysUserModel;
import com.admin.api.model.user.SysUserRoleModel;

/**
 * 用戶角色关系VO
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysUserRoleVo extends SysUserRoleModel {

    private static final long serialVersionUID = 43522857632264691L;

    /**
     * 删除标识
     */
    private boolean deleteFlag = false;

    /**
     * 用戶Model
     */
    private SysUserModel sysUserModel;

    /**
     * 角色Model
     */
    private SysRoleModel sysRoleModel;

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public SysUserModel getSysUserModel() {
        return sysUserModel;
    }

    public void setSysUserModel(SysUserModel sysUserModel) {
        this.sysUserModel = sysUserModel;
    }

    public SysRoleModel getSysRoleModel() {
        return sysRoleModel;
    }

    public void setSysRoleModel(SysRoleModel sysRoleModel) {
        this.sysRoleModel = sysRoleModel;
    }

}