package com.mornsun.app.manager.controller.param;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;
import com.mornsun.app.core.service.param.IParamService;
import com.mornsun.jsw.api.vo.param.ParamVo;

/**
 * 参数Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/param")
public class ParamController extends BasePageHelperApiServiceController<ParamVo, IParamService> {

}
