package com.mornsun.jsw.api.vo.user.pay;

import org.apache.commons.lang.StringUtils;

import com.common.base.model.base.BaseModel;
import com.mornsun.jsw.api.constant.PayTypeConstant;

/**
 * 用户交易Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserDealVo extends BaseModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 支付类别：1-秒懂，2-问答，3-公司秒懂，4-充值，5-充值奖励，6-注册奖励，7-邀请注册奖励，8-维护数据奖励，9-提交产品/品牌等数据
	 */
	private String playType;

	/**
	 * 支付类别名称
	 */
	private String playTypeName;

	/**
	 * 交易金额
	 */
	private int money;

	/**
	 * 交易时间
	 */
	private String playTime;

	public String getPlayType() {
		if (StringUtils.isEmpty(this.playTypeName)) {
			this.playTypeName = PayTypeConstant.getName(playType);
		}
		return playType;
	}

	public void setPlayType(String playType) {
		this.playType = playType;
	}

	public String getPlayTypeName() {
		return playTypeName;
	}

	public void setPlayTypeName(String playTypeName) {
		this.playTypeName = playTypeName;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getPlayTime() {
		return playTime;
	}

	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}

}
