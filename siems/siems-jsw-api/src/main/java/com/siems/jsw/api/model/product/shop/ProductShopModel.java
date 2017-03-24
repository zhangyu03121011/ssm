package com.siems.jsw.api.model.product.shop;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 商品门店关系Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductShopModel extends BaseModel  {

    /**
     * 商品ID
     */
    @Validate
    private String productId;

    /**
     * 门店ID
     */
    @Validate
    private String shopId;

    private static final long serialVersionUID = 1L;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

}
