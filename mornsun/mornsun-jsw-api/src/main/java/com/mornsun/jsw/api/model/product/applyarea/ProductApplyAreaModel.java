package com.mornsun.jsw.api.model.product.applyarea;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 产品应用领域Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductApplyAreaModel extends BaseModel  {

    /**
     * 产品ID
     */
    @Validate
    private String productId;

    /**
     * 应用领域ID
     */
    @Validate
    private String applyAreaId;

    private static final long serialVersionUID = 1L;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getApplyAreaId() {
        return applyAreaId;
    }

    public void setApplyAreaId(String applyAreaId) {
        this.applyAreaId = applyAreaId == null ? null : applyAreaId.trim();
    }

}
