package com.admin.api.vo.menu;

import com.admin.api.model.app.SysAppModel;
import com.admin.api.model.menu.SysMenuModel;

/**
 * 系统菜单VO
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysMenuVo extends SysMenuModel {

    private static final long serialVersionUID = -6236949005001633440L;

    /**
     * 应用Model
     */
    private SysAppModel sysAppModel;

    public SysAppModel getSysAppModel() {
        return sysAppModel;
    }

    public void setSysAppModel(SysAppModel sysAppModel) {
        this.sysAppModel = sysAppModel;
    }

}