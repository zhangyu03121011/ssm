package com.mornsun.jsw.api.vo.product.applyarea;

import com.mornsun.jsw.api.model.product.applyarea.ProductApplyAreaModel;
import com.mornsun.jsw.api.vo.applyarea.ApplyAreaVo;
import com.mornsun.jsw.api.vo.product.base.ProductVo;

/**
 * 产品应用领域Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductApplyAreaVo extends ProductApplyAreaModel {

    private static final long serialVersionUID = 1L;

    private ProductVo productVo;

    private ApplyAreaVo applyAreaVo;

    public ProductVo getProductVo() {
        return productVo;
    }

    public void setProductVo(ProductVo productVo) {
        this.productVo = productVo;
    }

    public ApplyAreaVo getApplyAreaVo() {
        return applyAreaVo;
    }

    public void setApplyAreaVo(ApplyAreaVo applyAreaVo) {
        this.applyAreaVo = applyAreaVo;
    }

}
