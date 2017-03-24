package com.mornsun.jsw.api.model.product.replace;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 产品替换Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductReplaceModel extends BaseModel  {

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 替换产品ID
     */
    private String replaceProductId;

    /**
     * 匹配度
     */
    @Validate
    private String matchType;

    private static final long serialVersionUID = 1L;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getReplaceProductId() {
        return replaceProductId;
    }

    public void setReplaceProductId(String replaceProductId) {
        this.replaceProductId = replaceProductId == null ? null : replaceProductId.trim();
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType == null ? null : matchType.trim();
    }

}
