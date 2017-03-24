package com.mornsun.jsw.api.vo.user.footprint;

import java.util.Date;

import com.mornsun.jsw.api.model.user.footprint.UserFootprintModel;
import com.mornsun.jsw.api.vo.user.answer.UserAnswerVo;
import com.mornsun.jsw.api.vo.user.course.UserCourseVo;
import com.mornsun.jsw.api.vo.user.question.UserQuestionVo;

/**
 * 用户足迹Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserFootprintVo extends UserFootprintModel {

	private static final long serialVersionUID = 1L;

	private UserAnswerVo userAnswerVo;

	private UserCourseVo userCourseVo;

	private UserQuestionVo userQuestionVo;

	private Date answerTime;

	public UserAnswerVo getUserAnswerVo() {
		return userAnswerVo;
	}

	public void setUserAnswerVo(UserAnswerVo userAnswerVo) {
		this.userAnswerVo = userAnswerVo;
	}

	public UserCourseVo getUserCourseVo() {
		return userCourseVo;
	}

	public void setUserCourseVo(UserCourseVo userCourseVo) {
		this.userCourseVo = userCourseVo;
	}

	public UserQuestionVo getUserQuestionVo() {
		return userQuestionVo;
	}

	public void setUserQuestionVo(UserQuestionVo userQuestionVo) {
		this.userQuestionVo = userQuestionVo;
	}

	public Date getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}

}
