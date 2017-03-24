package com.mornsun.app.manager.controller.product.replace;

import com.mornsun.app.core.service.product.replace.IProductReplaceService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mornsun.jsw.api.vo.product.replace.ProductReplaceVo;

/**
 * 产品替换Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/product/replace")
public class ProductReplaceController extends BasePageHelperApiServiceController<ProductReplaceVo,IProductReplaceService> {

}
