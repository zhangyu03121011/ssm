package com.mornsun.wechat.api.vo.wechat;

import com.common.base.model.base.BaseModel;

/**
 * 微信jsapi接口VO
 * 
 * @author: HuiJunLuo
 * @date:2016年2月27日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class JsapiTicketVo extends BaseModel {

    private static final long serialVersionUID = 6219033184022816093L;

    // 获取到的凭证
    private String ticket;

    // 凭证有效时间，单位：秒
    private int expiresIn;

    // 错误消息
    private String errmsg;

    // 错误代码
    private int errcode;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
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
