package com.mornsun.wechat.api.vo.wechat;

import com.common.base.model.base.BaseModel;

/**
 * 微信签名VO
 * 
 * @author: HuiJunLuo
 * @date:2016年4月2日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SignVo extends BaseModel {

    private static final long serialVersionUID = -1981434900228541630L;

    /**
     * appId
     */
    private String appId;

    /**
     * url
     */
    private String url;

    /**
     * ticket
     */
    private String jsapiTicket;

    /**
     * 随机串
     */
    private String nonceStr;

    /**
     * 时间
     */
    private String timestamp;

    /**
     * 签名算法
     */
    private String signature;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJsapiTicket() {
        return jsapiTicket;
    }

    public void setJsapiTicket(String jsapiTicket) {
        this.jsapiTicket = jsapiTicket;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

}
