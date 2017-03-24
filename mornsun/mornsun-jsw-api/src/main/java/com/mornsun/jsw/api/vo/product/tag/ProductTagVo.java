package com.mornsun.jsw.api.vo.product.tag;

import com.mornsun.jsw.api.model.product.tag.ProductTagModel;
import com.mornsun.jsw.api.vo.product.base.ProductVo;
import com.mornsun.jsw.api.vo.tag.TagVo;

/**
 * 产品标签Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductTagVo extends ProductTagModel {

    private static final long serialVersionUID = 1L;

    private ProductVo productVo;

    private TagVo tagVo;

    public ProductVo getProductVo() {
        return productVo;
    }

    public void setProductVo(ProductVo productVo) {
        this.productVo = productVo;
    }

    public TagVo getTagVo() {
        return tagVo;
    }

    public void setTagVo(TagVo tagVo) {
        this.tagVo = tagVo;
    }

}
