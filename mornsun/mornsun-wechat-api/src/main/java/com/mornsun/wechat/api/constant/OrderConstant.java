package com.mornsun.wechat.api.constant;

/**
 * 订单常量
 * 
 * @author: HuiJunLuo
 * @date:2016年1月8日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface OrderConstant {

    // 订单状态：0-待支付，1-已支付，2-支付失败
    public static final String ORDER_STATE_WAIT = "0";

    public static final String ORDER_STATE_PAY = "1";
    
    public static final String ORDER_STATE_FAIL = "2";

    // 订单支付通知状态：0-待确认，1-已确认，2-已放弃
    public static final String ORDER_Notify_STATE_WAIT = "0";

    public static final String ORDER_NOTIFY_STATE_SURE = "1";

    public static final String ORDER_NOTIFY_STATE_ABORT = "2";

}
