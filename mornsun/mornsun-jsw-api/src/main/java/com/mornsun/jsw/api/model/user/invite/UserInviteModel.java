package com.mornsun.jsw.api.model.user.invite;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 用户邀请Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserInviteModel extends BaseModel  {

    /**
     * 用户ID
     */
    @Validate
    private String userId;

    /**
     * 分享ID
     */
    @Validate
    private String shareId;

    /**
     * 邀请来源：1-朋友圈，2-微信，3-QQ，4-微博
     */
    @Validate
    private String inviteSource;

    /**
     * 好友ID
     */
    @Validate
    private String friendUserId;

    /**
     * 授权ID
     */
    @Validate
    private String authId;

    /**
     * 授权类别：1-微信，2-QQ，3-微博
     */
    @Validate
    private String authType;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId == null ? null : shareId.trim();
    }

    public String getInviteSource() {
        return inviteSource;
    }

    public void setInviteSource(String inviteSource) {
        this.inviteSource = inviteSource == null ? null : inviteSource.trim();
    }

    public String getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(String friendUserId) {
        this.friendUserId = friendUserId == null ? null : friendUserId.trim();
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

}
