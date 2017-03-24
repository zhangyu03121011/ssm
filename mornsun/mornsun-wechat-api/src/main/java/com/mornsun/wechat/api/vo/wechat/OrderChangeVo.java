package com.mornsun.wechat.api.vo.wechat;

import com.common.base.model.base.BaseModel;

/**
 * 订单变更VO
 * 
 * @author: HuiJunLuo
 * @date:2016年5月17日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class OrderChangeVo extends BaseModel {

    private static final long serialVersionUID = 4154743719677918216L;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 变更后订单编号
     */
    private String newOrderNo;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 总金额
     */
    private double totalAmount;

    /**
     * 支付金额
     */
    private double payAmount;

    /**
     * 优惠金额
     */
    private double couponAmount;

    /**
     * 活动名称
     */
    private String activityName;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getNewOrderNo() {
        return newOrderNo;
    }

    public void setNewOrderNo(String newOrderNo) {
        this.newOrderNo = newOrderNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public double getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(double couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
