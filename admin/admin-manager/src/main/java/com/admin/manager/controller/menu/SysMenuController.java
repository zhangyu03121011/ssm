package com.admin.manager.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.api.vo.menu.SysMenuVo;
import com.admin.core.service.menu.ISysMenuService;
import com.common.orm.mybatis.controller.BasePageHelperServiceController;

/**
 * 菜单Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/menu")
public class SysMenuController extends BasePageHelperServiceController<SysMenuVo, ISysMenuService>{

}
