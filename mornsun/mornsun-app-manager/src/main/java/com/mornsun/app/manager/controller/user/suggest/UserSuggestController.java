package com.mornsun.app.manager.controller.user.suggest;

import com.mornsun.app.core.service.user.suggest.IUserSuggestService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mornsun.jsw.api.vo.user.suggest.UserSuggestVo;

/**
 * 用户意见Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/suggest")
public class UserSuggestController extends BasePageHelperApiServiceController<UserSuggestVo,IUserSuggestService> {

}
