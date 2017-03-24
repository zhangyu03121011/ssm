package com.mornsun.jsw.api.vo.user.answer;

import java.util.List;

import com.mornsun.jsw.api.model.user.answer.UserAnswerModel;
import com.mornsun.jsw.api.vo.atta.AttaVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;

/**
 * 用户回答Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserAnswerVo extends UserAnswerModel {

	private static final long serialVersionUID = 1L;

	private List<AttaVo> attaVos;

	private String title;

	private UserVo userVo;

	/**
	 * 0-未支付，1-已支付
	 */
	private String payState;

	private String isPraise;

	/**
	 * 当前登录用户
	 */
	private String currUserId;

	public String getIsPraise() {
		return isPraise;
	}

	public void setIsPraise(String isPraise) {
		this.isPraise = isPraise;
	}

	public List<AttaVo> getAttaVos() {
		return attaVos;
	}

	public void setAttaVos(List<AttaVo> attaVos) {
		this.attaVos = attaVos;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public String getCurrUserId() {
		return currUserId;
	}

	public void setCurrUserId(String currUserId) {
		this.currUserId = currUserId;
	}

}
