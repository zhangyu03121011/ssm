package com.admin.api.vo.user;

import com.admin.api.model.app.SysAppModel;
import com.admin.api.model.user.SysUserAppModel;
import com.admin.api.model.user.SysUserModel;

/**
 * 用户应用关系VO
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysUserAppVo extends SysUserAppModel {

    private static final long serialVersionUID = 2163419946197246789L;

    /**
     * 删除标识
     */
    private boolean deleteFlag = false;

    /**
     * 应用Model
     */
    private SysAppModel sysAppModel;

    /**
     * 用户Model
     */
    private SysUserModel sysUserModel;

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public SysAppModel getSysAppModel() {
        return sysAppModel;
    }

    public void setSysAppModel(SysAppModel sysAppModel) {
        this.sysAppModel = sysAppModel;
    }

    public SysUserModel getSysUserModel() {
        return sysUserModel;
    }

    public void setSysUserModel(SysUserModel sysUserModel) {
        this.sysUserModel = sysUserModel;
    }

}