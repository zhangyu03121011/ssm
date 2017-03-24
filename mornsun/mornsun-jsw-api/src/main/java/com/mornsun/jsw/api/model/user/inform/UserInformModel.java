package com.mornsun.jsw.api.model.user.inform;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 用户举报Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserInformModel extends BaseModel  {

    /**
     * 用户ID
     */
    @Validate
    private String userId;

    /**
     * 源ID
     */
    @Validate
    private String sourceId;

    /**
     * 源类别
     */
    @Validate
    private String sourceType;

    /**
     * 举报类别
     */
    @Validate
    private String informType;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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

    public String getInformType() {
        return informType;
    }

    public void setInformType(String informType) {
        this.informType = informType == null ? null : informType.trim();
    }

}
