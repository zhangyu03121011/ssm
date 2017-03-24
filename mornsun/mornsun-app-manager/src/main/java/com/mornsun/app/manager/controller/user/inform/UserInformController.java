package com.mornsun.app.manager.controller.user.inform;

import com.mornsun.app.core.service.user.inform.IUserInformService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mornsun.jsw.api.vo.user.inform.UserInformVo;

/**
 * 用户举报Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/inform")
public class UserInformController extends BasePageHelperApiServiceController<UserInformVo,IUserInformService> {

}
