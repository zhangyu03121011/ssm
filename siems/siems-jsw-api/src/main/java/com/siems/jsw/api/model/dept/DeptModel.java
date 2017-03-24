package com.siems.jsw.api.model.dept;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 部门Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class DeptModel extends BaseModel  {
    /**
     * 部门ID
     */
    @Validate
    private String deptId;

    /**
     * 客户公司ID
     */
    @Validate
    private String customerId;

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
     * 部门类型
     */
    @Validate
    private String deptType;

    private static final long serialVersionUID = 1L;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
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

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType == null ? null : deptType.trim();
    }

}
