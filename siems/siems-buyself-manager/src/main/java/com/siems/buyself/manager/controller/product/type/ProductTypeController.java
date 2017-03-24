package com.siems.buyself.manager.controller.product.type;

import com.siems.buyself.core.service.product.type.IProductTypeService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.siems.jsw.api.vo.product.type.ProductTypeVo;

/**
 * 商品类别Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/product/type")
public class ProductTypeController extends BasePageHelperApiServiceController<ProductTypeVo,IProductTypeService> {

}
