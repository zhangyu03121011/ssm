package com.mornsun.app.manager.controller.user.employee;

import com.mornsun.app.core.service.user.employee.IUserEmployeeService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mornsun.jsw.api.vo.user.employee.UserEmployeeVo;

/**
 * 用户内部员工Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/employee")
public class UserEmployeeController extends BasePageHelperApiServiceController<UserEmployeeVo, IUserEmployeeService> {

}
