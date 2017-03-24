package com.mornsun.jsw.api.vo.user.order;

import com.mornsun.jsw.api.model.user.order.UserOrderModel;
import com.mornsun.jsw.api.vo.coupon.CouponVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;

/**
 * 用户订单Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserOrderVo extends UserOrderModel {

	private static final long serialVersionUID = 1L;

	private UserVo userVo;

	private CouponVo couponVo;

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public CouponVo getCouponVo() {
		return couponVo;
	}

	public void setCouponVo(CouponVo couponVo) {
		this.couponVo = couponVo;
	}

}
