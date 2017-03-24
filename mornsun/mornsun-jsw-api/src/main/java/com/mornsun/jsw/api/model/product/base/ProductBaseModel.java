package com.mornsun.jsw.api.model.product.base;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 产品基础Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductBaseModel extends BaseModel  {

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 产品状态
     */
    private String productState;

    /**
     * 产品描述
     */
    @Validate
    private String productDesc;

    private static final long serialVersionUID = 1L;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState == null ? null : productState.trim();
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }

}
