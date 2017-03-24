package com.mornsun.app.core.service.payscale.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;

import com.mornsun.jsw.api.api.payscale.IPayScaleApi;
import com.mornsun.app.core.service.payscale.IPayScaleService;
import com.mornsun.jsw.api.vo.payscale.PayScaleVo;

/**
 * 分成比例Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class PayScaleServiceImpl extends BasePageHelperApiServiceImpl<PayScaleVo, IPayScaleApi>
		implements IPayScaleService {

}
