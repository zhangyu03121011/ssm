package com.mornsun.jsw.api.model.user.order;

import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 用户订单Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserOrderModel extends BaseModel {

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
	 * 源类别：1-收益，2-交易
	 */
	@Validate
	private String sourceType;

	/**
	 * 优惠券ID
	 */
	@Validate
	private String couponId;

	/**
	 * 总金额
	 */
	@Validate
	private double totalMoney;

	/**
	 * 优惠金额
	 */
	@Validate
	private double couponMoney;

	/**
	 * 交易金额
	 */
	@Validate
	private double payMoney;
	
    /**
     * 分成金额
     */
    @Validate
    private double shareMoney;

    /**
     * 分成比例
     */
    @Validate
    private double shareScale;

	public double getShareMoney() {
		return shareMoney;
	}

	public void setShareMoney(double shareMoney) {
		this.shareMoney = shareMoney;
	}

	public double getShareScale() {
		return shareScale;
	}

	public void setShareScale(double shareScale) {
		this.shareScale = shareScale;
	}

	private static final long serialVersionUID = 1L;

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

}
