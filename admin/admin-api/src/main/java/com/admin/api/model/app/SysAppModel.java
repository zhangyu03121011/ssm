package com.admin.api.model.app;

import com.common.base.annotation.Primary;
import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 系統应用Model
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysAppModel extends BaseModel {

    private static final long serialVersionUID = -7765875848533116196L;

    /**
     * 应用ID
     */
    @Primary
    private String appId;

    /**
     * 应用KEY
     */
    @Validate
    private String appKey;

    /**
     * 应用名称
     */
    @Validate
    private String appName;

    /**
     * 应用URL
     */
    private String appUrl;

    /**
     * 应用首页
     */
    private String appIndex;
    
    /**
     * 应用图标
     */
    private String appIcon;

    /**
     * 排序
     */
    private Integer sort;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey == null ? null : appKey.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getAppIndex() {
        return appIndex;
    }

    public void setAppIndex(String appIndex) {
        this.appIndex = appIndex;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

}