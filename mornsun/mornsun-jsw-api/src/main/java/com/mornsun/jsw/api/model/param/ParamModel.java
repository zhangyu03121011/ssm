package com.mornsun.jsw.api.model.param;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 参数Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ParamModel extends BaseModel  {

    /**
     * 分类ID
     */
    @Validate
    private String catalogId;

    /**
     * 参数名称
     */
    @Validate
    private String paramName;

    private static final long serialVersionUID = 1L;

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId == null ? null : catalogId.trim();
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }

}
