package com.mornsun.crm.core.service.pay.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.mornsun.crm.core.service.pay.IPayService;
import com.mornsun.jsw.api.api.user.pay.IUserPayApi;
import com.mornsun.jsw.api.vo.user.pay.UserPayVo;

/**
 * 支付充值Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class PayServiceImpl extends BasePageHelperApiServiceImpl<UserPayVo, IUserPayApi> implements IPayService {

}
