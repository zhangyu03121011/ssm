package com.mornsun.jsw.api.model.user.share;

import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 用户分享Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserShareModel extends BaseModel {

	/**
	 * 源ID
	 */
	private String sourceId;

	/**
	 * 源类别（1-邀请好友，2-问答，3-秒懂，4-产品）
	 */
	@Validate
	private String sourceType;

	/**
	 * 用户ID
	 */
	@Validate
	private String userId;

	/**
	 * 分享类别：1-朋友圈，2-微信，3-QQ，4-微博
	 */
	@Validate
	private String shareType;

	/**
	 * 分享链接
	 */
	private String shareUrl;

	private static final long serialVersionUID = 1L;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getShareType() {
		return shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType == null ? null : shareType.trim();
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl == null ? null : shareUrl.trim();
	}

}
