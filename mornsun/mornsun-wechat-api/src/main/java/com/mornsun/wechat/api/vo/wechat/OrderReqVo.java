package com.mornsun.wechat.api.vo.wechat;

import com.common.base.model.base.BaseModel;

/**
 * 订单VO
 * 
 * @author: HuiJunLuo
 * @date:2016年5月17日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class OrderReqVo extends BaseModel {

	private static final long serialVersionUID = 4154743719677918216L;

	/**
	 * 支付类型（1-APP，2-公众号）
	 */
	private String payType;
	
	/**
	 * 订单编号
	 */
	private String orderNo;

	/**
	 * 商品描述
	 */
	private String productDesc;

	/**
	 * 附加数据,原样返回
	 */
	private String attach;

	/**
	 * 来源类别
	 */
	private String appKey;

	/**
	 * 总金额
	 */
	private int totalAmount;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
}
