package com.mornsun.crm.core.service.user.answer.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.mornsun.crm.core.service.user.answer.IUserAnswerService;
import com.mornsun.jsw.api.api.user.answer.IUserAnswerApi;
import com.mornsun.jsw.api.vo.user.answer.UserAnswerVo;

/**
 * 用户回答Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserAnswerServiceImpl extends BasePageHelperApiServiceImpl<UserAnswerVo, IUserAnswerApi>
		implements IUserAnswerService {

}
