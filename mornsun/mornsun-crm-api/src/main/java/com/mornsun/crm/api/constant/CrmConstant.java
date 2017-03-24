package com.mornsun.crm.api.constant;

/**
 * CRM常量类
 * 
 * @author: HuiJunLuo
 * @date:2016年2月3日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface CrmConstant {

	// 待审核
	public static final String STATE_WAIT = "0";

	// 已审核
	public static final String STATE_PASS = "1";

	// 审核失败
	public static final String STATUS_FAIL = "2";
	
	/**
	 * 代金劵类别：0-注册赠送，1-邀请赠送，2-系统赠送，3-活动赠送，4-消费赠送，5-提交产品/品牌等数据，6-维护数据奖励，7-充值奖励，8-其他'
	 */
	public static final String COUPON_TYPE_REG = "0";
	
	/**
	 * 1-邀请赠送
	 */
	public static final String COUPON_TYPE_INVITE = "1";
	/**
	 * 系统赠送
	 */
	public static final String COUPON_TYPE_SYSTEM = "2";
	/**
	 * 活动赠送
	 */
	public static final String COUPON_TYPE_ACTIVITY = "3";
	/**
	 * 消费赠送
	 */
	public static final String COUPON_TYPE_PAY = "4";
	/**
	 * 提交产品/品牌等数据
	 */
	public static final String COUPON_TYPE_UPLOAD_DATA = "5";
	/**
	 * 维护数据奖励
	 * 
	 */
	public static final String COUPON_TYPE_PROTECT_DATA_ = "6";
	/**
	 * 充值奖励
	 */
	public static final String COUPON_TYPE_RECHARGE = "7";
	/**
	 * 其他
	 */
	public static final String COUPON_TYPE_OTHER = "8";
	

	/**
	 * 文件上传类别
	 */
	public static final String CRM_FILE_IMPORT_TYPE_CATALOG = "catalog";

	public static final String CRM_FILE_IMPORT_TYPE_PRODUCT = "product";

	public static final String CRM_FILE_IMPORT_TYPE_COMPANY_COURSE = "company_course";
	
	public static final String CRM_FILE_IMPORT_TYPE_COMPANY_PARAM = "param";

	/**
	 * 产品类别（1-基本信息，2-替换关系，3-附件）
	 */
	public static final String CRM_FILE_IMPORT_TYPE_PRODUCT_BASE = "1";

	public static final String CRM_FILE_IMPORT_TYPE_PRODUCT_REPLACE = "2";

	public static final String CRM_FILE_IMPORT_TYPE_PRODUCT_ATTA = "3";
}
