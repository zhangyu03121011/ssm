package com.siems.buyself.manager.controller.shop;

import com.siems.buyself.core.service.shop.IShopService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.siems.jsw.api.vo.shop.ShopVo;

/**
 * 门店Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/shop")
public class ShopController extends BasePageHelperApiServiceController<ShopVo,IShopService> {

}
