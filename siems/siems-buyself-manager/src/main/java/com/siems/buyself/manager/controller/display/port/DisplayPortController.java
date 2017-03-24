package com.siems.buyself.manager.controller.display.port;

import com.siems.buyself.core.service.display.port.IDisplayPortService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.siems.jsw.api.vo.display.port.DisplayPortVo;

/**
 * 陈列区域端口Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/display/port")
public class DisplayPortController extends BasePageHelperApiServiceController<DisplayPortVo,IDisplayPortService> {

}
