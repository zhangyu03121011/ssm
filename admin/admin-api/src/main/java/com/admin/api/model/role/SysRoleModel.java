package com.admin.api.model.role;

import com.common.base.annotation.Primary;
import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 系统角色Model
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysRoleModel extends BaseModel {

    private static final long serialVersionUID = 5806245190191102037L;

    /**
     * 角色ID
     */
    @Primary
    private String roleId;

    /**
     * 角色名称
     */
    @Validate
    private String roleName;

    /**
     * 角色key
     */
    @Validate
    private String roleKey;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

}