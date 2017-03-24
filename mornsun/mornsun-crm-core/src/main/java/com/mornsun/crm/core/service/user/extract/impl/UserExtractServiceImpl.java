package com.mornsun.crm.core.service.user.extract.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.mornsun.crm.core.service.user.extract.IUserExtractService;
import com.mornsun.jsw.api.api.user.extract.IUserExtractApi;
import com.mornsun.jsw.api.vo.user.extract.UserExtractVo;

/**
 * 用户提现Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserExtractServiceImpl extends BasePageHelperApiServiceImpl<UserExtractVo, IUserExtractApi>
		implements IUserExtractService {

}
