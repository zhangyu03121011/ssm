package com.mornsun.app.manager.controller.applyarea;

import com.mornsun.app.core.service.applyarea.IApplyAreaService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mornsun.jsw.api.vo.applyarea.ApplyAreaVo;

/**
 * 应用领域Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/applyarea")
public class ApplyAreaController extends BasePageHelperApiServiceController<ApplyAreaVo,IApplyAreaService> {

}
