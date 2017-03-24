package com.mornsun.jsw.api.vo.user.profit;

import com.mornsun.jsw.api.model.user.profit.UserProfitModel;
import com.mornsun.jsw.api.vo.user.base.UserVo;

/**
 * 用户收益Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserProfitVo extends UserProfitModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 总余额
	 */
	private double totalMoney;

	private UserVo userVo;

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
}
