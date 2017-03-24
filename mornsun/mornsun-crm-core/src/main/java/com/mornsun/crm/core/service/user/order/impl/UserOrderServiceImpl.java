package com.mornsun.crm.core.service.user.order.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.mornsun.crm.core.service.user.order.IUserOrderService;
import com.mornsun.jsw.api.api.user.order.IUserOrderApi;
import com.mornsun.jsw.api.vo.user.order.UserOrderVo;

/**
 * 用户订单Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserOrderServiceImpl extends BasePageHelperApiServiceImpl<UserOrderVo, IUserOrderApi>
		implements IUserOrderService {

}
