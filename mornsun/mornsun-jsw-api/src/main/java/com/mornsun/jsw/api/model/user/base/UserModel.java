package com.mornsun.jsw.api.model.user.base;

import com.common.base.model.base.BaseModel;

/**
 * 用户Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserModel extends BaseModel {

	/**
	 * 用户账号
	 */
	private String userName;

	/**
	 * 昵称
	 */
	private String userAlias;

	/**
	 * 密码
	 */
	private String passWord;

	/**
	 * 头像
	 */
	private String headImage;

	/**
	 * 手机
	 */
	private String mobile;

	/**
	 * 头衔ID
	 */
	private String positionId;

	/**
	 * 用户总金额
	 */
	private double userMoney;

	/**
	 * 用户总代金劵
	 */
	private double userCoupon;

	/**
	 * 用户类别
	 */
	private String userType;

	/**
	 * 简介
	 */
	private String intro;

	/**
	 * 随机码
	 */
	private String randomCode;

	/**
	 * 授权ID
	 */
	private String authId;

	/**
	 * 授权类别
	 */
	private String authType;

	/**
	 * 来源类别：1-ios，2-android，3-web，4-wechat
	 */
	private String sourceType;

	/**
	 * 是否推送消息：0-不推送，1-推送
	 */
	private String isPushMessage;

	private static final long serialVersionUID = 1L;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getUserAlias() {
		return userAlias;
	}

	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias == null ? null : userAlias.trim();
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord == null ? null : passWord.trim();
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage == null ? null : headImage.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId == null ? null : positionId.trim();
	}

	public double getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(double userMoney) {
		this.userMoney = userMoney;
	}

	public double getUserCoupon() {
		return userCoupon;
	}

	public void setUserCoupon(double userCoupon) {
		this.userCoupon = userCoupon;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType == null ? null : userType.trim();
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro == null ? null : intro.trim();
	}

	public String getRandomCode() {
		return randomCode;
	}

	public void setRandomCode(String randomCode) {
		this.randomCode = randomCode == null ? null : randomCode.trim();
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId == null ? null : authId.trim();
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType == null ? null : authType.trim();
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType == null ? null : sourceType.trim();
	}

	public String getIsPushMessage() {
		return isPushMessage;
	}

	public void setIsPushMessage(String isPushMessage) {
		this.isPushMessage = isPushMessage == null ? null : isPushMessage.trim();
	}

}
