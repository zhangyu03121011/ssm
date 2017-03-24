package com.admin.manager.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.api.vo.user.SysUserRoleVo;
import com.admin.core.service.user.ISysUserRoleService;
import com.common.orm.mybatis.controller.BasePageHelperServiceController;

/**
 * 用户角色Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/role")
public class SysUserRoleController extends BasePageHelperServiceController<SysUserRoleVo, ISysUserRoleService>{

}
