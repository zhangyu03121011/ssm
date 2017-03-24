package com.mornsun.jsw.api.model.user.answer;

import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 用户回答Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserAnswerModel extends BaseModel {

	/**
	 * 问题ID
	 */
	@Validate
	private String questionId;

	/**
	 * 父级ID
	 */
	private String parentId;

	/**
	 * 用户ID
	 */
	@Validate
	private String userId;

	/**
	 * 支付金额
	 */
	@Validate
	private double payMoney;

	/**
	 * 回答类别：1-文字，2-声音，3-视频，4-图片
	 */
	@Validate
	private String answerType;

	/**
	 * 阅读人数
	 */
	private Integer readCount;

	/**
	 * 点赞数
	 */
	private int praiseCount;
	
	private static final long serialVersionUID = 1L;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId == null ? null : questionId.trim();
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId == null ? null : parentId.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getAnswerType() {
		return answerType;
	}

	public void setAnswerType(String answerType) {
		this.answerType = answerType == null ? null : answerType.trim();
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(double payMoney) {
		this.payMoney = payMoney;
	}
	
	public int getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}

}
