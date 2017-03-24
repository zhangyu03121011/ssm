package com.mornsun.jsw.api.vo.product.base;

import java.util.List;

import com.mornsun.jsw.api.model.product.base.ProductModel;
import com.mornsun.jsw.api.vo.product.tag.ProductTagVo;

/**
 * 产品Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductVo extends ProductModel {

    private static final long serialVersionUID = 1L;

    private String updateUserAlias;

    /**
     * 审批错误类别（1-分类错误，2-描述错误，3-应用领域错误，4-参数错误，5-其他错误）
     */
    private String errorType;

    private List<ProductTagVo> productTagVos;

    public String getUpdateUserAlias() {
        return updateUserAlias;
    }

    public void setUpdateUserAlias(String updateUserAlias) {
        this.updateUserAlias = updateUserAlias;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public List<ProductTagVo> getProductTagVos() {
        return productTagVos;
    }

    public void setProductTagVos(List<ProductTagVo> productTagVos) {
        this.productTagVos = productTagVos;
    }

}
