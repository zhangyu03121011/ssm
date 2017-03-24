package com.siems.jsw.api.model.display.base;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 陈列区域Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class DisplayModel extends BaseModel  {

    /**
     * 客户公司ID
     */
    @Validate
    private String customerId;

    /**
     * 类别名称
     */
    @Validate
    private String displayName;

    /**
     * 硬件数量
     */
    @Validate
    private Integer harewareCount;

    private static final long serialVersionUID = 1L;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    public Integer getHarewareCount() {
        return harewareCount;
    }

    public void setHarewareCount(Integer harewareCount) {
        this.harewareCount = harewareCount;
    }

}
