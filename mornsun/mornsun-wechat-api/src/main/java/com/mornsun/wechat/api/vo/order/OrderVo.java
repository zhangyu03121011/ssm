package com.mornsun.wechat.api.vo.order;

import com.mornsun.jsw.api.vo.user.base.UserVo;
import com.mornsun.wechat.api.model.order.OrderModel;
import com.mornsun.wechat.api.vo.wechat.PaySignVo;

/**
 * 订单VO
 * 
 * @author: HuiJunLuo
 * @date:2016年5月9日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class OrderVo extends OrderModel {

	private static final long serialVersionUID = 8636782374632608909L;

	/**
	 * 用户信息
	 */
	private UserVo userVo;

	/**
	 * 微信签名VO
	 */
	private PaySignVo paySignVo;

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public PaySignVo getPaySignVo() {
		return paySignVo;
	}

	public void setPaySignVo(PaySignVo paySignVo) {
		this.paySignVo = paySignVo;
	}

}