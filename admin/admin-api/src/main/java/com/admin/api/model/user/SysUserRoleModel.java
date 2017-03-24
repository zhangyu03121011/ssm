package com.admin.api.model.user;

import com.common.base.annotation.Primary;
import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 用戶角色关系Model
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysUserRoleModel extends BaseModel {

    private static final long serialVersionUID = 43522857632264691L;

    /**
     * 用戶角色ID
     */
    @Primary
    private String userRoleId;

    /**
     * 用戶ID
     */
    @Validate
    private String userId;

    /**
     * 角色ID
     */
    @Validate
    private String roleId;

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId == null ? null : userRoleId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

}