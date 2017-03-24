package com.mornsun.app.core.service.user.deal.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.mornsun.app.core.service.user.deal.IUserDealService;
import com.mornsun.jsw.api.api.user.deal.IUserDealApi;
import com.mornsun.jsw.api.vo.user.pay.UserDealVo;

/**
 * 用户交易Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserDealServiceImpl extends BasePageHelperApiServiceImpl<UserDealVo, IUserDealApi>
		implements IUserDealService {

}
