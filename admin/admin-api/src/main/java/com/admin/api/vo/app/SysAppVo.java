package com.admin.api.vo.app;

import java.util.List;

import com.admin.api.model.app.SysAppModel;
import com.admin.api.model.menu.SysMenuModel;

/**
 * 系统应用VO
 * 
 * @author: HuiJunLuo
 * @date:2016年2月2日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysAppVo extends SysAppModel {

    private static final long serialVersionUID = 8766311030150634742L;

    /**
     * 用户ID
     */
    private String userId;
    
    /**
     * 是否是管理员
     */
    private boolean adminFlag=false;

    /**
     * 系统菜单列表
     */
    private List<SysMenuModel> sysMenuModels;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(boolean adminFlag) {
        this.adminFlag = adminFlag;
    }

    public List<SysMenuModel> getSysMenuModels() {
        return sysMenuModels;
    }

    public void setSysMenuModels(List<SysMenuModel> sysMenuModels) {
        this.sysMenuModels = sysMenuModels;
    }

}
