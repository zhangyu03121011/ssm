package com.mornsun.jsw.api.model.user.employee;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 用户内部员工Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserEmployeeModel extends BaseModel  {

    /**
     * 用户ID
     */
    @Validate
    private String userId;

    /**
     * 员工姓名
     */
    @Validate
    private String employeeName;

    /**
     * 员工编号
     */
    @Validate
    private String employeeNo;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName == null ? null : employeeName.trim();
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo == null ? null : employeeNo.trim();
    }

}
