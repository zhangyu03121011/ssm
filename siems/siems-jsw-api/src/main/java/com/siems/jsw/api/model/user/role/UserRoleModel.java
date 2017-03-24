package com.siems.jsw.api.model.user.role;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 用户角色关系Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserRoleModel extends BaseModel  {
    /**
     * 用户角色关系ID
     */
    @Validate
    private String userRoleId;

    /**
     * 用户ID
     */
    @Validate
    private String userId;

    /**
     * 角色ID
     */
    @Validate
    private String roleId;

    private static final long serialVersionUID = 1L;

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
