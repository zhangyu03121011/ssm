package com.mornsun.wechat.core.service.order;

import javax.servlet.http.HttpServletRequest;

import com.common.base.vo.base.ResultVo;
import com.mornsun.wechat.api.vo.order.OrderVo;

/**
 * 订单Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月14日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IOrderService {

	/**
	 * 保存数据
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<OrderVo> save(OrderVo obj, HttpServletRequest request) throws Exception;

}
