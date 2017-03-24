package com.mornsun.jsw.api.vo.brand;

import com.mornsun.jsw.api.model.brand.BrandModel;

/**
 * 品牌Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class BrandVo extends BrandModel {

    private static final long serialVersionUID = 1L;

    /**
     * 产品数量
     */
    private int productCount;

    /**
     * 分类ID
     */
    private String catalogId;

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

}
