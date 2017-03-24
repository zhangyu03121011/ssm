package com.mornsun.jsw.api.model.payscale;

import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 分成比例Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class PayScaleModel extends BaseModel  {

    /**
     * 分成类别：1-秒懂，2-问答
     */
    @Validate
    private String payType;

    /**
     * 分成用户：1-平台，2-发布方，3-提问方，4-分享方，5-回答方
     */
    @Validate
    private String scaleType;

    /**
     * 分成比例
     */
    @Validate
    private double payScale;

    private static final long serialVersionUID = 1L;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getScaleType() {
        return scaleType;
    }

    public void setScaleType(String scaleType) {
        this.scaleType = scaleType == null ? null : scaleType.trim();
    }

    public double getPayScale() {
        return payScale;
    }

    public void setPayScale(double payScale) {
        this.payScale = payScale;
    }

}
