package com.mornsun.app.manager.controller.user.invite;

import com.mornsun.app.core.service.user.invite.IUserInviteService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mornsun.jsw.api.vo.user.invite.UserInviteVo;

/**
 * 用户邀请Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/invite")
public class UserInviteController extends BasePageHelperApiServiceController<UserInviteVo,IUserInviteService> {

}
