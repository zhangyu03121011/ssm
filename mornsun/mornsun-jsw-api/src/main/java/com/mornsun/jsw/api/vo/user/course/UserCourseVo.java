package com.mornsun.jsw.api.vo.user.course;

import java.util.List;

import com.mornsun.jsw.api.model.user.course.UserCourseModel;
import com.mornsun.jsw.api.vo.atta.AttaVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;

/**
 * 用户秒懂Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserCourseVo extends UserCourseModel {

	private static final long serialVersionUID = 1L;

	private List<AttaVo> attaVos;

	/**
	 * 查询类别（0-所有秒懂，1-我的秒懂）
	 */
	private String queryType;

	private UserVo userVo;

	/**
	 * 0-未支付，1-已支付
	 */
	private String payState;

	/**
	 * 是否已经关注
	 */
	private String isAttention;

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

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
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

	public String getIsAttention() {
		return isAttention;
	}

	public void setIsAttention(String isAttention) {
		this.isAttention = isAttention;
	}

	public String getCurrUserId() {
		return currUserId;
	}

	public void setCurrUserId(String currUserId) {
		this.currUserId = currUserId;
	}

}
