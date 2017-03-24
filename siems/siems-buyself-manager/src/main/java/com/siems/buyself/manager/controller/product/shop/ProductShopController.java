package com.siems.buyself.manager.controller.product.shop;

import com.siems.buyself.core.service.product.shop.IProductShopService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.siems.jsw.api.vo.product.shop.ProductShopVo;

/**
 * 商品门店关系Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/product/shop")
public class ProductShopController extends BasePageHelperApiServiceController<ProductShopVo,IProductShopService> {

}
