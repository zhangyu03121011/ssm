package com.siems.buyself.manager.controller.role.function;

import com.siems.buyself.core.service.role.function.IRoleFunctionService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.siems.jsw.api.vo.role.function.RoleFunctionVo;

/**
 * 角色功能关系Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/role/function")
public class RoleFunctionController extends BasePageHelperApiServiceController<RoleFunctionVo,IRoleFunctionService> {

}
