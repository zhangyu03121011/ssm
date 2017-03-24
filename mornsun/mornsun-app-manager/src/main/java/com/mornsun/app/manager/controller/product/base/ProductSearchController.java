package com.mornsun.app.manager.controller.product.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;
import com.mornsun.app.core.service.product.base.IProductSearchService;
import com.mornsun.jsw.api.vo.product.base.SearchVo;

/**
 * 产品Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/search")
public class ProductSearchController extends BasePageHelperApiServiceController<SearchVo, IProductSearchService> {

}
