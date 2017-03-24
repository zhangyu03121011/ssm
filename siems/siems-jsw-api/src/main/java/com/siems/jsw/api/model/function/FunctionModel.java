package com.siems.jsw.api.model.function;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 功能Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class FunctionModel extends BaseModel  {
    /**
     * 功能ID
     */
    @Validate
    private String functionId;

    /**
     * 父级ID
     */
    @Validate
    private String parentId;

    /**
     * 功能名称
     */
    @Validate
    private String functionName;

    /**
     * 功能控件KEY
     */
    @Validate
    private String controlKey;

    private static final long serialVersionUID = 1L;

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId == null ? null : functionId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName == null ? null : functionName.trim();
    }

    public String getControlKey() {
        return controlKey;
    }

    public void setControlKey(String controlKey) {
        this.controlKey = controlKey == null ? null : controlKey.trim();
    }

}
