package com.mornsun.jsw.api.constant;

/**
 * 处理信息类别常量
 * 
 * @author: HuiJunLuo
 * @date:2016年1月9日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public enum PayTypeConstant {

	COURSE("1", "支付秒懂"), ANSWER("2", "支付问答"), COMPANY_COURSE("3", "支付企业秒懂"), PAY("4", "充值"), RECHARGE("5", "充值奖励"), REG(
			"6", "注册奖励"), INVITE_REG("7", "邀请注册奖励"), DATA_UPDATE("8", "维护数据奖励"), PRODUCT_BRAND("9", "提交产品/品牌数据");

	/**
	 * 类别
	 */
	private String type;

	/**
	 * 名称
	 */
	private String name;

	// 构造方法
	private PayTypeConstant(String type, String name) {
		this.type = type;
		this.name = name;
	}

	// 普通方法
	public static String getName(String type) {
		for (PayTypeConstant fileTypeConstant : PayTypeConstant.values()) {
			if (fileTypeConstant.getType().equals(type)) {
				return fileTypeConstant.getName();
			}
		}
		return null;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
