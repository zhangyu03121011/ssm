package com.admin.api.vo.user;

import com.admin.api.model.user.SysUserModel;

/**
 * 用戶VO
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysUserVo extends SysUserModel {

    private static final long serialVersionUID = 43522857632264691L;

    /**
     * 原密码
     */
    private String oldPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

}