package com.admin.api.model.user;

import com.common.base.annotation.Primary;
import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 用戶应用关系Model
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysUserAppModel extends BaseModel {

    private static final long serialVersionUID = 43522857632264691L;

    /**
     * 用戶应用ID
     */
    @Primary
    private String userAppId;

    /**
     * 用戶ID
     */
    @Validate
    private String userId;

    /**
     * 应用ID
     */
    @Validate
    private String appId;

    public String getUserAppId() {
        return userAppId;
    }

    public void setUserAppId(String userAppId) {
        this.userAppId = userAppId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

}