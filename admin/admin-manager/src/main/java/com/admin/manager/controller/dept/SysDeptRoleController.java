package com.admin.manager.controller.dept;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.api.vo.dept.SysDeptRoleVo;
import com.admin.core.service.dept.ISysDeptRoleService;
import com.common.orm.mybatis.controller.BasePageHelperServiceController;

/**
 * 部门角色Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/dept/role")
public class SysDeptRoleController extends BasePageHelperServiceController<SysDeptRoleVo, ISysDeptRoleService> {

}
