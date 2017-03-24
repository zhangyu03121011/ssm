package com.siems.jsw.api.model.brand;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 品牌Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class BrandModel extends BaseModel  {

    /**
     * 品牌编号
     */
    @Validate
    private String brandNo;

    /**
     * 品牌名称
     */
    @Validate
    private String brandName;

    /**
     * 网站
     */
    @Validate
    private String brandUrl;

    private static final long serialVersionUID = 1L;

    public String getBrandNo() {
        return brandNo;
    }

    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo == null ? null : brandNo.trim();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getBrandUrl() {
        return brandUrl;
    }

    public void setBrandUrl(String brandUrl) {
        this.brandUrl = brandUrl == null ? null : brandUrl.trim();
    }

}
