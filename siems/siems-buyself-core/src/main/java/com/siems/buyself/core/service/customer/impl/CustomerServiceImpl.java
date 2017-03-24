package com.siems.buyself.core.service.customer.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;

import com.siems.jsw.api.api.customer.ICustomerApi;
import com.siems.buyself.core.service.customer.ICustomerService;
import com.siems.jsw.api.vo.customer.CustomerVo;

/**
 * 客户Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class CustomerServiceImpl extends BasePageHelperApiServiceImpl<CustomerVo,ICustomerApi>  implements ICustomerService {  

}
