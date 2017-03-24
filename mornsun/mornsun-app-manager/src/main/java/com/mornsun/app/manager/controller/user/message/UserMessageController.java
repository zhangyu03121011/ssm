package com.mornsun.app.manager.controller.user.message;

import com.mornsun.app.core.service.user.message.IUserMessageService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mornsun.jsw.api.vo.user.message.UserMessageVo;

/**
 * 用户消息Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/message")
public class UserMessageController extends BasePageHelperApiServiceController<UserMessageVo,IUserMessageService> {

}
