package com.siems.jsw.api.model.role.base;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 角色Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class RoleModel extends BaseModel  {
    /**
     * 角色ID
     */
    @Validate
    private String roleId;

    /**
     * 客户公司ID
     */
    @Validate
    private String customerId;

    /**
     * 角色key
     */
    @Validate
    private String roleKey;

    /**
     * 角色名称
     */
    @Validate
    private String roleName;

    private static final long serialVersionUID = 1L;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey == null ? null : roleKey.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

}
