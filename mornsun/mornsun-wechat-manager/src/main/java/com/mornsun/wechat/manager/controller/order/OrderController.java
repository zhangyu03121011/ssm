package com.mornsun.wechat.manager.controller.order;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.base.vo.base.ResultVo;
import com.common.controller.base.BaseController;
import com.mornsun.wechat.api.vo.order.OrderVo;
import com.mornsun.wechat.core.service.order.IOrderService;

/**
 * 订单Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年4月2日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

	@Autowired
	private IOrderService orderService;

	/**
	 * 保存订单
	 * 
	 * @param vo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save")
	public ResultVo<OrderVo> save(OrderVo vo, HttpServletRequest request) {
		ResultVo<OrderVo> resultVo = new ResultVo<>();
		try {
			resultVo = orderService.save(vo, request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			logger.error(e.getMessage(), e);
		}
		return resultVo;
	}
}
