package com.mornsun.jsw.api.vo.user.expert;

import java.util.List;

import com.mornsun.jsw.api.model.user.expert.UserExpertModel;
import com.mornsun.jsw.api.vo.position.PositionVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;
import com.mornsun.jsw.api.vo.user.catalog.UserCatalogVo;

/**
 * 用户专家Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserExpertVo extends UserExpertModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 分类ID
	 */
	private String catalogId;

	/**
	 * 排序类别（1-知识财富榜，2-才华榜，3-新晋榜）
	 */
	private String sortType;

	/**
	 * 关键字
	 */
	private String keyword;

	/**
	 * 用户信息
	 */
	private UserVo userVo;

	/**
	 * 擅长领域
	 */
	private List<UserCatalogVo> userCatalogVos;

	private PositionVo positionVo;
	
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

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public List<UserCatalogVo> getUserCatalogVos() {
		return userCatalogVos;
	}

	public void setUserCatalogVos(List<UserCatalogVo> userCatalogVos) {
		this.userCatalogVos = userCatalogVos;
	}

	public PositionVo getPositionVo() {
		return positionVo;
	}

	public void setPositionVo(PositionVo positionVo) {
		this.positionVo = positionVo;
	}

}
