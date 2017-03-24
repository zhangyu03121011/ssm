package com.mornsun.crm.manager.controller.user.profit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;
import com.mornsun.crm.core.service.user.profit.IUserProfitService;
import com.mornsun.jsw.api.vo.user.profit.UserProfitVo;

/**
 * 用户收益Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/profit")
public class UserProfitController extends BasePageHelperApiServiceController<UserProfitVo, IUserProfitService> {

}
