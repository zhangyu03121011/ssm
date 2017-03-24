package com.siems.buyself.manager.controller.dept;

import com.siems.buyself.core.service.dept.IDeptService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.siems.jsw.api.vo.dept.DeptVo;

/**
 * 部门Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/dept")
public class DeptController extends BasePageHelperApiServiceController<DeptVo,IDeptService> {

}
