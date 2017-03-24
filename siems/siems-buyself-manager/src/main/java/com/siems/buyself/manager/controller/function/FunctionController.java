package com.siems.buyself.manager.controller.function;

import com.siems.buyself.core.service.function.IFunctionService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.siems.jsw.api.vo.function.FunctionVo;

/**
 * 功能Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/function")
public class FunctionController extends BasePageHelperApiServiceController<FunctionVo,IFunctionService> {

}
