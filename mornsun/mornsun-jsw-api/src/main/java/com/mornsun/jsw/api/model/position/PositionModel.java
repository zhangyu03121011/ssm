package com.mornsun.jsw.api.model.position;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 头衔Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class PositionModel extends BaseModel  {

    /**
     * 头衔名称
     */
    @Validate
    private String positionName;

    private static final long serialVersionUID = 1L;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

}
