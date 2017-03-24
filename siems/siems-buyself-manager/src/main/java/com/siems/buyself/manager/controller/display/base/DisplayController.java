package com.siems.buyself.manager.controller.display.base;

import com.siems.buyself.core.service.display.base.IDisplayService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.siems.jsw.api.vo.display.base.DisplayVo;

/**
 * 陈列区域Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/display")
public class DisplayController extends BasePageHelperApiServiceController<DisplayVo,IDisplayService> {

}
