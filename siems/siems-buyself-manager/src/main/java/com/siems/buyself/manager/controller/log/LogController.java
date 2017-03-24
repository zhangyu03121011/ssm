package com.siems.buyself.manager.controller.log;

import com.siems.buyself.core.service.log.ILogService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.siems.jsw.api.vo.log.LogVo;

/**
 * 日志Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/log")
public class LogController extends BasePageHelperApiServiceController<LogVo,ILogService> {

}
