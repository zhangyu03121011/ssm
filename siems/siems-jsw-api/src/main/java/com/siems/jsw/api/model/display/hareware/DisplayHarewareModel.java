package com.siems.jsw.api.model.display.hareware;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 陈列区域硬件关系Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class DisplayHarewareModel extends BaseModel  {

    /**
     * 区域ID
     */
    @Validate
    private String displayId;

    /**
     * 硬件ID
     */
    @Validate
    private String hardwareId;

    private static final long serialVersionUID = 1L;

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId == null ? null : displayId.trim();
    }

    public String getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(String hardwareId) {
        this.hardwareId = hardwareId == null ? null : hardwareId.trim();
    }

}
