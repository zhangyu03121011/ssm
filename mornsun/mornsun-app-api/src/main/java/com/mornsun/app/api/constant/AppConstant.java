package com.mornsun.app.api.constant;

/**
 * App常量
 * 
 * @author: HuiJunLuo
 * @date:2015年12月28日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface AppConstant {

	// 待审核
	public static final String STATE_WAIT = "0";

	// 已审核
	public static final String STATE_PASS = "1";

	// 审核失败
	public static final String STATUS_FAIL = "2";

	// 代金劵类别：0-注册赠送，1-邀请赠送，2-系统赠送，3-活动赠送，4-消费赠送，5-其它
	public static final String COUPON_TYPE_REG = "0";
	public static final String COUPON_TYPE_INVITE = "1";
	public static final String COUPON_TYPE_SYSTEM = "2";
	public static final String COUPON_TYPE_ACTIVITY = "3";
	public static final String COUPON_TYPE_PAY = "4";
	public static final String COUPON_TYPE_OTHER = "5";

	// 分享类别（1-邀请好友，2-问答，3-秒懂，4-产品）
	public static final String SHARE_TYPE_INVITE = "1";
	public static final String SHARE_TYPE_ANSWER = "2";
	public static final String SHARE_TYPE_COURSE = "3";
	public static final String SHARE_TYPE_PRODUCT = "4";

	/**
	 * 领域类别：1-感兴趣，2-专长
	 */
	// 1-感兴趣
	public static final String AREA_TYPE_INTEREST = "1";

	// 2-专长
	public static final String AREA_TYPE_SPECIALTY = "2";

	// 错误类别（1-分类错误，2-描述错误，3-应用领域错误，4-参数错误，5-其他错误）
	public static final String ERROR_TYPE_OTHER = "5";

	/**
	 * 源类别（1-秒懂，2-问答，3-企业秒懂）
	 */
	public static final String SOURCETYPE_COURSE = "1";

	public static final String SOURCETYPE_ANSWER = "2";

	public static final String SOURCETYPE_COMPANY = "3";

	/**
	 * 错误来源类别（1-审批，2-详情）
	 */
	// 1-审批
	public static final String ERROR_TYPE_APPROVE = "1";

	// 2-详情
	public static final String ERROR_TYPE_DETAIL = "2";

	/**
	 * 支付类别（1-已支付，0-未支付）
	 */
	// 1-已支付
	public static final String PAY_TYPE_STATE_YES = "1";

	// 0-未支付
	public static final String PAY_TYPE_STATE_NO = "0";

	/**
	 * 问题查询类别（0-所有问题，1-我的问题，2-问我的，3-每日最新）
	 */
	// 0-所有问题
	public static final String QUESTION_TYPE_ALL = "0";

	// 1-我的问题
	public static final String QUESTION_TYPE_MY_REQUEST = "1";

	// 2-问我的）
	public static final String QUESTION_TYPE_MY_RESPONSE = "2";

	// 3-每日最新
	public static final String QUESTION_TYPE_DAY_NEW = "3";

	/**
	 * 秒懂查询类别（0-所有秒懂，1-我的秒懂）
	 */
	// 0-所有秒懂
	public static final String COURSE_TYPE_ALL = "0";

	// 1-我的秒懂
	public static final String COURSE_TYPE_MY_REQUEST = "1";
}
