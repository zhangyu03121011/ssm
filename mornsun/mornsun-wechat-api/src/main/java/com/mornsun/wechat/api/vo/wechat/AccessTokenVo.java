package com.mornsun.wechat.api.vo.wechat;

import com.common.base.model.base.BaseModel;

/**
 * 微信AccessToken授权VO
 * 
 * @author: HuiJunLuo
 * @date:2016年2月27日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class AccessTokenVo extends BaseModel {

    private static final long serialVersionUID = -6327597046537063639L;

    // 获取到的凭证
    private String token;

    // 凭证有效时间，单位：秒
    private int expiresIn;

    // 错误消息
    private String errmsg;

    // 错误代码
    private int errcode;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

}
