package com.siems.buyself.core.service.product.type.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;

import com.siems.jsw.api.api.product.type.IProductTypeApi;
import com.siems.buyself.core.service.product.type.IProductTypeService;
import com.siems.jsw.api.vo.product.type.ProductTypeVo;

/**
 * 商品类别Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class ProductTypeServiceImpl extends BasePageHelperApiServiceImpl<ProductTypeVo,IProductTypeApi>  implements IProductTypeService {  

}
