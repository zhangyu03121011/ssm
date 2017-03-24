package com.mornsun.jsw.api.model.coupon;

import java.util.Date;

import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 代金劵Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class CouponModel extends BaseModel  {

    /**
     * 代金劵名称
     */
    @Validate
    private String couponName;

    /**
     * 代金劵类别：0-注册赠送，1-邀请赠送，2-系统赠送，3-活动赠送，4-消费赠送，5-提交产品/品牌等数据，6-维护数据奖励，7-充值奖励，8-分享赠送，9-其他
     */
    @Validate
    private String couponType;

    /**
     * 限制使用金额
     */
    @Validate
    private double limitMoney;

    /**
     * 代金劵金额
     */
    @Validate
    private double couponMoney;

    /**
     * 有效开始时间
     */
    @Validate
    private Date beginTime;

    /**
     * 有效结束时间
     */
    @Validate
    private Date endTime;

    private static final long serialVersionUID = 1L;

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType == null ? null : couponType.trim();
    }

    public double getLimitMoney() {
        return limitMoney;
    }

    public void setLimitMoney(double limitMoney) {
        this.limitMoney = limitMoney;
    }

    public double getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(double couponMoney) {
        this.couponMoney = couponMoney;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
