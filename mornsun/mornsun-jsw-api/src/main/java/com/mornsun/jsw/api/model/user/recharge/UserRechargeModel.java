package com.mornsun.jsw.api.model.user.recharge;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 用户充值Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserRechargeModel extends BaseModel  {

    /**
     * 用户ID
     */
    @Validate
    private String userId;

    /**
     * 业务系统ID
     */
    @Validate
    private String bussId;

    /**
     * 充值金额
     */
    @Validate
    private Double rechargeMoney;

    /**
     * 业务系统类别：0-微信
     */
    @Validate
    private String bussType;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getBussId() {
        return bussId;
    }

    public void setBussId(String bussId) {
        this.bussId = bussId == null ? null : bussId.trim();
    }

    public Double getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Double rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public String getBussType() {
        return bussType;
    }

    public void setBussType(String bussType) {
        this.bussType = bussType == null ? null : bussType.trim();
    }

}
