package com.mornsun.jsw.api.vo.user.favorite;

import com.mornsun.jsw.api.model.user.favorite.UserFavoriteModel;
import com.mornsun.jsw.api.vo.product.base.ProductVo;

/**
 * 用户收藏Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserFavoriteVo extends UserFavoriteModel {

    private static final long serialVersionUID = 1L;

    private ProductVo productVo;

    public ProductVo getProductVo() {
        return productVo;
    }

    public void setProductVo(ProductVo productVo) {
        this.productVo = productVo;
    }
}
