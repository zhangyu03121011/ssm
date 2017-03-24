package com.mornsun.jsw.api.model.product.tag;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 产品标签Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductTagModel extends BaseModel  {

    /**
     * 产品ID
     */
    @Validate
    private String productId;

    /**
     * 标签ID
     */
    @Validate
    private String tagId;

    private static final long serialVersionUID = 1L;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId == null ? null : tagId.trim();
    }

}
