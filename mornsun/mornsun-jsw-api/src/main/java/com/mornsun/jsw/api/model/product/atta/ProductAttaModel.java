package com.mornsun.jsw.api.model.product.atta;


import com.common.base.model.base.BaseModel;

/**
 * 产品附件Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductAttaModel extends BaseModel  {

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 附件ID
     */
    private String attaId;

    private static final long serialVersionUID = 1L;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getAttaId() {
        return attaId;
    }

    public void setAttaId(String attaId) {
        this.attaId = attaId == null ? null : attaId.trim();
    }

}
