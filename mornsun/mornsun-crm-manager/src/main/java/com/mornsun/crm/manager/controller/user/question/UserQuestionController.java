package com.mornsun.crm.manager.controller.user.question;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;
import com.mornsun.crm.core.service.user.question.IUserQuestionService;
import com.mornsun.jsw.api.vo.user.question.UserQuestionVo;

/**
 * 用户问题Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/question")
public class UserQuestionController extends BasePageHelperApiServiceController<UserQuestionVo, IUserQuestionService> {

}
