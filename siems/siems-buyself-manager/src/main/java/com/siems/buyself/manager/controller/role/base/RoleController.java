package com.siems.buyself.manager.controller.role.base;

import com.siems.buyself.core.service.role.base.IRoleService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.siems.jsw.api.vo.role.base.RoleVo;

/**
 * 角色Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BasePageHelperApiServiceController<RoleVo,IRoleService> {

}
