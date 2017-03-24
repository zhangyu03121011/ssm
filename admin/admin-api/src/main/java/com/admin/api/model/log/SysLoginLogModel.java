package com.admin.api.model.log;

import com.common.base.annotation.Primary;
import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 系统登录日志Model
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysLoginLogModel extends BaseModel {
    
    private static final long serialVersionUID = 4663319325562675034L;

    /**
     * 登录日志ID
     */
    @Primary
    private String loginLogId;

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
     * 操作名称
     */
    private String operationName;

    /**
     * IP地址
     */
    @Validate
    private String ipAddress;

    /**
     * MAC地址
     */
    private String macAddress;

    /**
     * 类别
     */
    private String type;


    public String getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(String loginLogId) {
        this.loginLogId = loginLogId == null ? null : loginLogId.trim();
    }

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

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName == null ? null : operationName.trim();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress == null ? null : macAddress.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

}