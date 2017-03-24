package com.siems.buyself.manager.controller.customer;

import com.siems.buyself.core.service.customer.ICustomerService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.siems.jsw.api.vo.customer.CustomerVo;

/**
 * 客户Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BasePageHelperApiServiceController<CustomerVo,ICustomerService> {

}
