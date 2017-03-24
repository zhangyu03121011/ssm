package com.mornsun.jsw.api.model.brand;


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
     * 品牌名称（中文）
     */
    @Validate
    private String brandName;

    /**
     * 品牌名称（英文）
     */
    @Validate
    private String brandNameEn;

    /**
     * 网站
     */
    @Validate
    private String brandUrl;

    private static final long serialVersionUID = 1L;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getBrandNameEn() {
        return brandNameEn;
    }

    public void setBrandNameEn(String brandNameEn) {
        this.brandNameEn = brandNameEn == null ? null : brandNameEn.trim();
    }

    public String getBrandUrl() {
        return brandUrl;
    }

    public void setBrandUrl(String brandUrl) {
        this.brandUrl = brandUrl == null ? null : brandUrl.trim();
    }

}
