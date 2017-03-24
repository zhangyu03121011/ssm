package com.admin.manager.controller.role;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.api.vo.role.SysRoleMenuFunctionVo;
import com.admin.core.service.role.ISysRoleMenuFunctionService;
import com.common.orm.mybatis.controller.BasePageHelperServiceController;

/**
 * 角色菜单功能Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/role/menu/function")
public class SysRoleMenuFunctionController
        extends BasePageHelperServiceController<SysRoleMenuFunctionVo, ISysRoleMenuFunctionService> {

}
