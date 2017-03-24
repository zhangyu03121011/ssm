package com.admin.manager.controller.log;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.api.vo.log.SysLoginLogVo;
import com.admin.core.service.log.ISysLoginLogService;
import com.common.orm.mybatis.controller.BasePageHelperServiceController;

/**
 * 系统登录日志Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/login/log")
public class SysLoginLogController extends BasePageHelperServiceController<SysLoginLogVo, ISysLoginLogService>{

}
