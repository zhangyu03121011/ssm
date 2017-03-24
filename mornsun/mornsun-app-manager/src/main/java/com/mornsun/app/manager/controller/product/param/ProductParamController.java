package com.mornsun.app.manager.controller.product.param;

import com.mornsun.app.core.service.product.param.IProductParamService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mornsun.jsw.api.vo.product.param.ProductParamVo;

/**
 * 产品参数Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/product/param")
public class ProductParamController extends BasePageHelperApiServiceController<ProductParamVo,IProductParamService> {

}
