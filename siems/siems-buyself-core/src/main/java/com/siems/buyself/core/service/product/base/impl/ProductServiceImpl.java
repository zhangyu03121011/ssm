package com.siems.buyself.core.service.product.base.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;

import com.siems.jsw.api.api.product.base.IProductApi;
import com.siems.buyself.core.service.product.base.IProductService;
import com.siems.jsw.api.vo.product.base.ProductVo;

/**
 * 商品Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class ProductServiceImpl extends BasePageHelperApiServiceImpl<ProductVo,IProductApi>  implements IProductService {  

}
