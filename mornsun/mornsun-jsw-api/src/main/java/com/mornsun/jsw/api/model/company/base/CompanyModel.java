package com.mornsun.jsw.api.model.company.base;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 企业Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class CompanyModel extends BaseModel  {

    /**
     * 企业名称
     */
    @Validate
    private String companyName;

    /**
     * 企业品牌
     */
    @Validate
    private String companyBrand;

    /**
     * 头像
     */
    private String headImage;

    /**
     * 手机
     */
    @Validate
    private String mobile;

    private static final long serialVersionUID = 1L;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyBrand() {
        return companyBrand;
    }

    public void setCompanyBrand(String companyBrand) {
        this.companyBrand = companyBrand == null ? null : companyBrand.trim();
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage == null ? null : headImage.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

}
