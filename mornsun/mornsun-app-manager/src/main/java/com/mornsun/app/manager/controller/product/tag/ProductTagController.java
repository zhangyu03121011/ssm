package com.mornsun.app.manager.controller.product.tag;

import com.mornsun.app.core.service.product.tag.IProductTagService;
import com.mornsun.jsw.api.vo.product.tag.ProductTagVo;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 产品标签Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/product/tag")
public class ProductTagController extends BasePageHelperApiServiceController<ProductTagVo,IProductTagService> {

}
