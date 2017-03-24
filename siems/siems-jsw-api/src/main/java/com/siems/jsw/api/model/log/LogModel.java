package com.siems.jsw.api.model.log;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 日志Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class LogModel extends BaseModel  {

    /**
     * 用户ID
     */
    @Validate
    private String userId;

    /**
     * 用户名称
     */
    @Validate
    private String userName;

    /**
     * 操作类别
     */
    @Validate
    private String operationType;

    /**
     * 操作描述
     */
    @Validate
    private String operationDescr;

    /**
     * IP地址
     */
    @Validate
    private String ip;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType == null ? null : operationType.trim();
    }

    public String getOperationDescr() {
        return operationDescr;
    }

    public void setOperationDescr(String operationDescr) {
        this.operationDescr = operationDescr == null ? null : operationDescr.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

}
