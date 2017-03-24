package com.admin.manager.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.api.vo.user.SysUserAppVo;
import com.admin.core.service.user.ISysUserAppService;
import com.common.orm.mybatis.controller.BasePageHelperServiceController;

/**
 * 部门用户Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/app")
public class SysUserAppController extends BasePageHelperServiceController<SysUserAppVo, ISysUserAppService>{

}
