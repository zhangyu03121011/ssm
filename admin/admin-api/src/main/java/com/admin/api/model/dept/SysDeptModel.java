package com.admin.api.model.dept;

import com.common.base.annotation.Primary;
import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 系統部门Model
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysDeptModel extends BaseModel {

    private static final long serialVersionUID = -928853678474146677L;

    /**
     * 部门ID
     */
    @Primary
    private String deptId;

    /**
     * 父级ID
     */
    @Validate
    private String parentId;

    /**
     * 部门名称
     */
    @Validate
    private String deptName;

    /**
     * 地址
     */
    private String address;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

}