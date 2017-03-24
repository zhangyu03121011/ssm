package com.siems.jsw.api.model.product.type;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 商品类别Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductTypeModel extends BaseModel  {

    /**
     * 类别名称
     */
    @Validate
    private String productTypeName;

    private static final long serialVersionUID = 1L;

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName == null ? null : productTypeName.trim();
    }

}
