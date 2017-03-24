package com.mornsun.jsw.core.api.user.deal.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.api.page.impl.BasePageHelperProviderApiImpl;
import com.mornsun.jsw.api.api.user.deal.IUserDealApi;
import com.mornsun.jsw.api.vo.user.pay.UserDealVo;
import com.mornsun.jsw.core.service.user.deal.IUserDealService;

/**
 * 用户交易Api
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserDealApiImpl extends BasePageHelperProviderApiImpl<UserDealVo, IUserDealService> implements IUserDealApi {

}
