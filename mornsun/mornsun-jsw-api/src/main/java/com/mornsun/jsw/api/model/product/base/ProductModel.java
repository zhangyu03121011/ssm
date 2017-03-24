package com.mornsun.jsw.api.model.product.base;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 产品Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductModel extends BaseModel  {

    /**
     * 元器件部件号
     */
    @Validate
    private String productNo;

    /**
     * 产品分类
     */
    @Validate
    private String catalogId;

    /**
     * 品牌ID
     */
    @Validate
    private String brandId;

    private static final long serialVersionUID = 1L;

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId == null ? null : catalogId.trim();
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId == null ? null : brandId.trim();
    }

}
