package com.mornsun.jsw.api.model.user.error;

import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 用户纠错Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserErrorModel extends BaseModel {

    /**
     * 用户ID
     */
    @Validate
    private String userId;

    /**
     * 产品ID
     */
    @Validate
    private String productId;

    /**
     * 源类别：1-审批，2-详情
     */
    @Validate
    private String sourceType;

    /**
     * 错误类别（1-分类错误，2-描述错误，3-应用领域错误，4-参数错误，5-其他错误）
     */
    private String errorType;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType == null ? null : errorType.trim();
    }

}
