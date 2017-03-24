package com.mornsun.app.manager.controller.user.expert;

import com.mornsun.app.core.service.user.expert.IUserExpertService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mornsun.jsw.api.vo.user.expert.UserExpertVo;

/**
 * 用户专家Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/expert")
public class UserExpertController extends BasePageHelperApiServiceController<UserExpertVo,IUserExpertService> {

}
