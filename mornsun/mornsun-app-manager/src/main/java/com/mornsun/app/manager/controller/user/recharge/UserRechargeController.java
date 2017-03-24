package com.mornsun.app.manager.controller.user.recharge;

import com.mornsun.app.core.service.user.recharge.IUserRechargeService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mornsun.jsw.api.vo.user.recharge.UserRechargeVo;

/**
 * 用户充值Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/recharge")
public class UserRechargeController extends BasePageHelperApiServiceController<UserRechargeVo,IUserRechargeService> {

}
