package com.mornsun.crm.manager.controller.user.extract;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;
import com.mornsun.crm.core.service.user.extract.IUserExtractService;
import com.mornsun.jsw.api.vo.user.extract.UserExtractVo;

/**
 * 用户提现Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/extract")
public class UserExtractController extends BasePageHelperApiServiceController<UserExtractVo, IUserExtractService> {

}
