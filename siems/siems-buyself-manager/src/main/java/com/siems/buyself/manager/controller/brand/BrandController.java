package com.siems.buyself.manager.controller.brand;

import com.siems.buyself.core.service.brand.IBrandService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.siems.jsw.api.vo.brand.BrandVo;

/**
 * 品牌Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/brand")
public class BrandController extends BasePageHelperApiServiceController<BrandVo,IBrandService> {

}
