package com.mornsun.wechat.api.constant;

/**
 * 微信常量
 * 
 * @author: HuiJunLuo
 * @date:2016年1月8日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface WechatConstant {

	// 用户来源：0-公众号，1-微信分享
	public static final String USER_SOURCE_REG = "0";

	public static final String USER_SOURCE_SHARE = "1";

	// 邀请来源：0-公众号，1-微信分享
	public static final String USER_INVITE_SOURCE_REG = "0";

	public static final String USER_INVITE_SOURCE_SHARE = "1";

	// 微信商户ID
	public static final String WECHAT_BUSINESS_PAY_ACCOUNT_NO = "1440113002";

	// 微信支付商家key
	public static final String WECHAT_BUSINESS_PAY_KEY = "Da6nga3Maeng8onee8iom6jahFae0ieD";

	// 微信支付备注
	public static final String WECHAT_BUSINESS_PAY_ATTACH = "梦想电子科技有限公司微信APP支付";
	/**
	 * 支付类别（1-金钱，2-芯币）
	 */
	public static final String WECHAT_PAYTYPE_MONEY = "1";

	public static final String WECHAT_PAYTYPE_VIRTUAL = "2";

	/**
	 * 来源类别（1-秒懂，2-问答）
	 */
	public static final String WECHAT_SOURCETYPE_COURSE = "1";

	public static final String WECHAT_SOURCETYPE_ANSWER = "2";

	// 分成用户：1-平台，2-支付方，3-提问方，4-分享方，5-回答方
	public static final String WECHAT_SCALE_TYPE_FRAME = "1";
	
	public static final String WECHAT_SCALE_TYPE_PAY = "2";
	
	public static final String WECHAT_SCALE_TYPE_QUESTION = "3";
	
	public static final String WECHAT_SCALE_TYPE_SHARE = "4";
	
	public static final String WECHAT_SCALE_TYPE_ANSWER = "5";

	/**
	 * 交易类别：1-收入，2-支出
	 */
	public static final String WECHAT_PAYTYPE_IN = "1";

	public static final String WECHAT_PAYTYPE_OUT = "2";

	/**
	 * 金额类别：1-现金，2-芯币，3-优惠卷
	 */
	public static final String WECHAT_MONEYTYPE_MONEY = "1";

	public static final String WECHAT_MONEYTYPE_VIRTUAL = "2";

	public static final String WECHAT_MONEYTYPE_COUPON = "3";
}
