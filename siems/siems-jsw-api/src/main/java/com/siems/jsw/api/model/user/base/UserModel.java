package com.siems.jsw.api.model.user.base;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 用户Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserModel extends BaseModel  {

    /**
     * 客户公司ID
     */
    @Validate
    private String customerId;

    /**
     * 用户名
     */
    @Validate
    private String userName;

    /**
     * 密码
     */
    @Validate
    private String passWord;

    /**
     * 部门ID
     */
    @Validate
    private String deptId;

    /**
     * 用户姓名
     */
    @Validate
    private String userAlias;

    /**
     * 联系电话
     */
    @Validate
    private String contact;

    /**
     * 账号类别：1-系统管理员，2-超级管理员
     */
    @Validate
    private String type;

    private static final long serialVersionUID = 1L;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias == null ? null : userAlias.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

}
