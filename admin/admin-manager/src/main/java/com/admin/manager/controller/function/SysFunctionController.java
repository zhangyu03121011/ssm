package com.admin.manager.controller.function;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.api.vo.function.SysFunctionVo;
import com.admin.core.service.function.ISysFunctionService;
import com.common.orm.mybatis.controller.BasePageHelperServiceController;

/**
 * 系统功能Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/function")
public class SysFunctionController extends BasePageHelperServiceController<SysFunctionVo, ISysFunctionService> {

}
