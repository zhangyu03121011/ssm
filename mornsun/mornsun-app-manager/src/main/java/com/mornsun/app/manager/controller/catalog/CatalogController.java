package com.mornsun.app.manager.controller.catalog;

import com.mornsun.app.core.service.catalog.ICatalogService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mornsun.jsw.api.vo.catalog.CatalogVo;

/**
 * 分类Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/catalog")
public class CatalogController extends BasePageHelperApiServiceController<CatalogVo,ICatalogService> {

}
