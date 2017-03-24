package com.admin.manager.controller.role;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.api.vo.role.SysRoleVo;
import com.admin.core.service.role.ISysRoleService;
import com.common.orm.mybatis.controller.BasePageHelperServiceController;

/**
 * 角色Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/role")
public class SysRoleController extends BasePageHelperServiceController<SysRoleVo, ISysRoleService>{

}
