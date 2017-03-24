package com.siems.buyself.manager.controller.soft;

import com.siems.buyself.core.service.soft.ISoftService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.siems.jsw.api.vo.soft.SoftVo;

/**
 * 终端软件Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/soft")
public class SoftController extends BasePageHelperApiServiceController<SoftVo,ISoftService> {

}
