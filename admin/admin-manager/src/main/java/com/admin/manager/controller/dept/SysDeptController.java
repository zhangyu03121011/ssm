package com.admin.manager.controller.dept;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.api.vo.dept.SysDeptVo;
import com.admin.core.service.dept.ISysDeptService;
import com.common.orm.mybatis.controller.BasePageHelperServiceController;

/**
 * 部门Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/dept")
public class SysDeptController extends BasePageHelperServiceController<SysDeptVo, ISysDeptService> {

}
