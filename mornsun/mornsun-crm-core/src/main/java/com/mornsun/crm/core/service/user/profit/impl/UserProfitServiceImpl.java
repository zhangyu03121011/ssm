package com.mornsun.crm.core.service.user.profit.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.mornsun.crm.core.service.user.profit.IUserProfitService;
import com.mornsun.jsw.api.api.user.profit.IUserProfitApi;
import com.mornsun.jsw.api.vo.user.profit.UserProfitVo;

/**
 * 用户收益Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserProfitServiceImpl extends BasePageHelperApiServiceImpl<UserProfitVo, IUserProfitApi>
		implements IUserProfitService {

}
