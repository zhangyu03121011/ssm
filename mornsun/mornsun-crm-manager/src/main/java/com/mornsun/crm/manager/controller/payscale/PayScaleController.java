package com.mornsun.crm.manager.controller.payscale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;
import com.mornsun.crm.core.service.payscale.IPayScaleService;
import com.mornsun.jsw.api.vo.payscale.PayScaleVo;

/**
 * 支付比例Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/payscale")
public class PayScaleController extends BasePageHelperApiServiceController<PayScaleVo, IPayScaleService> {

}
