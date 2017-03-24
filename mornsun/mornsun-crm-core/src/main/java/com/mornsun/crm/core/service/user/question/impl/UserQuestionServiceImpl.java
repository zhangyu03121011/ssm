package com.mornsun.crm.core.service.user.question.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.mornsun.crm.core.service.user.question.IUserQuestionService;
import com.mornsun.jsw.api.api.user.question.IUserQuestionApi;
import com.mornsun.jsw.api.vo.user.question.UserQuestionVo;

/**
 * 用户问题Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserQuestionServiceImpl extends BasePageHelperApiServiceImpl<UserQuestionVo, IUserQuestionApi>
		implements IUserQuestionService {

}
