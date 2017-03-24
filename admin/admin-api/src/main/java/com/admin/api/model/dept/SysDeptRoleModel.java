package com.admin.api.model.dept;

import com.common.base.annotation.Primary;
import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 部门角色关系Model
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysDeptRoleModel extends BaseModel {
    
    private static final long serialVersionUID = 5153924195195670619L;

    /**
     * 部门角色关系ID
     */
    @Primary
    private String deptRoleId;

    /**
     * 部门ID
     */
    @Validate
    private String deptId;

    /**
     * 角色ID
     */
    @Validate
    private String roleId;


    public String getDeptRoleId() {
        return deptRoleId;
    }

    public void setDeptRoleId(String deptRoleId) {
        this.deptRoleId = deptRoleId == null ? null : deptRoleId.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

}