package com.admin.api.vo.dict;

import com.admin.api.model.app.SysAppModel;
import com.admin.api.model.dict.SysDictItemModel;

/**
 * 数据字典明细VO
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysDictItemVo extends SysDictItemModel {
    
    private static final long serialVersionUID = 4987844413256895342L;
    
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