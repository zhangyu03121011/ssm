package com.mornsun.jsw.api.model.user.pay;

import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 用户交易Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserPayModel extends BaseModel  {

    /**
     * 订单编码
     */
    @Validate
    private String orderNo;

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
     * 源类别：1-秒懂，2-问答
     */
    @Validate
    private String sourceType;

    /**
     * 金额类别：2-芯币，3-优惠卷
     */
    @Validate
    private String moneyType;

    /**
     * 交易金额
     */
    @Validate
    private double payMoney;

    /**
     * 交易类别：1-收入，2-支出
     */
    @Validate
    private String payType;

    private static final long serialVersionUID = 1L;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

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

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType == null ? null : moneyType.trim();
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

}
