package com.mornsun.app.core.service.user.recharge.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;

import com.mornsun.jsw.api.api.user.recharge.IUserRechargeApi;
import com.mornsun.app.core.service.user.recharge.IUserRechargeService;
import com.mornsun.jsw.api.vo.user.recharge.UserRechargeVo;

/**
 * 用户充值Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserRechargeServiceImpl extends BasePageHelperApiServiceImpl<UserRechargeVo,IUserRechargeApi>  implements IUserRechargeService {

}
