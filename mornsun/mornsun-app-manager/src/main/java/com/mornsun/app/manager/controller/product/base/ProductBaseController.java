package com.mornsun.app.manager.controller.product.base;

import com.mornsun.app.core.service.product.base.IProductBaseService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mornsun.jsw.api.vo.product.base.ProductBaseVo;

/**
 * 产品基础Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/product/base")
public class ProductBaseController extends BasePageHelperApiServiceController<ProductBaseVo,IProductBaseService> {

}
