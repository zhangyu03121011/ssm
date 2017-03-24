package com.siems.jsw.api.model.role.function;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 角色功能关系Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class RoleFunctionModel extends BaseModel  {
    /**
     * 角色功能关系ID
     */
    @Validate
    private String roleFunctionId;

    /**
     * 角色id
     */
    @Validate
    private String roleId;

    /**
     * 功能ID
     */
    @Validate
    private String functionId;

    private static final long serialVersionUID = 1L;

    public String getRoleFunctionId() {
        return roleFunctionId;
    }

    public void setRoleFunctionId(String roleFunctionId) {
        this.roleFunctionId = roleFunctionId == null ? null : roleFunctionId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId == null ? null : functionId.trim();
    }

}
