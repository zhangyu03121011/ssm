package com.mornsun.crm.manager.controller.user.pay;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;
import com.mornsun.crm.core.service.user.pay.IUserPayService;
import com.mornsun.jsw.api.vo.user.pay.UserPayVo;

/**
 * 用户交易Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/pay")
public class UserPayController extends BasePageHelperApiServiceController<UserPayVo, IUserPayService> {

}
