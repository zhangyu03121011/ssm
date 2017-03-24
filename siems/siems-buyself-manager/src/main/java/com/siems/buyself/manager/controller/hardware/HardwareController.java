package com.siems.buyself.manager.controller.hardware;

import com.siems.buyself.core.service.hardware.IHardwareService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.siems.jsw.api.vo.hardware.HardwareVo;

/**
 * 硬件Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/hardware")
public class HardwareController extends BasePageHelperApiServiceController<HardwareVo,IHardwareService> {

}
