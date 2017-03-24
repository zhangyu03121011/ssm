package com.mornsun.jsw.api.model.user.praise;

import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 用户点赞Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserPraiseModel extends BaseModel {

	/**
	 * 用户ID
	 */
	@Validate
	private String userId;

	/**
	 * 源用户ID
	 */
	private String sourceUserId;

	/**
	 * 源ID
	 */
	@Validate
	private String sourceId;

	/**
	 * 源类别（1-秒懂，2-问答）
	 */
	@Validate
	private String sourceType;

	/**
	 * 点赞数
	 */
	private int praiseCount;

	private static final long serialVersionUID = 1L;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getSourceUserId() {
		return sourceUserId;
	}

	public void setSourceUserId(String sourceUserId) {
		this.sourceUserId = sourceUserId == null ? null : sourceUserId.trim();
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId == null ? null : sourceId.trim();
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType == null ? null : sourceType.trim();
	}

	public int getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}

}
