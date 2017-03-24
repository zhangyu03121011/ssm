package com.mornsun.wechat.api.vo.wechat;

import java.io.Serializable;
import java.util.Date;

/**
 * 支付授权VO
 * 
 * @author: HuiJunLuo
 * @date:2016年5月17日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class PaySignVo implements Serializable {

	private static final long serialVersionUID = -7453960274794794222L;

	/**
	 * 公众号id
	 */
	private String appId;

	/**
	 * 商户号
	 */
	private String partnerid;

	/**
	 * 预支付交易会话ID
	 */
	private String prepayid;

	/**
	 * 时间戳
	 */
	private String timeStamp;

	/**
	 * 随机字符串
	 */
	private String nonceStr;

	/**
	 * 订单详情扩展字符串
	 */
	private String packageStr;

	/**
	 * 签名方式
	 */
	private String signType;

	/**
	 * 签名
	 */
	private String paySign;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 订单编号
	 */
	private String orderNo;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackageStr() {
		return packageStr;
	}

	public void setPackageStr(String packageStr) {
		this.packageStr = packageStr;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPartnerid() {
		return partnerid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
