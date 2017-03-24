package com.mornsun.wechat.api.model.order;

import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 订单Model
 * 
 * @author: HuiJunLuo
 * @date:2016年5月9日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class OrderModel extends BaseModel {

	private static final long serialVersionUID = 8636782374632608909L;

	/**
	 * 来源类别
	 */
	@Validate
	private String appKey;

	/**
	 * 订单编码
	 */
	@Validate
	private String orderNo;

	/**
	 * 订单描述
	 */
	@Validate
	private String orderDesc;

	/**
	 * 用户ID
	 */
	@Validate
	private String userId;

	/**
	 * 目标用户ID
	 */
	@Validate
	private String targetUserId;

	/**
	 * 源ID
	 */
	@Validate
	private String sourceId;

	/**
	 * 源类别
	 */
	@Validate
	private String sourceType;

	/**
	 * 优惠券ID
	 */
	private String couponId;

	/**
	 * 总金额
	 */
	@Validate
	private double totalMoney;

	/**
	 * 优惠金额
	 */
	private double couponMoney;

	/**
	 * 交易金额
	 */
	@Validate
	private double payMoney;

	/**
	 * 支付类别（1-金钱，2-芯币）
	 */
	@Validate
	private String payType;

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
		this.sourceId = sourceId;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId == null ? null : couponId.trim();
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public double getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(double couponMoney) {
		this.couponMoney = couponMoney;
	}

	public double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(double payMoney) {
		this.payMoney = payMoney;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

}