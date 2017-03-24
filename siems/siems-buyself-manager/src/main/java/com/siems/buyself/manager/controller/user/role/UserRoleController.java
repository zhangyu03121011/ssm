package com.siems.buyself.manager.controller.user.role;

import com.siems.buyself.core.service.user.role.IUserRoleService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.siems.jsw.api.vo.user.role.UserRoleVo;

/**
 * 用户角色关系Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/role")
public class UserRoleController extends BasePageHelperApiServiceController<UserRoleVo,IUserRoleService> {

}
