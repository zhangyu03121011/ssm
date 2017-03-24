package com.mornsun.jsw.api.vo.user.base;

import java.util.List;

import com.mornsun.jsw.api.model.user.base.UserModel;
import com.mornsun.jsw.api.vo.position.PositionVo;
import com.mornsun.jsw.api.vo.user.catalog.UserCatalogVo;
import com.mornsun.jsw.api.vo.user.employee.UserEmployeeVo;

/**
 * 用户Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserVo extends UserModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 分类ID
	 */
	private String catalogId;

	private String openId;

	/**
	 * 验证码
	 */
	private String code;

	/**
	 * 新密码
	 */
	private String newPassWord;

	private PositionVo positionVo;

	/**
	 * 总余额
	 */
	private double totalMoney;

	/**
	 * 昨天收益
	 */
	private double newProfitMoney;

	private UserEmployeeVo userEmployeeVo;

	private String isEmployee;

	private List<UserCatalogVo> userCatalogVos;
	
	/**
	 * 是否已经关注
	 */
	private String isAttention;

	public String getIsAttention() {
		return isAttention;
	}

	public void setIsAttention(String isAttention) {
		this.isAttention = isAttention;
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNewPassWord() {
		return newPassWord;
	}

	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}

	public PositionVo getPositionVo() {
		return positionVo;
	}

	public void setPositionVo(PositionVo positionVo) {
		this.positionVo = positionVo;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public double getNewProfitMoney() {
		return newProfitMoney;
	}

	public void setNewProfitMoney(double newProfitMoney) {
		this.newProfitMoney = newProfitMoney;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public UserEmployeeVo getUserEmployeeVo() {
		return userEmployeeVo;
	}

	public void setUserEmployeeVo(UserEmployeeVo userEmployeeVo) {
		this.userEmployeeVo = userEmployeeVo;
	}

	public String getIsEmployee() {
		return isEmployee;
	}

	public void setIsEmployee(String isEmployee) {
		this.isEmployee = isEmployee;
	}

	public List<UserCatalogVo> getUserCatalogVos() {
		return userCatalogVos;
	}

	public void setUserCatalogVos(List<UserCatalogVo> userCatalogVos) {
		this.userCatalogVos = userCatalogVos;
	}

}
