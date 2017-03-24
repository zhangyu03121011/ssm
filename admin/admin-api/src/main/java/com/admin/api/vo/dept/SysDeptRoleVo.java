package com.admin.api.vo.dept;

import com.admin.api.model.dept.SysDeptModel;
import com.admin.api.model.dept.SysDeptRoleModel;
import com.admin.api.model.role.SysRoleModel;

/**
 * 部门角色关系VO
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysDeptRoleVo extends SysDeptRoleModel {

    private static final long serialVersionUID = 5153924195195670619L;

    /**
     * 删除标识
     */
    private boolean deleteFlag = false;

    /**
     * 部门Model
     */
    private SysDeptModel sysDeptModel;

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

    public SysDeptModel getSysDeptModel() {
        return sysDeptModel;
    }

    public void setSysDeptModel(SysDeptModel sysDeptModel) {
        this.sysDeptModel = sysDeptModel;
    }

    public SysRoleModel getSysRoleModel() {
        return sysRoleModel;
    }

    public void setSysRoleModel(SysRoleModel sysRoleModel) {
        this.sysRoleModel = sysRoleModel;
    }

}