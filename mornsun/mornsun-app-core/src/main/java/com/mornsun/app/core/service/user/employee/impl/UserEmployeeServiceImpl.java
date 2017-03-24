package com.mornsun.app.core.service.user.employee.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;

import com.mornsun.jsw.api.api.user.employee.IUserEmployeeApi;
import com.mornsun.app.core.service.user.employee.IUserEmployeeService;
import com.mornsun.jsw.api.vo.user.employee.UserEmployeeVo;

/**
 * 用户内部员工Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserEmployeeServiceImpl extends BasePageHelperApiServiceImpl<UserEmployeeVo, IUserEmployeeApi>
		implements IUserEmployeeService {

}
