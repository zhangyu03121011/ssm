package com.mornsun.jsw.api.model.user.suggest;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 用户意见Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserSuggestModel extends BaseModel  {

    /**
     * 用户ID
     */
    @Validate
    private String userId;

    /**
     * 联系方式
     */
    @Validate
    private String contact;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

}
