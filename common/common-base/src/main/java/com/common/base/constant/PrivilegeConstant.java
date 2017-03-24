package com.common.base.constant;

/**
 * 权限有关的常量
 * 
 * @author: HuiJunLuo
 * @date:2015年12月28日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface PrivilegeConstant {

	/**
	 * session在缓存中的存活时间（7天）
	 */
	public static final int TIMEOUT_SESSION = 7 * 24 * 60 * 60;

	/**
	 * 验证码在缓存中的存活时间（30分钟）
	 */
	public static final int TIMEOUT_CODE = 10 * 60;

	/**
	 * Cookie存活期（永久）
	 */
	public static final int TIMEOUT_COOKIE = -1;

	/**
	 * 用户未登录的key值
	 */
	public static final String USER_NO_LOGIN_KEY = "no_login";

	/**
	 * 登录用户的session中的key值
	 */
	public static final String SESSION_LOGIN_USER = "common_login_user";

	/**
	 * 登录用户的session中的id值
	 */
	public static final String SESSION_LOGIN_USER_SESSION_ID = "common_login_user_session_id";

	/**
	 * PBE加密中的key值
	 */
	public static final String PBE_PASSWORD_KEY = "common_pbe_pwd_key_2016";

}
