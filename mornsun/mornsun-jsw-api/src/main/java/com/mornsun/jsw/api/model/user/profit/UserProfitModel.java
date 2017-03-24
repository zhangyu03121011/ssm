package com.mornsun.jsw.api.model.user.profit;

import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 用户收益Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserProfitModel extends BaseModel {

	/**
	 * 订单编码
	 */
	@Validate
	private String orderNo;

	/**
	 * 用户ID
	 */
	@Validate
	private String userId;

	/**
	 * 源ID
	 */
	@Validate
	private String sourceId;

	/**
	 * 源类别：1-充值，2-充值奖励，3-支付秒懂，4-支付问答，5-提交产品/品牌等数据，6-注册奖励，7-邀请注册奖励，8-维护数据奖励
	 */
	@Validate
	private String sourceType;

	/**
	 * 金额类别：1-现金
	 */
	@Validate
	private String moneyType;

	/**
	 * 交易金额
	 */
	@Validate
	private double payMoney;

	/**
	 * 交易类别：1-收入，2-支出
	 */
	@Validate
	private String payType;

	/**
	 * 收益金额
	 */
	@Validate
	private double profitMoney;

	/**
	 * 分成比例
	 */
	@Validate
	private double shareScale;

	@Validate
	private String scaleType;

	private static final long serialVersionUID = 1L;

	public String getScaleType() {
		return scaleType;
	}

	public void setScaleType(String scaleType) {
		this.scaleType = scaleType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == null ? null : orderNo.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId == null ? null : sourceId.trim();
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType == null ? null : sourceType.trim();
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType == null ? null : moneyType.trim();
	}

	public double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(double payMoney) {
		this.payMoney = payMoney;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType == null ? null : payType.trim();
	}

	public double getProfitMoney() {
		return profitMoney;
	}

	public void setProfitMoney(double profitMoney) {
		this.profitMoney = profitMoney;
	}

	public double getShareScale() {
		return shareScale;
	}

	public void setShareScale(double shareScale) {
		this.shareScale = shareScale;
	}

}
