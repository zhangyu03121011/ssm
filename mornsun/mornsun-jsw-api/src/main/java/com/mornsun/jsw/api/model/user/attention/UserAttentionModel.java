package com.mornsun.jsw.api.model.user.attention;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 用户关注Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserAttentionModel extends BaseModel  {

    /**
     * 用户ID
     */
    @Validate
    private String userId;

    /**
     * 关注用户ID
     */
    @Validate
    private String attentionUserId;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAttentionUserId() {
        return attentionUserId;
    }

    public void setAttentionUserId(String attentionUserId) {
        this.attentionUserId = attentionUserId == null ? null : attentionUserId.trim();
    }

}
