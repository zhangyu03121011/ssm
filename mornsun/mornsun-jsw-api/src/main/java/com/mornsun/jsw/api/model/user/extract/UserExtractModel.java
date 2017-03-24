package com.mornsun.jsw.api.model.user.extract;

import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 用户提现Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserExtractModel extends BaseModel {

	/**
	 * 用户ID
	 */
	@Validate
	private String userId;

	/**
	 * 交易金额
	 */
	@Validate
	private double payMoney;

	/**
	 * 提现状态：1-提现成功，2-提现失败
	 */
	private String payState;

	private static final long serialVersionUID = 1L;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(double payMoney) {
		this.payMoney = payMoney;
	}

	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState == null ? null : payState.trim();
	}

}
