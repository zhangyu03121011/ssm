package com.mornsun.jsw.api.vo.user.question;

import java.util.List;

import com.mornsun.jsw.api.model.user.question.UserQuestionModel;
import com.mornsun.jsw.api.vo.user.answer.UserAnswerVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;

/**
 * 用户问题Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserQuestionVo extends UserQuestionModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 查询类别（0-所有问题，1-我的问题，2-问我的，3-每日最新）
	 */
	private String queryType;

	private UserVo userVo;

	private UserVo answerUserVo;

	private boolean answerFlag = false;

	private boolean hotFlag = false;

	private List<UserAnswerVo> userAnswerVos;

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public UserVo getAnswerUserVo() {
		return answerUserVo;
	}

	public void setAnswerUserVo(UserVo answerUserVo) {
		this.answerUserVo = answerUserVo;
	}

	public boolean isAnswerFlag() {
		return answerFlag;
	}

	public void setAnswerFlag(boolean answerFlag) {
		this.answerFlag = answerFlag;
	}

	public List<UserAnswerVo> getUserAnswerVos() {
		return userAnswerVos;
	}

	public void setUserAnswerVos(List<UserAnswerVo> userAnswerVos) {
		this.userAnswerVos = userAnswerVos;
	}

	public boolean isHotFlag() {
		return hotFlag;
	}

	public void setHotFlag(boolean hotFlag) {
		this.hotFlag = hotFlag;
	}
}
